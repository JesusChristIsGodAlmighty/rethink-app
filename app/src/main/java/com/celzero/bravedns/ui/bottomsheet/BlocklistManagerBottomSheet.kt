/*
Copyright 2026 RethinkDNS and its authors

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package com.celzero.bravedns.ui.bottomsheet

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.celzero.bravedns.R
import com.celzero.bravedns.database.CustomBlocklistEntry
import com.celzero.bravedns.databinding.BottomSheetBlocklistManagerBinding
import com.celzero.bravedns.databinding.ItemBlocklistManagerBinding
import com.celzero.bravedns.service.PersistentState
import com.celzero.bravedns.util.Themes.Companion.getBottomSheetCurrentTheme
import com.celzero.bravedns.viewmodel.CustomBlocklistViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * F224 Blocklist Manager: one-screen curator for blocklists - add custom
 * lists (name + URL), remove them, reorder with up/down buttons and toggle
 * selection. Backed by CustomBlocklistViewModel -> CustomBlocklistEntryRepository,
 * which sanitizes all user input before it reaches the DB (Module Contract 4.2.7).
 */
class BlocklistManagerBottomSheet : BaseBottomSheetDialogFragment() {

    private var _binding: BottomSheetBlocklistManagerBinding? = null
    private val b
        get() = checkNotNull(_binding) { "Binding accessed outside of view lifecycle" }

    private val viewModel: CustomBlocklistViewModel by viewModel()
    private val persistentState by inject<PersistentState>()
    private val adapter: BlocklistAdapter by lazy {
        BlocklistAdapter(
            onToggle = { entry, checked -> viewModel.toggle(entry, checked) },
            onMove = { from, to -> viewModel.move(from, to) },
            onRemove = { entry -> confirmRemove(entry) }
        )
    }

    override fun getTheme(): Int =
        getBottomSheetCurrentTheme(isDarkThemeOn(), persistentState.theme)

    private fun isDarkThemeOn(): Boolean {
        return resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK ==
            Configuration.UI_MODE_NIGHT_YES
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetBlocklistManagerBinding.inflate(inflater, container, false)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        b.blmbsRecycler.layoutManager = LinearLayoutManager(context)
        b.blmbsRecycler.adapter = adapter

        b.blmbsAdd.setOnClickListener { showAddDialog() }

        viewModel.entries.observe(viewLifecycleOwner, Observer { updateUi(it) })
        viewModel.lastError.observe(viewLifecycleOwner, Observer { err ->
            if (err != null) {
                Toast.makeText(requireContext(), R.string.f224_bm_invalid_input, Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    private fun updateUi(entries: List<CustomBlocklistEntry>) {
        if (_binding == null) return
        b.blmbsEmpty.visibility = if (entries.isEmpty()) View.VISIBLE else View.GONE
        adapter.submit(entries)
    }

    private fun showAddDialog() {
        val view = layoutInflater.inflate(R.layout.dialog_blocklist_manager_add, null)
        val nameLayout = view.findViewById<TextInputLayout>(R.id.dbma_name_layout)
        val urlLayout = view.findViewById<TextInputLayout>(R.id.dbma_url_layout)
        val nameInput = view.findViewById<TextInputEditText>(R.id.dbma_name)
        val urlInput = view.findViewById<TextInputEditText>(R.id.dbma_url)

        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.f224_bm_add_dialog_title)
            .setView(view)
            .setPositiveButton(R.string.f224_bm_add_dialog_positive) { _, _ ->
                val title = nameInput.text?.toString().orEmpty()
                val url = urlInput.text?.toString().orEmpty()
                if (title.isBlank()) {
                    nameLayout.error = getString(R.string.f224_bm_invalid_input)
                    return@setPositiveButton
                }
                nameLayout.error = null
                // repository re-validates + sanitizes; lastError surfaces failures
                viewModel.addUserList(title, url)
                Toast.makeText(requireContext(), R.string.f224_bm_added, Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton(R.string.f224_bm_add_dialog_negative) { d, _ -> d.dismiss() }
            .show()
    }

    private fun confirmRemove(entry: CustomBlocklistEntry) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.f224_bm_remove_confirm_title)
            .setMessage(getString(R.string.f224_bm_remove_confirm_message, entry.title))
            .setPositiveButton(R.string.f224_bm_remove) { _, _ ->
                viewModel.remove(entry)
                Toast.makeText(requireContext(), R.string.f224_bm_removed, Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton(R.string.f224_bm_add_dialog_negative) { d, _ -> d.dismiss() }
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

        // F224: nested (NOT inner) adapter — inner classes are prohibited as members of
    // classes that extend framework Activity/Fragment types under Kotlin 2.x ("'Class'
    // is prohibited here", CI run 41791839017). Row actions are delivered via callbacks.
    class BlocklistAdapter(
        private val onToggle: (CustomBlocklistEntry, Boolean) -> Unit,
        private val onMove: (Int, Int) -> Unit,
        private val onRemove: (CustomBlocklistEntry) -> Unit,
    ) : RecyclerView.Adapter<BlocklistAdapter.VH>() {
        private var items: List<CustomBlocklistEntry> = emptyList()

        fun submit(newItems: List<CustomBlocklistEntry>) {
            items = newItems
            notifyDataSetChanged()
        }

        class VH(val ib: ItemBlocklistManagerBinding) : RecyclerView.ViewHolder(ib.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
            val ib = ItemBlocklistManagerBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            return VH(ib)
        }

        override fun getItemCount(): Int = items.size

        override fun onBindViewHolder(holder: VH, position: Int) {
            val entry = items[position]
            val ctx = holder.ib.root.context
            holder.ib.iblmTitle.text = entry.title
            holder.ib.iblmSubtitle.text = if (entry.sourceUrl.isNotBlank()) {
                entry.sourceUrl
            } else if (entry.kind == CustomBlocklistEntry.KIND_REFRESHABLE) {
                ctx.getString(R.string.f224_bm_kind_refreshable)
            } else {
                ctx.getString(R.string.f224_bm_kind_user_added)
            }

            // programmatic updates must not fire the listener
            holder.ib.iblmSwitch.setOnCheckedChangeListener(null)
            holder.ib.iblmSwitch.isChecked = entry.isSelected
            holder.ib.iblmSwitch.setOnCheckedChangeListener { _, checked ->
                val pos = holder.bindingAdapterPosition
                if (pos == RecyclerView.NO_POSITION) return@setOnCheckedChangeListener
                onToggle(items[pos], checked)
            }

            holder.ib.iblmMoveUp.isEnabled = position > 0
            holder.ib.iblmMoveUp.alpha = if (position > 0) 1f else 0.3f
            holder.ib.iblmMoveUp.setOnClickListener {
                val pos = holder.bindingAdapterPosition
                if (pos > 0) onMove(pos, pos - 1)
            }

            holder.ib.iblmMoveDown.isEnabled = position < itemCount - 1
            holder.ib.iblmMoveDown.alpha = if (position < itemCount - 1) 1f else 0.3f
            holder.ib.iblmMoveDown.setOnClickListener {
                val pos = holder.bindingAdapterPosition
                if (pos in 0 until itemCount - 1) onMove(pos, pos + 1)
            }

            // refreshable entries mirror upstream tags; only custom lists are removable
            val removable = entry.kind == CustomBlocklistEntry.KIND_USER_ADDED
            holder.ib.iblmRemove.visibility = if (removable) View.VISIBLE else View.INVISIBLE
            holder.ib.iblmRemove.setOnClickListener {
                val pos = holder.bindingAdapterPosition
                if (pos != RecyclerView.NO_POSITION) onRemove(items[pos])
            }
        }
    }
}

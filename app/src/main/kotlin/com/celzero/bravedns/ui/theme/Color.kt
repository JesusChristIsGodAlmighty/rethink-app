package com.celzero.bravedns.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

val ObsidianBg = Color(0xFF0B101B)
val ObsidianSurface = Color(0xFF111827)
val ObsidianSurfaceHigh = Color(0xFF1A2438)
val ObsidianSurfaceHighest = Color(0xFF23304A)
val ObsidianBorder = Color(0xFF2A3956)

val TealPrimary = Color(0xFF2EE6D6)
val TealOnPrimary = Color(0xFF003733)
val TealContainer = Color(0xFF0A3D3B)
val TealOnContainer = Color(0xFF8FF7EC)

val IndigoSecondary = Color(0xFF7B9CFF)
val IndigoOnSecondary = Color(0xFF0D205A)
val IndigoContainer = Color(0xFF213163)
val IndigoOnContainer = Color(0xFFDCE3FF)

val AllowedEmerald = Color(0xFF34D399)
val BlockedCoral = Color(0xFFFB7185)
val PausedAmber = Color(0xFFFBBF24)

val DaylightBg = Color(0xFFF4F7FB)
val DaylightSurface = Color(0xFFFFFFFF)
val DaylightSurfaceHigh = Color(0xFFEAF0F8)
val DaylightBorder = Color(0xFFD2DCEB)
val DaylightTeal = Color(0xFF00796B)
val DaylightIndigo = Color(0xFF3F51B5)

@Immutable
data class SemanticStatusColors(
    val allowed: Color,
    val allowedContainer: Color,
    val blocked: Color,
    val blockedContainer: Color,
    val paused: Color,
    val pausedContainer: Color,
    val surfaceBorder: Color,
    val heroDarkSurface: Color
)

val DarkSemanticColors = SemanticStatusColors(
    allowed = AllowedEmerald,
    allowedContainer = Color(0xFF0D3A2D),
    blocked = BlockedCoral,
    blockedContainer = Color(0xFF441825),
    paused = PausedAmber,
    pausedContainer = Color(0xFF42300A),
    surfaceBorder = ObsidianBorder,
    heroDarkSurface = Color(0xFF0D1526)
)

val LightSemanticColors = SemanticStatusColors(
    allowed = Color(0xFF059669),
    allowedContainer = Color(0xFFD1FAE5),
    blocked = Color(0xFFE11D48),
    blockedContainer = Color(0xFFFFE4E6),
    paused = Color(0xFFD97706),
    pausedContainer = Color(0xFFFEF3C7),
    surfaceBorder = DaylightBorder,
    heroDarkSurface = Color(0xFF0D1526)
)

val RethinkDarkColorScheme = darkColorScheme(
    primary = TealPrimary,
    onPrimary = TealOnPrimary,
    primaryContainer = TealContainer,
    onPrimaryContainer = TealOnContainer,
    secondary = IndigoSecondary,
    onSecondary = IndigoOnSecondary,
    secondaryContainer = IndigoContainer,
    onSecondaryContainer = IndigoOnContainer,
    tertiary = AllowedEmerald,
    onTertiary = Color(0xFF003824),
    background = ObsidianBg,
    onBackground = Color(0xFFEBF1FA),
    surface = ObsidianSurface,
    onSurface = Color(0xFFEBF1FA),
    surfaceVariant = ObsidianSurfaceHigh,
    onSurfaceVariant = Color(0xFF9FB0CA),
    surfaceContainerLowest = ObsidianBg,
    surfaceContainerLow = ObsidianSurface,
    surfaceContainer = ObsidianSurfaceHigh,
    surfaceContainerHigh = ObsidianSurfaceHighest,
    outline = ObsidianBorder,
    outlineVariant = Color(0xFF1F2B42),
    error = BlockedCoral,
    onError = Color(0xFF490414),
    errorContainer = Color(0xFF441825),
    onErrorContainer = Color(0xFFFFD9DF)
)

val RethinkLightColorScheme = lightColorScheme(
    primary = DaylightTeal,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFB2DFDB),
    onPrimaryContainer = Color(0xFF00251F),
    secondary = DaylightIndigo,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFE0E7FF),
    onSecondaryContainer = Color(0xFF111D5E),
    tertiary = Color(0xFF059669),
    onTertiary = Color.White,
    background = DaylightBg,
    onBackground = Color(0xFF0F172A),
    surface = DaylightSurface,
    onSurface = Color(0xFF0F172A),
    surfaceVariant = DaylightSurfaceHigh,
    onSurfaceVariant = Color(0xFF475569),
    surfaceContainerLowest = DaylightSurface,
    surfaceContainerLow = DaylightBg,
    surfaceContainer = DaylightSurfaceHigh,
    surfaceContainerHigh = Color(0xFFE0E8F5),
    outline = DaylightBorder,
    outlineVariant = Color(0xFFE2E8F0),
    error = Color(0xFFE11D48),
    onError = Color.White,
    errorContainer = Color(0xFFFFE4E6),
    onErrorContainer = Color(0xFF4C0519)
)

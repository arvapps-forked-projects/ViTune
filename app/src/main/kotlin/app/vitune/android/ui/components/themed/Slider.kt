package app.vitune.android.ui.components.themed

import androidx.annotation.IntRange
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.vitune.core.ui.LocalAppearance
import app.vitune.core.ui.surface

@Composable
fun Slider(
    state: Float,
    setState: (Float) -> Unit,
    onSlideComplete: () -> Unit,
    range: ClosedFloatingPointRange<Float>,
    modifier: Modifier = Modifier,
    @IntRange(from = 0) steps: Int = 0
) {
    val (colorPalette) = LocalAppearance.current

    androidx.compose.material3.Slider(
        value = state,
        onValueChange = setState,
        onValueChangeFinished = onSlideComplete,
        valueRange = range,
        modifier = modifier,
        steps = steps,
        colors = SliderDefaults.colors(
            thumbColor = colorPalette.onAccent,
            activeTrackColor = colorPalette.accent,
            inactiveTrackColor = colorPalette.surface.copy(alpha = 0.75f),
            activeTickColor = colorPalette.surface,
            inactiveTickColor = colorPalette.accent
        )
    )
}

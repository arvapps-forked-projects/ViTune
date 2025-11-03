package app.vitune.compose.reordering

import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.ui.Modifier

context(scope: LazyItemScope)
fun Modifier.animateItemPlacement(reorderingState: ReorderingState) =
    if (reorderingState.draggingIndex == -1) with(scope) {
        animateItem(
            fadeInSpec = null,
            fadeOutSpec = null
        )
    } else this

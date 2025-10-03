package com.dawn.common.presentation.component

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseBottomSheet(
    modalSheetState: SheetState,
    isShowBottomSheet: Boolean,
    onDismiss: () -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) {
//    val isLoadAdSuccess = LocalLoadAdBannerIsSuccess.current.value

//    val bottomSheetCornerRadius = dimensionResource(id = R.dimen.cornerRadius_allBottomSheet)
//    val dragHandleModifier =
//        if (title.isNotBlank() || hasCloseButton) Modifier
//            .background(Color.White)
//            .padding(dimensionResource(id = R.dimen.paddingAll_allBottomSheet_dragHandle))
//        else Modifier

    if (isShowBottomSheet) {
        ModalBottomSheet(
            modifier = Modifier
                .wrapContentHeight(),
            sheetState = modalSheetState,
            onDismissRequest = onDismiss,
            containerColor = Color.Transparent,
//            shape = RoundedCornerShape(
//                topStart = bottomSheetCornerRadius,
//                topEnd = bottomSheetCornerRadius,
//            ),
            contentWindowInsets = {
                WindowInsets.navigationBars.union(WindowInsets.ime)
            },
            scrimColor = Color.Transparent,
            dragHandle = {
                // Drag Handle here
            },
        ) {
            content()

//            Spacer(
//                modifier = Modifier
//                    .padding(bottom = if (isLoadAdSuccess) dimensionResource(R.dimen.height_ads_banner) else 0.dp),
//            )
        }
    }
}
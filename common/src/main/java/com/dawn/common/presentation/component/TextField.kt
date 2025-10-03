package com.dawn.common.presentation.component

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import com.dawn.common.presentation.theme.CustomTypography

@Composable
fun BaseTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    isError: Boolean = false,
    hint: String = "",
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    trailingIcon: @Composable (() -> Unit)? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
//        shape = RoundedCornerShape(dimensionResource(id = R.dimen.cornerRadius_all_textField)),
//        colors = OutlinedTextFieldDefaults.colors(
//            focusedContainerColor = Color.Transparent,
//            unfocusedContainerColor = colorResource(id = R.color.backgroundColor_all_textField_unfocused),
//            errorContainerColor = Color.Transparent,
//
//            focusedBorderColor = colorResource(id = R.color.borderColor_all_textField_focused),
//            unfocusedBorderColor = Color.Transparent,
//            errorBorderColor = colorResource(R.color.borderColor_all_textField_error),
//
//            unfocusedTextColor = colorResource(R.color.textColor_all_textField_content),
//            focusedTextColor = colorResource(id = R.color.textColor_all_textField_content),
//            errorTextColor = colorResource(R.color.textColor_all_textField_content),
//
//            focusedPlaceholderColor = colorResource(R.color.textColor_all_textField_hint),
//            unfocusedPlaceholderColor = colorResource(R.color.textColor_all_textField_hint),
//            errorPlaceholderColor = colorResource(R.color.textColor_all_textField_hint),
//
//            cursorColor = colorResource(R.color.textColor_all_textField_content),
//            errorCursorColor = colorResource(R.color.textColor_all_textField_content),
//        ),
        enabled = enabled,
        isError = isError,
        readOnly = readOnly,
        textStyle = CustomTypography.TextField,
        placeholder = {
            Text(
                text = hint,
                style = CustomTypography.TextField
            )
        },
        trailingIcon = trailingIcon,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        interactionSource = interactionSource,
    )
}
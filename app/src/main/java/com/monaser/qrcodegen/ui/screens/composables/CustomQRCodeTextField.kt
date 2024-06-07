/**
 * @author Mohamed Naser.
 */

package com.monaser.qrcodegen.ui.screens.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun QRCodeTextField(
    hint: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    text: String = "",
    errorMessage: String = "",
    oneLineOnly: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(
        imeAction = ImeAction.Default
    ),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    val isError = errorMessage.isNotEmpty()

    Column {
        OutlinedTextField(
            value = text,
            onValueChange = onValueChange,
            singleLine = oneLineOnly,
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            label = {
                Text(
                    text = hint,
                    color = if (isError) MaterialTheme.colorScheme.error else Color.Gray,
                )
            },
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            shape = MaterialTheme.shapes.medium,
            maxLines = 1,
            colors = OutlinedTextFieldDefaults.colors(
                focusedSupportingTextColor = if (isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onTertiaryContainer,
                focusedContainerColor = Color.Transparent,
                disabledContainerColor = MaterialTheme.colorScheme.onTertiary,
                focusedBorderColor = if (isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onTertiaryContainer,
                unfocusedBorderColor = if (isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onTertiaryContainer
            ),
            isError = isError,
            trailingIcon = trailingIcon
        )
        if (isError) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}
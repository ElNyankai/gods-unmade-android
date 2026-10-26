package com.sixseven.godsunmade.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.sixseven.godsunmade.ui.components.BodyText
import com.sixseven.godsunmade.ui.components.GodsButton
import com.sixseven.godsunmade.ui.components.GodsCard
import com.sixseven.godsunmade.ui.components.GodsTextField
import com.sixseven.godsunmade.ui.components.OrnamentalDivider
import com.sixseven.godsunmade.ui.components.ScreenTitle

@Composable
fun LoginScreen(
    onSubmit: () -> Unit,
    modifier: Modifier = Modifier
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(start = 24.dp, end = 28.dp, top = 24.dp, bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ScreenTitle("Iniciar sesión")
        OrnamentalDivider()

        GodsCard(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                GodsTextField(
                    value = username,
                    onValueChange = { username = it },
                    label = "Usuario"
                )
                GodsTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = "Contraseña",
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )
                GodsButton(
                    text = "ENTRAR",
                    onClick = onSubmit,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        BodyText(text = "El inicio de sesión se conectará a la API de Django más adelante.")
    }
}
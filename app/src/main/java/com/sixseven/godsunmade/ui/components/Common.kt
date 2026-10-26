package com.sixseven.godsunmade.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sixseven.godsunmade.ui.theme.Gold
import com.sixseven.godsunmade.ui.theme.GoldLight
import com.sixseven.godsunmade.ui.theme.GodsMono
import com.sixseven.godsunmade.ui.theme.SecondaryBlue
import com.sixseven.godsunmade.ui.theme.Surface
import com.sixseven.godsunmade.ui.theme.TextMuted
import com.sixseven.godsunmade.ui.theme.TextPrimary
import com.sixseven.godsunmade.ui.theme.TextSecondary

@Composable
fun ScreenTitle(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text.uppercase(),
        color = TextPrimary,
        fontFamily = GodsMono,
        fontWeight = FontWeight.Black,
        fontSize = 24.sp,
        letterSpacing = 0.15.em,
        textAlign = TextAlign.Center,
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
fun SubTitle(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Gold
) {
    Text(
        text = text.uppercase(),
        color = color,
        fontFamily = GodsMono,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        letterSpacing = 0.08.em,
        modifier = modifier
    )
}

@Composable
fun BodyText(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        text = text,
        color = TextSecondary,
        fontSize = 14.sp,
        lineHeight = 24.sp,
        textAlign = textAlign,
        modifier = modifier
    )
}

@Composable
fun LabelText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Gold
) {
    Text(
        text = text.uppercase(),
        color = color,
        fontFamily = GodsMono,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        letterSpacing = 0.1.em,
        modifier = modifier
    )
}

/** Imagen remota (Coil) o placeholder si no hay URL. */
@Composable
fun GodsImage(url: String?, glyph: String, modifier: Modifier = Modifier) {
    if (url.isNullOrBlank()) {
        Box(
            modifier = modifier.background(SecondaryBlue),
            contentAlignment = Alignment.Center
        ) {
            Text(text = glyph, color = Gold, fontSize = 36.sp, fontFamily = GodsMono)
        }
    } else {
        AsyncImage(
            model = url,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
        )
    }
}

@Composable
fun GodsTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    singleLine: Boolean = true
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label.uppercase(), fontFamily = GodsMono, fontSize = 12.sp) },
        singleLine = singleLine,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        shape = RoundedCornerShape(0.dp),
        textStyle = TextStyle(color = TextPrimary, fontSize = 14.sp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = GoldLight,
            unfocusedBorderColor = Gold,
            focusedContainerColor = Surface,
            unfocusedContainerColor = Surface,
            focusedLabelColor = GoldLight,
            unfocusedLabelColor = TextMuted,
            cursorColor = GoldLight,
            focusedTextColor = TextPrimary,
            unfocusedTextColor = TextPrimary
        ),
        modifier = modifier.fillMaxWidth()
    )
}
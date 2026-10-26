package com.sixseven.godsunmade.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.sixseven.godsunmade.ui.theme.Background
import com.sixseven.godsunmade.ui.theme.Gold
import com.sixseven.godsunmade.ui.theme.GoldLight
import com.sixseven.godsunmade.ui.theme.GodsMono
import com.sixseven.godsunmade.ui.theme.SecondaryBlue
import com.sixseven.godsunmade.ui.theme.SurfaceAlpha
import com.sixseven.godsunmade.ui.theme.TextPrimary
import com.sixseven.godsunmade.ui.theme.TextSecondary

/** Chip retro. Con onClick es un filtro; sin onClick es una etiqueta. */
@Composable
fun GodsChip(
    text: String,
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    onClick: (() -> Unit)? = null
) {
    val shape = RoundedCornerShape(0.dp)
    Box(
        modifier = modifier
            .background(if (selected) Gold else SurfaceAlpha, shape)
            .border(2.dp, Gold, shape)
            .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier)
            .padding(horizontal = 10.dp, vertical = 6.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text.uppercase(),
            color = if (selected) Background else GoldLight,
            fontFamily = GodsMono,
            fontWeight = FontWeight.Bold,
            fontSize = 11.sp,
            letterSpacing = 0.05.em,
            maxLines = 1
        )
    }
}

/** Tabla de dos columnas con borde dorado. */
@Composable
fun GodsTable(
    headerLeft: String,
    headerRight: String,
    rows: List<Pair<String, String>>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .border(2.dp, Gold, RoundedCornerShape(0.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(SecondaryBlue)
                .padding(horizontal = 12.dp, vertical = 10.dp)
        ) {
            Text(
                text = headerLeft.uppercase(),
                color = Gold,
                fontFamily = GodsMono,
                fontWeight = FontWeight.Black,
                fontSize = 12.sp,
                letterSpacing = 0.1.em,
                modifier = Modifier.weight(0.4f)
            )
            Text(
                text = headerRight.uppercase(),
                color = Gold,
                fontFamily = GodsMono,
                fontWeight = FontWeight.Black,
                fontSize = 12.sp,
                letterSpacing = 0.1.em,
                modifier = Modifier.weight(0.6f)
            )
        }
        rows.forEach { (left, right) ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Gold.copy(alpha = 0.5f))
            )
            Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp, vertical = 10.dp)) {
                Text(
                    text = left.uppercase(),
                    color = TextPrimary,
                    fontFamily = GodsMono,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    modifier = Modifier.weight(0.4f).padding(end = 8.dp)
                )
                Text(
                    text = right,
                    color = TextSecondary,
                    fontSize = 13.sp,
                    lineHeight = 20.sp,
                    modifier = Modifier.weight(0.6f)
                )
            }
        }
    }
}

/** Arte del personaje: usa res/drawable/<drawable> si existe; si no, un placeholder. */
@Composable
fun CharacterArt(
    drawable: String,
    categoria: String,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val resId = remember(drawable) {
        context.resources.getIdentifier(drawable, "drawable", context.packageName)
    }
    if (resId != 0) {
        Image(
            painter = painterResource(id = resId),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
        )
    } else {
        val glyph = when (categoria) {
            "PROTAGONISTA" -> "✦"
            "JEFE" -> "♛"
            else -> "☠"
        }
        GodsImage(url = null, glyph = glyph, modifier = modifier)
    }
}
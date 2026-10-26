package com.sixseven.godsunmade.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.sixseven.godsunmade.ui.theme.Crimson
import com.sixseven.godsunmade.ui.theme.Gold
import com.sixseven.godsunmade.ui.theme.GodsMono
import com.sixseven.godsunmade.ui.theme.SurfaceAlpha
import com.sixseven.godsunmade.ui.theme.TextPrimary

@Composable
fun GodsHeader(
    showLogin: Boolean,
    onLoginClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(SurfaceAlpha)
            .statusBarsPadding()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .background(Crimson)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .hardShadow(3.dp)
                    .background(Crimson, RoundedCornerShape(0.dp))
                    .border(2.dp, Gold, RoundedCornerShape(0.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "✦",
                    color = TextPrimary,
                    fontSize = 22.sp,
                    fontFamily = GodsMono
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    text = "GODS",
                    color = TextPrimary,
                    fontFamily = GodsMono,
                    fontWeight = FontWeight.Black,
                    fontSize = 18.sp
                )
                Text(
                    text = "U N M A D E",
                    color = Gold,
                    fontFamily = GodsMono,
                    fontSize = 12.sp,
                    letterSpacing = 0.3.em
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            if (showLogin) {
                GodsButton(text = "INICIAR SESIÓN", onClick = onLoginClick)
                Spacer(modifier = Modifier.width(4.dp))
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .background(Gold)
        )
        OrnamentalDivider(modifier = Modifier.padding(vertical = 2.dp))
    }
}
package com.sixseven.godsunmade.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sixseven.godsunmade.ui.theme.*
import kotlin.math.min

/**
 * Arte generado con Compose (sin imágenes externas) para los capítulos que no tienen ilustración.
 * tipo: camino, mar, flecha, forja, umbral, hogar, tacha, balanza, aguja, concordancia, escudo, mapa, fragmentos.
 */
@Composable
fun ChapterArt(tipo: String?, modifier: Modifier = Modifier) {
    Canvas(modifier.fillMaxSize()) {
        val w = size.width
        val h = size.height
        // Fondo: degradado de brasas y piedra
        drawRect(Brush.verticalGradient(listOf(Sombra, ArcillaOscura, Superficie)))
        drawRect(
            Brush.radialGradient(listOf(Bronce.copy(alpha = 0.28f), Color.Transparent), Offset(w / 2, h / 2), min(w, h) * 0.9f),
        )
        // Textura de bloques de piedra
        var y = 0f
        while (y < h) {
            drawLine(Bronce.copy(alpha = 0.05f), Offset(0f, y), Offset(w, y), 1f)
            y += 22.dp.toPx()
        }
        // Brasas
        for (i in 0 until 14) {
            val px = (i * 977 % 100) / 100f * w
            val py = (i * 389 % 100) / 100f * h
            drawCircle(BronceClaro.copy(alpha = 0.18f), 1.5.dp.toPx() + (i % 3), Offset(px, py))
        }
        symbol(tipo, w, h)
    }
}

private fun DrawScope.symbol(tipo: String?, w: Float, h: Float) {
    val u = min(w, h) * 0.25f
    val cx = w / 2
    val cy = h * 0.36f
    val line = Stroke(width = (u * 0.07f).coerceAtLeast(2f), cap = StrokeCap.Round)
    val thin = Stroke(width = (u * 0.035f).coerceAtLeast(1f), cap = StrokeCap.Round)
    val main = Bronce.copy(alpha = 0.9f)
    val soft = BronceClaro.copy(alpha = 0.5f)
    when (tipo) {
        // Camino: dos líneas que convergen hacia el horizonte, con huellas en el centro.
        "camino" -> {
            drawLine(main, Offset(cx - u * 1.6f, h), Offset(cx - u * 0.2f, cy - u * 0.8f), line.width, StrokeCap.Round)
            drawLine(main, Offset(cx + u * 1.6f, h), Offset(cx + u * 0.2f, cy - u * 0.8f), line.width, StrokeCap.Round)
            for (i in 0..4) {
                val t = i / 5f
                drawLine(soft, Offset(cx, cy - u * 0.7f + t * (h - cy + u * 0.7f)), Offset(cx, cy - u * 0.5f + t * (h - cy + u * 0.7f)), line.width, StrokeCap.Round)
            }
        }
        // Mar: tres olas sinusoidales, la central resaltada.
        "mar" -> for (k in 0..2) {
            val p = Path()
            val yy = cy - u * 0.5f + k * u * 0.55f
            p.moveTo(cx - u * 1.5f, yy)
            for (i in 0 until 3) {
                val x0 = cx - u * 1.5f + i * u
                p.quadraticTo(x0 + u * 0.25f, yy - u * 0.35f, x0 + u * 0.5f, yy)
                p.quadraticTo(x0 + u * 0.75f, yy + u * 0.35f, x0 + u, yy)
            }
            drawPath(p, if (k == 1) main else soft, style = line)
        }
        // Flecha: asta diagonal con punta triangular y plumas en el extremo.
        "flecha" -> {
            val a = Offset(cx - u * 1.2f, cy + u * 0.9f)
            val b = Offset(cx + u * 1.2f, cy - u * 0.9f)
            drawLine(main, a, b, line.width, StrokeCap.Round)
            val head = Path().apply {
                moveTo(b.x, b.y); lineTo(b.x - u * 0.45f, b.y + u * 0.1f); lineTo(b.x - u * 0.1f, b.y + u * 0.45f); close()
            }
            drawPath(head, main)
            drawLine(soft, a, Offset(a.x - u * 0.3f, a.y - u * 0.2f), line.width, StrokeCap.Round)
            drawLine(soft, Offset(a.x + u * 0.25f, a.y - u * 0.17f), Offset(a.x - u * 0.05f, a.y - u * 0.37f), line.width, StrokeCap.Round)
            drawCircle(soft, u * 0.45f, Offset(cx + u * 0.9f, cy + u * 0.7f), style = thin)
        }
        // Forja: crisol con chispas que suben desde el centro.
        "forja" -> {
            val p = Path().apply {
                moveTo(cx - u * 1.2f, cy - u * 0.1f); lineTo(cx + u * 1.1f, cy - u * 0.1f)
                lineTo(cx + u * 0.5f, cy + u * 0.3f); lineTo(cx + u * 0.4f, cy + u * 0.9f)
                lineTo(cx - u * 0.6f, cy + u * 0.9f); lineTo(cx - u * 0.5f, cy + u * 0.3f); close()
            }
            drawPath(p, main, style = line)
            for (i in 0..4) {
                val ang = -1.2f + i * 0.5f
                drawLine(soft, Offset(cx, cy - u * 0.2f), Offset(cx + ang * u * 0.9f, cy - u * (0.7f + (i % 2) * 0.5f)), thin.width, StrokeCap.Round)
            }
        }
        // Umbral: marco de puerta con arco interior y línea de suelo.
        "umbral" -> {
            drawRect(main, Offset(cx - u, cy - u * 0.9f), Size(u * 2, u * 1.9f), style = line)
            drawRect(soft, Offset(cx - u * 1.2f, cy - u * 1.1f), Size(u * 2.4f, u * 0.22f), style = thin)
            val arch = Path().apply {
                moveTo(cx - u * 0.45f, cy + u); lineTo(cx - u * 0.45f, cy - u * 0.2f)
                quadraticTo(cx, cy - u * 0.9f, cx + u * 0.45f, cy - u * 0.2f); lineTo(cx + u * 0.45f, cy + u)
            }
            drawPath(arch, soft, style = thin)
            drawLine(soft, Offset(cx, cy + u), Offset(cx, h), thin.width)
        }
        // Hogar: tres llamas sobre una base horizontal.
        "hogar" -> {
            for (k in 0..2) {
                val s = if (k == 1) 1.3f else 0.9f
                val x = cx + (k - 1) * u * 0.8f
                val f = Path().apply {
                    moveTo(x, cy + u * 0.8f)
                    cubicTo(x - u * 0.6f * s, cy + u * 0.3f, x - u * 0.2f * s, cy - u * 0.2f * s, x, cy - u * 0.9f * s)
                    cubicTo(x + u * 0.2f * s, cy - u * 0.2f * s, x + u * 0.6f * s, cy + u * 0.3f, x, cy + u * 0.8f)
                }
                drawPath(f, if (k == 1) main else soft, style = line)
            }
            drawLine(main, Offset(cx - u * 1.5f, cy + u * 0.95f), Offset(cx + u * 1.5f, cy + u * 0.95f), line.width, StrokeCap.Round)
        }
        // Tacha: círculo con trazo cruzado, símbolo de rechazo.
        "tacha" -> {
            drawCircle(soft, u, Offset(cx, cy), style = thin)
            drawLine(main, Offset(cx - u * 1.3f, cy + u * 1.1f), Offset(cx + u * 1.3f, cy - u * 1.1f), line.width * 1.3f, StrokeCap.Round)
            drawLine(soft, Offset(cx - u * 0.9f, cy - u * 0.3f), Offset(cx + u * 0.3f, cy - u * 0.3f), thin.width, StrokeCap.Round)
            drawLine(soft, Offset(cx - u * 0.5f, cy + u * 0.4f), Offset(cx + u * 0.8f, cy + u * 0.4f), thin.width, StrokeCap.Round)
        }
        // Balanza: fiel con dos platillos colgando y base.
        "balanza" -> {
            drawLine(main, Offset(cx, cy - u), Offset(cx, cy + u), line.width, StrokeCap.Round)
            drawLine(main, Offset(cx - u * 1.2f, cy - u * 0.6f), Offset(cx + u * 1.2f, cy - u * 0.6f), line.width, StrokeCap.Round)
            for (s in listOf(-1f, 1f)) {
                val x = cx + s * u * 1.2f
                drawLine(soft, Offset(x, cy - u * 0.6f), Offset(x - u * 0.4f, cy + u * 0.1f), thin.width)
                drawLine(soft, Offset(x, cy - u * 0.6f), Offset(x + u * 0.4f, cy + u * 0.1f), thin.width)
                drawArc(main, 0f, 180f, false, Offset(x - u * 0.4f, cy - u * 0.25f), Size(u * 0.8f, u * 0.7f), style = line)
            }
            drawLine(main, Offset(cx - u * 0.5f, cy + u), Offset(cx + u * 0.5f, cy + u), line.width, StrokeCap.Round)
        }
        // Aguja: hilo que sale del ojo de una aguja en curva.
        "aguja" -> {
            val a = Offset(cx - u * 1.3f, cy + u * 0.9f)
            val b = Offset(cx + u * 1.3f, cy - u * 0.9f)
            drawLine(main, a, b, line.width, StrokeCap.Round)
            drawCircle(main, u * 0.14f, Offset(a.x + u * 0.12f, a.y - u * 0.08f), style = thin)
            val thread = Path().apply {
                moveTo(a.x + u * 0.12f, a.y - u * 0.08f)
                cubicTo(cx - u, cy + u * 1.2f, cx + u * 0.2f, cy + u * 0.2f, cx + u * 1.3f, cy + u * 0.9f)
            }
            drawPath(thread, soft, style = thin)
        }
        // Concordancia: tres círculos superpuestos en triángulo.
        "concordancia" -> {
            drawCircle(main, u * 0.8f, Offset(cx, cy - u * 0.45f), style = line)
            drawCircle(soft, u * 0.8f, Offset(cx - u * 0.7f, cy + u * 0.4f), style = line)
            drawCircle(soft, u * 0.8f, Offset(cx + u * 0.7f, cy + u * 0.4f), style = line)
        }
        // Escudo: silueta con eje vertical y franja horizontal.
        "escudo" -> {
            val p = Path().apply {
                moveTo(cx - u * 0.9f, cy - u * 0.9f); lineTo(cx + u * 0.9f, cy - u * 0.9f)
                lineTo(cx + u * 0.9f, cy + u * 0.1f)
                quadraticTo(cx + u * 0.8f, cy + u * 0.8f, cx, cy + u * 1.1f)
                quadraticTo(cx - u * 0.8f, cy + u * 0.8f, cx - u * 0.9f, cy + u * 0.1f); close()
            }
            drawPath(p, main, style = line)
            drawLine(soft, Offset(cx, cy - u * 0.9f), Offset(cx, cy + u * 1.1f), thin.width)
            drawLine(soft, Offset(cx - u * 0.9f, cy - u * 0.2f), Offset(cx + u * 0.9f, cy - u * 0.2f), thin.width)
        }
        "mapa" -> {
            // Península con montañas y una pequeña brújula
            val costa = Path().apply {
                moveTo(cx - 1.3f * u, cy - 0.45f * u); lineTo(cx - 0.5f * u, cy - 0.9f * u)
                lineTo(cx + 0.5f * u, cy - 0.8f * u); lineTo(cx + 1.0f * u, cy - 0.3f * u)
                lineTo(cx + 0.55f * u, cy - 0.05f * u); lineTo(cx + 0.8f * u, cy + 0.85f * u)
                lineTo(cx + 0.4f * u, cy + 0.35f * u); lineTo(cx + 0.1f * u, cy + 0.95f * u)
                lineTo(cx - 0.1f * u, cy + 0.15f * u); lineTo(cx - 0.7f * u, cy + 0.25f * u)
                lineTo(cx - 1.15f * u, cy - 0.05f * u); close()
            }
            drawPath(costa, main, style = line)
            for ((dx, s) in listOf(-0.6f to 1f, -0.15f to 0.7f)) {
                val m = Path().apply {
                    moveTo(cx + dx * u - 0.25f * u * s, cy - 0.05f * u)
                    lineTo(cx + dx * u, cy - 0.05f * u - 0.45f * u * s)
                    lineTo(cx + dx * u + 0.25f * u * s, cy - 0.05f * u)
                }
                drawPath(m, soft, style = thin)
            }
            val bx = cx + 1.35f * u
            val by = cy - 0.85f * u
            drawCircle(soft, 0.3f * u, Offset(bx, by), style = thin)
            drawLine(main, Offset(bx, by - 0.45f * u), Offset(bx, by + 0.45f * u), thin.width, StrokeCap.Round)
            drawLine(soft, Offset(bx - 0.45f * u, by), Offset(bx + 0.45f * u, by), thin.width, StrokeCap.Round)
        }
        "fragmentos" -> {
            // Anillo roto en arcos con huecos, esquirlas flotando y un eco tenue
            val tl = Offset(cx - u, cy - u)
            val sz = Size(2 * u, 2 * u)
            drawArc(main, 200f, 95f, false, tl, sz, style = line)
            drawArc(soft, 320f, 55f, false, tl, sz, style = line)
            drawArc(main, 60f, 85f, false, tl, sz, style = line)
            drawArc(soft, 170f, 15f, false, tl, sz, style = line)
            drawArc(soft.copy(alpha = 0.25f), 0f, 360f, false, Offset(cx - 1.35f * u, cy - 1.35f * u), Size(2.7f * u, 2.7f * u), style = thin)
            for ((px, py, r) in listOf(Triple(0.1f, -0.1f, 0.35f), Triple(-0.45f, 0.35f, 0.25f), Triple(0.5f, 0.45f, 0.2f))) {
                val t = Path().apply {
                    moveTo(cx + px * u, cy + py * u - r * u)
                    lineTo(cx + px * u + r * u, cy + py * u + r * u * 0.7f)
                    lineTo(cx + px * u - r * u * 0.6f, cy + py * u + r * u)
                    close()
                }
                drawPath(t, main, style = thin)
            }
        }
        else -> drawCircle(main, u * 0.6f, Offset(cx, cy), style = line)
    }
}

/** Imagen del capítulo si existe; si no, arte generado. */
@Composable
fun ChapterVisual(imagen: String?, arte: String?, modifier: Modifier = Modifier) {
    val res = drawableFor(imagen)
    Box(modifier) {
        if (res != null) {
            Image(painterResource(res), null, Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
        } else {
            ChapterArt(arte)
        }
    }
}

@Preview(widthDp = 360, heightDp = 640, showBackground = true)
@Composable
private fun ChapterArtPreview() = GodsUnmadeTheme {
    val tipos = listOf("camino", "mar", "flecha", "forja", "umbral", "hogar", "tacha", "balanza", "aguja", "concordancia", "escudo", "mapa", "fragmentos")
    Column {
        tipos.chunked(3).forEach { fila ->
            Row { fila.forEach { ChapterArt(it, Modifier.weight(1f).height(100.dp)) } }
        }
    }
}

package io.github.simonscholz

import io.github.simonscholz.qrcode.QrCodeConfig
import io.github.simonscholz.qrcode.QrCodeDotShape
import io.github.simonscholz.qrcode.QrCodeFactory
import java.awt.Color
import java.awt.Graphics2D
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import javax.imageio.ImageIO

fun main() {
    val path = Paths.get(System.getProperty("user.home"), "qr-code-samples")
    Files.createDirectories(path)
    val qrCodeDir = path.toAbsolutePath().toString()
    val qrCodeApi = QrCodeFactory.createQrCodeApi()
    QrCodeConfig.Builder("https://simonscholz.github.io/")
        .qrCodeDotStyler(QrCodeDotShape.CIRCLE)
        .qrCodeSize(800)
        .build()
        .run {
            qrCodeApi.createQrCodeImage(this)
                .toFile(File(qrCodeDir, "/qr-with-CIRCLE-dots-kotlin.png"))
        }

    QrCodeConfig.Builder("https://simonscholz.github.io/")
        .qrCodeDotStyler(QrCodeDotShape.ROUNDED_SQUARE)
        .qrCodeSize(800)
        .build()
        .run {
            qrCodeApi.createQrCodeImage(this)
                .toFile(File(qrCodeDir, "/qr-with-ROUND-SQUARE-dots-kotlin.png"))
        }

    QrCodeConfig.Builder("https://simonscholz.github.io/")
        .qrCodeDotStyler(QrCodeDotShape.HEXAGON)
        .qrCodeSize(800)
        .build()
        .run {
            qrCodeApi.createQrCodeImage(this)
                .toFile(File(qrCodeDir, "/qr-with-HEXAGON-dots-kotlin.png"))
        }

    QrCodeConfig.Builder("https://simonscholz.github.io/")
        .qrCodeDotStyler(QrCodeDotShape.TRIANGLE)
        .qrCodeSize(800)
        .build()
        .run {
            qrCodeApi.createQrCodeImage(this)
                .toFile(File(qrCodeDir, "/qr-with-TRIANGLE-dots-kotlin.png"))
        }

    QrCodeConfig.Builder("https://simonscholz.github.io/")
        .qrCodeDotStyler(QrCodeDotShape.HEART)
        .qrCodeSize(800)
        .build()
        .run {
            qrCodeApi.createQrCodeImage(this)
                .toFile(File(qrCodeDir, "/qr-with-HEART-dots-kotlin.png"))
        }

    QrCodeConfig.Builder("https://simonscholz.github.io/")
        .qrCodeDotStyler(QrCodeDotShape.HOUSE)
        .qrCodeSize(800)
        .build()
        .run {
            qrCodeApi.createQrCodeImage(this)
                .toFile(File(qrCodeDir, "/qr-with-HOUSE-dots-kotlin.png"))
        }

    QrCodeConfig.Builder("https://simonscholz.github.io/")
        .qrCodeDotStyler(QrCodeDotShape.STAR)
        .qrCodeSize(800)
        .build()
        .run {
            qrCodeApi.createQrCodeImage(this)
                .toFile(File(qrCodeDir, "/qr-with-STAR-dots-kotlin.png"))
        }

    QrCodeConfig.Builder("https://simonscholz.github.io/")
        .qrCodeDotStyler(QrCodeDotShape.DIAMOND)
        .qrCodeSize(800)
        .build()
        .run {
            qrCodeApi.createQrCodeImage(this)
                .toFile(File(qrCodeDir, "/qr-with-DIAMOND-dots-kotlin.png"))
        }

    QrCodeConfig.Builder("https://simonscholz.github.io/")
        .qrCodeDotStyler(QrCodeDotShape.CROSS)
        .qrCodeSize(800)
        .build()
        .run {
            qrCodeApi.createQrCodeImage(this)
                .toFile(File(qrCodeDir, "/qr-with-CROSS-dots-kotlin.png"))
        }

    QrCodeConfig.Builder("https://simonscholz.github.io/")
        .qrCodeDotStyler(::drawColorfulHouseWithDoorAndWindow)
        .qrCodeSize(800)
        .build()
        .run {
            qrCodeApi.createQrCodeImage(this)
                .toFile(File(qrCodeDir, "/qr-with-COLORFUL-HOUSE-dots-kotlin.png"))
        }

    QrCodeConfig.Builder("https://simonscholz.github.io/")
        .qrCodeDotStyler(::drawSmiley)
        .qrCodeSize(800)
        .build()
        .run {
            qrCodeApi.createQrCodeImage(this)
                .toFile(File(qrCodeDir, "/qr-with-SMILEY-dots-kotlin.png"))
        }

    QrCodeConfig.Builder("https://simonscholz.github.io/")
        .qrCodeDotStyler(::drawSkull)
        .qrCodeSize(800)
        .build()
        .run {
            qrCodeApi.createQrCodeImage(this)
                .toFile(File(qrCodeDir, "/qr-with-SKULL-dots-kotlin.png"))
        }

    QrCodeConfig.Builder("https://simonscholz.github.io/")
        .qrCodeDotStyler(::drawPumpkin)
        .qrCodeSize(800)
        .build()
        .run {
            qrCodeApi.createQrCodeImage(this)
                .toFile(File(qrCodeDir, "/qr-with-pumpkin-dots-kotlin.png"))
        }

    QrCodeConfig.Builder("https://simonscholz.github.io/")
        .qrCodeDotStyler(::drawEvilPumpkin)
        .qrCodeSize(800)
        .build()
        .run {
            qrCodeApi.createQrCodeImage(this)
                .toFile(File(qrCodeDir, "/qr-with-pumpkin-evil-dots-kotlin.png"))
        }

    QrCodeConfig.Builder("https://simonscholz.github.io/")
        .qrCodeDotStyler(::drawChristmasTree)
        .qrCodeSize(800)
        .build()
        .run {
            qrCodeApi.createQrCodeImage(this)
                .toFile(File(qrCodeDir, "/qr-with-christmas-tree-dots-kotlin.png"))
        }
}

private fun drawSmiley(
    x: Int,
    y: Int,
    dotSize: Int,
    graphics: Graphics2D,
) {
    drawDotImage(x, y, dotSize, graphics, "smiley_fill.png")
}

private fun drawSkull(
    x: Int,
    y: Int,
    dotSize: Int,
    graphics: Graphics2D,
) {
    drawDotImage(x, y, dotSize, graphics, "skull_fill.png")
}

private fun drawPumpkin(
    x: Int,
    y: Int,
    dotSize: Int,
    graphics: Graphics2D,
) {
    drawDotImage(x, y, dotSize, graphics, "halloween_pumpkin.png")
}

private fun drawEvilPumpkin(
    x: Int,
    y: Int,
    dotSize: Int,
    graphics: Graphics2D,
) {
    drawDotImage(x, y, dotSize, graphics, "halloween_pumpkin_evil.png")
}

private fun drawChristmasTree(
    x: Int,
    y: Int,
    dotSize: Int,
    graphics: Graphics2D,
) {
    drawDotImage(x, y, dotSize, graphics, "christmas_tree.png")
}

private fun drawDotImage(
    x: Int,
    y: Int,
    dotSize: Int,
    graphics: Graphics2D,
    image: String,
) {
    val resource = Main::class.java.getClassLoader().getResource(image)
    resource?.let {
        val imageDot = ImageIO.read(it)
        graphics.drawImage(imageDot, x, y, dotSize, dotSize, null)
    }
}

private fun drawColorfulHouseWithDoorAndWindow(
    x: Int,
    y: Int,
    size: Int,
    graphic: Graphics2D,
) {
    val roofHeight = size / 2
    val houseWidth = size
    val houseHeight = size - roofHeight

    // Draw the base of the house
    graphic.color = Color.RED
    graphic.fillRect(x, y + roofHeight, houseWidth, houseHeight)

    // Draw the roof
    graphic.color = Color.BLUE
    val roofXPoints = intArrayOf(x, x + houseWidth / 2, x + houseWidth)
    val roofYPoints = intArrayOf(y + roofHeight, y, y + roofHeight)
    graphic.fillPolygon(roofXPoints, roofYPoints, 3)

    // Draw the door
    val doorWidth = size / 5
    val doorHeight = size / 2 - 1
    val doorX = x + (houseWidth - size / 5) / 2 + size / 10
    val doorY = y + roofHeight + houseHeight - doorHeight + 1
    graphic.color = Color.GREEN
    graphic.fillRect(doorX, doorY, doorWidth, doorHeight)

    // Draw the window
    val windowSize = size / 5
    val windowX = x + size / 5
    val windowY = y + roofHeight + size / 5
    graphic.color = Color.YELLOW
    graphic.fillRect(windowX, windowY, windowSize, windowSize)
}

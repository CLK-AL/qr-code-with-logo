package io.github.simonscholz.qrcode.internal.graphics

import java.awt.BasicStroke
import java.awt.Color
import java.awt.Graphics2D
import java.awt.Point
import java.awt.Polygon
import java.awt.geom.AffineTransform
import java.awt.geom.Ellipse2D
import java.awt.geom.Path2D
import java.awt.geom.Rectangle2D
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

internal object CustomQrCodeDotStyler {
    fun drawHouse(
        x: Int,
        y: Int,
        size: Int,
        graphic: Graphics2D,
    ) {
        val roofHeight = size / 2
        val houseWidth = size - 1 // -1 to have a gap between the houses
        val houseHeight = size - roofHeight

        // Draw the base of the house
        graphic.fillRect(x, y + roofHeight, houseWidth, houseHeight)

        // Draw the roof
        val roofXPoints = intArrayOf(x, x + houseWidth / 2, x + houseWidth)
        val roofYPoints = intArrayOf(y + roofHeight, y, y + roofHeight)
        graphic.fillPolygon(roofXPoints, roofYPoints, 3)
    }

    fun drawHeart(
        x: Int,
        y: Int,
        size: Int,
        graphic: Graphics2D,
    ) {
        val heartWidth = decreaseOnRoundingIssues(size)
        val heartHeight = decreaseOnRoundingIssues(size)
        val gap = increaseOnRoundingIssues(heartWidth / 4)

        // Draw the left arc of the heart
        graphic.fillArc(x, y, heartWidth / 2, heartHeight / 2, 0, 180)

        // Draw the right arc of the heart
        graphic.fillArc(x + heartWidth / 2, y, heartWidth / 2, heartHeight / 2, 0, 180)

        // Draw the bottom triangle of the heart
        val triangleXPoints = intArrayOf(x, x + heartWidth / 2, x + heartWidth, x)
        val triangleYPoints =
            intArrayOf(y + heartHeight / 2 - gap, y + heartHeight - gap, y + heartHeight / 2 - gap, y + heartHeight / 2 - gap)
        graphic.fillPolygon(triangleXPoints, triangleYPoints, 4)
    }

    private fun decreaseOnRoundingIssues(size: Int): Int =
        if (size % 2 == 0) {
            size
        } else {
            size - 1
        }

    private fun increaseOnRoundingIssues(size: Int): Int =
        if (size % 2 == 0) {
            size
        } else {
            size + 1
        }

    fun drawHexagon(
        x: Int,
        y: Int,
        size: Int,
        graphic: Graphics2D,
    ) {
        val hexRadius = size / 2
        graphic.fillPolygon(createHexagon(Point(x + hexRadius, y + hexRadius), hexRadius))
    }

    private fun createHexagon(
        center: Point,
        radius: Int,
    ): Polygon {
        val polygon = Polygon()
        for (i in 0..5) {
            polygon.addPoint(
                (center.x + radius * cos(i * 2 * Math.PI / 6.0)).toInt(),
                (center.y + radius * sin(i * 2 * Math.PI / 6.0)).toInt(),
            )
        }
        return polygon
    }

    fun drawEquilateralTriangle(
        x: Int,
        y: Int,
        size: Int,
        graphic: Graphics2D,
    ) {
        val triangleHeight = (sqrt(3.0) / 2 * size).toInt()

        val triangleXPoints = intArrayOf(x, x + size / 2, x + size, x)
        val triangleYPoints = intArrayOf(y + triangleHeight, y, y + triangleHeight, y + triangleHeight)

        graphic.fillPolygon(triangleXPoints, triangleYPoints, 3)
    }

    fun drawStar(
        x: Int,
        y: Int,
        size: Int,
        graphic: Graphics2D,
    ) {
        val outerRadius: Double = (size / 2).toDouble()
        val innerRadius: Double = (size / 4).toDouble()

        val xPoints = IntArray(10)
        val yPoints = IntArray(10)

        for (i in 0 until 10) {
            val angle = Math.PI / 5 * i
            val radius = if (i % 2 == 0) outerRadius else innerRadius
            xPoints[i] = (x + size / 2 + radius * cos(angle)).toInt()
            yPoints[i] = (y + size / 2 + radius * sin(angle)).toInt()
        }

        val star = Polygon(xPoints, yPoints, 10)

        graphic.fill(star)
    }

    fun drawDiamond(
        x: Int,
        y: Int,
        size: Int,
        graphic: Graphics2D,
    ) {
        val halfSize = size / 2

        val xPoints = intArrayOf(x + halfSize, x + size, x + halfSize, x)
        val yPoints = intArrayOf(y, y + halfSize, y + size, y + halfSize)

        val diamond = Polygon(xPoints, yPoints, 4)

        graphic.fill(diamond)
    }

    fun drawCross(
        x: Int,
        y: Int,
        size: Int,
        graphic: Graphics2D,
    ) {
        graphic.fillRect(x, y + size / 4, size, size / 2)
        graphic.fillRect(x + size / 4, y, size / 2, size)
    }

    fun drawSmiley(
        x: Int,
        y: Int,
        size: Int,
        graphic: Graphics2D,
    ) {
        // Draw face
        graphic.fillOval(x, y, size, size)

        val originalColor = graphic.color
        try {
            val oneFith = size / 5
            // Invert color for eyes and mouth
            graphic.color = invertColor(originalColor)

            // Draw eyes
            val eyeSize = (size * 0.2).toInt()
            graphic.fillOval(x + oneFith, y + oneFith, eyeSize, eyeSize)
            graphic.fillOval(x + oneFith * 3, y + oneFith, eyeSize, eyeSize)

            // Draw mouth
            val mouthWidth = (size * 0.6).toInt()
            val mouthHeight = (size * 0.3).toInt()
            graphic.drawArc(x + oneFith, y + oneFith * 2, mouthWidth, mouthHeight, 0, -180)
        } finally {
            graphic.color = originalColor
        }
    }

    fun drawFlower(
        x: Int,
        y: Int,
        size: Int,
        graphics: Graphics2D,
        petalCount: Int = 8,
        petalLength: Double = size * 0.7,
        petalWidth: Double = size * 0.25,
        centerDotSize: Double = size * 0.3,
    ) {
        val centerX = x + size / 2.0
        val centerY = y + size / 2.0
        val angleStep = (2 * Math.PI) / petalCount

        val petalShape = Path2D.Double()

        for (i in 0 until petalCount) {
            val angle = i * angleStep
            val rotation = AffineTransform.getRotateInstance(angle, centerX, centerY)

            // Create a single petal shape (like an almond or oval wedge)
            petalShape.reset()
            petalShape.moveTo(centerX, centerY - petalWidth / 2)
            petalShape.curveTo(
                centerX + petalLength * 0.8,
                centerY - petalWidth / 2,
                centerX + petalLength * 0.8,
                centerY + petalWidth / 2,
                centerX,
                centerY + petalWidth / 2,
            )
            petalShape.closePath()

            val transformed = rotation.createTransformedShape(petalShape)
            graphics.fill(transformed)
        }

        val originalColor = graphics.color
        try {
            graphics.color = invertColor(originalColor)
            graphics.fill(
                Ellipse2D.Double(
                    centerX - centerDotSize / 2,
                    centerY - centerDotSize / 2,
                    centerDotSize,
                    centerDotSize,
                ),
            )
        } finally {
            graphics.color = originalColor
        }
    }

    fun drawEasterEgg(
        x: Int,
        y: Int,
        dotSize: Int,
        graphics: Graphics2D,
    ) {
        val centerX = x + dotSize / 2.0
        val centerY = y + dotSize / 2.0
        val eggWidth = dotSize * 0.9
        val eggHeight = dotSize.toDouble()

        // Draw the egg shape
        graphics.fill(
            Ellipse2D.Double(
                centerX - eggWidth / 2,
                centerY - eggHeight / 2,
                eggWidth,
                eggHeight,
            ),
        )

        // Draw zigzag pattern
        val zigzagPath = Path2D.Double()
        val zigzagCount = 5
        val zigzagTop = centerY - dotSize * 0.1
        val zigzagBottom = centerY + dotSize * 0.1
        val step = eggWidth / zigzagCount

        zigzagPath.moveTo(centerX - eggWidth / 2, zigzagBottom)
        for (i in 0 until zigzagCount) {
            val nextX = centerX - eggWidth / 2 + (i + 0.5) * step
            val nextY = if (i % 2 == 0) zigzagTop else zigzagBottom
            zigzagPath.lineTo(nextX, nextY)
        }
        zigzagPath.lineTo(centerX + eggWidth / 2, zigzagBottom)
        val originalColor = graphics.color
        try {
            graphics.color = Color.WHITE
            graphics.stroke = BasicStroke((dotSize * 0.03).toFloat())
            graphics.draw(zigzagPath)

            // Dashed lines (top and bottom bands)
            val dashYOffsets = listOf(-dotSize * 0.25, dotSize * 0.25)
            for (dy in dashYOffsets) {
                val yLine = centerY + dy
                val dashes = 6
                val dashSpacing = eggWidth / (dashes * 2)
                for (i in 0 until dashes) {
                    val dashX = centerX - eggWidth / 2 + i * 2 * dashSpacing
                    graphics.fill(Rectangle2D.Double(dashX, yLine - 1, dashSpacing, 2.0))
                }
            }
        } finally {
            graphics.color = originalColor
        }
    }

    private fun invertColor(color: Color): Color {
        val red = 255 - color.red
        val green = 255 - color.green
        val blue = 255 - color.blue
        return Color(red, green, blue)
    }
}

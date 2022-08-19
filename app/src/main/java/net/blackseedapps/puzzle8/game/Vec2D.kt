package net.blackseedapps.puzzle8.game

open class Vec2D(var x : Float = 0.0F, var y: Float = 0.0F, var ang : Float = 0.0F)
{
    fun dot(v2: Vec2D): Float {
        return x * v2.x + y * v2.y
    }
    fun getVectorLength(): Float {
        val tsq = x * x + y * y
        return Math.sqrt(tsq.toDouble()).toFloat()
    }
    fun getGeometricDistance(v2: Vec2D): Float {
        val tsq = (v2.x - x) * (v2.x - x) + (v2.y - y) * (v2.y - y)
        return Math.sqrt(tsq.toDouble()).toFloat()
    }
    fun add(v2: Vec2D) {
        x += v2.x
        y += v2.y
    }
    fun subtract(v2: Vec2D) {
        x -= v2.x
        y -= v2.y
    }
    fun multiply(scaleFactor: Float) {
        x = (x * scaleFactor)
        y = (y * scaleFactor)
    }
    fun normalize() {
        val len = getVectorLength()
        if (len != 0.0f) {
            x = (x / len)
            y = (y / len)
        }
        else {
            x = 0.0f
            y = 0.0f
        }
    }
    fun average(secndVec : Vec2D) {
        x = (x + secndVec.x) / 2F
        y = (y + secndVec.y) / 2F
    }
    fun copyFrom(vec : Vec2D) {
        x = vec.x
        y = vec.y
    }
}

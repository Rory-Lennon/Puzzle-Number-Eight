package net.blackseedapps.puzzle8.game

enum class TrailMode { ALIVE, DEAD }

class TrailNode(val mTrailNum : Int) {
    var mAlpha = 255
    var mTrailMode = TrailMode.DEAD
    var mPosition: Vec2D = Vec2D()
    fun setAlpha(maxTrailLength : Int){
        if(mTrailNum > maxTrailLength) return
        var maxTrailLengthF = maxTrailLength.toFloat()
        var mTrailNumF = mTrailNum.toFloat()
        mAlpha = (255F * ((maxTrailLengthF + 1F - mTrailNumF) / (maxTrailLengthF + 1F))).toInt()
    }
}
import kotlin.math.max
import kotlin.math.min

data class Vector2d(var x: Int, var y: Int) {

    operator fun plus(v: Vector2d) = Vector2d(x + v.x, y + v.y)
    operator fun minus(v: Vector2d) = Vector2d(x - v.x, y - v.y)
    operator fun compareTo(other: Vector2d) : Int {
        return when {
            x > other.x && y > other.y -> 1
            x < other.x && y < other.y -> -1
            else -> 0
        };
    }
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Vector2d) return false

        // Compares properties for structural equality
        return this.x == other.x && this.y == other.y
    }

    fun precedes(other: Vector2d) = this.x <= other.x && this.y <= other.y

    fun follows(other: Vector2d) = this.x >= other.x && this.y >= other.y

    fun upperRight(other: Vector2d) =  Vector2d(max(this.x, other.x), max(this.y, other.y))

    fun lowerLeft(other: Vector2d) = Vector2d(min(this.x, other.x), min(this.y, other.y))

    fun opposite() = Vector2d(this.x, this.y)
}

fun MapDirection.toUnitVector(): Vector2d {
    return when (this) {
        MapDirection.NORTH -> Vector2d(0, 1)
        MapDirection.EAST -> Vector2d(1, 0)
        MapDirection.SOUTH -> Vector2d(0, -1)
        MapDirection.WEST -> Vector2d(-1, 0)
    }
}
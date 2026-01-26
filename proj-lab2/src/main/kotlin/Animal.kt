import kotlin.Boolean
import kotlin.String

class Animal : WorldElement {
    override var position: Vector2d
    var dir: MapDirection

    constructor(pos: Vector2d) {
        this.position = pos
        this.dir = MapDirection.NORTH
    }

    fun move(direction: MoveDirection, validator: MoveValidator) {
        when (direction) {
            MoveDirection.LEFT -> this.dir = this.dir.previous()
            MoveDirection.RIGHT -> this.dir = this.dir.next()
            MoveDirection.FORWARD -> {
                val newPosition: Vector2d = this.position + this.dir.toUnitVector()
                if (validator.canMoveTo(newPosition)) this.position = newPosition
            }

            MoveDirection.BACKWARD -> {
                val newPosition: Vector2d = this.position - this.dir.toUnitVector()
                if (validator.canMoveTo(newPosition)) this.position = newPosition
            }
        }
    }

    override fun toString(): String {
        return this.dir.toString()
    }

    fun isAt(position: Vector2d?): Boolean {
        return this.position == position
    }
}
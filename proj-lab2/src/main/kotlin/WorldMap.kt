interface WorldMap : MoveValidator {

    @Throws(IncorrectPositionException::class)
    fun place(animal: Animal)

    fun move(animal: Animal, direction: MoveDirection)

    fun isOccupied(position: Vector2d): Boolean

    fun getElements(): MutableList<WorldElement>

    fun objectAt(position: Vector2d): WorldElement?

    fun getCurrentBounds(): Boundary
}
class BouncyMap : WorldMap, MoveValidator {
    private var animalsMap: MutableMap<Vector2d, Animal> = mutableMapOf()
    private val width: Int;
    private val height: Int;

    constructor(width: Int, height: Int) {
        this.width = width
        this.height = height
    }

    override fun place(animal: Animal) {
        var prev_pos_iter = animalsMap.filter { it == animal }.keys.iterator();
        if (prev_pos_iter.hasNext()) {
            animalsMap.remove(prev_pos_iter.next())
            animalsMap[animal.position] = animal;
        } else {
            if (animalsMap.size >= width * height) {
                animalsMap[animal.position] = animal
            } else {
                val pos = animalsMap.randomFreePosition(Vector2d(width, height));
                animalsMap[pos!!] = animal;
            }
        }
        if (animalsMap[animal.position] == animal) {
            var prev_pos = animalsMap.filter { it == animal }.keys.iterator().hasNext()
        }
    }

    override fun move(animal: Animal, direction: MoveDirection) {
        if (animalsMap.get(animal.position) == animal) {
            animalsMap.remove(animal.position, animal);
            animal.move(direction, this);
            animalsMap.put(animal.position, animal);
        }
    }

    override fun getElements(): MutableList<WorldElement> {
        return animalsMap.values.toMutableList()
    }

    override fun getCurrentBounds(): Boundary {
        return Boundary(Vector2d(0, 0), Vector2d(this.width, this.height))
    }

    override fun isOccupied(position: Vector2d): Boolean {
        return this.animalsMap.containsKey(position)
    }

    override fun objectAt(position: Vector2d): WorldElement? {
        if (animalsMap[position] != null) {
            return animalsMap.get(position)!!;
        } else {
            return null;
        }
    }

    override fun canMoveTo(position: Vector2d): Boolean {
        return Vector2d(0,0) < position && position < Vector2d(this.width, this.height)
    }
}
import kotlin.random.Random
import kotlin.random.nextInt

fun Map<Vector2d, Any>.randomPosition() : Vector2d? {
    if (this.isEmpty()) {
        return null;
    }
    return this.keys.random();
}

fun Map<Vector2d, Any>.randomFreePosition(mapSize: Vector2d) : Vector2d? {
    val rand = Random(0);
    var choice = Vector2d(rand.nextInt().coerceIn(0, mapSize.x), rand.nextInt().coerceIn(0,mapSize.y));
    var tries = 0;
    while (tries < mapSize.x * mapSize.y && this.containsKey(choice)) {
        tries++;
        choice = Vector2d(rand.nextInt().coerceIn(0, mapSize.x), rand.nextInt().coerceIn(0,mapSize.y));
    }
    if (tries < mapSize.x * mapSize.y) {
        return choice;
    } else {
        return null;
    }
}
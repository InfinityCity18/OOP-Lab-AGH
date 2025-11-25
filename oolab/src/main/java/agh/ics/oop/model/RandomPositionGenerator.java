package agh.ics.oop.model;

import java.util.*;

public class RandomPositionGenerator implements Iterable<Vector2d>, Iterator<Vector2d> {
    private List<Vector2d> positions;

    public RandomPositionGenerator(int maxWidth,int maxHeight,int grassCount) {
        //O(w * h) czasowe i pamieciowe, szedlem po linii najmniejszego oporu wiec w sumie pierwszy lepszy pomysl
        // nie jest to dobry generator, bez narzuconych wymagan trudno jest sie oprzec takiemu podejsciu
        List<Vector2d> positions = new ArrayList<>();
        for (int i=0;i<maxWidth;i++) {
            for(int j=0;j<maxHeight;j++) {
                positions.add(new Vector2d(i,j));
            }
        }
        Collections.shuffle(positions);
        this.positions = positions.subList(0,grassCount);
    }


    @Override
    public Iterator<Vector2d> iterator() {
        return positions.iterator();
    }

    @Override
    public boolean hasNext() {
        return !this.positions.isEmpty();
    }

    @Override
    public Vector2d next() {
        return this.positions.removeLast();
    }
}

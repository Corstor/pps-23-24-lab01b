package e1;

public class MovementKnight implements Movement {

    private final int size;

    public MovementKnight(int size) {
        this.size = size;
    }

    @Override
    public Pair<Integer, Integer> move(int x, int y, Pair<Integer, Integer> pair) {
        if (x < 0 || y < 0 || x >= this.size || y >= this.size) {
            throw new IndexOutOfBoundsException();
        }

        return pair;
    }

}

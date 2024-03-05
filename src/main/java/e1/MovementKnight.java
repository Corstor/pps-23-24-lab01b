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

        int movementOnX = x - pair.getX();
        int movementOnY = y - pair.getY();

        if (movementOnX != 0 && movementOnY != 0 && Math.abs(movementOnX) + Math.abs(movementOnY) == 3) {
            return new Pair<Integer,Integer>(x, y);
        }

        return pair;
    }

}

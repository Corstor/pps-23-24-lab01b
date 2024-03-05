package e1;

public class MovementImpl implements Movement{

    private final int size;

    public MovementImpl(int size) {
        this.size = size;
    }

    @Override
    public Pair<Integer, Integer> move(Pair<Integer, Integer> destination, Pair<Integer, Integer> pairToMove) {
        int x = destination.getX();
        int y = destination.getY();

        if (x < 0 || y < 0 || x >= this.size || y >= this.size) {
            throw new IndexOutOfBoundsException();
        }

        int movementOnX = x - pairToMove.getX();
        int movementOnY = y - pairToMove.getY();

        if (movementOnX != 0 && movementOnY != 0 && Math.abs(movementOnX) + Math.abs(movementOnY) == 3) {
            return new Pair<Integer,Integer>(x, y);
        }

        return pairToMove;
        
    }

}

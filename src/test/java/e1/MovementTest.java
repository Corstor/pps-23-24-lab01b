package e1;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MovementTest {
    private static final int SIZE = 10;
    private static final int NEGATIVE_POSITION = -1;
    private Movement movement;
    private Pair<Integer, Integer> knight = new Pair<Integer,Integer>(4, 4);

    @BeforeEach
    public void createMovement() {
        this.movement = (destination, pairToMove) -> {
            int x = destination.getX();
            int y = destination.getY();

            if (x < 0 || y < 0 || x >= SIZE || y >= SIZE) {
                throw new IndexOutOfBoundsException();
            }
    
            int movementOnX = x - pairToMove.getX();
            int movementOnY = y - pairToMove.getY();
    
            if (movementOnX != 0 && movementOnY != 0 && Math.abs(movementOnX) + Math.abs(movementOnY) == 3) {
                return new Pair<Integer,Integer>(x, y);
            }
    
            return pairToMove;
        };
    }

    @Test
    public void testOutOfBoundMovement() {
        Pair<Integer, Integer> negativeMovementOnY = new Pair<>(1, NEGATIVE_POSITION);
        Pair<Integer, Integer> negativeMovementOnX = new Pair<>(NEGATIVE_POSITION, 1);
        Pair<Integer, Integer> excessiveMovementOnY = new Pair<>(1, SIZE);
        Pair<Integer, Integer> excessiveMovementOnX = new Pair<>(SIZE, 1);
        assertAll(
        () -> assertThrows(IndexOutOfBoundsException.class, () -> this.movement.move(negativeMovementOnY, this.knight)),
        () -> assertThrows(IndexOutOfBoundsException.class, () -> this.movement.move(negativeMovementOnX, this.knight)),
        () -> assertThrows(IndexOutOfBoundsException.class, () -> this.movement.move(excessiveMovementOnY, this.knight)),
        () -> assertThrows(IndexOutOfBoundsException.class, () -> this.movement.move(excessiveMovementOnX, this.knight)));
    }

    @Test
    public void testKnightMoveCorrectly() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                Pair<Integer, Integer> destination = new Pair<>(i, j);
                var newPosition = this.movement.move(destination, this.knight);
                int x = i - this.knight.getX();
                int y = j - this.knight.getY();

                if (x != 0 && y != 0 && Math.abs(x)+Math.abs(y)==3) {
                    assertEquals(new Pair<>(i, j), newPosition);
                } else {
                    assertEquals(knight, newPosition);
                }
            }
        }
    }
}

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
        this.movement = (x, y) -> {
            if (x < 0 || y < 0 || x >= SIZE || y >= SIZE) {
                throw new IndexOutOfBoundsException();
            }
    
            int movementOnX = x - knight.getX();
            int movementOnY = y - knight.getY();
    
            if (movementOnX != 0 && movementOnY != 0 && Math.abs(movementOnX) + Math.abs(movementOnY) == 3) {
                return new Pair<Integer,Integer>(x, y);
            }
    
            return knight;
        };
    }

    @Test
    public void testOutOfBoundMovement() {
        assertAll(
        () -> assertThrows(IndexOutOfBoundsException.class, () -> this.movement.move(1, NEGATIVE_POSITION)),
        () -> assertThrows(IndexOutOfBoundsException.class, () -> this.movement.move(NEGATIVE_POSITION, 1)),
        () -> assertThrows(IndexOutOfBoundsException.class, () -> this.movement.move(1, SIZE)),
        () -> assertThrows(IndexOutOfBoundsException.class, () -> this.movement.move(SIZE, 1)));
    }

    @Test
    public void testKnightMoveCorrectly() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                var newPosition = this.movement.move(i, j);
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

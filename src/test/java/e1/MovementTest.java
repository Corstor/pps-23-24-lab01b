package e1;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MovementTest {
    private static final int SIZE = 10;
    private static final int NEGATIVE_POSITION = -1;

    private Movement movement;

    @BeforeEach
    public void createMovement() {
        this.movement = new Movement() {
            @Override
            public void move(int x, int y) {
                if (x < 0 || y < 0 || x >= SIZE || y >= SIZE) {
                    throw new IndexOutOfBoundsException();
                }
            }
        };
    }

    @Test
    public void testMoveOutOfBounds() {
        assertAll(
        () -> assertThrows(IndexOutOfBoundsException.class, () -> movement.move(1, NEGATIVE_POSITION)),
        () -> assertThrows(IndexOutOfBoundsException.class, () -> movement.move(NEGATIVE_POSITION, 1)),
        () -> assertThrows(IndexOutOfBoundsException.class, () -> movement.move(1, SIZE)),
        () -> assertThrows(IndexOutOfBoundsException.class, () -> movement.move(SIZE, 1)));
    }
}

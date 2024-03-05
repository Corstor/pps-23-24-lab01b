package e1;

import static org.junit.jupiter.api.Assertions.assertAll;
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
        this.movement = new MovementKnight(SIZE);
    }

    @Test
    public void testOutOfBoundMovement() {
        assertAll(
        () -> assertThrows(IndexOutOfBoundsException.class, () -> this.movement.move(1, NEGATIVE_POSITION, this.knight)),
        () -> assertThrows(IndexOutOfBoundsException.class, () -> this.movement.move(NEGATIVE_POSITION, 1, this.knight)),
        () -> assertThrows(IndexOutOfBoundsException.class, () -> this.movement.move(1, SIZE, this.knight)),
        () -> assertThrows(IndexOutOfBoundsException.class, () -> this.movement.move(SIZE, 1, this.knight)));
    }
}

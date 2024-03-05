package e1;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RandomPositionGeneratorTest {
    private static final int SIZE = 20;

    private PositionGenerator positionGenerator;

    @BeforeEach
    public void createRandomPositionGenerator() {
        createRandomPositionGeneratorWithSize(SIZE);
    }

    private void createRandomPositionGeneratorWithSize(int size) {
        this.positionGenerator = new RandomPositionGenerator(size);
    }

    @Test
    public void testCreateRandomPosition() {
        Pair<Integer, Integer> position = this.positionGenerator.generate();
        assertAll(
            () -> assertTrue(position.getX() < SIZE),
            () -> assertTrue(position.getY() < SIZE),
            () -> assertTrue(position.getX() >= 0),
            () -> assertTrue(position.getY() >= 0)
        );
    }

    @Test
    public void testCreateMultiplePositions() {
        for (int i = 0; i < SIZE; i++) {
            createRandomPositionGeneratorWithSize(2);
            Pair<Integer, Integer> firstPosition = this.positionGenerator.generate();
            Pair<Integer, Integer> secondPosition = this.positionGenerator.generate();

            assertNotEquals(firstPosition, secondPosition);
        }
    }

    @Test
    public void testGetSizeOfPositionGenerator() {
        assertEquals(SIZE, this.positionGenerator.getSize());
    }
}

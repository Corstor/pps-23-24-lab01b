package e2;

import org.junit.jupiter.api.Test;

import e2.model.MineGenerator;
import e2.model.RandomMineGenerator;

public class RandomMineGeneratorTest {

    private static final int NUMBER_OF_MINES = 5;
    private static final int SIZE = 5;

    @Test
    public void testCreateRandomMineGenerator() {
        MineGenerator mineGenerator = new RandomMineGenerator(SIZE, NUMBER_OF_MINES);
    }
}

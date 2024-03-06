package e2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import e2.model.Cell;
import e2.model.CellImpl;
import e2.model.MineGenerator;
import e2.model.RandomMineGenerator;

public class RandomMineGeneratorTest {

    private MineGenerator mineGenerator;

    @BeforeEach
    public void testCreateRandomMineGenerator() {
        mineGenerator = new RandomMineGenerator();
    }

    @Test
    public void testGenerateCell() {
        var position = new Pair<>(0, 0);
        Cell cell = new CellImpl(position);

        assertEquals(cell, this.mineGenerator.generate(position));
    }
}

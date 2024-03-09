package e2;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import e2.model.Cell;
import e2.model.CellImpl;
import e2.model.CellsGenerator;
import e2.model.RandomCellsGenerator;

public class RandomCellsGeneratorTest {

    private static final int SIZE = 3;
    private static final int NUMBER_OF_CELLS = 5;
    private CellsGenerator cellsGenerator;
    private List<Cell> cells;

    @BeforeEach
    public void testCreateRandomCellsGenerator() {
        cellsGenerator = new RandomCellsGenerator();
        cells = cellsGenerator.generateCells(SIZE, NUMBER_OF_CELLS);
    }

    @Test
    public void testGenerateCell() {
        var position = new Pair<>(0, 0);
        Cell cell = new CellImpl(position);

        assertEquals(cell, new CellImpl(position));
    }

    @Test
    public void testGenerateRandomCells() {
        cells.forEach( (cell) ->
            assertAll(
                () -> assertTrue(cell.getPosition().getX() >= 0),
                () -> assertTrue(cell.getPosition().getY() >= 0),
                () -> assertTrue(cell.getPosition().getX() < SIZE),
                () -> assertTrue(cell.getPosition().getY() < SIZE)
            )
        );
        assertEquals(NUMBER_OF_CELLS, cells.size());
    }

    @Test
    public void testGenerateNotOnSamePosition() {
        for (int i = 0; i < NUMBER_OF_CELLS; i++) {
            for (int j = 0; j < NUMBER_OF_CELLS; j++) {
                if (i != j) {
                    assertNotEquals(cells.get(i), cells.get(j));
                }
            }
        }
    }
}

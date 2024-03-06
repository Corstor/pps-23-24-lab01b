package e2;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import e2.model.Cell;
import e2.model.CellFactory;
import e2.model.CellFactoryImpl;
import e2.model.CellImpl;
import e2.model.CellsGenerator;
import e2.model.RandomCellsGenerator;

public class RandomCellsGeneratorTest {

    private static final int SIZE = 5;
    private static final int NUMBER_OF_CELLS = 5;
    private CellsGenerator cellsGenerator;
    private CellFactory cellFactory;

    @BeforeEach
    public void testCreateRandomCellsGenerator() {
        cellFactory = new CellFactoryImpl();
        cellsGenerator = new RandomCellsGenerator(cellFactory);
    }

    @Test
    public void testGenerateCell() {
        var position = new Pair<>(0, 0);
        Cell cell = new CellImpl(position);

        assertEquals(cell, this.cellFactory.generate(position));
    }

    @Test
    public void testGenerateRandomCells() {
        List<Cell> cells = this.cellsGenerator.generateCells(SIZE, NUMBER_OF_CELLS);
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
}

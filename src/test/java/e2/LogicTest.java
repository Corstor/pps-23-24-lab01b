package e2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import e2.model.Cell;
import e2.model.CellsGenerator;
import e2.model.Grid;
import e2.model.GridImpl;
import e2.model.Logics;
import e2.model.LogicsImpl;
import e2.model.RandomCellsGenerator;

public class LogicTest {

    private static final int SIZE = 5;
    private static final int NUMBER_OF_MINES = 5;

    private Logics logic;
    private Grid grid;

    @BeforeEach
    public void createLogic() {
        CellsGenerator cellsGenerator = new RandomCellsGenerator();
        this.grid = new GridImpl(SIZE, NUMBER_OF_MINES, cellsGenerator);
        this.logic = new LogicsImpl(grid);
    }

    @Test
    public void testFreeCellIsNotAMine() {
        this.grid.getFreeCells().forEach( (cell) ->
            assertFalse(this.logic.isMine(cell.getPosition()))
        );
    }

    @Test
    public void testCheckIfMinesExists() {
        this.grid.getMines().forEach( (mine) -> 
            assertTrue(this.logic.isMine(mine.getPosition()))
        );
    }
}

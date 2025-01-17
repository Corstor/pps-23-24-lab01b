package e2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import e2.model.Cell;
import e2.model.CellsGenerator;
import e2.model.FreeCellsGenerator;
import e2.model.Grid;
import e2.model.GridImpl;
import e2.model.Logics;
import e2.model.LogicsImpl;
import e2.model.RandomCellsGenerator;

public class LogicTest {

    private static final int SIZE = 5;
    private static final int NUMBER_OF_MINES = 24;

    private Logics logic;
    private Grid grid;

    @BeforeEach
    public void createLogic() {
        CellsGenerator<Integer> cellsGenerator = new RandomCellsGenerator();
        CellsGenerator<Cell> freeCellsGenerator = new FreeCellsGenerator();
        this.grid = new GridImpl(SIZE, NUMBER_OF_MINES, cellsGenerator, freeCellsGenerator);
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

    @Test
    public void testHitOnAFreeCell() {
        int neighborMines = this.logic.hit(this.grid.getFreeCells().get(0).getPosition());
        assertEquals(this.grid.getFreeCells().get(0).getNeighborsMines(), neighborMines);
    }

    @Test
    public void testCellIsNotInitiallyFlagged() {
        assertFalse(this.logic.isFlagged(this.grid.getFreeCells().get(0).getPosition()));
    }

    @Test
    public void testSwitchFlagOfACell() {
        var position = this.grid.getFreeCells().get(0).getPosition();
        this.logic.switchFlag(position);
        assertTrue(this.logic.isFlagged(position));
        this.logic.switchFlag(position);
        assertFalse(this.logic.isFlagged(position));
    }

    @Test
    public void testCellIsInitiallyActive() {
        assertTrue(this.logic.isActive(this.grid.getFreeCells().get(0).getPosition()));
    }

    @Test
    public void testDisableCell() {
        var position = this.grid.getFreeCells().get(0).getPosition();
        this.logic.disable(position);
        assertFalse(this.logic.isActive(position));
        this.logic.disable(position);
        assertFalse(this.logic.isActive(position));
    }

    @Test
    public void testInitiallyIsNotWon() {
        assertFalse(this.logic.isWon());
    }

    @Test
    public void testWin() {
        this.grid.getFreeCells().forEach( cell -> cell.disable() );
        assertTrue(this.logic.isWon());
    }
}

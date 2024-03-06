package e2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import e2.model.Cell;
import e2.model.Logics;
import e2.model.LogicsImpl;

public class LogicTest {

    private static final int SIZE = 5;
    private static final int NUMBER_OF_MINES = 5;

    private Logics logic;
    private List<Cell> mines;
    private List<Cell> freeCells;

    @BeforeEach
    public void createLogic() {
        this.logic = new LogicsImpl(SIZE, NUMBER_OF_MINES);
        this.mines = logic.getMines();
        this.freeCells = logic.getFreeCells();
    }

    @Test
    public void testMinesArePresent() {
        assertEquals(NUMBER_OF_MINES, this.mines.size());
    }

    @Test
    public void testThereAreFreeCells() {
        int gridDimension = SIZE * SIZE;
        assertEquals(gridDimension - NUMBER_OF_MINES, this.freeCells.size());
    }

    @Test
    public void testFreeCellsAreNotMines() {
        this.freeCells.forEach((cell) ->
            assertFalse(this.logic.hasMine(cell.getPosition()))
        );
    }
}

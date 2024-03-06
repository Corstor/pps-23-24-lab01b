package e2;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import e2.model.Cell;
import e2.model.Logics;
import e2.model.LogicsImpl;

public class LogicTest {

    private static final int SIZE = 20;
    private static final int NUMBER_OF_MINES = 5;

    @Test
    public void testCreateLogic() {
        Logics logic = new LogicsImpl(SIZE, NUMBER_OF_MINES);
        List<Cell> mines = logic.getMines();
        List<Cell> freeCells = logic.getFreeCells();
    }
}

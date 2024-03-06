package e2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import e2.model.Cell;
import e2.model.CellFactory;
import e2.model.CellFactoryImpl;
import e2.model.CellImpl;
import e2.model.CellsGenerator;
import e2.model.Logics;
import e2.model.LogicsImpl;
import e2.model.RandomCellsGenerator;

public class LogicTest {

    private static final int SIZE = 5;
    private static final int NUMBER_OF_MINES = 5;

    private Logics logic;
    private List<Cell> mines;

    @BeforeEach
    public void createLogic() {
        CellFactory cellFactory = new CellFactoryImpl();
        CellsGenerator cellsGenerator = new RandomCellsGenerator(cellFactory);
        this.logic = new LogicsImpl(SIZE, NUMBER_OF_MINES, cellsGenerator);
        this.mines = logic.getMines();
    }

    @Test
    public void testMinesArePresent() {
        assertEquals(NUMBER_OF_MINES, this.mines.size());
    }

    @Test
    public void testMinesAreGenerated() {
        getAllCells().forEach((cell) -> {
            var mineIsPresent = this.logic.hasMine(cell.getPosition());
            assertTrue(this.mines.contains(cell) ? mineIsPresent : !mineIsPresent);
        });
    }

    private List<Cell> getAllCells() {
        List<Cell> cells = new LinkedList<>();

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                var position = new Pair<Integer,Integer>(i, j);
                cells.add(new CellImpl(position));
            }
        }

        return cells;
    }
}

package e2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import e2.model.Cell;
import e2.model.CellImpl;

public class CellTest {

    private final Pair<Integer, Integer> initialPosition = new Pair<>(0, 0);
    private Cell cell;

    @BeforeEach
    public void createCell() {
        this.cell = new CellImpl(this.initialPosition);
    }

    @Test
    public void testGetRightPosition() {
        assertEquals(this.initialPosition, this.cell.getPosition());
    }

    @Test
    public void testEqualsCells() {
        assertEquals(new CellImpl(initialPosition), cell);
    }
}

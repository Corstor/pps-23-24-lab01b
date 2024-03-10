package e2;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import e2.model.Cell;
import e2.model.CellImpl;

public class CellTest {

    private static final int ALL_NEIGHBORS_MINE = 8;
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

    @Test
    public void testNeighborsMinesAreInitiallyZero() {
        assertEquals(0, cell.getNeighborsMines());
    }

    @Test
    public void testIncrementNeighborsMines() {
        cell.incrementNeighborsMines();
        assertEquals(1, cell.getNeighborsMines());
    }

    @Test
    public void testNeighborsMinesNumberOutOfBounds() {
        for (int i = 0; i < ALL_NEIGHBORS_MINE; i++) {
            cell.incrementNeighborsMines();
        }
        assertAll(
            () -> assertThrows(IllegalArgumentException.class, () -> this.cell.incrementNeighborsMines())
        );
    }

    @Test
    public void testIsFlaggedInitiallyFalse() {
        assertFalse(this.cell.isFlagged());
    }

    @Test
    public void testSwitchFlag() {
        this.cell.switchFlag();
        assertTrue(this.cell.isFlagged());
        this.cell.switchFlag();
        assertFalse(this.cell.isFlagged());
    }

    @Test
    public void testIsInitiallyActive() {
        assertTrue(this.cell.isActive());
    }

    @Test
    public void testSetOff() {
        this.cell.disable();
        assertFalse(this.cell.isActive());
        this.cell.disable();
        assertFalse(this.cell.isActive());
    }
}

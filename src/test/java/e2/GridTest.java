package e2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import e2.model.Cell;
import e2.model.CellImpl;
import e2.model.CellsGenerator;
import e2.model.Grid;
import e2.model.GridImpl;
import e2.model.RandomCellsGenerator;

public class GridTest {

    private static final int SIZE = 5;
    private static final int NUMBER_OF_MINES = 5;
    private Grid grid;
    private List<Cell> mines;
    private List<Cell> freeCells;

    @BeforeEach
    public void testCreateGrid() {
        CellsGenerator cellsGenerator = new RandomCellsGenerator();
        grid = new GridImpl(SIZE, NUMBER_OF_MINES, cellsGenerator);
        this.mines = grid.getMines();
        this.freeCells = grid.getFreeCells();
    }

    @Test
    public void testMinesArePresent() {
        assertEquals(NUMBER_OF_MINES, this.mines.size());
    }

    @Test
    public void testGetAllFreeCells() {
        int totalNumberOfCells = SIZE * SIZE;
        assertEquals(totalNumberOfCells - NUMBER_OF_MINES, freeCells.size());
    }

    @Test
    public void testFreeCellsAreNotMines() {
        this.freeCells.forEach((cell) -> {
            assertFalse(this.grid.cellIsMine(cell));
        });
    }

    @Test
    public void testFreeCellsHaveNeighborsMines() {
        this.freeCells.forEach((cell) -> {
            int neighborsMines = getNeighborsMines(cell);
            assertEquals(neighborsMines, cell.getNeighborsMines());
        });
    }

    private int getNeighborsMines(Cell mine) {
        var position = mine.getPosition();

        var nearPositions = getNearPositions(position);

        int mines = 0;

        for (var neighbor : nearPositions) {
            if (this.grid.cellIsMine(new CellImpl(neighbor))) {
                mines++;
            }
        }

        return mines;
    }

    private List<Pair<Integer, Integer>> getNearPositions(Pair<Integer, Integer> position) {
        List<Pair<Integer, Integer>> positions = new LinkedList<>();
        int adiacentPositionsOnSingleAxis = 3;
        for (int i = 0; i < adiacentPositionsOnSingleAxis; i++) {
            for (int j = 0; j < adiacentPositionsOnSingleAxis; j++) {
                var neighborPosition = getNeighborPositionFromIndexes(position, i, j);
                if (checkPositionIsNotOutOfBound(neighborPosition) && !position.equals(neighborPosition)) {
                    positions.add(neighborPosition);
                }
            }
        }
        return positions;
    }

    private Pair<Integer, Integer> getNeighborPositionFromIndexes(Pair<Integer, Integer> position, int i, int j) {
        return new Pair<>(position.getX() + getOffsetFromIndex(i), position.getY() + getOffsetFromIndex(j));
    }

    private Integer getOffsetFromIndex(int index) {
        return index - 1;
    }

    private boolean checkPositionIsNotOutOfBound(Pair<Integer, Integer> neighborPosition) {
        return neighborPosition.getX() > 0 
            && neighborPosition.getY() > 0 
            && neighborPosition.getX() < SIZE 
            && neighborPosition.getY() < SIZE;
    }
}

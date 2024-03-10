package e2;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import e2.model.Cell;
import e2.model.CellImpl;
import e2.model.CellsGenerator;
import e2.model.FreeCellsGenerator;
import e2.model.Grid;
import e2.model.GridImpl;
import e2.model.NeighborMinesSetter;
import e2.model.NeighborMinesSetterImpl;
import e2.model.RandomCellsGenerator;

public class GridTest {

    private static final int SIZE = 5;
    private static final int NUMBER_OF_MINES = 23;
    private Grid grid;
    private List<Cell> mines;
    private List<Cell> freeCellsWithNeighborMines;
    private CellsGenerator<Cell> freeCellsGenerator;
    private List<Cell> freeCells;

    @BeforeEach
    public void testCreateGrid() {
        CellsGenerator<Integer> cellsGenerator = new RandomCellsGenerator();
        this.freeCellsGenerator = new FreeCellsGenerator();
        this.grid = new GridImpl(SIZE, NUMBER_OF_MINES, cellsGenerator, this.freeCellsGenerator);
        this.mines = grid.getMines();
        this.freeCellsWithNeighborMines = grid.getFreeCells();
        freeCells = freeCellsGenerator.generateCells(SIZE, (cell) -> !this.grid.cellIsMine(cell));
    }

    @Test
    public void testMinesArePresent() {
        assertEquals(NUMBER_OF_MINES, this.mines.size());
    }

    @Test
    public void testGetAllFreeCells() {
        int totalNumberOfCells = SIZE * SIZE;
        assertEquals(totalNumberOfCells - NUMBER_OF_MINES, freeCellsWithNeighborMines.size());
    }

    @Test
    public void testFreeCellsAreNotMines() {
        this.freeCellsWithNeighborMines.forEach((cell) -> {
            assertFalse(this.grid.cellIsMine(cell));
        });
    }

    @Test
    public void testFreeCellsHaveNeighborsMines() {
        this.freeCellsWithNeighborMines.forEach((cell) -> {
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
        return neighborPosition.getX() >= 0 
            && neighborPosition.getY() >= 0 
            && neighborPosition.getX() < SIZE 
            && neighborPosition.getY() < SIZE;
    }

    @Test
    public void testGenerateFreeCells() {
        assertEquals(this.freeCellsWithNeighborMines.size(), freeCells.size());
    }

    @Test
    public void testSetFreeCellsNeighborMines() {
        NeighborMinesSetter neighborMinesSetter = new NeighborMinesSetterImpl(SIZE);
        List<Cell> cells = neighborMinesSetter.set(freeCells, (mine) -> this.grid.cellIsMine(mine));
        assertEquals(this.freeCellsWithNeighborMines, cells);
    }

    @Test
    public void testGetCells() {
        var firstMine = this.mines.get(0);
        var firstFreeCell = this.freeCellsWithNeighborMines.get(0);
        var secondMine = this.mines.get(1);
        var secondFreeCell = this.freeCellsWithNeighborMines.get(1);
        var firstMinePosition = firstMine.getPosition();

        assertAll(
            () -> assertEquals(firstMine, this.grid.getCell(firstMine.getPosition())),
            () -> assertEquals(secondMine, this.grid.getCell(secondMine.getPosition())),
            () -> assertEquals(firstFreeCell, this.grid.getCell(firstFreeCell.getPosition())),
            () -> assertEquals(secondFreeCell, this.grid.getCell(secondFreeCell.getPosition())),
            () -> assertEquals(this.grid.getCell(firstMinePosition), this.grid.getCell(firstMine.getPosition()))
        );
    }

    @Test
    public void testGetCellsThatDoNotExist() {
        var negativeX = new Pair<>(-1, 0);
        var negativeY = new Pair<>(0, -1);
        var overflowX = new Pair<>(SIZE, 0);
        var overflowY = new Pair<>(0, SIZE);

        assertAll(
            () -> assertThrows(IllegalArgumentException.class, () -> this.grid.getCell(negativeX)),
            () -> assertThrows(IllegalArgumentException.class, () -> this.grid.getCell(negativeY)),
            () -> assertThrows(IllegalArgumentException.class, () -> this.grid.getCell(overflowX)),
            () -> assertThrows(IllegalArgumentException.class, () -> this.grid.getCell(overflowY))
        );
    }
}

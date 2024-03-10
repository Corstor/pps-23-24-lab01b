package e2.model;

import java.util.List;
import java.util.function.Predicate;


public class GridImpl implements Grid {

    private final List<Cell> mines;
    private final List<Cell> freeCells;

    public GridImpl(int size, int numberOfMines, CellsGenerator<Integer> cellsGenerator, CellsGenerator<Cell> freeCellsGenerator) {
        this.mines = cellsGenerator.generateCells(size, (index) -> index < numberOfMines);
        Predicate<Cell> filter = (cell) -> this.cellIsMine(cell);
        var freeCellsWithoutNeighborMines = freeCellsGenerator.generateCells(size, filter.negate());
        NeighborMinesSetter neighborMinesSetter = new NeighborMinesSetterImpl(size);
        this.freeCells = neighborMinesSetter.set(freeCellsWithoutNeighborMines, filter);
    }

    @Override
    public boolean cellIsMine(Cell newCell) {
        return this.mines.contains(newCell);
    }

    @Override
    public List<Cell> getMines() {
        return List.copyOf(this.mines);
    }

    @Override
    public List<Cell> getFreeCells() {
        return List.copyOf(this.freeCells);
    }
}

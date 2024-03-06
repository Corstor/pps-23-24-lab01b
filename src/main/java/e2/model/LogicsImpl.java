package e2.model;

import java.util.LinkedList;
import java.util.List;

public class LogicsImpl implements Logics {

    private final List<Cell> mines;
    private final List<Cell> freeCells;

    public LogicsImpl(int size, int numberOfMines) {
        int gridSize = size * size;
        this.freeCells = new LinkedList<>();
        for (int i = 0; i < gridSize - numberOfMines; i++) {
            this.freeCells.add(new CellImpl());
        }
        this.mines = new LinkedList<>();
        for (int i = 0; i < numberOfMines; i++) {
            Cell mine = new CellImpl();
            this.mines.add(mine);
        }
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

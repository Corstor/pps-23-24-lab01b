package e2.model;

import java.util.LinkedList;
import java.util.List;

public class LogicsImpl implements Logics {

    private final List<Cell> mines;

    public LogicsImpl(int size, int numberOfMines) {
        this.mines = new LinkedList<>();
        for (int i = 0; i < numberOfMines; i++) {
            this.mines.add(new CellImpl());
        }
    }

    @Override
    public List<Cell> getMines() {
        return List.copyOf(this.mines);
    }

    @Override
    public List<Cell> getFreeCells() {
        return new LinkedList<>();
    }
}

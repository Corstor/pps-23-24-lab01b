package e2.model;

import java.util.LinkedList;
import java.util.List;

import e2.Pair;

public class LogicsImpl implements Logics {

    private final List<Cell> mines;

    public LogicsImpl(int size, int numberOfMines) {
        this.mines = new LinkedList<>();
        for (int i = 0; i < numberOfMines; i++) {
            Cell mine = new CellImpl(new Pair<>(i, i));
            this.mines.add(mine);
        }
    }

    @Override
    public List<Cell> getMines() {
        return List.copyOf(this.mines);
    }

    @Override
    public boolean hasMine(Pair<Integer, Integer> position) {
        var cellToCheck = new CellImpl(position);
        return this.mines.contains(cellToCheck);
    }
}

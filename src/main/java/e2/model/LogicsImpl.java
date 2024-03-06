package e2.model;

import java.util.LinkedList;
import java.util.List;

public class LogicsImpl implements Logics {

    public LogicsImpl(int size, int numberOfMines) {
    }

    @Override
    public List<Cell> getMines() {
        return new LinkedList<>();
    }

    @Override
    public List<Cell> getFreeCells() {
        return new LinkedList<>();
    }

}

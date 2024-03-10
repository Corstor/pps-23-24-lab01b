package e2.model;

import e2.Pair;

public class LogicsImpl implements Logics {

    private final Grid grid;

    public LogicsImpl(Grid grid) {
        this.grid = grid;
    }

    @Override
    public boolean isMine(Pair<Integer, Integer> pair) {
        return this.grid.cellIsMine(new CellImpl(pair));
    }

    @Override
    public int hit(Cell cell) {
        return this.grid.getCell(cell.getPosition()).getNeighborsMines();
    }

}

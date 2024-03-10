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
    public int hit(Pair<Integer, Integer> position) {
        return this.grid.getCell(position).getNeighborsMines();
    }

    @Override
    public boolean isFlagged(Pair<Integer, Integer> position) {
        return this.grid.getCell(position).isFlagged();
    }

    @Override
    public void switchFlag(Pair<Integer, Integer> position) {
        this.grid.getCell(position).switchFlag();
    }

    @Override
    public boolean isActive(Pair<Integer, Integer> position) {
        return this.grid.getCell(position).isActive();
    }

    @Override
    public void disable(Pair<Integer, Integer> position) {
        this.grid.getCell(position).disable();
    }

    @Override
    public boolean isWon() {
        return this.grid.getFreeCells().stream().allMatch(cell -> !cell.isActive());
    }

}

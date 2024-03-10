package e2.model;

import e2.Pair;

public class CellImpl implements Cell {

    private static final int ALL_NEIGHBORS_MINE = 8;
    private final Pair<Integer, Integer> position;
    private int neighborsMines;
    private boolean flag;
    private boolean active;

    public CellImpl(Pair<Integer, Integer> position) {
        this.position = position;
        this.active = true;
	}

	@Override
    public Pair<Integer, Integer> getPosition() {
        return position;
    }

    @Override
    public int getNeighborsMines() {
        return neighborsMines;
    }

    @Override
    public void incrementNeighborsMines() {
        if (this.neighborsMines == ALL_NEIGHBORS_MINE) {
            throw new IllegalArgumentException("A cell cannot have more than 8 neighbor mines. ");
        }
        this.neighborsMines++;
    }

    @Override
    public boolean isFlagged() {
        return flag;
    }

    @Override
    public void switchFlag() {
        this.flag = !this.flag;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((position == null) ? 0 : position.hashCode());
        result = prime * result + neighborsMines;
        result = prime * result + (flag ? 1231 : 1237);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CellImpl other = (CellImpl) obj;
        if (position == null) {
            if (other.position != null)
                return false;
        } else if (!position.equals(other.position))
            return false;
        if (neighborsMines != other.neighborsMines)
            return false;
        if (flag != other.flag)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CellImpl [position=" + position + ", neighborsMines=" + neighborsMines + ", flag=" + flag + "]";
    }

    @Override
    public boolean isActive() {
        return this.active;
    }

    @Override
    public void disable() {
        this.active = false;
    }
}

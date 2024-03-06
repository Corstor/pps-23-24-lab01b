package e2.model;

import e2.Pair;

public class CellImpl implements Cell {

    private final Pair<Integer, Integer> position;

    public CellImpl(Pair<Integer, Integer> position) {
        this.position = position;
	}

	@Override
    public Pair<Integer, Integer> getPosition() {
        return position;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((position == null) ? 0 : position.hashCode());
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
        return true;
    }

    
}

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

}

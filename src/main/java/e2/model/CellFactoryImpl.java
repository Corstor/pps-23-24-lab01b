package e2.model;

import e2.Pair;

public class CellFactoryImpl implements CellFactory {

    @Override
    public Cell generate(Pair<Integer, Integer> position) {
        return new CellImpl(position);
    }
}

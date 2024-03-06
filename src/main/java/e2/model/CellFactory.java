package e2.model;

import e2.Pair;

public interface CellFactory {

    Cell generate(Pair<Integer, Integer> position);

}

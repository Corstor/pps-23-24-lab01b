package e2.model;

import e2.Pair;

public interface Cell {

    Pair<Integer, Integer> getPosition();

    int getNeighborsMines();

    void incrementNeighborsMines();

    boolean isFlagged();

    void switchFlag();

    boolean isActive();

    void disable();

}

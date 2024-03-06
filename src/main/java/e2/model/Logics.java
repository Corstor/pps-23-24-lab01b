package e2.model;

import java.util.List;

import e2.Pair;

public interface Logics {

    List<Cell> getMines();

    List<Cell> getFreeCells();

    boolean hasMine(Pair<Integer, Integer> position);
    
}
package e2.model;

import java.util.List;

import e2.Pair;

public interface Grid {

    List<Cell> getMines();

    List<Cell> getFreeCells();

    boolean cellIsMine(Cell cell);

	Cell getCell(Pair<Integer, Integer> position);

}

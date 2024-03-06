package e2.model;

import java.util.List;

import e2.Pair;

public interface CellsGenerator {

    Object generate(Pair<Integer, Integer> position);

	List<Cell> generateCells(int size, int numberOfCells);


}

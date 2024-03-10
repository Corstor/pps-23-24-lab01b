package e2.model;

import java.util.List;
import java.util.function.Predicate;

public interface CellsGenerator<E> {

	List<Cell> generateCells(int size, Predicate<E> filter);

}

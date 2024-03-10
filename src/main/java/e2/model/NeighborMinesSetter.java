package e2.model;

import java.util.List;
import java.util.function.Predicate;

public interface NeighborMinesSetter {

    List<Cell> set(List<Cell> freeCells, Predicate<Cell> filter);

}

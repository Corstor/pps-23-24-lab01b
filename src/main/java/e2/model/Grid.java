package e2.model;

import java.util.List;

public interface Grid {

    List<Cell> getMines();

    List<Cell> getFreeCells();

    boolean cellIsMine(Cell cell);

}

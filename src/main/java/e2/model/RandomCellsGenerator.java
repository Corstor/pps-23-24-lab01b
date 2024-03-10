package e2.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

import e2.Pair;

public class RandomCellsGenerator implements CellsGenerator<Integer> {

    private final Random random;
    private final List<Cell> cells = new LinkedList<>();

    public RandomCellsGenerator() {
        this.random = new Random();
    }

    @Override
    public List<Cell> generateCells(int size, Predicate<Integer> filter) {
        for (int i = 0; filter.test(Integer.valueOf(i)); i++) {
            this.generateCell(size);
        }

        return this.cells;
    }

    private void generateCell(int size) {
        var randomPosition = new Pair<>(this.random.nextInt(size), this.random.nextInt(size));
        var cell = new CellImpl(randomPosition);
        if (this.cells.contains(cell)) {
            this.generateCell(size);
        } else {
            this.cells.add(cell);
        }
    }

}

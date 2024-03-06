package e2.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import e2.Pair;

public class RandomCellsGenerator implements CellsGenerator {

    private final Random random;

    public RandomCellsGenerator() {
        this.random = new Random();
    }

    @Override
    public Cell generate(Pair<Integer, Integer> position) {
        return new CellImpl(position);
    }

    @Override
    public List<Cell> generateCells(int size, int numberOfCells) {
        List<Cell> cells = new LinkedList<>();

        for (int i = 0; i < numberOfCells; i++) {
            var randomPosition = new Pair<>(random.nextInt(size), random.nextInt(size));
            cells.add(generate(randomPosition));
        }

        return cells;
    }

}

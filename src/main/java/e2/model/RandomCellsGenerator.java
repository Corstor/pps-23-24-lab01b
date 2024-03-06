package e2.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import e2.Pair;

public class RandomCellsGenerator implements CellsGenerator {

    private final Random random;
    private final CellFactory cellFactory;
    private final List<Cell> cells = new LinkedList<>();

    public RandomCellsGenerator(CellFactory cellFactory) {
        this.random = new Random();
        this.cellFactory = new CellFactoryImpl();
    }

    @Override
    public List<Cell> generateCells(int size, int numberOfCells) {
        for (int i = 0; i < numberOfCells; i++) {
            this.generateCell(size);
        }

        return cells;
    }

    private void generateCell(int size) {
        var randomPosition = new Pair<>(random.nextInt(size), random.nextInt(size));
        var cell = this.cellFactory.generate(randomPosition);
        if (cells.contains(cell)) {
            generateCell(size);
        } else {
            cells.add(cell);
        }
    }

}

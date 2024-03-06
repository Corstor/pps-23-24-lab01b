package e2.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import e2.Pair;

public class RandomCellsGenerator implements CellsGenerator {

    private final Random random;
    private final CellFactory cellFactory;

    public RandomCellsGenerator(CellFactory cellFactory) {
        this.random = new Random();
        this.cellFactory = new CellFactoryImpl();
    }

    @Override
    public List<Cell> generateCells(int size, int numberOfCells) {
        List<Cell> cells = new LinkedList<>();

        for (int i = 0; i < numberOfCells; i++) {
            var randomPosition = new Pair<>(random.nextInt(size), random.nextInt(size));
            cells.add(this.cellFactory.generate(randomPosition));
        }

        return cells;
    }

}

package e2.model;

import e2.Pair;

public class RandomMineGenerator implements MineGenerator {


    public RandomMineGenerator() {
    }

    @Override
    public Cell generate(Pair<Integer, Integer> position) {
        return new CellImpl(position);
    }

}

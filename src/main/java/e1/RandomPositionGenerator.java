package e1;

import java.util.Random;

public class RandomPositionGenerator implements PositionGenerator {

    private final Random random = new Random();
    private final int size;
    private Pair<Integer, Integer> lastPair;

    public RandomPositionGenerator(int size) {
        this.size = size;
    }

    @Override
    public Pair<Integer, Integer> generate() {
        this.lastPair = getNewPair();
        return this.lastPair;
    }

    private Pair<Integer, Integer> getNewPair() {
        var pair = getRandomPair();
        return pairIsEqualToLastPair(pair) ? generate() : pair;
    }

    private Pair<Integer, Integer> getRandomPair() {
        return new Pair<>(this.random.nextInt(this.size), this.random.nextInt(this.size));
    }

    private boolean pairIsEqualToLastPair(Pair<Integer, Integer> pair) {
        return this.lastPair!=null && this.lastPair.equals(pair);
    }

    @Override
    public int getSize() {
        return this.size;
    }

}

package e2.model;

import e2.Pair;

public interface Logics {

    boolean isMine(Pair<Integer, Integer> position);

    int hit(Pair<Integer, Integer> position);

    boolean isFlagged(Pair<Integer, Integer> position);

    void switchFlag(Pair<Integer, Integer> position);

    boolean isActive(Pair<Integer, Integer> position);

	void disable(Pair<Integer, Integer> position);

    boolean isWon();
    
}

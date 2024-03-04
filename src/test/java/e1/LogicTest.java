package e1;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.function.BiFunction;

public class LogicTest {

  private static final int NEGATIVE_POSITION = -1;
  private static final int MOVE_BY_ONE = 1;
  private static final int MOVE_BY_TWO = 2;
  private static final int SIZE = 10;

  private Logics logic;
  private Pair<Integer, Integer> knight = new Pair<Integer, Integer>(4, 4);
  private Pair<Integer, Integer> pawn = new Pair<Integer, Integer>(5, 5);

  @BeforeEach
  public void createLogic() {
    logic = new LogicsImpl(SIZE, knight, pawn);
  }

  @Test
  public void testKnightIsNotOnNegativeAxis() {
    assertAll(
        () -> assertFalse(logic.hasKnight(NEGATIVE_POSITION, knight.getY())),
        () -> assertFalse(logic.hasKnight(knight.getX(), NEGATIVE_POSITION)));
  }

  @Test
  public void testPawnIsNotOnNegativeAxis() {
    assertAll(
        () -> assertFalse(logic.hasPawn(NEGATIVE_POSITION, pawn.getY())),
        () -> assertFalse(logic.hasPawn(pawn.getX(), NEGATIVE_POSITION)));
  }

  @Test
  public void testOutOfBoundHit() {
    assertAll(
        () -> assertThrows(IndexOutOfBoundsException.class, () -> logic.hit(1, NEGATIVE_POSITION)),
        () -> assertThrows(IndexOutOfBoundsException.class, () -> logic.hit(NEGATIVE_POSITION, 1)),
        () -> assertThrows(IndexOutOfBoundsException.class, () -> logic.hit(1, SIZE)),
        () -> assertThrows(IndexOutOfBoundsException.class, () -> logic.hit(SIZE, 1)));
  }

  @Test
  public void testKnightIsOnTheRightPosition() {
    getPairIsPresent(this.knight, (x, y) -> this.logic.hasKnight(x, y));
  }

  @Test
  public void testPawnIsOnTheRightPosition() {
    getPairIsPresent(this.pawn, (x, y) -> this.logic.hasPawn(x, y));
  }

  private void getPairIsPresent(Pair<Integer, Integer> pairToFind, BiFunction<Integer, Integer, Boolean> filter) {
    boolean isPresent = false;

    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        if (filter.apply(i, j)) {
          if (pairToFind.getX().equals(i) && pairToFind.getY().equals(j)) {
            isPresent = true;
          } else {
            fail();
          }
        }
      }
    }

    assertTrue(isPresent);
  }

  @Test
  public void testKnightAndPawnOnSamePosition() {
    assertAll(
        () -> assertFalse(logic.hasKnight(this.pawn.getX(), this.pawn.getY())),
        () -> assertFalse(logic.hasPawn(this.knight.getX(), this.knight.getY())));
  }

  @Test
  public void testKnightMoveCorrectly() {
    var firstMove = doRightMove(MOVE_BY_ONE, MOVE_BY_TWO, true, true);
    var secondMove = doRightMove(MOVE_BY_ONE, MOVE_BY_TWO, true, false);
    var thirdMove = doRightMove(MOVE_BY_ONE, MOVE_BY_TWO, false, true);
    var fourthMove = doRightMove(MOVE_BY_ONE, MOVE_BY_TWO, false, false);
    var fifthMove = doRightMove(MOVE_BY_TWO, MOVE_BY_ONE, true, true);
    var sixthMove = doRightMove(MOVE_BY_TWO, MOVE_BY_ONE, true, false);
    var seventhMove = doRightMove(MOVE_BY_TWO, MOVE_BY_ONE, false, true);
    var eighthMove = doRightMove(MOVE_BY_TWO, MOVE_BY_ONE, false, false);

    //TODO() Generalize this making a for that see every move, then assertTrue(logic.hasKnight(x,y)) when the knight has moved, assertFalse() when it should not move
    assertAll(
      () -> assertFalse(firstMove),
      () -> assertFalse(secondMove),
      () -> assertFalse(thirdMove),
      () -> assertFalse(fourthMove),
      () -> assertFalse(fifthMove),
      () -> assertFalse(sixthMove),
      () -> assertFalse(seventhMove),
      () -> assertFalse(eighthMove)
    );
  }

  private boolean doRightMove(int x, int y, boolean xPositive, boolean yPositive) {
    logic.hit(this.knight.getX(), this.knight.getY());

    var xPosition = this.knight.getX() + x;
    var yPosition = this.knight.getX() + y;
    boolean hit = logic.hit(xPosition, yPosition);

    assertTrue(logic.hasKnight(xPosition, yPosition));

    return hit;
  }
}

package e1;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.function.BiFunction;

public class LogicTest {

  private static final int NEGATIVE_POSITION = -1;
  private static final int SIZE = 10;

  private Logics logic;
  private Pair<Integer, Integer> knight = new Pair<Integer, Integer>(4, 4);
  private Pair<Integer, Integer> pawn = new Pair<Integer, Integer>(5, 5);

  @BeforeEach
  public void createLogic() {
    logic = new LogicsImpl(knight, pawn, new MovementImpl(SIZE));
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
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        this.logic.hit(i, j);
        int x = i - this.knight.getX();
        int y = j - this.knight.getY();

        if (x != 0 && y != 0) {
          if (Math.abs(x) + Math.abs(y) == 3) {
            assertTrue(this.logic.hasKnight(i, j));
            this.logic.hit(this.knight.getX(), this.knight.getY());
          } else {
            assertFalse(this.logic.hasKnight(i, j));
          }
        }
      }
    }
  }

  @Test
  public void testKnightCatchesPawn() {
    this.logic.hit(this.knight.getX()+2, this.knight.getY()-1);
    assertTrue(this.logic.hit(this.pawn.getX(), this.pawn.getY()));
  }
}

package e1;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MovementTest {

    private Movement movement;

    @BeforeEach
    public void createMovement() {
        this.movement = new Movement() {
            @Override
            public void move(int x, int y) {
                
            }
        };
    }
}

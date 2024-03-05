package e1;

import org.junit.jupiter.api.BeforeEach;

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

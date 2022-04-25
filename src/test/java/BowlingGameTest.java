import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BowlingGameTest {

    BowlingGame bowlingGame;

    @BeforeEach
    public void setup() {
        bowlingGame = new BowlingGame();
    }

    @Test
    void allMiss() {
        for (int frameNumber = 0; frameNumber < 10; frameNumber++) {
            bowlingGame.addFrame(0, 0);
        }
        Assertions.assertEquals(0, bowlingGame.getGameScore());
    }

    @Test
    void allOnes() {
        for (int frameNumber = 0; frameNumber < 10; frameNumber++) {
            bowlingGame.addFrame(1, 1);
        }
        Assertions.assertEquals(20, bowlingGame.getGameScore());
    }

}
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BowlingGameTest {

    BowlingGame bowlingGame;

    @BeforeEach
    public void setup() {
        bowlingGame = new BowlingGame();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9})
    void openFramesAllSameThrows(int throwValue) {
        for (int frameNumber = 0; frameNumber < 10; frameNumber++) {
            bowlingGame.addFrame(throwValue, throwValue);
        }
        Assertions.assertEquals(20 * throwValue, bowlingGame.getGameScore());
    }

}
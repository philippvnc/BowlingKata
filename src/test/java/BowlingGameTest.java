import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BowlingGameTest {

    BowlingGame bowlingGame;

    @BeforeEach
    public void setup() {
        bowlingGame = new BowlingGame();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4})
    public void openFramesAllSameThrows(int throwValue) {
        for (int frameNumber = 0; frameNumber < 10; frameNumber++) {
            bowlingGame.addFrame(throwValue, throwValue);
        }
        Assertions.assertEquals(20 * throwValue, bowlingGame.getGameScore());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4})
    public void spareAndOpenFramesAllSameThrows(int throwValue) {
        bowlingGame.addFrame(2, 8);
        for (int frameNumber = 0; frameNumber < 9; frameNumber++) {
            bowlingGame.addFrame(throwValue, throwValue);
        }
        Assertions.assertEquals(20 * throwValue + 10, bowlingGame.getGameScore());
    }

}
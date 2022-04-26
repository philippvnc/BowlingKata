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

    @Test
    void shouldAddFrames() {
        Assertions.assertEquals(0, bowlingGame.getFrameListSize());
        bowlingGame.addFrame(0, 0);
        Assertions.assertEquals(1, bowlingGame.getFrameListSize());
        bowlingGame.addFrame(10, 0);
        Assertions.assertEquals(2, bowlingGame.getFrameListSize());
        bowlingGame.addFrame(2, 8);
        Assertions.assertEquals(3, bowlingGame.getFrameListSize());
    }

    @Test
    void shouldDetectLatestAddedFrameSpare() {
        Assertions.assertFalse(bowlingGame.isLatestAddedFrameSpare());
        bowlingGame.addFrame(8, 2);
        Assertions.assertTrue(bowlingGame.isLatestAddedFrameSpare());
        bowlingGame.addFrame(1, 0);
        Assertions.assertFalse(bowlingGame.isLatestAddedFrameSpare());
        bowlingGame.addFrame(4, 6);
        Assertions.assertTrue(bowlingGame.isLatestAddedFrameSpare());
        bowlingGame.addFrame(10, 0);
        Assertions.assertFalse(bowlingGame.isLatestAddedFrameSpare());
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
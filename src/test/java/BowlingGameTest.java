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
        Assertions.assertTrue(bowlingGame.isLatestAddedFrameSpare());
    }

    @Test
    void shouldDetectSecondLatestAddedFrameStrike() {
        Assertions.assertFalse(bowlingGame.isSecondLatestAddedFrameStrike());
        bowlingGame.addFrame(8, 2);
        Assertions.assertFalse(bowlingGame.isSecondLatestAddedFrameStrike());
        bowlingGame.addFrame(10, 0);
        Assertions.assertFalse(bowlingGame.isSecondLatestAddedFrameStrike());
        bowlingGame.addFrame(4, 6);
        Assertions.assertTrue(bowlingGame.isSecondLatestAddedFrameStrike());
        bowlingGame.addFrame(10, 0);
        Assertions.assertFalse(bowlingGame.isSecondLatestAddedFrameStrike());
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
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9})
    public void openFramesAllSameThrowsThenMiss(int throwValue) {
        for (int frameNumber = 0; frameNumber < 10; frameNumber++) {
            bowlingGame.addFrame(throwValue, 0);
        }
        Assertions.assertEquals(10 * throwValue, bowlingGame.getGameScore());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4})
    public void spareAndOpenFramesAllSameThrows(int throwValue) {
        bowlingGame.addFrame(2, 8);
        for (int frameNumber = 0; frameNumber < 9; frameNumber++) {
            bowlingGame.addFrame(throwValue, throwValue);
        }
        Assertions.assertEquals(10 + 19 * throwValue, bowlingGame.getGameScore());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9})
    public void spareFramesAllSameThrows(int throwValue) {
        for (int frameNumber = 0; frameNumber < 10; frameNumber++) {
            bowlingGame.addFrame(throwValue, 10 - throwValue);
        }
        bowlingGame.addFrame(throwValue, 0);
        Assertions.assertEquals(100 + 10 * throwValue, bowlingGame.getGameScore());
    }

    @Test
    public void strikeFrames() {
        for (int frameNumber = 0; frameNumber < 10; frameNumber++) {
            bowlingGame.addFrame(10, 0);
        }
        bowlingGame.addFrame(10, 0);
        bowlingGame.addFrame(10, 0);
        Assertions.assertEquals(300, bowlingGame.getGameScore());
    }

    @Test
    public void missFrames() {
        for (int frameNumber = 0; frameNumber < 10; frameNumber++) {
            bowlingGame.addFrame(0, 0);
        }
        Assertions.assertEquals(0, bowlingGame.getGameScore());
    }

    @Test
    public void mixedFramesOne() {
        bowlingGame.addFrame(0, 0);
        bowlingGame.addFrame(1, 2);
        bowlingGame.addFrame(3, 4);
        bowlingGame.addFrame(10, 0);
        bowlingGame.addFrame(5, 5);
        bowlingGame.addFrame(7, 2);
        bowlingGame.addFrame(0, 0);
        bowlingGame.addFrame(10, 0);
        bowlingGame.addFrame(10, 0);
        bowlingGame.addFrame(8, 2);
        bowlingGame.addFrame(10, 0);
        Assertions.assertEquals(124, bowlingGame.getGameScore());
    }

    @Test
    public void mixedFramesTwo() {
        bowlingGame.addFrame(10, 0);
        bowlingGame.addFrame(1, 2);
        bowlingGame.addFrame(6, 4);
        bowlingGame.addFrame(10, 0);
        bowlingGame.addFrame(5, 2);
        bowlingGame.addFrame(10, 0);
        bowlingGame.addFrame(0, 0);
        bowlingGame.addFrame(8, 2);
        bowlingGame.addFrame(10, 0);
        bowlingGame.addFrame(8, 1);
        Assertions.assertEquals(118, bowlingGame.getGameScore());
    }

}
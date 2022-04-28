package bowling;

import bowling.BowlingGame;
import bowling.model.Frame;
import bowling.model.OpenFrame;
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
    void shouldGetFrameByIndex() {
        Frame testFrame1 = new OpenFrame(0 ,0);
        bowlingGame.addFrame(testFrame1);
        Frame testFrame2 = new OpenFrame(0 ,0);
        bowlingGame.addFrame(testFrame2);
        Assertions.assertEquals(bowlingGame.getFrameByIndex(0), testFrame1);
        Assertions.assertEquals(bowlingGame.getFrameByIndex(1), testFrame2);
        Assertions.assertNotEquals(bowlingGame.getFrameByIndex(0), testFrame2);
        Assertions.assertNotEquals(bowlingGame.getFrameByIndex(1), testFrame1);
    }

    @Test
    void shouldGetNewOpenFrameForIndexOutOfBounds() {
        Frame testFrame1 = new OpenFrame(0 ,0);
        bowlingGame.addFrame(testFrame1);
        Frame testFrame2 = new OpenFrame(0 ,0);
        bowlingGame.addFrame(testFrame2);
        Assertions.assertTrue(bowlingGame.getFrameByIndex(-1) instanceof OpenFrame);
        Assertions.assertTrue(bowlingGame.getFrameByIndex(2) instanceof OpenFrame);
        Assertions.assertTrue(bowlingGame.getFrameByIndex(10) instanceof OpenFrame);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4})
    public void openFramesAllSameThrows(int throwValue) {
        for (int frameIndex = 0; frameIndex < 10; frameIndex++) {
            bowlingGame.addFrame(throwValue, throwValue);
        }
        Assertions.assertEquals(20 * throwValue, bowlingGame.getGameScore());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9})
    public void openFramesAllSameThrowsThenMiss(int throwValue) {
        for (int frameIndex = 0; frameIndex < 10; frameIndex++) {
            bowlingGame.addFrame(throwValue, 0);
        }
        Assertions.assertEquals(10 * throwValue, bowlingGame.getGameScore());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4})
    public void spareAndOpenFramesAllSameThrows(int throwValue) {
        bowlingGame.addFrame(2, 8);
        for (int frameIndex = 0; frameIndex < 9; frameIndex++) {
            bowlingGame.addFrame(throwValue, throwValue);
        }
        Assertions.assertEquals(10 + 19 * throwValue, bowlingGame.getGameScore());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9})
    public void spareFramesAllSameThrows(int throwValue) {
        for (int frameIndex = 0; frameIndex < 10; frameIndex++) {
            bowlingGame.addFrame(throwValue, 10 - throwValue);
        }
        bowlingGame.addFrame(throwValue, 0);
        Assertions.assertEquals(100 + 10 * throwValue, bowlingGame.getGameScore());
    }

    @Test
    public void strikeFrames() {
        for (int frameIndex = 0; frameIndex < 12; frameIndex++) {
            bowlingGame.addFrame(10, 0);
        }
        Assertions.assertEquals(300, bowlingGame.getGameScore());
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

    @Test
    public void shouldParseAndCalculateStrikeFrames() {
        bowlingGame = new BowlingGame("X X X X X X X X X X X X");
        Assertions.assertEquals(300, bowlingGame.getGameScore());
    }

    @Test
    public void shouldParseAndCalculateSpareFrames() {
        bowlingGame = new BowlingGame("9- 9- 9- 9- 9- 9- 9- 9- 9- 9-");
        Assertions.assertEquals(90, bowlingGame.getGameScore());
    }

    @Test
    public void shouldParseAndCalculateOpenFrames() {
        bowlingGame = new BowlingGame("5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/5");
        Assertions.assertEquals(150, bowlingGame.getGameScore());
    }

    @Test
    public void shouldParseAndCalculateMissFrames() {
        bowlingGame = new BowlingGame("-- -- -- -- -- -- -- -- -- --");
        Assertions.assertEquals(0, bowlingGame.getGameScore());
    }

    @Test
    public void shouldParseAndCalculateMixedFramesOne() {
        bowlingGame = new BowlingGame("9- 9- 9- 9- 9- 5/ 5/ 5/ 5/ 5/5");
        Assertions.assertEquals(120, bowlingGame.getGameScore());
    }

    @Test
    public void shouldParseAndCalculateMixedFramesTwo() {
        bowlingGame = new BowlingGame("X X X X X -- -- -- 5/ 5/5");
        Assertions.assertEquals(150, bowlingGame.getGameScore());
    }

    @Test
    public void shouldParseAndCalculateMixedFramesThree() {
        bowlingGame = new BowlingGame("X 5/ X 9/ 32 4/ X 9/ 02 8/9");
        Assertions.assertEquals(149, bowlingGame.getGameScore());
    }

}
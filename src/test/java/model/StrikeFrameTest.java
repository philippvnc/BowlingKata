package model;

import bowling.model.OpenFrame;
import bowling.model.StrikeFrame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StrikeFrameTest {

    @Test
    void shouldGetFinalScoreOneStrike() {
        StrikeFrame strikeFrame = new StrikeFrame(10, 0);
        OpenFrame nextFrame = new OpenFrame(4, 4);
        OpenFrame veryNextFrame = new OpenFrame(2, 3);
        strikeFrame.setNextFrame(nextFrame);
        strikeFrame.setVeryNextFrame(veryNextFrame);
        Assertions.assertEquals(18, strikeFrame.getFinalScore());
    }

    @Test
    void shouldGetFinalScoreTwoStrikes() {
        StrikeFrame strikeFrame = new StrikeFrame(10, 0);
        StrikeFrame nextFrame = new StrikeFrame(10, 0);
        OpenFrame veryNextFrame = new OpenFrame(2, 3);
        strikeFrame.setNextFrame(nextFrame);
        strikeFrame.setVeryNextFrame(veryNextFrame);
        Assertions.assertEquals(22, strikeFrame.getFinalScore());
    }

    @Test
    void shouldGetFinalScoreThreeStrikes() {
        StrikeFrame strikeFrame = new StrikeFrame(10, 0);
        StrikeFrame nextFrame = new StrikeFrame(10, 0);
        StrikeFrame veryNextFrame = new StrikeFrame(10, 0);
        strikeFrame.setNextFrame(nextFrame);
        strikeFrame.setVeryNextFrame(veryNextFrame);
        Assertions.assertEquals(30, strikeFrame.getFinalScore());
    }
}
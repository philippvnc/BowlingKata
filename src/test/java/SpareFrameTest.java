import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SpareFrameTest {

    @Test
    void shouldGetFinalScore() {
        SpareFrame spareFrame = new SpareFrame(2, 8);
        OpenFrame openFrame = new OpenFrame(3, 4);
        spareFrame.setNextFrame(openFrame);
        Assertions.assertEquals(13, spareFrame.getFinalScore());
    }

    @Test
    void shouldGetFinalScoreFollowUpStrike() {
        SpareFrame spareFrame = new SpareFrame(2, 8);
        StrikeFrame openFrame = new StrikeFrame(10, 0);
        spareFrame.setNextFrame(openFrame);
        Assertions.assertEquals(20, spareFrame.getFinalScore());
    }
}
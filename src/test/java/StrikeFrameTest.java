import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StrikeFrameTest {

    @Test
    void shouldGetFinalScore() {
        StrikeFrame strikeFrame = new StrikeFrame(10, 0);
        OpenFrame nextFrame = new OpenFrame(4, 4);
        OpenFrame veryNextFrame = new OpenFrame(2, 3);
        strikeFrame.setNextFrame(nextFrame);
        strikeFrame.setVeryNextFrame(veryNextFrame);
        Assertions.assertEquals(23, strikeFrame.getFinalScore());
    }
}
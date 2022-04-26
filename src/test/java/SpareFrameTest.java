import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpareFrameTest {

    @Test
    void shouldGetFinalScore() {
        SpareFrame spareFrame = new SpareFrame(2, 8);
        OpenFrame openFrame = new OpenFrame(3, 4);
        spareFrame.setNextFrame(openFrame);
        Assertions.assertEquals(17, spareFrame.getFinalScore());
    }
}
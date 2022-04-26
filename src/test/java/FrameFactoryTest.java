import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FrameFactoryTest {

    FrameFactory frameFactory;

    @BeforeEach
    void setUp() {
        frameFactory = new FrameFactory();
    }

    @Test
    void shouldGetOpenFrame() {
        Frame frame = frameFactory.getFrame(2, 4);
        Assertions.assertTrue(frame instanceof OpenFrame);
    }

    @Test
    void shouldGetSpareFrame() {
        Frame frame = frameFactory.getFrame(4, 6);
        Assertions.assertTrue(frame instanceof SpareFrame);
    }
}
package model;

import bowling.model.OpenFrame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OpenFrameTest {

    OpenFrame openFrame;

    @BeforeEach
    void setUp() {
        openFrame = new OpenFrame(4, 5);
    }

    @Test
    void shouldGetFirstThrow() {
        Assertions.assertEquals(4, openFrame.getFirstThrow());
    }

    @Test
    void shouldGetBaseScore() {
        Assertions.assertEquals(9, openFrame.getBaseScore());
    }

    @Test
    void shouldGetFinalScore() {
        Assertions.assertEquals(9, openFrame.getFinalScore());
    }
}
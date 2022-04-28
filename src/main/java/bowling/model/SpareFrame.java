package bowling.model;

/**
 * This class extends {@link OpenFrame} und thus also implements the interface {@link Frame}.
 * A {@link SpareFrame} object differs from {@link OpenFrame} in regard to final score calculation
 * und therefore has a reference to the consecutive frame in {@link SpareFrame#nextFrame}.
 */
public class SpareFrame extends OpenFrame {

    /**
     * Reference to the following frame.
     */
    protected Frame nextFrame;

    /**
     * Class constructor specifying first and second throw, extends subclass constructor.
     * Also sets {@link SpareFrame#nextFrame} to new {@link OpenFrame} to avoid null value.
     *
     * @param firstThrow  value of the first throw of the frame
     * @param secondThrow value of the second throw of the frame
     */
    public SpareFrame(int firstThrow, int secondThrow) {
        super(firstThrow, secondThrow);
        nextFrame = new OpenFrame(0, 0);
    }

    /**
     * This method overwrites corresponding method of {@link OpenFrame}.
     * Return the final score of a {@link SpareFrame} as the sum of its base score and the first throw
     * of the next frame.
     *
     * @return the final score of the spare frame
     */
    public int getFinalScore() {
        return getBaseScore() + nextFrame.getFirstThrow();
    }

    /**
     * Setter method for {@link SpareFrame#nextFrame}.
     *
     * @param nextFrame the consecutive frame
     */
    public void setNextFrame(Frame nextFrame) {
        this.nextFrame = nextFrame;
    }
}

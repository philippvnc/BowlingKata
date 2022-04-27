/**
 * This class extends {@link SpareFrame} und thus also implements the interface {@link Frame}.
 * A {@link StrikeFrame} object differs from {@link SpareFrame} in regard to final score calculation
 * und therefore has references to the two consecutive frames in {@link SpareFrame#nextFrame} and
 * {@link StrikeFrame#veryNextFrame} respectively.
 */
public class StrikeFrame extends SpareFrame {

    /**
     * Reference to the very next frame.
     */
    private Frame veryNextFrame;

    /**
     * Class constructor specifying first and second throw, extends subclass constructor.
     * Also sets {@link StrikeFrame#veryNextFrame} to new {@link OpenFrame} to avoid null value.
     *
     * @param firstThrow  value of the first throw of the frame
     * @param secondThrow value of the second throw of the frame
     */
    public StrikeFrame(int firstThrow, int secondThrow) {
        super(firstThrow, secondThrow);
        veryNextFrame = new OpenFrame(0, 0);
    }

    /**
     * This method calculates and returns the sum of the next throws following this {@link StrikeFrame}.
     * If the consecutive {@link Frame} is also a {@link StrikeFrame} the return value is equal to the sum of
     * the base score of the next frame and the first throw of the very next frame. Otherwise, the return value is
     * simply equal to the base score of the next frame.
     *
     * @return the sum of the next two throws following this frame
     */
    private int getSumOfNextTwoThrows() {
        if (nextFrame instanceof StrikeFrame) {
            return nextFrame.getBaseScore() + veryNextFrame.getFirstThrow();
        } else {
            return nextFrame.getBaseScore();
        }
    }

    /**
     * This method overwrites corresponding method of {@link SpareFrame}.
     * Return the final score of a {@link StrikeFrame} as the sum of its base score and two following throws
     * by evoking {@link StrikeFrame#getSumOfNextTwoThrows()}.
     *
     * @return the final score of the strike frame
     */
    public int getFinalScore() {
        return getBaseScore() + getSumOfNextTwoThrows();
    }

    /**
     * Setter method for {@link StrikeFrame#veryNextFrame}.
     *
     * @param veryNextFrame the very next frame
     */
    public void setVeryNextFrame(Frame veryNextFrame) {
        this.veryNextFrame = veryNextFrame;
    }
}

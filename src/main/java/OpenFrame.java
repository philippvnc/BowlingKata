/**
 * This class implements the interface frame as an open frame.
 */
public class OpenFrame implements Frame {

    /**
     * The value of the first throw of the frame.
     */
    private int firstThrow;
    /**
     * The value of the second throw of the frame.
     */
    private int baseScore;

    /**
     * Class constructor specifying first and second throw.
     * Saves value of first throw in member variable {@link OpenFrame#firstThrow}.
     * Calculates and saves base score of frame in member variable {@link OpenFrame#baseScore}.
     *
     * @param firstThrow  value of the first throw of the frame
     * @param secondThrow value of the second throw of the frame
     */
    public OpenFrame(int firstThrow, int secondThrow) {
        this.firstThrow = firstThrow;
        baseScore = firstThrow + secondThrow;
    }

    /**
     * Getter method for {@link OpenFrame#firstThrow}.
     *
     * @return the value of the first throw of the frame
     */
    @Override
    public int getFirstThrow() {
        return firstThrow;
    }

    /**
     * Getter method for {@link OpenFrame#baseScore}.
     *
     * @return the base score of the frame
     */
    @Override
    public int getBaseScore() {
        return baseScore;
    }

    /**
     * This method returns the final score of the open frame.
     * The final score of an open frame is equal to its {@link OpenFrame#baseScore}.
     *
     * @return the final score of the open frame
     */
    @Override
    public int getFinalScore() {
        return getBaseScore();
    }
}

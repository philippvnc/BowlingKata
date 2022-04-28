/**
 * This interface defines common methods for Frame objects.
 */
public interface Frame {

    /**
     * This method should return the value of the first throw of the frame.
     *
     * @return the value of the first throw
     */
    int getFirstThrow();

    /**
     * This method should return the value of the base score of the frame.
     * The value should equal the sum of the first and second throw of the frame.
     *
     * @return the value of the base score
     */
    int getBaseScore();

    /**
     * This method should return the value of the final score of the frame.
     * The value should equal the correct final score of the frame according to official bowling scoring rules.
     *
     * @return the value of the final score
     */
    int getFinalScore();
}

/**
 * This class defines a factory class for frame objects, and should be used to generate different frames.
 */
public class FrameFactory {

    /**
     * This method returns a new frame object based on the value of the first and second throw of the frame.
     * Returns a {@link StrikeFrame} if the first throw equals 10, a {@link SpareFrame} if the sum of both throws
     * equals 10, and otherwise an {@link OpenFrame}.
     *
     * @param firstThrow  the value of the first throw of the frame
     * @param secondThrow the value of the second throw of the frame
     * @return a new frame
     */
    public Frame getFrame(int firstThrow, int secondThrow) {
        if (firstThrow == 10) {
            return new StrikeFrame(firstThrow, 0);
        } else if (firstThrow + secondThrow == 10) {
            return new SpareFrame(firstThrow, secondThrow);
        } else {
            return new OpenFrame(firstThrow, secondThrow);
        }
    }
}

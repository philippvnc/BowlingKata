public class StrikeFrame extends SpareFrame {

    private Frame veryNextFrame;

    public StrikeFrame(int firstThrow, int secondThrow) {
        super(firstThrow, secondThrow);
        veryNextFrame = new OpenFrame(0, 0);
    }

    private int getSumOfNextTwoThrows() {
        if (nextFrame instanceof StrikeFrame) {
            return nextFrame.getBaseScore() + veryNextFrame.getFirstThrow();
        } else {
            return nextFrame.getBaseScore();
        }
    }

    public int getFinalScore() {
        return getBaseScore() + getSumOfNextTwoThrows();
    }

    public void setVeryNextFrame(Frame veryNextFrame) {
        this.veryNextFrame = veryNextFrame;
    }
}

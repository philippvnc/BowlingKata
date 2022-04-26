public class SpareFrame extends OpenFrame {

    private Frame nextFrame;

    public SpareFrame(int firstThrow, int secondThrow) {
        super(firstThrow, secondThrow);
    }

    public int getFinalScore(){
        return getBaseScore() + nextFrame.getBaseScore();
    }
}

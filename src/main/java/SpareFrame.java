public class SpareFrame extends OpenFrame {

    private Frame nextFrame;

    public SpareFrame(int firstThrow, int secondThrow) {
        super(firstThrow, secondThrow);
    }

    public int getFinalScore(){
        if(nextFrame != null){
            return getBaseScore() + nextFrame.getBaseScore();
        } else {
            return getBaseScore();
        }
    }

    public void setNextFrame(Frame nextFrame) {
        this.nextFrame = nextFrame;
    }
}

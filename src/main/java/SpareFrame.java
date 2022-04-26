public class SpareFrame extends OpenFrame {

    protected Frame nextFrame;

    public SpareFrame(int firstThrow, int secondThrow) {
        super(firstThrow, secondThrow);
        nextFrame = new OpenFrame(0,0);
    }

    public int getFinalScore(){
        return getBaseScore() + nextFrame.getFirstThrow();
    }

    public void setNextFrame(Frame nextFrame) {
        this.nextFrame = nextFrame;
    }
}

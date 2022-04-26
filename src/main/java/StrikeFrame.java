public class StrikeFrame extends SpareFrame{

    private Frame veryNextFrame;

    public StrikeFrame(int firstThrow, int secondThrow) {
        super(firstThrow, secondThrow);
    }

    public int getFinalScore(){
        if(veryNextFrame != null){
            return super.getFinalScore() + veryNextFrame.getBaseScore();
        } else {
            return super.getFinalScore();
        }
    }

    public void setVeryNextFrame(Frame veryNextFrame) {
        this.veryNextFrame = veryNextFrame;
    }
}

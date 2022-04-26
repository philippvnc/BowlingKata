import java.util.ArrayList;

public class BowlingGame {

    private ArrayList<Frame> frameList;
    private FrameFactory frameFactory;

    public BowlingGame() {
        frameList = new ArrayList<>();
        frameFactory = new FrameFactory();
    }

    public void addFrame(int firstThrow, int secondThrow) {
        Frame frameToAdd = frameFactory.getFrame(firstThrow, secondThrow);
        if(isLatestAddedFrameSpare()){
            ((SpareFrame) frameList.get(getFrameListSize()-1)).setNextFrame(frameToAdd);
        }
        frameList.add(frameToAdd);
    }

    public boolean isLatestAddedFrameSpare(){
        if (frameList.size() == 0) {
            return false;
        } else {
            return (frameList.get(getFrameListSize()-1) instanceof SpareFrame);
        }
    }

    public boolean isSecondLatestAddedFrameStrike(){
        if (frameList.size() <= 1) {
            return false;
        } else {
            return (frameList.get(getFrameListSize()-2) instanceof StrikeFrame);
        }
    }

    public int getGameScore() {
        int totalScore = 0;
        frameList.subList(10, getFrameListSize()).clear();
        for (Frame frame : frameList) {
            totalScore += frame.getFinalScore();
            System.out.println(frame.getFinalScore());
        }
        return totalScore;
    }

    public int getFrameListSize(){
        return frameList.size();
    }
}

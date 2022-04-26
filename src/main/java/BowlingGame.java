import java.util.ArrayList;

public class BowlingGame {

    private ArrayList<Frame> frameList;

    public BowlingGame() {
        frameList = new ArrayList<>();
    }

    public void addFrame(int firstThrow, int secondThrow) {
        frameList.add(new Frame(firstThrow, secondThrow));
    }

    public int getGameScore() {
        int totalScore = 0;
        for (Frame frame : frameList) {
            totalScore += frame.getBaseScore();
        }
        return totalScore;
    }
}

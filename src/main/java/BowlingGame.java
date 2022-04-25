import java.util.ArrayList;

public class BowlingGame {

    private ArrayList<Integer> throwList;

    public BowlingGame() {
        throwList = new ArrayList<Integer>();
    }

    public void addFrame(int firstThrow, int secondThrow) {
        throwList.add(firstThrow);
        throwList.add(secondThrow);
    }

    public int getGameScore() {
        int totalScore = 0;
        for (Integer throwScore : throwList) {
            totalScore += throwScore;
        }
        return totalScore;
    }
}

import java.util.ArrayList;

public class BowlingGame {

    private final ArrayList<Frame> frameList;
    private final FrameFactory frameFactory;

    public BowlingGame() {
        frameList = new ArrayList<>();
        frameFactory = new FrameFactory();
    }

    public BowlingGame(String gameDefinition) {
        this();
        this.parseAndInitializeGame(gameDefinition);
    }

    public static void main(String[] args) {
        BowlingGame bowlingGame = new BowlingGame(args[0]);
        System.out.println("This Games score is " + bowlingGame.getGameScore());
    }

    private void parseAndInitializeGame(String gameDefinition) {
        String tmpGameDefinition = gameDefinition.replace(" ", "");
        tmpGameDefinition = tmpGameDefinition.replaceAll("(.)", "$1 ");
        tmpGameDefinition = tmpGameDefinition.replace("-", "0");
        tmpGameDefinition = tmpGameDefinition.replace("X", "10 0");
        tmpGameDefinition = tmpGameDefinition.replace("/", "-1");

        String[] split = tmpGameDefinition.split(" ");
        int[] throwValues = new int[split.length];
        for (int splitIndex = 0; splitIndex < split.length; splitIndex++) {
            throwValues[splitIndex] = Integer.parseInt(split[splitIndex]);
            if (throwValues[splitIndex] == -1) {
                throwValues[splitIndex] = 10 - throwValues[splitIndex - 1];
            }
        }

        for (int frameNumber = 0; frameNumber * 2 + 1 < throwValues.length; frameNumber++) {
            addFrame(throwValues[frameNumber * 2], throwValues[frameNumber * 2 + 1]);
        }
        if (throwValues.length % 2 == 1) {
            addFrame(throwValues[throwValues.length - 1], 0);
        }
    }

    public void addFrame(int firstThrow, int secondThrow) {
        Frame frameToAdd = frameFactory.getFrame(firstThrow, secondThrow);
        if (isLatestAddedFrameSpare()) {
            ((SpareFrame) frameList.get(getFrameListSize() - 1)).setNextFrame(frameToAdd);
        }
        if (isSecondLatestAddedFrameStrike()) {
            ((StrikeFrame) frameList.get(getFrameListSize() - 2)).setVeryNextFrame(frameToAdd);
        }
        frameList.add(frameToAdd);
    }

    public boolean isLatestAddedFrameSpare() {
        if (frameList.size() == 0) {
            return false;
        } else {
            return (frameList.get(getFrameListSize() - 1) instanceof SpareFrame);
        }
    }

    public boolean isSecondLatestAddedFrameStrike() {
        if (frameList.size() <= 1) {
            return false;
        } else {
            return (frameList.get(getFrameListSize() - 2) instanceof StrikeFrame);
        }
    }

    public int getGameScore() {
        int totalScore = 0;
        if (getFrameListSize() > 10) {
            frameList.subList(10, getFrameListSize()).clear();
        }
        for (Frame frame : frameList) {
            totalScore += frame.getFinalScore();
        }
        return totalScore;
    }

    public int getFrameListSize() {
        return frameList.size();
    }
}

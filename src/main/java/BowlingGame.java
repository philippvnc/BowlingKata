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

        for (int frameIndex = 0; frameIndex * 2 + 1 < throwValues.length; frameIndex++) {
            addFrame(throwValues[frameIndex * 2], throwValues[frameIndex * 2 + 1]);
        }
        if (throwValues.length % 2 == 1) {
            addFrame(throwValues[throwValues.length - 1], 0);
        }
    }

    public void addFrame(int firstThrow, int secondThrow) {
        addFrame(frameFactory.getFrame(firstThrow, secondThrow));
    }

    public void addFrame(Frame frame) {
        frameList.add(frame);
    }

    public int getGameScore() {
        int numberOfFramesPerGame = 10;
        int totalScore = 0;
        Frame currentFrame;
        for (int frameIndex = 0; frameIndex < Math.min(numberOfFramesPerGame, getFrameListSize()); frameIndex++) {
            currentFrame = getFrameByIndex(frameIndex);
            if (currentFrame instanceof SpareFrame) {
                ((SpareFrame) currentFrame).setNextFrame(getFrameByIndex(frameIndex + 1));
            }
            if (currentFrame instanceof StrikeFrame) {
                ((StrikeFrame) currentFrame).setVeryNextFrame(getFrameByIndex(frameIndex + 2));
            }
            totalScore += currentFrame.getFinalScore();
        }
        return totalScore;
    }

    public int getFrameListSize() {
        return frameList.size();
    }

    public Frame getFrameByIndex(int frameIndex) {
        if (frameIndex < getFrameListSize()) {
            return frameList.get(frameIndex);
        } else {
            return frameFactory.getFrame(0, 0);
        }
    }
}

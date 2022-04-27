import java.util.ArrayList;

public class BowlingGame {

    private final ArrayList<Frame> frameList;
    private final FrameFactory frameFactory;

    private final int numberOfFramesPerGame = 10;

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
        frameList.add(frameFactory.getFrame(firstThrow, secondThrow));
    }

    public int getGameScore() {
        addFrame(0, 0);
        int totalScore = 0;
        for (int frameNumber = 0; frameNumber < Math.min(numberOfFramesPerGame, getFrameListSize()); frameNumber++) {
            if (frameList.get(frameNumber) instanceof SpareFrame) {
                ((SpareFrame) frameList.get(frameNumber)).setNextFrame(frameList.get(frameNumber + 1));
            }
            if (frameList.get(frameNumber) instanceof StrikeFrame) {
                ((StrikeFrame) frameList.get(frameNumber)).setVeryNextFrame(frameList.get(frameNumber + 2));
            }
            totalScore += frameList.get(frameNumber).getFinalScore();
        }
        return totalScore;
    }

    public int getFrameListSize() {
        return frameList.size();
    }
}

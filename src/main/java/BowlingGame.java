import java.util.ArrayList;

/**
 * This is the main class of the bowling application, that stores bowling frames and calculates the final game score.
 *
 */
public class BowlingGame {

    /**
     * An array list of frames, holding all frames that were added to a specific bowling game.
     * Used to retain the order of frames and calculate the final game score.
     */
    private final ArrayList<Frame> frameList;
    /**
     * this frame factory is used to generate new frames for this bowling game.
     */
    private final FrameFactory frameFactory;

    /**
     * class constructor without arguments to create empty bowling game object. Will initialize
     * {@link BowlingGame#frameList} and {@link BowlingGame#frameFactory}, but not add any frames.
     */
    public BowlingGame() {
        frameList = new ArrayList<>();
        frameFactory = new FrameFactory();
    }

    /**
     * class constructor with game definition argument to create a bowling game object with frames.
     * Will call overloaded class constructor without arguments for initialization and then add frames based on
     * parsing of game definition string.
     * @param gameDefinition string defining the frames, that will be parsed
     */
    public BowlingGame(String gameDefinition) {
        this();
        this.parseAndAddFrames(gameDefinition);
    }

    /**
     * executable main method for bowling game class. Takes a game definition string as input,
     * creates a bowling game object based on the input, then calculates and prints the final game score
     * to standard out.
     * @param args input string array parameter, string at index 0 is used as game definition string
     */
    public static void main(String[] args) {
        BowlingGame bowlingGame = new BowlingGame(args[0]);
        System.out.println("This Games score is " + bowlingGame.getGameScore());
    }


    /**
     * this method parses a given game definition string and adds frames accordingly.
     * this method requires a string with the following constraints:
     *
     * - strike frames are represented as 'X'
     * - second throws of spare frames are represented as '/'
     * - all remaining throws are represented as the number of pins thrown
     *
     * optional string format:
     *
     * - the frames and or throws can be separated with whitespaces ' '
     * - zero pin throws aka missed can be represented as '-'
     *
     * example game definition strings:
     *
     * - "X X X X X X X X X X X X"
     * - "9- 9- 9- 9- 9- 9- 9- 9- 9- 9-"
     * - "5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/5"
     * - "-- -- -- -- -- -- -- -- -- --"
     * - "X 5/ X 9/ 32 4/ X 9/ 02 8/9"
     *
     * this method will not check for correct number of frames in the game definition.
     * first the individual throws are separated and the literals in the string are replaced with numbers.
     * then the strings are parsed as ints and stored in an array and the second throw of each spare frame is
     * calculated based on the first throw.
     *
     * finally, the frames are added in pairs of throws. For uneven numbers of throws caused by bonus throws, an empty
     * throw is added to make a complete frame.
     *
     * @param gameDefinition the full game definition containing all frames
     */
    public void parseAndAddFrames(String gameDefinition) {
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

    /**
     * this method creates and adds a new {@link Frame} object, based on parameters firstThrow and secondThrow
     * @param firstThrow  the value of the first throw of the frame
     * @param secondThrow the value of the second throw of the frame
     */
    public void addFrame(int firstThrow, int secondThrow) {
        addFrame(frameFactory.getFrame(firstThrow, secondThrow));
    }

    /**
     * this method adds a given {@link Frame} object to the {@link BowlingGame#frameList} of this {@link BowlingGame}
     * object.
     * @param frame the frame that will be added
     */
    public void addFrame(Frame frame) {
        frameList.add(frame);
    }

    /**
     * this method calculates and returns the final game score based on frames in {@link BowlingGame#frameList}.
     * Loops over all non bonus frames in {@link BowlingGame#frameList}. For each frame the needed frame references
     * are assigned. For spare frames the consecutive frame is assigned, for strike frames the two consecutive frames
     * are assigned. (Note the following code works expected because of the class heritage of strike frame and
     * spare frame.) For each frame the final score is then calculated and added to the total game score, which is
     * finally returned.
     *
     * @return the total score of the game
     */
    public int getGameScore() {
        int numberOfNonBonusFrames = 10;
        int totalScore = 0;
        Frame currentFrame;
        for (int frameIndex = 0; frameIndex < Math.min(numberOfNonBonusFrames, getFrameListSize()); frameIndex++) {
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

    /**
     * this method returns the current size of the {@link BowlingGame#frameList} aka the current number of frames
     * that have been added to the {@link BowlingGame}.
     * @return current size of frame list
     */
    public int getFrameListSize() {
        return frameList.size();
    }

    /**
     * this method safely retrieves frames from {@link BowlingGame#frameList}.
     * Will return the frame object at specified frame index for indices in range.
     * Will return a new frame object with zero throws for indices outside the range.
     * @param frameIndex the index of the frame that should be retrieved
     * @return           the frame at the index or a new frame
     */
    public Frame getFrameByIndex(int frameIndex) {
        if (frameIndex < getFrameListSize() && frameIndex >= 0) {
            return frameList.get(frameIndex);
        } else {
            return frameFactory.getFrame(0, 0);
        }
    }
}

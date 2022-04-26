public class OpenFrame implements Frame {

    private int firstThrow;
    private int secondThrow;
    private int baseScore;

    public OpenFrame(int firstThrow, int secondThrow) {
        this.firstThrow = firstThrow;
        this.secondThrow = secondThrow;
        baseScore = firstThrow + secondThrow;
    }

    @Override
    public int getFirstThrow() {
        return firstThrow;
    }

    @Override
    public int getSecondThrow() {
        return secondThrow;
    }

    @Override
    public int getBaseScore() {
        return baseScore;
    }

    @Override
    public int getFinalScore() {
        return getBaseScore();
    }
}

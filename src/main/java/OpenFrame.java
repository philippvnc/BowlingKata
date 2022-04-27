public class OpenFrame implements Frame {

    private int firstThrow;
    private int baseScore;

    public OpenFrame(int firstThrow, int secondThrow) {
        this.firstThrow = firstThrow;
        baseScore = firstThrow + secondThrow;
    }

    @Override
    public int getFirstThrow() {
        return firstThrow;
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

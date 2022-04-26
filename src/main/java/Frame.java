public class Frame {

    private int baseScore;

    public Frame(int firstThrow, int secondThrow){
        baseScore = firstThrow + secondThrow;
    }

    public int getBaseScore() {
        return baseScore;
    }
}

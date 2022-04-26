public class OpenFrame implements Frame{

    private int score;

    public OpenFrame(int firstThrow, int secondThrow){
        score = firstThrow + secondThrow;
    }

    @Override
    public int getBaseScore() {
        return score;
    }

    @Override
    public int getFinalScore() {
        return getBaseScore();
    }
}

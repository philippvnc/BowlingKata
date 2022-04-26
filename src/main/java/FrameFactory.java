public class FrameFactory {

    public Frame getFrame(int firstThrow, int secondThrow) {
        if (firstThrow + secondThrow == 10) {
            return new SpareFrame(firstThrow, secondThrow);
        } else {
            return new OpenFrame(firstThrow, secondThrow);
        }
    }
}

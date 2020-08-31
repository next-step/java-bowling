package bowling.domian.frame;

public class Frames {
    private Frame firstFrame;
    private Frame currentFrame;

    private Frames() {
        this.firstFrame = NormalFrame.firstFrame();
        this.currentFrame = this.firstFrame;
    }

    public static Frames of() {
        return new Frames();
    }

    public void bowl(int falledPinsCount) {
        this.currentFrame = this.currentFrame.bowl(falledPinsCount);
    }

    public Board getBoard() {
        Board board = new Board();

        firstFrame.addBoard(board);

        return board;
    }

    public boolean isGameEnd() {
        return currentFrame.isGameEnd();
    }

    public int getCurrentFrameNumber() {
        return currentFrame.getFrameNumber();
    }

    public boolean isFrameNumber(int frameNumber) {
        return currentFrame.getFrameNumber() == frameNumber;
    }
}

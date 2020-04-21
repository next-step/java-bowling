package bowling.domain.frame;

public class NormalFrame extends Frame {
    public NormalFrame() {
        super();
    }

    @Override
    public boolean isThrowable() {
        return points.isThrowableForNormalFrame();
    }

    @Override
    public void addScore() {
        if (!isThrowable()) {
            this.score = points.makeScore();
        }
    }
}

package bowling.domain.frame;

import bowling.domain.status.running.Ready;

public class NormalFrame extends Frame {
    public NormalFrame() {
        super();
        this.status = Ready.of();
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

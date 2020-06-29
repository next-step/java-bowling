package bowling.domain;

import bowling.domain.vo.Round;

public class NormalFrame {
    private final Round round;
    private final FrameSnapshot snapshot = new FrameSnapshot();

    public NormalFrame(final Round round) {
        this.round = round;
    }

    public NormalFrame execute(final GameExecutor gameExecutor) {
        RoundUnit currentUnit = snapshot.record(gameExecutor);
        if (currentUnit.equals(RoundUnit.ONE)) {
            return this;
        }
        return new NormalFrame(this.round.next());
    }

    public boolean isFinalFrame() {
        return round.isFinal();
    }
}

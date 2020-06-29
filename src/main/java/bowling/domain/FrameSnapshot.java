package bowling.domain;

import bowling.domain.vo.BowlingBalls;
import bowling.domain.vo.RoundUnit;

import java.util.HashMap;
import java.util.Map;

public class FrameSnapshot {
    private final Map<RoundUnit, BowlingBalls> snapshot;

    public FrameSnapshot() {
        this.snapshot = new HashMap<>();
    }

    public boolean isStrike() {
        return snapshot.get(RoundUnit.ONE).getNumber() == 10;
    }

    public boolean isSpare() {
        if (isStrike()) {
            throw new IllegalArgumentException("RoundUnit.TWO 기록을 찾을 수 없습니다");
        }
        return snapshot.get(RoundUnit.ONE).getNumber() + snapshot.get(RoundUnit.TWO).getNumber() == 10;
    }

    public RoundUnit record(final GameExecutor gameExecutor) {
        if (snapshot.get(RoundUnit.ONE) == null) {
            BowlingBalls result = gameExecutor.execute(BowlingBalls.INITIAL_BOWLING_BALLS);
            snapshot.put(RoundUnit.ONE, result);
            return RoundUnit.ONE;
        }
        BowlingBalls result = gameExecutor.execute(BowlingBalls.INITIAL_BOWLING_BALLS.minus(this.snapshot.get(RoundUnit.ONE)));
        snapshot.put(RoundUnit.TWO, result);
        return RoundUnit.TWO;
    }
}

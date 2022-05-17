package bowling.frame;

import java.util.ArrayList;
import java.util.List;

public class Frames {

    private static final int FIRST_ROUND = 0;
    private static final int LAST_ROUND = 9;

    private final List<Frame> frames = new ArrayList<>();

    private Frames() {
        for (int round = FIRST_ROUND; round < LAST_ROUND; round++) {
            frames.add(NormalFrame.create());
        }
        frames.add(LastFrame.create());
    }

    public static Frames create() {
        return new Frames();
    }

    public void shoot(Round round, ShootScore shootScore) {
        Frame specificRoundFrame = findFrameByRound(round);

        if (specificRoundFrame.isEnd()) {
            throw new IllegalArgumentException("투구가 끝난 Frame 에는 더 투구할 수 없습니다.");
        }

        specificRoundFrame.shoot(shootScore);
    }

    public Frame findFrameByRound(Round round) {
        return frames.get(round.getRound());
    }

    public boolean isRoundEnd(Round round) {
        return round.isRoundEnd(frames);
    }
}

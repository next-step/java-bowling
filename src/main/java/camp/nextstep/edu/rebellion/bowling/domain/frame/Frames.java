package camp.nextstep.edu.rebellion.bowling.domain.frame;

import camp.nextstep.edu.rebellion.bowling.domain.Round;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frames {
    private static final int MAX_FRAMES = 10;
    private static final int FINAL_FRAME = 9;

    private final List<Frame> frames;

    private Frames() {
        this.frames = generateNormalFrames();
    }

    public static Frames clear() {
        return new Frames();
    }

    private List<Frame> generateNormalFrames() {
        List<Frame> frames = new ArrayList<>();
        for (int i = 0; i < MAX_FRAMES; i++) {
            frames.add(new NormalFrame());
        }
        return frames;
    }

    public Frame findByRound(Round round) {
        return frames.get(round.getCurrent());
    }

    public boolean isFinalFrameStrike() {
        return this.frames.get(FINAL_FRAME)
                .getFrameScore()
                .isStrike();
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(this.frames);
    }

    public void makeBonusFrame() {
        if (frames.stream()
                .anyMatch(f -> f instanceof BonusFrame)) {
            throw new IllegalArgumentException("보너스 프레임은 하나만 생성할 수 있습니다");
        }

        this.frames.add(new BonusFrame());
    }
}

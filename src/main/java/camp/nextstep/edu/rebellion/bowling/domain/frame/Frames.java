package camp.nextstep.edu.rebellion.bowling.domain.frame;

import camp.nextstep.edu.rebellion.bowling.domain.game.Round;
import camp.nextstep.edu.rebellion.bowling.domain.score.FrameScore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frames {
    private static final int COUNT_OF_MAX_FRAMES = 10;
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

        for (int i = 0; i < COUNT_OF_MAX_FRAMES; i++) {
            frames.add(FrameFactory.get(FrameType.NORMAL));
        }

        return frames;
    }

    public void markScoreOnRound(Round round, int hits) {
        findByRound(round)
                .markScore(hits);
    }

    public void markBonusOnPrevious(Round round, int hits) {
        Round currentRound = Round.currentOf(round);
        while (currentRound.hasPrev()) {
            currentRound.prev();
            findByRound(currentRound)
                    .markBonus(hits);
        }
    }

    public boolean meetEnd(Round round) {
        return findByRound(round)
                .meetEnd();
    }

    public void makeBonusChance(Round round) {
        Frame frame = findByRound(round);

        if (frame.match(FrameType.BONUS)) {
            return;
        }

        frame.makeBonusChance();
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(this.frames);
    }

    public boolean canMakeBonusFrame() {
        return isFinalStrikeOrSpare() && !containBonusFrame();
    }

    public void makeBonusFrame() {
        if (containBonusFrame()) {
            throw new IllegalArgumentException("보너스 프레임은 하나만 생성할 수 있습니다");
        }

        frames.add(FrameFactory.get(FrameType.BONUS,
                getFinalFrameScore().getTryAttempt()));
    }


    private boolean isFinalStrikeOrSpare() {
        FrameScore frameScore = getFinalFrameScore();
        return frameScore.isStrike() || frameScore.isSpare();
    }

    private boolean containBonusFrame() {
        return frames.stream()
                .anyMatch(f -> f instanceof BonusFrame);
    }

    private FrameScore getFinalFrameScore() {
        Frame finalFrame = frames.get(FINAL_FRAME);
        FrameScore frameScore = finalFrame.getFrameScore();
        return frameScore;
    }

    private Frame findByRound(Round round) {
        return frames.get(round.getCurrent());
    }
}

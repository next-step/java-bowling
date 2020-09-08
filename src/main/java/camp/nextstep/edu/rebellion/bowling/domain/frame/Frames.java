package camp.nextstep.edu.rebellion.bowling.domain.frame;

import camp.nextstep.edu.rebellion.bowling.domain.game.Round;
import camp.nextstep.edu.rebellion.bowling.domain.score.FrameScore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frames {
    private static final int COUNT_OF_MAX_FRAMES = 10;
    private static final int FINAL_FRAME = 9;
    private static final int TRY_TWO = 2;
    private static final int TRY_ONE = 1;
    private static final int NO_TRY = 0;

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

    public boolean isFinalFrameStrikeOrSpare() {
        FrameScore frameScore = getFinalFrameScore();
        return frameScore.isStrike() || frameScore.isSpare();
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(this.frames);
    }

    public void makeBonusFrame() {
        if (containBonusFrame()) {
            throw new IllegalArgumentException("보너스 프레임은 하나만 생성할 수 있습니다");
        }

        frames.add(FrameFactory.get(FrameType.BONUS,
                getTryAttempt(getFinalFrameScore())));
    }

    public boolean containBonusFrame() {
        return frames.stream()
                .anyMatch(f -> f instanceof BonusFrame);
    }

    private int getTryAttempt(FrameScore finalFrameScore) {
        return finalFrameScore.isStrike() ? TRY_TWO :
                finalFrameScore.isSpare() ? TRY_ONE :
                        NO_TRY;
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

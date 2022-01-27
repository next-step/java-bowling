package bowling.domain.frame;

import bowling.domain.Game;
import bowling.domain.KnockedPins;
import bowling.domain.Score;
import bowling.domain.pitch.Pitch;

import java.util.Collections;
import java.util.List;

public class FinalFrame extends DefaultFrame {
    public FinalFrame(List<Pitch> pitches) {
        super(pitches);
    }

    @Override
    protected int maxPitchesCount() {
        return 3;
    }

    private boolean isThirdPitch() {
        return isBonus() && pitches.size() == 2;
    }

    private boolean isBonus() {
        return pitches.get(0).getKnockedPins().getKnockedPins() == 10 || isSpare(pitches.get(0).getKnockedPins(), pitches.get(1).getKnockedPins());
    }

    @Override
    public Frame play(KnockedPins knockedPins) {
        return null;
    }

    @Override
    public boolean isSpare(KnockedPins knockedPinsA, KnockedPins knockedPinsB) {
        return knockedPinsA.getKnockedPins() + knockedPinsB.getKnockedPins() == 10;
    }

    @Override
    public boolean isPlaying() {
        if (isFirstPitch()) {
            return true;
        }

        if (isSecondPitch()) {
            return true;
        }

        if (isThirdPitch()) {
            return true;
        }

        return isFirstPitch() || isSecondPitch() || isThirdPitch();
    }

    private boolean isSecondPitch() {
        if (pitches.size() == 1) {
            return true;
        }
        return false;
    }

    @Override
    public Score cacluateAdditionalScore(Score beforeScore, List<Frame> frames) {
        return null;
    }

    @Override
    public Score calculateScore(Game game) {
        if (isPlaying()) {
            return playingScore();
        }
        Score score = createScore();
        return score;
    }

    @Override
    public Score additionalScore(Score beforeScore, List<Frame> frames) {
        return null;
    }

    private Score createScore() {
        return new Score(pitches.stream().map(pitch -> pitch.getKnockedPins().getKnockedPins()).reduce(0, Integer::sum), 2);
    }

    public static DefaultFrame init() {
        return new FinalFrame(Collections.emptyList());
    }
}

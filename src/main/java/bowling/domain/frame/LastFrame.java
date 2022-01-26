package bowling.domain.frame;

import bowling.domain.Game;
import bowling.domain.KnockedPins;
import bowling.domain.Score;
import bowling.domain.pitch.Pitch;

import java.util.List;

public class LastFrame extends DefaultFrame {
    protected LastFrame(List<Pitch> pitches) {
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
        return isFirstPitch() || isSecondPitch() || isThirdPitch();
    }

    @Override
    public Score cacluateAdditionalScore(Score beforeScore, List<Frame> frames) {
        return null;
    }

    @Override
    public Score calculateScore(Game game) {
        return null;
    }

    @Override
    public Score additionalScore(Score beforeScore, List<Frame> frames) {
        return null;
    }
}

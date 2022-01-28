package bowling.domain.frame;

import bowling.domain.Game;
import bowling.domain.KnockedPins;
import bowling.domain.Score;
import bowling.domain.pitch.Pitch;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FinalFrame extends DefaultFrame {
    public FinalFrame(List<Pitch> pitches) {
        super(pitches);
    }

    public static DefaultFrame init() {
        return new FinalFrame(Collections.emptyList());
    }

    private boolean isThirdPitch() {
        return isBonus() && pitches.size() == 2;
    }

    private boolean isBonus() {
        return getFirstKnockedPins().getCount() == 10 || isSpare(getFirstKnockedPins(), getSecondKnockedPins());
    }

    @Override
    public Frame play(KnockedPins knockedPins) {
        return new FinalFrame(playPitches(knockedPins));
    }

    @Override
    public boolean isSpare(KnockedPins knockedPinsA, KnockedPins knockedPinsB) {
        return knockedPinsA.getCount() + knockedPinsB.getCount() == 10 && !knockedPinsA.isStrike();
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

    @Override
    public Score cacluateAdditionalScore(Score beforeScore, List<Frame> frames) {
        return null;
    }

    @Override
    public Score calculateScore(Game game) {
        return new Score(
                pitches.stream()
                        .map(Pitch::getKnockedPins)
                        .map(KnockedPins::getCount)
                        .reduce(0, Integer::sum),
                isPlaying() ? 1 : 0
        );
    }

    @Override
    public Score additionalScore(Score beforeScore, List<Frame> frames) {
        final List<Pitch> limitedPitches = getPitches().stream()
                .limit(beforeScore.getLeft())
                .collect(Collectors.toList());
        for (final Pitch pitch : limitedPitches) {
            beforeScore = beforeScore.play(pitch.getKnockedPins().getCount());
        }
        return beforeScore;
    }

    @Override
    public DefaultFrame createFinalFrame() {
        throw new IllegalArgumentException();
    }

    @Override
    public String convert() {
        if (isFirstPitch()) {
            return "";
        }
        if (isSecondPitch()) {
            return getFirstKnockedPins().convert() + "|";
        }
        if (isThirdPitch()) {
            return convertThirdPitch();
        }
        return convertFinishedPitch();
    }

    private String convertThirdPitch() {
        if (isSpare(getFirstKnockedPins(), getSecondKnockedPins())) {
            return getFirstKnockedPins().convert() + "|" + "/";
        }
        return getFirstKnockedPins().convert() + "|" + getSecondKnockedPins().convert();
    }

    private String convertFinishedPitch() {
        if (isSpare(getFirstKnockedPins(), getSecondKnockedPins())) {
            return getFirstKnockedPins().convert() + "|" + "/" + "|" + getSecondKnockedPins().convert();
        }
        if (isSpare(getSecondKnockedPins(), getThirdKnockedPins())) {
            return getFirstKnockedPins().convert() + "|" + getSecondKnockedPins().convert() + "|" + "/";
        }
        return getFirstKnockedPins().convert() + "|" + getSecondKnockedPins().convert() + "|" + getThirdKnockedPins().convert();
    }

    private boolean isSecondPitch() {
        return pitches.size() == 1;
    }

    private KnockedPins getThirdKnockedPins() {
        return pitches.get(2).getKnockedPins();
    }

}

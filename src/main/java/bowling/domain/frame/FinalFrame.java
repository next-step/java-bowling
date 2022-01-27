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
        return new FinalFrame(playedPitches(knockedPins));
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

            return pitches.get(0).getKnockedPins().convert() + "|";
        }
        if (isSecondPitch()) {
            return pitches.get(0).getKnockedPins().convert() + "|";
        }
        if (isThirdPitch()) {
            if (isSpare(pitches.get(0).getKnockedPins(), pitches.get(1).getKnockedPins())) {
                return pitches.get(0).getKnockedPins().convert() + "|" + "/";
            }
            return pitches.get(0).getKnockedPins().convert() + "|" + pitches.get(1).getKnockedPins().convert();
        }
        if (isSpare(pitches.get(0).getKnockedPins(), pitches.get(1).getKnockedPins())) {
            return pitches.get(0).getKnockedPins().convert() + "|" + "/" + pitches.get(2).getKnockedPins().convert();
        }
        if (isSpare(pitches.get(1).getKnockedPins(), pitches.get(2).getKnockedPins())) {
            return pitches.get(0).getKnockedPins().convert() + "|" + pitches.get(1).getKnockedPins().convert() + "/";
        }
        return pitches.get(0).getKnockedPins().convert() + "|" + pitches.get(1).getKnockedPins().convert() + "|" + pitches.get(2).getKnockedPins().convert();
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
        return new Score(
                pitches.stream()
                        .map(Pitch::getKnockedPins)
                        .map(KnockedPins::getKnockedPins)
                        .reduce(0, Integer::sum),
                isPlaying() ? maxPitchesCount() - pitches.size() : 0
        );
    }

    @Override
    public Score additionalScore(Score beforeScore, List<Frame> frames) {
        final List<Pitch> limitedPitches = getPitches().stream()
                .limit(beforeScore.getLeft())
                .collect(Collectors.toList());

        for (final Pitch pitch : limitedPitches) {
            beforeScore = beforeScore.play(pitch.getKnockedPins().getKnockedPins());
        }

        return beforeScore;
    }

    public static DefaultFrame init() {
        return new FinalFrame(Collections.emptyList());
    }
}

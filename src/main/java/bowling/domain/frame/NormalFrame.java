package bowling.domain.frame;

import bowling.domain.Game;
import bowling.domain.KnockedPins;
import bowling.domain.Score;
import bowling.domain.pitch.Pitch;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class NormalFrame extends DefaultFrame {
    public NormalFrame(List<Pitch> pitches) {
        super(pitches);
    }

    @Override
    public Frame play(KnockedPins knockedPins) {
        return new NormalFrame(playedPitches(knockedPins));
    }

    @Override
    public Score calculateScore(Game game) {
        if (isPlaying()) {
            return playingScore();
        }

        Score score = createScore();

        if (score.canCalucateScore()) {
            return score;
        }

        return cacluateAdditionalScore(score, game.getFrames());
    }

    @Override
    public Score cacluateAdditionalScore(Score score, List<Frame> frames) {
        final Frame nextFrame = nextFrame(frames);
        if (nextFrame == null) {
            return score;
        }
        return nextFrame.additionalScore(score, frames);
    }

    @Override
    public Score additionalScore(Score beforeScore, List<Frame> frames) {
        final List<Pitch> limitedPitches = getPitches().stream()
                .limit(beforeScore.getLeft())
                .collect(Collectors.toList());

        for (final Pitch pitch : limitedPitches) {
            beforeScore = beforeScore.play(pitch.getKnockedPins().getKnockedPins());
        }

        if (beforeScore.canCalucateScore()) {
            return beforeScore;
        }

        return cacluateAdditionalScore(beforeScore, frames);
    }

    private Score createScore() {
        if (isStrike()) {
            return new Score(10, 2);
        }
        if (isSpare(pitches.get(0).getKnockedPins(), pitches.get(1).getKnockedPins())) {
            return new Score(10, 1);
        }
        return new Score(pitches.get(0).getKnockedPins().getKnockedPins() + pitches.get(1).getKnockedPins().getKnockedPins(), 0);
    }

    public boolean isStrike() {
        return pitches.get(0).getKnockedPins().equals(new KnockedPins(10));
    }

    @Override
    public boolean isSpare(KnockedPins knockedPinsA, KnockedPins knockedPinsB) {
        return knockedPinsA.add(knockedPinsB).equals(new KnockedPins(10));
    }


    @Override
    public boolean isPlaying() {
        return isFirstPitch() || isSecondPitch();
    }

    private boolean isSecondPitch() {
        return !pitches.isEmpty() && pitches.size() == 1 && !pitches.get(0).getKnockedPins().isStrike();
    }

    public static DefaultFrame init() {
        return new NormalFrame(Collections.emptyList());
    }

    private Frame nextFrame(List<Frame> frames) {
        try {
            return frames.get(frames.indexOf(this) + 1);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public int maxPitchesCount() {
        return 2;
    }
}

package bowling.domain.frame;

import bowling.domain.KnockedPins;
import bowling.domain.Score;
import bowling.domain.pitch.Pitch;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class NormalFrame extends DefaultFrame {
    private static final String FIRST_STRIKE_MESSAGE = "첫 번재 투구가 스트라이크인 경우, 두 번째 투구를 진행할 수 없습니다.";

    private NormalFrame(final List<Pitch> pitches) {
        super(pitches);
    }

    public static DefaultFrame init() {
        return new NormalFrame(Collections.emptyList());
    }

    @Override
    public boolean playing() {
        return !isPitchesFull() && !isFirstStrike();
    }

    @Override
    public DefaultFrame play(final int knockedPinsCount) {
        return play(KnockedPins.from(knockedPinsCount));
    }

    @Override
    public DefaultFrame play(final KnockedPins knockedPins) {
        validatePitchesFull();
        validateFirstStrike();

        return new NormalFrame(playedPitches(knockedPins));
    }

    private void validateFirstStrike() {
        if (isFirstStrike()) {
            throw new IllegalArgumentException(FIRST_STRIKE_MESSAGE);
        }
    }

    @Override
    public DefaultFrame next() {
        return NormalFrame.init();
    }

    @Override
    public DefaultFrame last() {
        return FinalFrame.init();
    }

    @Override
    protected int maxPitchesCount() {
        return 2;
    }

    @Override
    public Score score(List<Frame> frames) {
        if (playing()) {
            return playingScore();
        }

        final Score score = playedScore();

        if (score.calculable()) {
            return score;
        }

        return nextCalculatedScore(score, frames);
    }

    private Score playingScore() {
        return new Score(
                pitches().stream()
                        .map(Pitch::knockedPins)
                        .map(KnockedPins::count)
                        .reduce(0, Integer::sum),
                maxPitchesCount() - pitches().size()
        );
    }

    private Score playedScore() {
        if (isFirstStrike()) {
            return Score.strike();
        }

        if (isSecondSpare()) {
            return Score.spare();
        }

        return Score.open(
                pitches().stream()
                        .map(Pitch::knockedPins)
                        .map(KnockedPins::count)
                        .reduce(0, Integer::sum)
        );
    }

    @Override
    public Score additionalScore(Score beforeScore, List<Frame> frames) {
        final List<Pitch> limitedPitches = pitches().stream()
                .limit(beforeScore.left())
                .collect(Collectors.toList());

        for (final Pitch pitch : limitedPitches) {
            beforeScore = beforeScore.play(pitch);
        }

        if (beforeScore.calculable()) {
            return beforeScore;
        }

        return nextCalculatedScore(beforeScore, frames);
    }

    private Score nextCalculatedScore(Score score, List<Frame> frames) {
        final Frame nextFrame = nextFrame(frames);

        if (nextFrame == null) {
            return score;
        }

        return nextFrame.additionalScore(score, frames);
    }

    private Frame nextFrame(List<Frame> frames) {
        try {
            return frames.get(frames.indexOf(this) + 1);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
}

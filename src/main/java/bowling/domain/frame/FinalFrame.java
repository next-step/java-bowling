package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.score.ScoreCreator;
import java.util.ArrayList;
import java.util.List;

public class FinalFrame implements Frame {

    private static final String SEPARATOR = "|";
    private static final String NOT_OVER_MAX_PITCH_COUNT = "보통 프레임은 투구수가 3를 넘을 수 없습니다.";
    private static final String NOT_INPUT_FIRST_BOWL = "첫 투구를 하지 않았습니다.";

    private List<Score> scores;
    private int pitchCount;

    private FinalFrame(List<Score> scores, int pitchCount) {
        validateScores(scores);
        validatePitchCount(pitchCount);
        this.scores = scores;
        this.pitchCount = pitchCount;
    }

    public static Frame create() {
        List<Score> Scores = new ArrayList<>();
        return new FinalFrame(Scores, 0);
    }

    public static Frame create(List<Score> scores, int pitchCount) {
        return new FinalFrame(scores, pitchCount);
    }

    @Override
    public Frame bowl(Point point) {
        if (pitchCount == 0) {
            return firstBowl(point);
        }
        return nextBowl(point);
    }

    private Frame firstBowl(Point point) {
        Score score = ScoreCreator.create(point);
        scores.add(score);

        if (score.isStrike()) {
            return FinalFrame.create(scores, ++pitchCount);
        }

        return FinalFrame.create(scores, ++pitchCount);
    }

    private Frame nextBowl(Point point) {
        validateNextBowl();
        Score score = scores.get(pitchCount - 1);
        Score nextScore = score.nextScore(point);
        scores.add(nextScore);

        if (nextScore.isSpare()) {
            return FinalFrame.create(scores, ++pitchCount);
        }

        if (score.isStrike()) {
            return FinalFrame.create(scores, ++pitchCount);
        }

        return FinalFrame.create(scores, FINAL_MAX_BOWL_PITCH);
    }

    public String getScores() {
        String result = "";
        if (scores.size() == 3) {
            return scores.get(0).getScore() + SEPARATOR + scores.get(1).getScore() + SEPARATOR + scores.get(2).getScore();
        }

        if (scores.size() == 2) {
            return scores.get(0).getScore() + SEPARATOR + scores.get(1).getScore();
        }

        if (scores.size() == 1) {
            return scores.get(0).getScore();
        }

        return result;
    }

    public boolean isLastPitch() {
        return pitchCount == FINAL_MAX_BOWL_PITCH;
    }

    private void validateNextBowl() {
        if (scores.size() == 0) {
            throw new IllegalArgumentException(NOT_INPUT_FIRST_BOWL);
        }
    }

    private void validatePitchCount(int pitchCount) {
        if (pitchCount > FINAL_MAX_BOWL_PITCH) {
            throw new IllegalArgumentException(NOT_OVER_MAX_PITCH_COUNT);
        }
    }

    private void validateScores(List<Score> scores) {
        if (scores == null) {
            this.scores = new ArrayList<>();
        }
    }
}

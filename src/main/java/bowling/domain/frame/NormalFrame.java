package bowling.domain.frame;

import bowling.domain.point.Point;
import bowling.domain.score.Score;
import bowling.domain.score.ScoreCreator;

import java.util.ArrayList;
import java.util.List;

public class NormalFrame implements Frame {

    private List<Score> scores;
    private int pitchCount = 0;

    private NormalFrame(List<Score> scores, int pitchCount) {
        validateScores(scores);
        validatePitchCount(pitchCount);
        this.scores = scores;
        this.pitchCount = pitchCount;
    }

    public static Frame create(List<Score> scores, int pitchCount) {
        return new NormalFrame(scores, pitchCount);
    }

    @Override
    public Frame bowl(Point point) {
        if (pitchCount == 0) {
            return firstBowl(point);
        }
        return nextBowl(point);
    }

    public boolean isLastPitch() {
        return pitchCount == NORMAL_MAX_BOWL_PITCH;
    }

    private Frame firstBowl(Point point) {
        Score score = ScoreCreator.create(point);
        scores.add(score);

        if (score.isStrike()) {
            return NormalFrame.create(scores, NORMAL_MAX_BOWL_PITCH);
        }

        return NormalFrame.create(scores, ++pitchCount);
    }

    public List<Score> getScores() {
        return scores;
    }

    private Frame nextBowl(Point point) {
        validateNextBowl();
        Score score = scores.get(pitchCount - 1);
        Score nextScore = score.nextScore(point);
        scores.add(nextScore);
        return NormalFrame.create(scores, NORMAL_MAX_BOWL_PITCH);
    }

    private void validateNextBowl() {
        if (scores.size() == 0) {
            throw new IllegalArgumentException("첫 투구를 하지 않았습니다.");
        }
    }

    private void validatePitchCount(int pitchCount) {
        if (pitchCount > NORMAL_MAX_BOWL_PITCH) {
            throw new IllegalArgumentException("보통 프레임은 투구수가 2를 넘을 수 없습니다.");
        }
    }

    private void validateScores(List<Score> scores) {
        if (scores == null) {
            this.scores = new ArrayList<>();
        }
    }

}

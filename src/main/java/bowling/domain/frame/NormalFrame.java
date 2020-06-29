package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.score.ScoreCreator;

import java.util.ArrayList;
import java.util.List;

public class NormalFrame implements Frame {

    private static final String SEPARATOR = "|";

    private List<Score> scores;
    private int pitchCount = 0;

    private NormalFrame(List<Score> scores, int pitchCount) {
        validatePitchCount(pitchCount);
        this.scores = scores;
        this.pitchCount = pitchCount;
    }

    public static Frame create() {
        List<Score> Scores = new ArrayList<>();
        return new NormalFrame(Scores, 0);
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

    @Override
    public String getScores() {
        String result = "";
        if (scores.size() == FRAME_SECOND_PITCH_END) {
            return scores.get(0).getScore() + SEPARATOR + scores.get(1).getScore();
        }

        if (scores.size() == FRAME_FIRST_PITCH_END) {
            return scores.get(0).getScore();
        }

        return result;
    }

    @Override
    public String getPoint() {
        return null;
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

}

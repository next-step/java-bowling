package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.score.ScoreCreator;

import java.util.ArrayList;
import java.util.List;

public class FinalFrame implements Frame {

    private static final String SEPARATOR = "|";

    private List<Score> scores;
    private int pitchCount = 0;
    private int framePoint = 0;

    private FinalFrame(List<Score> scores, int framePoint, int pitchCount) {
        validateScores(scores);
        validatePitchCount(pitchCount);
        this.scores = scores;
        this.pitchCount = pitchCount;
        this.framePoint = framePoint;
    }

    public static Frame create() {
        List<Score> Scores = new ArrayList<>();
        return new FinalFrame(Scores, 0, 0);
    }

    public static Frame create(List<Score> scores, int framePoint, int pitchCount) {
        return new FinalFrame(scores, framePoint, pitchCount);
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
            int strikePoint = point.getPoint();
            return FinalFrame.create(scores, strikePoint, ++pitchCount);
        }

        return FinalFrame.create(scores, this.framePoint + point.getPoint(), ++pitchCount);
    }

    private Frame nextBowl(Point point) {
        validateNextBowl();
        Score score = scores.get(pitchCount - 1);
        Score nextScore = score.nextScore(point);
        scores.add(nextScore);

        if (nextScore.isSpare()) {
            int sparePoint = framePoint + point.getPoint();
            return FinalFrame.create(scores, sparePoint, ++pitchCount);
        }

        if (score.isStrike()) {
            int strikePoint = framePoint + point.getPoint();
            return FinalFrame.create(scores, strikePoint, ++pitchCount);
        }

        return FinalFrame.create(scores, framePoint + point.getPoint(), FINAL_MAX_BOWL_PITCH);
    }

    @Override
    public String getScores() {
        String result = "";
        if (scores.size() == FRAME_THIRD_PITCH_END) {
            return scores.get(0).getScore() + SEPARATOR + scores.get(1).getScore() + SEPARATOR + scores.get(2).getScore();
        }

        if (scores.size() == FRAME_SECOND_PITCH_END) {
            return scores.get(0).getScore() + SEPARATOR + scores.get(1).getScore();
        }

        if (scores.size() == FRAME_FIRST_PITCH_END) {
            return scores.get(0).getScore();
        }

        return result;
    }

    @Override
    public int getFramePoint() {
        if (pitchCount == FINAL_MAX_BOWL_PITCH) {
            return this.framePoint;
        }
        return 0;
    }

    public boolean isLastPitch() {
        return pitchCount == FINAL_MAX_BOWL_PITCH;
    }

    private void validateNextBowl() {
        if (scores.size() == 0) {
            throw new IllegalArgumentException("첫 투구를 하지 않았습니다.");
        }
    }

    private void validatePitchCount(int pitchCount) {
        if (pitchCount > FINAL_MAX_BOWL_PITCH) {
            throw new IllegalArgumentException("보통 프레임은 투구수가 3를 넘을 수 없습니다.");
        }
    }

    private void validateScores(List<Score> scores) {
        if (scores == null) {
            this.scores = new ArrayList<>();
        }
    }
}

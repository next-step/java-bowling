package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.score.ScoreCreator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NormalFrame implements Frame {

    private static final String SEPARATOR = "|";

    private List<Score> scores;
    private int pitchCount;
    private int framePoint;

    private NormalFrame(List<Score> scores, int framePoint, int pitchCount) {
        validatePitchCount(pitchCount);
        this.scores = scores;
        this.pitchCount = pitchCount;
        this.framePoint = framePoint;
    }

    public static Frame create() {
        List<Score> scores = new ArrayList<>();
        return new NormalFrame(scores, 0, 0);
    }

    public static Frame create(List<Score> scores, int framePoint, int pitchCount) {
        return new NormalFrame(scores, framePoint, pitchCount);
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
            int strikePoint = score.getPoint();
            return NormalFrame.create(scores, strikePoint, NORMAL_MAX_BOWL_PITCH);
        }

        return NormalFrame.create(scores, score.getPoint(), ++pitchCount);
    }

    private Frame nextBowl(Point point) {
        validateNextBowl();
        Score score = scores.get(pitchCount - 1);
        Score nextScore = score.nextScore(point);
        scores.add(nextScore);

        if (score.isSpare()) {
            int sparePoint = score.getPoint();
            NormalFrame.create(scores, this.framePoint + sparePoint, NORMAL_MAX_BOWL_PITCH);
        }

        return NormalFrame.create(scores, this.framePoint + score.getPoint(), NORMAL_MAX_BOWL_PITCH);
    }

    @Override
    public String getScores() {
        return scores.stream()
                .map(Score::getScore)
                .collect(Collectors.joining(SEPARATOR));
    }

    @Override
    public int getFramePoint() {
        if (pitchCount == NORMAL_MAX_BOWL_PITCH) {
            return this.framePoint;
        }
        return 0;
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

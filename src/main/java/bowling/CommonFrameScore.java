package bowling;

import java.util.ArrayList;
import java.util.List;

public class CommonFrameScore implements FrameScore {

    private static final int COMMON_FRAME_FULL_PITCH_COUNT = 2;

    private final List<Score> scores;

    public CommonFrameScore() {
        this.scores = new ArrayList<>();
    }

    public static CommonFrameScore newInstance(final List<Integer> scores) {
        CommonFrameScore frameScore = new CommonFrameScore();
        scores.forEach(frameScore::pitch);

        return frameScore;
    }

    @Override
    public void pitch(int scoreCount) {
        if (isOver()) {
            throw new RuntimeException("Can not pitch next bowl");
        }

        Score score = Score.of(scoreCount);

        if (scores.size() == 1) {
            score = secondPitchingScore(scoreCount);
        }

        scores.add(score);
    }

    @Override
    public int sum() {
        return Score.sum(scores);
    }

    @Override
    public boolean isOver() {
        if (scores.size() == COMMON_FRAME_FULL_PITCH_COUNT) {
            return true;
        }

        return scores.size() == COMMON_FRAME_FULL_PITCH_COUNT - 1 && Score.sum(scores) == 10;
    }

    private Score secondPitchingScore(final int scoreCount) {
        Score firstScore = scores.get(0);
        return firstScore.secondPitching(scoreCount);
    }
}

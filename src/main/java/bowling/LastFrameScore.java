package bowling;

import java.util.ArrayList;
import java.util.List;

public class LastFrameScore implements FrameScore {

    private static final int LAST_FRAME_FULL_PITCH_COUNT = 3;

    private final List<Score> scores;

    public LastFrameScore() {
        this.scores = new ArrayList<>();
    }

    public static LastFrameScore newInstance(final List<Integer> scores) {
        LastFrameScore frameScore = new LastFrameScore();
        scores.forEach(frameScore::pitch);

        return frameScore;
    }


    @Override
    public void pitch(int scoreCount) {
        if(isOver()) {
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
        if (scores.size() == LAST_FRAME_FULL_PITCH_COUNT) {
            return true;
        }

        return scores.size() == LAST_FRAME_FULL_PITCH_COUNT - 1 && Score.sum(scores) % 10 != 0;
    }

    private Score secondPitchingScore(final int scoreCount) {
        Score firstScore = scores.get(0);
        if (firstScore.isStrike()) {
            return Score.of(scoreCount);
        }

        return firstScore.secondPitching(scoreCount);
    }
}

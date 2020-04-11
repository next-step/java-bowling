package bowling.domain;

import java.util.List;

public class Score {
    private static final int FRAME_MAX_SCORE = 10;
    private static final int FRAME_MIN_SCORE = 0;
    private int score = 0;

    public Score(int score) {
        checkScore(score);
        this.score = score;
    }

    private void checkScore(int score) {
        if (score < FRAME_MIN_SCORE || score > FRAME_MAX_SCORE){
            throw new IllegalArgumentException(FRAME_MIN_SCORE + "~" + FRAME_MAX_SCORE + " 입력");
        }
    }

    public int getScore() {
        return score;
    }

    public boolean isMaxScore() {
        return score == FRAME_MAX_SCORE;
    }

    public boolean isMinScore() {
        return score == FRAME_MIN_SCORE;
    }

    public void checkScore(List<Score> scores) {
        int scoresSum = getScoresSum(scores);
        if(scoresSum > FRAME_MAX_SCORE){
            System.out.println(scoresSum);
            throw new IllegalArgumentException(FRAME_MAX_SCORE + "을 넘으면 안됩니다.");
        }
    }

    private int getScoresSum(List<Score> scores) {
        return scores.stream()
                    .mapToInt(Score::getScore)
                    .sum();
    }
}

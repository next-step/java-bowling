package bowling.domain.score;

import bowling.domain.frame.FrameNumber;

import java.util.List;

import static bowling.view.OutputView.BLANK;

public class Scores {
    private final List<Score> scores;

    public Scores(List<Score> scores) {
        this.scores = scores;
    }

    private Score getScoreOrNull(int index) {
        if (scores.size() - 1 >= index) {
            return scores.get(index);
        }
        return null;
    }

    @Override
    public String toString() {
        Score acc = new Score(0, 0);
        StringBuilder scoreBoard = new StringBuilder("|      |");

        for (int i = 0; i < FrameNumber.MAX; i++) {
            Score score = getScoreOrNull(i);

            if (score == null || !score.canScore()) {
                scoreBoard.append(BLANK);
                continue;
            }

            if (score.canScore()) {
                acc = acc.add(score);
                scoreBoard.append(acc);
            }
        }

        return scoreBoard.toString();
    }
}

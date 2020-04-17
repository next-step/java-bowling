package bowling.view;

import bowling.domain.score.Score;
import bowling.view.format.ScoreFormatter;

import java.util.List;

public class ScoreView {
    public static void print(final List<Score> scores) {
        System.out.print(OutputView.DELIMITER);
        System.out.print(ScoreFormatter.format(OutputView.EMPTY_STRING));
        System.out.print(OutputView.DELIMITER);

        for (Score score : scores) {
            System.out.print(ScoreFormatter.format(score.getValue()) + OutputView.DELIMITER);
        }

        fillEmptyString(scores.size());
        System.out.println();
    }

    private static void fillEmptyString(final int size) {
        for (int i = 0; i < 10 - size; i++) {
            System.out.print(ScoreFormatter.format(OutputView.EMPTY_STRING) + OutputView.DELIMITER);
        }
    }
}

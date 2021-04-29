package step4.view;

import step4.domain.*;

import java.util.List;
import java.util.stream.IntStream;

import static step4.domain.Score.UN_COUNTABLE_SCORE;

public class ResultView {
    private static final String PRINT_FORMAT = "  %-4s";
    private static final String SCORE_HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String DELIMETER = "|";
    private static final String BLANK = "";

    public void printResult(List<Name> names, List<Frames> frames, List<Scores> scores) {
        IntStream.range(0, names.size())
                .forEach(i -> {
                    printMark(names.get(i), frames.get(i).marks());
                    printScore(scores.get(i));
                });
    }

    private void printMark(Name name, List<String> marks) {
        System.out.println(SCORE_HEADER);
        System.out.print(DELIMETER);

        System.out.printf(PRINT_FORMAT, name.name());
        marks.forEach((symbols) -> System.out.print(DELIMETER + String.format(PRINT_FORMAT, symbols)));

        System.out.println(DELIMETER);
    }

    private void printScore(Scores scores) {
        System.out.print(DELIMETER);

        System.out.printf(PRINT_FORMAT, BLANK);
        scores.accumulatedScore().stream()
                .map(this::changeScoreFormat)
                .forEach((score) -> System.out.print(DELIMETER + String.format(PRINT_FORMAT, score)));

        System.out.println(DELIMETER);
    }

    private String changeScoreFormat(Integer currentScore) {
        if (currentScore == UN_COUNTABLE_SCORE) {
            return BLANK;
        }

        return String.valueOf(currentScore);
    }
}

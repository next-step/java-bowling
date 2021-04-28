package step4.view;

import step4.domain.Frames;
import step4.domain.Name;
import step4.domain.Score;
import step4.domain.Scores;

import java.util.List;
import java.util.stream.IntStream;

public class ResultView {
    private static final String PRINT_FORMAT = "  %-4s";
    private static final String SCORE_HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String DELIMETER = "|";
    private static final String BLANK = "";
    private static final int UN_COUNTABLE_SCORE = -1;

    public void printResult(List<Name> names, List<Frames> frames) {
        IntStream.range(0, names.size())
                .forEach(i -> {
                    printMark(names.get(i), frames.get(i).marks());
                    printScore(frames.get(i).scores());
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
        IntStream.range(0, scores.scores().size())
                .mapToObj((i) -> changeScoreFormat(i, scores))
                .forEach((score) -> System.out.print(DELIMETER + String.format(PRINT_FORMAT, score)));

        System.out.println(DELIMETER);
    }

    private String changeScoreFormat(int currentIndex, Scores scores) {
        if (scores.scores().get(currentIndex).value() == UN_COUNTABLE_SCORE) {
            return BLANK;
        }

        return String.valueOf(scores.scores()
                .stream()
                .limit(currentIndex + 1)
                .mapToInt(Score::value)
                .sum());
    }
}

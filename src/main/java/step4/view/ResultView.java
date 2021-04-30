package step4.view;

import step4.domain.BowlingGame;
import step4.domain.Frame;
import step4.domain.Frames;
import step4.domain.Name;

import java.util.List;

import static step4.domain.Score.UN_COUNTABLE_SCORE;

public class ResultView {
    private static final String PRINT_FORMAT = "  %-4s";
    private static final String SCORE_HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String DELIMETER = "|";
    private static final String BLANK = "";

    public void printResult(List<BowlingGame> bowlingGames) {
        bowlingGames.forEach(bowlingGame -> {
            printMark(bowlingGame.name(), bowlingGame.frames());
            printScore(bowlingGame);
        });
    }

    private void printMark(Name name, Frames frames) {
        System.out.println(SCORE_HEADER);
        System.out.print(DELIMETER);

        System.out.printf(PRINT_FORMAT, name.name());

        frames.frames()
                .stream()
                .map(Frame::states)
                .map(symbols -> String.join(DELIMETER, symbols))
                .forEach((symbols) -> System.out.print(DELIMETER + String.format(PRINT_FORMAT, symbols)));

        System.out.println(DELIMETER);
    }

    private void printScore(BowlingGame bowlingGame) {
        System.out.print(DELIMETER);

        System.out.printf(PRINT_FORMAT, BLANK);
        bowlingGame.scores()
                .accumulatedScore().stream()
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

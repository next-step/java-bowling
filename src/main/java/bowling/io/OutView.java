package bowling.io;

import bowling.model.Board;
import bowling.model.Player;
import bowling.model.frame.FrameNumber;
import bowling.model.frame.Results;
import bowling.utils.Pretty;

import java.util.List;
import java.util.stream.IntStream;

import static bowling.model.frame.state.Score.DEFAULT_SCORE;
import static bowling.utils.Pretty.EMPTY;
import static bowling.utils.Pretty.PARTITION;

public class OutView {

    private static final String MESSAGE_OF_HEADER = "|  NAME  |   01   |   02   |   03   |   04   |   05   |   06   |   07   |   08   |   09   |   10   |";
    private static final String MESSAGE_OF_GAME_OVER = "게임이 종료되었습니다";

    public static void printProgress(List<Board> boards) {
        System.out.println(MESSAGE_OF_HEADER);

        for (Board board : boards) {
            printProgress(board);
        }
    }

    private static void printProgress(Board board) {
        Results bowlingResults = board.getBowlingResults();
        System.out.println(getCurrentStates(board.getPlayer(), bowlingResults.getStates()));
        System.out.println(getCurrentScores(bowlingResults.
                getScores()));
        System.out.println();
    }

    private static String getCurrentStates(Player player, List<String> states) {
        StringBuilder stringBuilder = new StringBuilder();
        settingLeftColumn(stringBuilder, player.toString());

        states.stream()
                .map(Pretty::alignCenter)
                .map(frame -> frame.concat(PARTITION))
                .forEach(stringBuilder::append);

        printEmpty(stringBuilder, states.size());

        return stringBuilder.toString();
    }

    private static String getCurrentScores(List<Integer> scores) {
        StringBuilder stringBuilder = new StringBuilder();
        settingLeftColumn(stringBuilder, EMPTY);

        scores.stream()
                .map(OutView::formatScore)
                .map(Pretty::alignCenter)
                .map(frame -> frame.concat(PARTITION))
                .forEach(stringBuilder::append);

        printEmpty(stringBuilder, scores.size());

        return stringBuilder.toString();
    }

    private static String formatScore(Integer score) {
        if (DEFAULT_SCORE == score) {
            return EMPTY;
        }
        return score.toString();
    }

    private static void settingLeftColumn(StringBuilder stringBuilder, String info) {
        stringBuilder.append(PARTITION);
        stringBuilder.append(Pretty.alignCenter(info));
        stringBuilder.append(PARTITION);
    }

    private static void printEmpty(StringBuilder stringBuilder, int size) {
        IntStream.range(size, FrameNumber.MAX)
                .mapToObj((ignore) -> Pretty.alignCenter(EMPTY).concat(PARTITION))
                .forEach(stringBuilder::append);
    }

    public static void printGameOver() {
        System.out.println(MESSAGE_OF_GAME_OVER);
    }
}
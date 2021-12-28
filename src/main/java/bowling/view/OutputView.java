package bowling.view;

import bowling.domain.BowlingGame;
import bowling.domain.Frame;
import bowling.domain.Score;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OutputView {
    private static final String MAIN_BOARD_HEAD_MESSAGE = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String SCORE_BOARD_NAME_TEMPLATE = "|  %-4s|";
    private static final String SCORE_BOARD_EMPTY_TEMPLATE = "|      |";
    private static final String SCORE_BOARD_MARK_TEMPLATE = "  %-4s|";
    private static final String SCORE_BOARD_SCORE_TEMPLATE = "  %-4d|";

    private static final String EMPTY = "";
    private static final StringBuilder sb = new StringBuilder();

    private static final int ZERO = 0;
    private static final int TEN = 10;

    private OutputView() {}

    public static void printBowlingBoard(List<BowlingGame> bowlingGames) {
        System.out.println(MAIN_BOARD_HEAD_MESSAGE);
        bowlingGames.forEach(OutputView::printMainBoardBody);
        System.out.println();
    }

    private static void printMainBoardBody(BowlingGame bowlingGame) {
        printScoreMark(bowlingGame);
        printScore(bowlingGame);
    }

    private static void printScoreMark(BowlingGame bowlingGame) {
        clearStringBuilder();
        sb.append(String.format(SCORE_BOARD_NAME_TEMPLATE, bowlingGame.player().name()));
        sb.append(IntStream.range(ZERO, TEN)
                .mapToObj(index -> String.format(SCORE_BOARD_MARK_TEMPLATE, scoreMark(index, bowlingGame)))
                .collect(Collectors.joining()));

        System.out.println(sb);
    }

    private static String scoreMark(int index, BowlingGame bowlingGame) {
        if (index < bowlingGame.frames().size()) {
            Frame frame = bowlingGame.frames().get(index);
            return frame.getState().display();
        }

        return EMPTY;
    }

    private static void printScore(BowlingGame bowlingGame) {
        clearStringBuilder();
        sb.append(String.format(SCORE_BOARD_EMPTY_TEMPLATE));
        int prevScore = 0;
        for (int index = ZERO; index < TEN; index++) {
            String score = score(index, bowlingGame);
            prevScore = sumScore(prevScore, score);
        }
        System.out.println(sb);
    }

    private static String score(int index, BowlingGame bowlingGame) {
        if (index < bowlingGame.frames().size()) {
            Frame frame = bowlingGame.frames().get(index);
            return makeScore(frame);
        }
        return EMPTY;
    }

    private static int sumScore(int prevScore, String score) {
        if (!score.isEmpty()) {
            prevScore += Integer.parseInt(score);
            sb.append(String.format(SCORE_BOARD_SCORE_TEMPLATE, prevScore));
            return prevScore;
        }
        sb.append(String.format(SCORE_BOARD_MARK_TEMPLATE, score));
        return prevScore;
    }

    private static String makeScore(Frame frame) {
        if (frame.isFinalFrame()) {
            return makeFinalScore(frame);
        }
        return makeNormalScore(frame);
    }

    private static String makeFinalScore(Frame frame) {
        if (frame.isEnd()) {
            return toScore(frame.getScore());
        }
        return EMPTY;
    }

    private static String toScore(Score score) {
        if (score.canCalculateScore()) {
            return EMPTY + score.getScore();
        }
        return EMPTY;
    }

    private static String makeNormalScore(Frame frame) {
        if (frame.isFinished()) {
            return toScore(frame.getScore());
        }
        return EMPTY;
    }

    private static void clearStringBuilder() {
        sb.setLength(ZERO);
    }
}

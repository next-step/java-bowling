package bowling.view;

import bowling.domain.*;
import bowling.domain.state.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OutputView {
    private static final String MAIN_BOARD_HEAD_MESSAGE = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String SCORE_BOARD_NAME_TEMPLATE = "|  %-4s|";
    private static final String SCORE_BOARD_EMPTY_TEMPLATE = "|      |";
    private static final String SCORE_BOARD_MARK_TEMPLATE = "  %-4s|";
    private static final String SCORE_BOARD_SCORE_TEMPLATE = "  %-4d|";

    private static final String STRIKE_MARK = "X";
    private static final String SPARE_MARK = "/";
    private static final String GUTTER_MARK = "-";
    private static final String SEPARATOR = "|";
    private static final String EMPTY = "";
    private static final StringBuilder sb = new StringBuilder();

    private static final int ZERO = 0;
    private static final int TWO = 2;
    private static final int TEN = 10;

    private OutputView() {}

    public static void printBowlingBoard(BowlingGames bowlingGames) {
        System.out.println(MAIN_BOARD_HEAD_MESSAGE);
        bowlingGames.values()
                .forEach(OutputView::printMainBoardBody);
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
            return makeScoreMark(frame);
        }

        return EMPTY;
    }

    private static String makeScoreMark(Frame frame) {
        if (frame.isFinalFrame()) {
            return makeFinalScoreMark(frame.getState());
        }
        return makeScoreMark(frame.getState());
    }

    private static String makeFinalScoreMark(State state) {
        if (state instanceof Bonus && isSpare(state)) {
            List<KnockedPinCount> values = ((Bonus) state).getValues();
            return toMark(values.get(ZERO).value()) + SEPARATOR + SPARE_MARK + SEPARATOR + toMark(values.get(TWO).value());
        }
        return makeScoreMark(state);
    }

    private static boolean isSpare(State state) {
        return ((Bonus) state).getPrevious() instanceof Spare;
    }

    private static String makeScoreMark(State state) {
        if (state instanceof Finished) {
            return makeJoiningScoreMark((Finished) state);
        }

        if (state instanceof Running) {
            return makeOneScoreMark((Running) state);
        }

        return EMPTY;
    }

    private static String makeJoiningScoreMark(Finished state) {
        if (state instanceof Spare) {
            return toMark(state.getValues().get(ZERO).value()) + SEPARATOR + SPARE_MARK;
        }

        return state.getValues().stream()
                .map(knockedPinCount -> toMark(knockedPinCount.value()))
                .collect(Collectors.joining(SEPARATOR));
    }

    private static String makeOneScoreMark(Running state) {
        return toMark(state.getFirst());
    }

    private static String toMark(int knockOutCount) {
        if (knockOutCount == TEN) {
            return STRIKE_MARK;
        }
        if (knockOutCount == ZERO) {
            return GUTTER_MARK;
        }
        return String.valueOf(knockOutCount);
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
        if (frame.getState() instanceof Finished) {
            return toScore(frame.getScore());
        }
        return EMPTY;
    }

    private static void clearStringBuilder() {
        sb.setLength(ZERO);
    }
}

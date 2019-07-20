package com.jaeyeonling.bowling.view;

import com.jaeyeonling.bowling.domain.BowlingGame;
import com.jaeyeonling.bowling.domain.BowlingGames;
import com.jaeyeonling.bowling.domain.BowlingSymbol;
import com.jaeyeonling.bowling.domain.frame.Frame;
import com.jaeyeonling.bowling.domain.frame.Frames;
import com.jaeyeonling.bowling.domain.frame.score.FrameScore;
import com.jaeyeonling.bowling.domain.frame.state.FrameState;
import com.jaeyeonling.bowling.domain.user.User;
import com.jaeyeonling.bowling.domain.user.Username;
import com.jaeyeonling.bowling.utils.BowlingUtils;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.System.lineSeparator;

public final class ConsoleOutputView {

    private static final String BOWLING_GAME_HEADER =
            "|  NAME |  01   |  02   |  03   |  04   |  05   |  06   |  07   |  08   |  09   |  10   |";
    private static final String SCORE_PREFIX = "|       |";
    private static final int DEFAULT_SCORE = 0;
    private static final String EMPTY = "";
    private static final String RESULT_MESSAGE = "게임이 끝났습니다.";

    private static final PrintStream CONSOLE = System.out;

    private ConsoleOutputView() { }

    public static void printBowlingGames(final BowlingGames bowlingGames) {
        println(BOWLING_GAME_HEADER);
        println(format(bowlingGames));
        newline();
    }

    public static void printResult(final BowlingGames bowlingGames) {
        printBowlingGames(bowlingGames);
        newline();
        println(RESULT_MESSAGE);
    }

    static void print(final Object message) {
        CONSOLE.print(message);
    }

    private static void println(final Object message) {
        CONSOLE.println(message);
    }

    private static void newline() {
        CONSOLE.println();
    }

    private static String format(final BowlingGames bowlingGames) {
        return bowlingGames.getBowlingGames()
                .stream()
                .map(ConsoleOutputView::format)
                .collect(Collectors.joining(lineSeparator()));
    }

    private static String format(final BowlingGame bowlingGame) {
        return format(bowlingGame.getUser()) + format(bowlingGame.getFrames());
    }

    private static String format(final User user) {
        return format(user.getUsername());
    }

    private static String format(final Username username) {
        return BowlingSymbol.DELIMITER + BowlingUtils.format(username.getUsername());
    }

    private static String format(final Frames frames) {
        return format(frames.getFrames());
    }

    private static String format(final List<Frame> frames) {
        return formatFrameStates(frames) + lineSeparator() + formatFrameScores(frames);
    }

    private static String formatFrameStates(final List<Frame> frames) {
        return frames.stream()
                .map(Frame::getFrameState)
                .map(ConsoleOutputView::format)
                .collect(Collectors.joining());
    }

    private static String format(final FrameState frameState) {
        return BowlingUtils.format(frameState.toSymbol());
    }

    private static String formatFrameScores(final List<Frame> frames) {
        return SCORE_PREFIX + parseFrameScores(frames).stream()
                .map(BowlingUtils::format)
                .collect(Collectors.joining());
    }

    private static List<String> parseFrameScores(final List<Frame> frames) {
        List<String> scores = new ArrayList<>();

        FrameScore totalFrameScore = FrameScore.of(DEFAULT_SCORE);
        for (final Frame frame : frames) {
            FrameScore frameScore = frame.getFrameScore();
            totalFrameScore = totalFrameScore.sum(frameScore);

            scores.add(format(totalFrameScore));
        }

        return scores;
    }

    private static String format(final FrameScore frameScore) {
        if (frameScore.isComplete()) {
            return String.valueOf(frameScore.getScore());
        }

        return EMPTY;
    }
}

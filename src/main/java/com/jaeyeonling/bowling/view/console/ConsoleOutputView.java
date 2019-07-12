package com.jaeyeonling.bowling.view.console;

import com.jaeyeonling.bowling.domain.frame.BowlingSymbol;
import com.jaeyeonling.bowling.domain.frame.Frame;
import com.jaeyeonling.bowling.domain.game.BowlingGame;
import com.jaeyeonling.bowling.domain.user.User;
import com.jaeyeonling.bowling.domain.user.Username;
import com.jaeyeonling.bowling.utils.BowlingUtils;

import java.io.PrintStream;

public final class ConsoleOutputView {

    private static final String BOWLING_GAME_HEADER =
            "|  NAME |  01   |  02   |  03   |  04   |  05   |  06   |  07   |  08   |  09   |  10   |";
    private static final String RESULT_MESSAGE = "게임이 끝났습니다.";

    private static final PrintStream CONSOLE = System.out;

    private ConsoleOutputView() { }

    public static void printBowlingGame(final BowlingGame bowlingGame) {
        println(BOWLING_GAME_HEADER);
        print(format(bowlingGame.getUser()));
        println(format(bowlingGame.getFirstFrame()));
        newline();
    }

    public static void printResult(final BowlingGame bowlingGame) {
        printBowlingGame(bowlingGame);
        newline();
        println(RESULT_MESSAGE);
    }

    static void print(final Object message) {
        CONSOLE.print(message);
    }

    static void println(final Object message) {
        CONSOLE.println(message);
    }

    private static void newline() {
        CONSOLE.println();
    }

    private static String format(final User user) {
        return BowlingSymbol.DELIMITER + BowlingUtils.format(format(user.getUsername())) + BowlingSymbol.DELIMITER;
    }

    private static String format(final Username username) {
        return username.getUsername();
    }

    private static String format(final Frame frame) {
        return frame.getFrameState();
    }
}

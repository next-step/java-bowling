package com.jaeyeonling.bowling.view.console;

import com.jaeyeonling.bowling.view.Visualizable;

import java.io.PrintStream;

public final class ConsoleOutputView {

    private static final String BOWLING_GAME_HEADER =
            "|  NAME |  01   |  02   |  03   |  04   |  05   |  06   |  07   |  08   |  09   |  10   |";
    private static final String RESULT_MESSAGE = "게임이 끝났습니다.";

    private static final PrintStream CONSOLE = System.out;

    private ConsoleOutputView() { }

    public static void printBowlingGame(final Visualizable<?> visualizer) {
        println(BOWLING_GAME_HEADER);
        println(visualizer.visualize());
        newline();
    }

    public static void printResult(final Visualizable<?> visualizer) {
        printBowlingGame(visualizer);
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
}

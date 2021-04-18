package bowling.view;

import bowling.service.BowlingGame;

import java.io.PrintStream;

public class ResultView {

    private final PrintStream out;

    public ResultView() {
        this.out = new PrintStream(System.out);
    }

    public void printBoard(String name) {
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
        System.out.printf("|  %s |      |      |      |      |      |      |      |      |      |      |", name);
        System.out.println();

    }

    public void printResult(BowlingGame bowling) {

    }
}

package bowling.view;

import bowling.domain.Renderer;

import java.io.PrintStream;
import java.util.Objects;

public class OutputView {

    private final PrintStream printStream;

    public OutputView(PrintStream printStream) {
        this.printStream = Objects.requireNonNull(printStream);
    }

    public void printPlayerNameInputMessage() {
        printStream.print("플레이어 이름은(3 english letters)?: ");
    }

    public void printBowlInputMessage(int frameNumber) {
        printStream.printf("%s프레임 투구 : ", frameNumber);
    }

    public void printScoreBoard(Renderer renderer) {
        printStream.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
        printStream.println(renderer.render());
        printStream.println();
    }
}

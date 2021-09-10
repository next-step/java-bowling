package bowling.view;

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
}

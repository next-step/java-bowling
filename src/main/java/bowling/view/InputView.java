package bowling.view;

import bowling.utility.Assert;

import java.io.PrintStream;
import java.util.Scanner;

public final class InputView {

    private static final String PARTICIPANT_INPUT_MESSAGE = "플레이어 이름은(3 english letters)?: ";

    private static final String NEXT_FRAME_MESSAGE_FORMAT = "%s프레임 투구 : ";

    private final PrintStream guidePrinter;
    private final Scanner scanner;

    private InputView(Scanner scanner, PrintStream guidePrinter) {
        Assert.notNull(scanner, "scanner must not be null");
        Assert.notNull(guidePrinter, "guidePrinter must not be null");
        this.scanner = scanner;
        this.guidePrinter = guidePrinter;
    }

    public static InputView of(Scanner scanner, PrintStream output) {
        return new InputView(scanner, output);
    }

    public String participant() {
        guidePrinter.print(PARTICIPANT_INPUT_MESSAGE);
        return scanner.next();
    }

    public int nextPitch(int frameNumber) {
        guidePrinter.printf(NEXT_FRAME_MESSAGE_FORMAT, frameNumber);
        return scanner.nextInt();
    }
}


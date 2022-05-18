package bowling.view;

import bowling.utility.Assert;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class InputView {

    private static final String PARTICIPANT_COUNT_INPUT_MESSAGE = "How many people?";
    private static final String PARTICIPANT_INPUT_MESSAGE_FORMAT = "플레이어 %d의 이름은?(3 english letters): ";
    private static final String NEXT_FRAME_MESSAGE_FORMAT = "%s's turn : ";
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

    public List<String> participants() {
        guidePrinter.print(PARTICIPANT_COUNT_INPUT_MESSAGE);
        return IntStream.rangeClosed(1, scanner.nextInt())
                .mapToObj(i -> {
                    guidePrinter.printf(PARTICIPANT_INPUT_MESSAGE_FORMAT, i);
                    return scanner.next();
                }).collect(Collectors.toList());
    }

    public int nextPitch(String participant) {
        guidePrinter.printf(NEXT_FRAME_MESSAGE_FORMAT, participant);
        return scanner.nextInt();
    }
}


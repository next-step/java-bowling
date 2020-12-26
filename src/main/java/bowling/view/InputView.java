package bowling.view;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InputView {

    private static final String INPUT_NAME_MESSAGE = "플레이어 이름은(3 english letters)?: ";
    private static final String INPUT_PIN_COUNT = "%s 프레임 투구 : ";

    private static final Scanner SCANNER;

    static {
        SCANNER = new Scanner(System.in);
    }

    private InputView() {}

    public static List<String> enterUserNames() {
        return IntStream.range(0, 1)
                .mapToObj(index -> enterUserName())
                .collect(Collectors.toList());
    }

    private static String enterUserName() {
        System.out.print(INPUT_NAME_MESSAGE);
        return SCANNER.nextLine();
    }

    public static int enterPinCount(int frameNumber) {
        System.out.printf(INPUT_PIN_COUNT, frameNumber);
        return SCANNER.nextInt();
    }
}

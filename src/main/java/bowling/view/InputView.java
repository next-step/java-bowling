package bowling.view;

import java.text.MessageFormat;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class InputView {

    private static final String INPUT_PLAYER_COUNT = "Input player count : ";
    private static final String INPUT_PLAYER_NAME = "Input player {0} name (* 3 English letters): ";
    private static final String INPUT_DRAW_PINS = "Input draw result  : ";

    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> InputPlayerNames() {
        System.out.println(INPUT_PLAYER_COUNT);
        // 1(inclusive) to N(inclusive)
        return IntStream.rangeClosed(1, Integer.parseInt(scanner.nextLine()))
                .mapToObj(InputView::InputPlayerName)
                .collect(toList());
    }

    private static String InputPlayerName(int count) {
        System.out.println(MessageFormat.format(INPUT_PLAYER_NAME, count));
        return scanner.nextLine();
    }

    public static int inputDrawPins(int index) {
        System.out.println(index + 1 + ":" + INPUT_DRAW_PINS);
        return scanner.nextInt();
    }
    public static int getFelled() {
        return scanner.nextInt();
    }
}

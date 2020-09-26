package bowling.view;

import java.text.MessageFormat;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class InputView {

    private static final String NAME_OF_PLAYER = "플레이어 {0}의 이름은(3 English Letters)?: ";
    private static final String NUMBER_OF_PLAYER = "How many people? ";

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static List<String> getUserNames() {
        System.out.print(NUMBER_OF_PLAYER);
        return IntStream.rangeClosed(1, Integer.parseInt(scanner.nextLine()))
                .mapToObj(InputView::getUserName)
                .collect(toList());
    }

    public static String getUserName(int number) {
        System.out.print(MessageFormat.format(NAME_OF_PLAYER, number));
        return scanner.nextLine();
    }

    public static int getFelledPin() {
        return scanner.nextInt();
    }

}

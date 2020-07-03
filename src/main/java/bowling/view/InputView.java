package bowling.view;

import static java.util.stream.Collectors.toList;

import java.text.MessageFormat;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class InputView {

    private InputView() {
    }

    private static final String COUNT_OF_PLAYER = "How many people?";
    private static final String NAME_OF_PLAYER = "플레이어 {0}의 이름은(3 english letters)?:";
    private static final Scanner scanner = new Scanner(System.in);
    private static final Scanner scanner2 = new Scanner(System.in);

    public static List<String> getUserNames() {
        System.out.print(COUNT_OF_PLAYER);
        return IntStream.rangeClosed(1, Integer.parseInt(scanner.nextLine()))
            .mapToObj(InputView::getUserName)
            .collect(toList());
    }

    private static String getUserName(int count) {
        System.out.print(MessageFormat.format(NAME_OF_PLAYER, count));
        return scanner2.nextLine();
    }


    public static int getFelled() {
        return scanner.nextInt();
    }

}

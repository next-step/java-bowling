package bowling.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String INPUT_NAME_PHRASE = "플레이어 이름은(3 english letters)?: ";
    private static final String INPUT_MULTIPLE_NAME_PHRASE = "플레이어%d 이름은(3 english letters)?: ";
    private static final String INPUT_FALLEN_PINS_PHRASE = "%d프레임 투구 : ";
    private static final String INPUT_FALLEN_PINS_WITH_NAME_PHRASE = "%s's turn : ";
    private static final String INPUT_COUNT_OF_USERS_PHRASE = "How many people? ";

    private static final Scanner scanner = new Scanner(System.in);

    public static String getUserName() {
        System.out.print(INPUT_NAME_PHRASE);
        return scanner.nextLine();
    }

    public static int getFallenPins(int frameNo) {
        System.out.printf(INPUT_FALLEN_PINS_PHRASE, frameNo);
        return scanner.nextInt();
    }

    public static int getFallenPins(String name) {
        System.out.printf(INPUT_FALLEN_PINS_WITH_NAME_PHRASE, name);
        return scanner.nextInt();
    }

    public static int getCountOfUsers() {
        System.out.print(INPUT_COUNT_OF_USERS_PHRASE);
        return Integer.parseInt(scanner.nextLine());
    }

    public static List<String> getUserNames(int countOfUsers) {
        List<String> userNames = new ArrayList<>();
        for (int i = 1; i <= countOfUsers; i++) {
            System.out.printf(INPUT_MULTIPLE_NAME_PHRASE, i);
            userNames.add(scanner.nextLine());
        }

        return userNames;
    }

}

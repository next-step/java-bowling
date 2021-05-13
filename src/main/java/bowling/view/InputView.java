package bowling.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class InputView {
    private static final String ASK_INPUT_PLAYER_NUM = "How many people? ";
    private static final String ASK_INPUT_NAME = "플레이어 %d의 이름은(3 english letters)? : ";
    private static final String ASK_INPUT_HIT = "%s's turn : ";
    private static Scanner sc;

    static {
        sc = new Scanner(System.in);
    }

    public static List<String> inputName() {
        ArrayList<String> names = new ArrayList<>();
        int playerNum = inputPlayerNum();
        for (int i = 1; i <= playerNum; i++) {
            System.out.print(String.format(ASK_INPUT_NAME, i));
            names.add(sc.nextLine().trim());
        }
        return names;
    }

    public static Integer inputHitNumber(String name) {
        System.out.print(String.format(ASK_INPUT_HIT, name));
        return parseInt(sc.nextLine().trim());
    }

    public static void close() {
        sc.close();
    }

    private static int inputPlayerNum() {
        System.out.println(ASK_INPUT_PLAYER_NUM);
        return parseInt(sc.nextLine().trim());
    }
}

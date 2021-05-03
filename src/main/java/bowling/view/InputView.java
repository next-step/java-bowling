package bowling.view;

import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class InputView {
    private static final String ASK_INPUT_NAME = "플레이어 이름은(3 english letters)?:";
    private static final String ASK_INPUT_HIT = "%d프레임 투구 :";
    private static Scanner sc;
    static {
        sc = new Scanner(System.in);
    }
    
    public static String inputName() {
        System.out.print(ASK_INPUT_NAME);
        return sc.nextLine().trim();
    }

    public static Integer inputHitNumber(int index) {
        System.out.print(String.format(ASK_INPUT_HIT, index));
        return parseInt(sc.nextLine().trim());
    }

    public static void close() {
        sc.close();
    }
}

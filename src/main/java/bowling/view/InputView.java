package bowling.view;

import java.util.Scanner;

public class InputView {
    private static final String NAME_MESSAGE = "플레이어 이름은(3 english letters)?:";
    private static final String PITCHING_MESSAGE = "%d프레임 투구 : ";

    public static String getName() {
        System.out.println(NAME_MESSAGE);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static int getPitching(int frame) {
        System.out.println(String.format(PITCHING_MESSAGE, frame));
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private InputView() {
    }
}

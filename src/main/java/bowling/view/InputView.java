package bowling.view;

import java.util.Scanner;

public final class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String ASK_PLAYER_NAME = "플레이어 이름은(3 english letters)?";
    private static final String ASK_FIRST_BOWL = "%d프레임 투구 : ";

    private InputView() {
    }

    public static String scanPlayer() {
        System.out.println(ASK_PLAYER_NAME);

        return scanner.nextLine();
    }

    public static String scanBowl(int frameNumber) {
        System.out.println(String.format(ASK_FIRST_BOWL, frameNumber));

        return scanner.nextLine();
    }
}


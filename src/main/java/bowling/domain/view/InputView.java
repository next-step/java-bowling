package bowling.domain.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static String getPlayer() {
        System.out.println("\n플레이어 이름은(3 english letters)?:");
        return scanner.nextLine();
    }

    public static int getPitch(int frameCount) {
        System.out.println("\n" + frameCount + "프레임 투구 :");
        return Integer.parseInt(scanner.nextLine());
    }


}

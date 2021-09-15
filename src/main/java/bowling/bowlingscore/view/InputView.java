package bowling.bowlingscore.view;

import bowling.bowlingscore.domain.Player;

import java.util.Scanner;

public class InputView {

    public static final Scanner scanner = new Scanner(System.in);

    public static String inputPlayer() {
        System.out.print("플레이어 이름은(3 english letters)? : ");
        return scanner.nextLine();
    }

    public static int inputPins(Player player) {
        System.out.printf("%d 프레임 투구 : ", player.currentFrame());
        String pinsString = scanner.nextLine();
        try {
            return Integer.parseInt(pinsString);
        } catch (NumberFormatException e) {
            System.out.println("숫자만 입력 가능 합니다.");
            System.exit(0);
            return 0;
        } finally {
            System.out.println();
        }
    }

}

package bowling.bowlingdrawing.view;

import bowling.bowlingdrawing.domain.Player;

import java.util.Scanner;

public class InputView {

    public static final Scanner scanner = new Scanner(System.in);

    public static String inputPlayer() {
        return scanner.nextLine();
    }

    public static int inputPins(Player player) {
        System.out.printf("%d 프레임 투구 : ", player.currentFrame());
        String pinsString = scanner.nextLine();
        return Integer.parseInt(pinsString);
    }

}

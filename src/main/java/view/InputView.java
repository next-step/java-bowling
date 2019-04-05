package view;

import domain.PlayerName;
import domain.pin.Pin;
import util.StringUtils;

import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static PlayerName getPlayerName() {
        showInputPlayerNameMessage();
        return new PlayerName(getInputLine());
    }

    private static void showInputPlayerNameMessage() {
        System.out.print("플레이어 이름은(3 english letters)?: ");
    }

    public static int getBowl(int currentFrameNumber) {
        showBowlInputMessage(currentFrameNumber);
        return Integer.parseInt(getInputLine());
    }

    private static void showBowlInputMessage(int currentFrameNumber) {
        System.out.print(String.format("%d프레임 투구: ", currentFrameNumber));
    }

    private static String getInputLine() {
        String inputLine = scanner.nextLine();

        if(StringUtils.isEmpty(inputLine)) {
            throw new IllegalArgumentException("잘못된 입력값입니다.");
        }

        return inputLine;
    }
}
package view;

import domain.PlayerName;
import util.StringUtils;

import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static PlayerName getPlayerName() {
        showInputPlayerNameMessage();
        try {
            return new PlayerName(getInputLine());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println();
            return getPlayerName();
        }
    }
    private static void showInputPlayerNameMessage() {
        System.out.print("플레이어 이름은(3 english letters)? ");
    }

    public static int getBowl(int frameNumber) {
        showBowlInputMessage(frameNumber);
        return Integer.parseInt(getInputLine());
    }

    private static void showBowlInputMessage(int frameNumber) {
        System.out.print(String.format("%02d 프레임 투구: ", frameNumber));
    }

    private static String getInputLine() {
        String inputLine = scanner.nextLine();

        if (StringUtils.isEmpty(inputLine)) {
            throw new IllegalArgumentException("잘못된 입력값입니다.");
        }

        return inputLine;
    }
}
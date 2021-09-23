package bowling.bowlingplayers.view;

import bowling.bowlingplayers.domain.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {

    public static final Scanner scanner = new Scanner(System.in);

    public static List<String> inputPlayers() {
        List<String> nameString = new ArrayList<>();

        System.out.print("총 참여 인원은? ");
        int numberOfPlayers = inputInteger();

        for (int i = 0; i < numberOfPlayers; i++) {
            String name = inputPlayer();
            nameString.add(name);
        }

        return nameString;
    }

    private static int inputInteger() {
        String integerString = scanner.nextLine();
        try {
            return Integer.parseInt(integerString);
        } catch (NumberFormatException e) {
            System.out.println("숫자만 입력 가능 합니다.");
            System.exit(0);
            return 0;
        } finally {
            System.out.println();
        }
    }

    public static String inputPlayer() {
        System.out.print("플레이어 이름은(3 english letters)? : ");
        return scanner.nextLine();
    }

    public static int inputPins(Player player) {
        System.out.printf("%s 의 투구 : ", player.name());
        return inputInteger();
    }
}

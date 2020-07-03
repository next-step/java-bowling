package bowling.view;

import bowling.player.domain.Player;
import bowling.player.vo.Name;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static int inputNumberOfPlayer() {
        System.out.print("How many people? ");
        int numberOfPlayer = SCANNER.nextInt();
        SCANNER.nextLine();
        return numberOfPlayer;
    }

    public static List<String> inputNames(int numberOfPlayer) {
        List<String> names = new ArrayList<>();

        for (int playerNumber = 1; playerNumber <= numberOfPlayer; playerNumber++) {
            names.add(inputName(playerNumber));
        }

        return names;
    }

    public static String inputName(int playerNumber) {
        System.out.print(String.format("플레이어%d 이름은(3 english letters)?: ", playerNumber));
        return SCANNER.nextLine();
    }

    public static int inputPinCount(Name playerName) {
        System.out.print(String.format("%s's turn : ", playerName));
        return SCANNER.nextInt();
    }
}

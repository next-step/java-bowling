package bowling.view;

import java.util.Scanner;

public class InputView {
    public static String inputPlayerName(int number) {
        System.out.print("플레이어 " + (number + 1) + "의 이름은(3 english letters)?: ");

        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static int inputFramePitches(String playerName) {
        System.out.print(playerName + "'s turn : ");

        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static int inputNumberOfPeople() {
        System.out.print("How many people? ");

        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}

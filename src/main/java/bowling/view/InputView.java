package bowling.view;

import bowling.domain.Player;
import bowling.domain.score.Point;

import java.util.Scanner;

public class InputView {

    private static Scanner scanner = new Scanner(System.in);

    //플레이어 이름은(3 english letters)?: PJS
    public static String inputPlayer(int playerPosition) {
        System.out.print("플레이어 " + playerPosition + "의 이름은(3 english letters)?:");
        return scanner.nextLine();
    }

    //1프레임 투구 : 10
    public static Point inputPoint(Player playerName) {
        System.out.print(playerName + "'s turn : ");
        return Point.of(scanner.nextInt());
    }

    //How many people? 2
    public static int playerCount() {
        System.out.print("How many people? ");
        int playerCount = scanner.nextInt();
        scanner.nextLine();
        return playerCount;
    }
}

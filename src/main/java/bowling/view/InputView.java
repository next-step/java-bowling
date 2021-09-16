package bowling.view;

import bowling.Player;
import bowling.Players;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class InputView {

    private static Scanner scanner = new Scanner(System.in);

    public static Player player() {
        System.out.print("플레이어 이름은(3 english letters)?:");
        return new Player(scanner.nextLine());
    }

    public static int playerSize() {
        System.out.print("How many people? ");
        return Integer.parseInt(scanner.nextLine());
    }


    public static Players players() {
        List<Player> players = new ArrayList<>();
        int playerSize = playerSize();

        IntStream.range(0, playerSize)
                .forEach(i -> players.add(player()));

        return Players.of(players);
    }


    public static int score() {
        return scanner.nextInt();
    }


}

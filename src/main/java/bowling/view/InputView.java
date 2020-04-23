package bowling.view;

import bowling.domain.game.BowlingGame;
import bowling.domain.player.Player;
import bowling.domain.player.Players;
import bowling.domain.point.Point;

import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InputView {
    private static Scanner scanner = new Scanner(System.in);

    public static Players inputPlayers() {
        System.out.println("How many people?");
        int playerCount = scanner.nextInt();
        scanner.nextLine();
        return IntStream.rangeClosed(1, playerCount)
                .mapToObj(i -> InputView.inputPlayer(i))
                .collect(Collectors.collectingAndThen(Collectors.toList(), Players::new));
    }

    private static Player inputPlayer(int count) {
        System.out.println(String.format("플레이어 %d의 이름은(3 english letters)?:", count));
        return new Player(scanner.nextLine());
    }

    public static Point inputThrowCount(BowlingGame bowlingGame) {
        System.out.println(bowlingGame.getPlayerName() + "'s turn : ");
        try {
            return Point.of(scanner.nextInt());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return inputThrowCount(bowlingGame);
        }
    }
}

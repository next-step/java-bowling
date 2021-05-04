package bowling.view;

import bowling.domain.Player;
import bowling.domain.Players;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class InputView {
    private static Scanner scanner = new Scanner(System.in);

    private int playerCount() {
        System.out.printf("How many people? ");
        return scanner.nextInt();
    }

    public Player player(int playerIndex) {
        System.out.printf(String.format("플레이어 %d의 이름은(3 english letters)?: ", playerIndex + 1));
        return new Player(scanner.next());
    }

    public int pitch(Player player, int frameNo) {
        System.out.printf("%s의 %d프레임 투구 : ", player.name(), frameNo);
        return scanner.nextInt();
    }

    public Players players() {
        List<Player> players = new ArrayList<>();
        int playerCount = playerCount();

        IntStream.range(0, playerCount)
                .forEach(playerIndex -> players.add(player(playerIndex)));

        return new Players(players);
    }
}

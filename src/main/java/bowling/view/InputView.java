package bowling.view;

import bowling.domain.player.Player;
import bowling.domain.player.Players;
import bowling.domain.score.Score;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static int askPlayerCount() {
        System.out.println("How many people? : ");
        return SCANNER.nextInt();
    }

    public static Players askPlayers(int playerCount) {
        List<Player> players = new ArrayList<>();

        for (int i = 0; i < playerCount; i++) {
            System.out.println("플레이어 이름은(3 english letters)? : ");
            players.add(Player.of(SCANNER.next()));
        }

        return Players.of(players);
    }

    public static Score askScore(Player player) {
        System.out.println(player.getName() + "'s turn : ");
        return Score.of(SCANNER.nextInt());
    }
}

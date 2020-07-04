package bowling.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import bowling.domain.player.Player;
import bowling.domain.player.Players;
import bowling.domain.score.Score;

public class InputView {

	private static final Scanner scanner = new Scanner(System.in);

	public static int askPlayerCount() {
		System.out.println("how many people here?: ");
		return scanner.nextInt();
	}

	public static Players askPlayer(int playerCount) {
		List<Player> players = new ArrayList<>();
		for (int index = 0; index < playerCount; index++) {
			System.out.println("플레이어 이름은(3 english letters)?: ");
			players.add(Player.ofName(scanner.next()));
		}
		return Players.of(players);
	}

	public static Score askScore(Player player) {
		System.out.println(player.getName() + " 's turn : ");
		return Score.of(scanner.nextInt());
	}
}

package bowling.view;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

import bowling.user.Player;

public class InputView {

	private static final Scanner SCANNER = new Scanner(System.in);

	public static int inputCountOfPlayer() {
		System.out.println("How many people?");
		int countOfPlayer = SCANNER.nextInt();
		SCANNER.nextLine();

		return countOfPlayer;
	}

	public static String inputPlayerName() {
		System.out.println("플레이어 이름은?(3 English letters) ? : ");
		return SCANNER.nextLine();
	}

	public static List<String> inputPlayerNames(int countOfPlayer) {
		return IntStream.range(0, countOfPlayer)
						.mapToObj(i -> {
							System.out.println("플레이어 이름은?(3 English letters) ? : ");
							return SCANNER.nextLine();
						})
						.collect(toList());
	}

	public static int inputPitchingOf(Player player) {
		System.out.println(String.format("%s's turn' : ", player.getName()));
		int knockingDownPins = SCANNER.nextInt();
		SCANNER.nextLine();
		return knockingDownPins;
	}
}

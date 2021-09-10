package bowling.view;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InputView {

	private static final Scanner SCANNER = new Scanner(System.in);

	private InputView() {
	}

	public static List<String> inputPlayerNames() {
		final int numberOfPlayers = playerCount();

		return IntStream.range(0, numberOfPlayers)
			.mapToObj(InputView::playerName)
			.collect(Collectors.toList());
	}

	private static int playerCount() {
		System.out.print("How many people?: ");
		return Integer.parseInt(SCANNER.nextLine());
	}

	private static String playerName(final int index) {
		System.out.printf("플레이어 %d의 이름은(3 english letters)?:", index + 1);
		return SCANNER.nextLine();
	}

	public static int inputHitPinCount(final String name) {
		System.out.printf("%s's turn : ", name);
		return Integer.parseInt(SCANNER.nextLine());
	}
}

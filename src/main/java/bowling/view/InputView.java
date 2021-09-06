package bowling.view;

import java.util.Scanner;

import bowling.exception.InputMachTypeException;
import bowling.model.Count;
import bowling.model.Player;

public class InputView {

	private static final String NUMBER_TYPE_ERROR_MESSAGE = "숫자 타입과 일치 하지 않습니다.";
	private static final String INPUT_PLAYER_NAME_MESSAGE = "플레이어 %d의 이름은(3 english letters)?: ";
	private static final String INPUT_STRIKE_NUMBER_MESSAGE = "%s`s turn :";
	private static final String INPUT_PLAYER_COUNT = "How many people?";
	private static final Scanner scanner = new Scanner(System.in);
	private static final int START_INCLUSIVE = 0;

	public static String[] inputPlayerName(Count count) {
		String[] players = new String[count.getCount()];
		for (int i = START_INCLUSIVE; i < count.getCount(); i++) {
			System.out.printf(System.lineSeparator() + INPUT_PLAYER_NAME_MESSAGE, i + 1);
			players[i] = scanner.nextLine();
		}
		return players;
	}

	public static int inputStrikeNumber(Player player) {
		System.out.printf(System.lineSeparator() + INPUT_STRIKE_NUMBER_MESSAGE, player.getPlayerName());
		return inputMatchTypeInteger();
	}

	public static int inputPlayerCount() {
		System.out.println(INPUT_PLAYER_COUNT);
		return inputMatchTypeInteger();
	}

	private static int inputMatchTypeInteger() {
		if (!scanner.hasNextInt()) {
			throw new InputMachTypeException(NUMBER_TYPE_ERROR_MESSAGE);
		}
		return Integer.parseInt(scanner.nextLine());
	}
}

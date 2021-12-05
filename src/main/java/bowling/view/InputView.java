package bowling.view;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

import bowling.domain.Pins;
import bowling.domain.Player;
import bowling.exception.UtilCreationException;

public final class InputView {
	private static final Scanner SCANNER = new Scanner(System.in);

	private static final String SIZE = "How many people? ";
	private static final String PLAYER = "플레이어 %d의 이름은?(3 english letters): ";
	private static final String PINS = "%s's turn : : ";

	private InputView() {
		throw new UtilCreationException();
	}

	public static List<Player> scanPlayers() {
		System.out.print(SIZE);
		int size = SCANNER.nextInt();

		SCANNER.nextLine();

		return createPlayers(size);
	}

	private static List<Player> createPlayers(int size) {
		return IntStream.rangeClosed(1, size).mapToObj(index -> {
			System.out.printf(PLAYER, index);
			String name = SCANNER.nextLine();
			return Player.create(name);
		}).collect(toList());
	}

	public static Pins scanPins(String playerName) {
		System.out.printf(PINS, playerName);
		String pins = SCANNER.nextLine();
		return Pins.create(Integer.parseInt(pins));
	}
}

package bowling.view;

import bowling.domain.DownedPinCount;
import bowling.domain.game.Games;
import bowling.domain.player.Player;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InputView {

	private static final String INPUT_SHOULD_INTEGER = "자연수로 입력 해 주세요.";
	private static final String PLEASE_INPUT_PLAYER_NAME = "플레이어 이름은(3 english letters)?:";
	private static final String PLEASE_INPUT_N_FRAME_PITCH_RESULT = "%s's turn :";
	private static final String PLEASE_INPUT_PLAYER_COUNT = "How many people?";
	private static final Scanner SCANNER = new Scanner(System.in);

	public static Games prepareGames() {
		int playerCount = getPlayerCount();
		List<Player> players = IntStream.rangeClosed(1, playerCount)
				.mapToObj(count -> getPlayer())
				.collect(Collectors.toList());
		return new Games(players);
	}

	public static int getPlayerCount() {
		System.out.println(PLEASE_INPUT_PLAYER_COUNT);
		return getIntValue();
	}

	public static Player getPlayer() {
		System.out.println(PLEASE_INPUT_PLAYER_NAME);
		return new Player(getStringValue());
	}

	public static DownedPinCount getCurrentFramePitch(String playerName) {
		System.out.println(String.format(PLEASE_INPUT_N_FRAME_PITCH_RESULT, playerName));
		return DownedPinCount.fromDownCount(getIntValue());
	}

	private static int getIntValue() {
		try {
			return SCANNER.nextInt();
		} catch (InputMismatchException e) {
			throw new IllegalArgumentException(INPUT_SHOULD_INTEGER, e);
		} finally {
			SCANNER.nextLine();
		}
	}

	private static String getStringValue() {
		return SCANNER.nextLine();
	}
}

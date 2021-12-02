package bowling.view;

import java.util.Scanner;

import bowling.domain.Pins;
import bowling.domain.Player;
import bowling.exception.UtilCreationException;

public final class InputView {
	private static final Scanner SCANNER = new Scanner(System.in);

	private static final String PLAYER = "플레이어 이름은(3 english letters)?: ";
	private static final String PINS = "%d 프레임 투구 : ";

	private InputView() {
		throw new UtilCreationException();
	}

	public static Player scanPlayer() {
		System.out.print(PLAYER);
		String name = SCANNER.nextLine();
		return Player.create(name);
	}

	public static Pins scanPins(int currentFrameIndex) {
		System.out.printf(PINS, currentFrameIndex);
		String pins = SCANNER.nextLine();
		return Pins.create(Integer.parseInt(pins));
	}
}

package bowling.view;

import bowling.domain.Ball;
import bowling.domain.Player;
import bowling.domain.State;

import java.util.Scanner;

public final class InputView {
	private static final Scanner SCANNER = new Scanner(System.in);

	private InputView() {
		throw new AssertionError();
	}

	public static Player player() {
		System.out.println("플레이어 이름은(3 english letters)?: ");
		String name = SCANNER.nextLine();
		return new Player(name);
	}

	public static Ball fallenPins(int frameIndex) {
		System.out.println(frameIndex + "프레임 투구 : ");
		int pins = SCANNER.nextInt();
		return Ball.of(pins, State.READY);
	}
}

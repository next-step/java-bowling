package bowling.step2.view;

import java.util.Scanner;

public class InputView {
	private final static Scanner scanner = new Scanner(System.in);

	private final static String INPUT_PLAYER_NAME = "플레이어 이름은(3 english letters)? : ";
	private final static String INPUT_PIN_COUNT = "%d프레임 투구 : ";

	private InputView() {}

	public static String inputPlayerName() {
		System.out.print(INPUT_PLAYER_NAME);
		return scanner.nextLine();
	}

	public static int inputPinCount(int index) {
		System.out.printf(INPUT_PIN_COUNT, index);
		return Integer.parseInt(scanner.nextLine());
	}
}

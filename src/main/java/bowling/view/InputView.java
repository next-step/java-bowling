package bowling.view;

import java.util.Scanner;

public class InputView {

	public static final String INPUT_MESSAGE = "플레이어 이름은(3 english letters)?:";
	private static final Scanner scanner = new Scanner(System.in);
	public static final String INPUT_PITCH_COUNT_MESSAGE = "%d프레임 투구: ";

	public static String inputName() {
		System.out.println(INPUT_MESSAGE);
		return scanner.nextLine();
	}

	public static int inputHitCount(int frameNumber) {
		System.out.printf((INPUT_PITCH_COUNT_MESSAGE) + "%n", frameNumber);
		return scanner.nextInt();
	}
}

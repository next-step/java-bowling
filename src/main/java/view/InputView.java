package view;

import java.util.Scanner;

public class InputView {

	private static final Scanner scanner = new Scanner(System.in);

	private InputView() {

	}

	// TODO: 2019-11-18 UserName으로 wrapping, validation
	public static String receiveUserName() {
		System.out.print("플레이어 이름은(3 english letters)?: ");
		return scanner.nextLine();
	}

	// TODO: 2019-11-18 BowlingPinsCount로 wrapping, validation
	public static int receiveFallenBowlingPins(int frameCount) {
		System.out.print(String.format("%s프레임 투구 : ", frameCount));
		return scanner.nextInt();
	}

}

package view;

import domain.UserName;

import java.util.Scanner;

public class InputView {

	private static final Scanner scanner = new Scanner(System.in);

	private InputView() {

	}

	public static UserName receiveUserName() {
		System.out.print("플레이어 이름은(3 english letters)?: ");
		return UserName.of(scanner.nextLine());
	}

	public static int receiveFallenBowlingPins(int frameCount) {
		System.out.print(String.format("%s프레임 투구 : ", frameCount));
		return scanner.nextInt();
	}

}

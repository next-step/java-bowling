package view;

import domain.BowlingPins;
import domain.UserName;
import domain.UserNumber;

import java.util.Scanner;

public class InputView {

	private static final Scanner scanner = new Scanner(System.in);

	private InputView() {

	}

	public static UserNumber receiveUserNumber() {
		System.out.print("How many people? ");
		UserNumber userNumber =  UserNumber.of(scanner.nextInt());
		cleanScannerBuffer();
		return userNumber;
	}

	public static UserName receiveUserName() {
		System.out.print("플레이어 이름은(3 english letters)?: ");
		return UserName.of(scanner.nextLine());
	}

	public static BowlingPins receiveFallenBowlingPins(UserName userName) {
		System.out.print(String.format("%s's turn : ", userName));
		return BowlingPins.of(scanner.nextInt());
	}

	private static void cleanScannerBuffer() {
		scanner.nextLine();
	}


}

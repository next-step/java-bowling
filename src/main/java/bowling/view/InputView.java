package bowling.view;

import java.util.Scanner;

public final class InputView {

	private static final Scanner scanner = new Scanner(System.in);

	public static int playerCount() {
		System.out.print("How many people?: ");
		return Integer.parseInt(scanner.nextLine());
	}

	public static String name() {
		System.out.print("플레이어 이름은(3 english letters)?: ");
		return scanner.nextLine();
	}

	public static int pinsCount(final int frameIndex) {
		System.out.printf("%d프레임 투구 : ", frameIndex);
		return Integer.parseInt(scanner.nextLine());
	}
}

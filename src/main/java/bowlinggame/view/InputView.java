package bowlinggame.view;

import java.util.Scanner;

public class InputView {

	private static final Scanner scanner = new Scanner(System.in);

	public static String inputPlayerName() {
		System.out.print("플레이어 이름은(3 english letters)?: ");
		return scanner.nextLine();
	}

	public static int inputKnockDownPickCount(int currentFrame) {
		System.out.print(String.format("%d 프레임 투구 : ", currentFrame));
		return scanner.nextInt();
	}
}

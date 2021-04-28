package bowling.view;

import java.util.Scanner;

public class InputView {

	private final static Scanner sc = new Scanner(System.in);

	private InputView() {
	}

	public static String inputPlayerName() {
		System.out.print("플레이어 이름은(3 english letters)?: ");
		return sc.nextLine();
	}

	public static int inputHittingNumber(int currentFrame) {
		System.out.printf("%d프레임 투구 : ", currentFrame);
		return sc.nextInt();
	}

}

package bowling.view;

import java.util.Scanner;

public class InputView {

	private static final Scanner SCANNER = new Scanner(System.in);

	public static String inputPlayerName() {
		System.out.println("플레이어 이름은?(3 English letters) ? : ");
		return SCANNER.nextLine();
	}

	public static int inputPitchingOf(int frameNo) {
		System.out.println(String.format("%d프레임 투구 : ", frameNo));
		int knockingDownPins = SCANNER.nextInt();
		SCANNER.nextLine();
		return knockingDownPins;
	}
}

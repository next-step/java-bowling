package bowling.view;

import java.util.Scanner;

import bowling.domain.Frames;

public class InputView {

	private final static Scanner sc = new Scanner(System.in);

	public static String inputPlayerName() {
		System.out.print("플레이어 이름은(3 english letters)?: ");
		return sc.nextLine();
	}

	public static int inputHittingNumber(Frames frames) {
		System.out.printf("%d프레임 투구 : ", frames.currentFrame());
		return sc.nextInt();
	}

}

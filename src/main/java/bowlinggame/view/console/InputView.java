package bowlinggame.view.console;

import bowlinggame.domain.frame.FrameNumber;
import java.util.Scanner;

public class InputView {

	private static final Scanner scanner = new Scanner(System.in);

	public static String inputPlayerName() {
		System.out.print("플레이어 이름은(3 english letters)?: ");
		return scanner.nextLine();
	}

	public static int inputKnockDownPickCount(FrameNumber frameNumber) {
		System.out.print(String.format("%d 프레임 투구 : ", frameNumber.getNumber()));
		return scanner.nextInt();
	}
}

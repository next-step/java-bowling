package bowling.view;

import java.util.Scanner;

public class InputView {

	private static Scanner SCANNER = new Scanner(System.in);

	public String getPlayer() {
		System.out.print("플레이어 이름은(3 english letters)? ");
		return SCANNER.next();
	}

	public int getScore(int frame) {
		System.out.print(frame + "프레임 투구 : ");
		return SCANNER.nextInt();
	}
}

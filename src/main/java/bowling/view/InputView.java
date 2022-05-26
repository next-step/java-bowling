package bowling.view;

import static java.lang.System.*;

import java.util.Scanner;

public class InputView {

	private final Scanner scanner = new Scanner(System.in);

	public String askPlayerName() {
		ask("플레이어 이름은(3 english letters)?");
		return scanner.nextLine();
	}

	public int askThrowCount(int frameNumber) {
		ask(String.format("%d프레임 투구 :", frameNumber));
		return Integer.parseInt(scanner.nextLine());
	}

	public void ask(String text) {
		out.println(text);
	}
}

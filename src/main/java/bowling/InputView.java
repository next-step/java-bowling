package bowling;

import static java.lang.System.*;

import java.util.Scanner;

public class InputView {

	private final Scanner scanner = new Scanner(System.in);

	public InputView() {
	}

	public String askPlayerName() {
		ask("플레이어 이름은(3 english letters)?");
		return scanner.nextLine();
	}

	public void ask(String text) {
		out.println(text);
	}
}

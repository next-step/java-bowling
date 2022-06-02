package bowling.view;

import static java.lang.System.*;

import java.util.Scanner;

public class InputView {

	private final Scanner scanner = new Scanner(System.in);

	public int askPlayerCount() {
		ask("How many people?");
		return Integer.parseInt(scanner.nextLine());
	}

	public String askPlayerName(int index) {
		ask(String.format("플레이어%d의 이름은(3 english leççtters)?", index));
		return scanner.nextLine();
	}

	public int askThrowCount(String playerNameOfTurn) {
		ask(String.format("%s's turn :", playerNameOfTurn));
		return Integer.parseInt(scanner.nextLine());
	}

	public void ask(String text) {
		out.println(text);
	}
}

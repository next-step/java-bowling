package bowling.views;

import java.util.Scanner;

public class InputView extends View {

	private final Scanner scanner;

	public InputView() {
		this.scanner = new Scanner(System.in);
	}

	public String getUserName() {
		out.println("플레이어 이름은(3 english letters)?:");
		return scanner.nextLine();
	}

	public int getFrameScore(int frameIndex) {
		out.println(frameIndex + "프레임 투구 : ");
		return Integer.parseInt(scanner.nextLine());
	}
}

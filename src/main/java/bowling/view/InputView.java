package bowling.view;

import java.util.Scanner;

import bowling.domain.player.Player;
import bowling.domain.score.Score;

public class InputView {

	private static final Scanner scanner = new Scanner(System.in);

	public static Player askPlayer() {
		System.out.println("플레이어 이름은(3 english letters)?: ");
		return Player.ofName(scanner.nextLine());
	}

	public static Score askScore(int frameIndex) {
		System.out.println(frameIndex + "프레임 투구 : ");
		return Score.of(scanner.nextInt());
	}
}

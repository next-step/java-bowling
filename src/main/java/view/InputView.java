package view;

import domain.Player;
import domain.Score;

import java.util.Scanner;

/**
 * Created by hspark on 21/11/2018.
 */
public class InputView {
	public static Player inputName() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("플레이어 이름은(3 english letters)?:");
		String name = scanner.nextLine();
		return new Player(name);
	}

	public static Score inputScore(int frameNumber) {
		Scanner scanner = new Scanner(System.in);
		System.out.println(String.format("%d프레임 투구", frameNumber));
		return Score.of(scanner.nextInt());
	}
}

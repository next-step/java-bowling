package bowling.view;

import java.util.Scanner;

public class InputView {

	private static Scanner scanner = new Scanner(System.in);

	public static String inputName(){
		System.out.println("플레이어 이름은(3 english letters)?");
		String userName = scanner.nextLine();

		return userName;
	}

	public static int inputBowl(){
		return scanner.nextInt();
	}
}

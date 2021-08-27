package bowling.view;

import java.util.Scanner;

import bowling.exception.InputMachTypeException;

public class InputView {

	private static final String NUMBER_TYPE_ERROR_MESSAGE = "숫자 타입과 일치 하지 않습니다.";
	private static final String INPUT_PLAYER_NAME_MESSAGE = "플레이어 이름은(3 english letters)?: ";
	private static final String INPUT_STRIKE_NUMBER_MESSAGE = "%d 프레임 투구 :";
	private static final Scanner scanner = new Scanner(System.in);

	public static String inputPlayerName() {
		System.out.println(INPUT_PLAYER_NAME_MESSAGE);
		return scanner.nextLine();
	}

	public static int inputStrikeNumber(int presentFrame) {
		System.out.print("\n");
		System.out.printf(INPUT_STRIKE_NUMBER_MESSAGE, presentFrame);
		if (!scanner.hasNextInt()) {
			throw new InputMachTypeException(NUMBER_TYPE_ERROR_MESSAGE);
		}
		return Integer.parseInt(scanner.nextLine());
	}
}

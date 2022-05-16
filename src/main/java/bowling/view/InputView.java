package bowling.view;

import bowling.exception.InvalidNameOfPlayerException;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String INPUT_NAME_OF_PLAYER = "플레이어 이름은(3 english letters)?: ";
    private static final String INPUT_NUMBER_OF_FALLEN_PINS_IN_FRAME = "%s프레임 투구 : ";

    private InputView() {
    }

    public static String inputNameOfPlayer() {
        try {
            System.out.println(INPUT_NAME_OF_PLAYER);
            String name = SCANNER.nextLine();
            validateNameOfPlayer(name);
            return name;
        } catch (InvalidNameOfPlayerException e) {
            System.out.println(e.getMessage());
            return inputNameOfPlayer();
        }
    }

    private static void validateNameOfPlayer(String name) {
        if (name == null || name.length() != 3) {
            throw new InvalidNameOfPlayerException(name);
        }
    }

    public static String inputNumberOfFallenPinsInFrame(final int frame) {
        System.out.println(String.format(INPUT_NUMBER_OF_FALLEN_PINS_IN_FRAME, frame));
        return SCANNER.nextLine();
    }
}

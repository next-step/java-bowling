package bowling.view;


import java.util.Scanner;

public class InputView {

    private static final String INPUT_NAME_MESSAGE = "플레이어 이름은(3 english letters)?: ";
    private static final String INPUT_PITCHING_MESSAGE = "프레임 투구 : ";

    private static String getUserInputValue(String message) {
        System.out.print(message);
        Scanner sc = new Scanner(System.in);
        String inputValue = sc.nextLine();

        return inputValue;
    }

    public static String inputNameOfPlayer() {
        return getUserInputValue(INPUT_NAME_MESSAGE);
    }

    public static int inputPitchingCount(int frameNumber) {
        return Integer.parseInt(getUserInputValue(frameNumber + INPUT_PITCHING_MESSAGE));
    }
}

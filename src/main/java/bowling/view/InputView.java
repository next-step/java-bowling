package bowling.view;

import bowling.domain.Player;

import java.util.Scanner;
import java.util.regex.Pattern;

public class InputView {
    private static final String INPUT_NAME_STRING = "플레이어 이름은(3 english letters)?: ";
    private static final String INPUT_DONWNPIN_STRING = "%d프레임 투구 : ";
    private static final String EXCEPTION_MESSAGE = "Wrong Input : downPinCount is not a number";
    private static final Pattern NUMBER_PATTERN = Pattern.compile("^[0-9]\\d*(\\.\\d+)?$");

    public static Player inputPlayer(){
        System.out.println(INPUT_NAME_STRING);
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        return new Player(name);
    }

    public static int inputDownPinCount(int index){
        System.out.printf(INPUT_DONWNPIN_STRING, index);
        Scanner scanner = new Scanner(System.in);
        String downPinCount = scanner.nextLine();
        if (!isNumber(downPinCount)) {
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }
        return Integer.parseInt(downPinCount);
    }

    private static boolean isNumber(String numberString) {
        return NUMBER_PATTERN.matcher(numberString).find();
    }

}

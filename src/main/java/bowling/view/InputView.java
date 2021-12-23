package bowling.view;

import java.util.Scanner;
import java.util.regex.Pattern;

public class InputView {

    private static final String INPUT_NAME_MSG = "플레이어 이름은(3 english letters)?: ";
    private static final String NOT_NUMBER_MESSAGE = "숫자만 입력 할 수 있습니다!!!";
    private static final Pattern PATTERN = Pattern.compile("-?\\d+");

    private static Scanner sc = new Scanner(System.in);

    public static String inputUserName() {

        System.out.print(INPUT_NAME_MSG);

        return sc.nextLine();
    }

    public int getBowlPinCount(int frameNo) {

        System.out.print(String.format("%d프레임 투구 : ", frameNo));

        String input = sc.nextLine();
        checkNumber(input);

        return Integer.parseInt(input);
    }

    private void checkNumber(String input) {
        if(!PATTERN.matcher(input).matches()) {
            throw new IllegalArgumentException(NOT_NUMBER_MESSAGE);
        }
    }
}

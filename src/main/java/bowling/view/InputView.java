package bowling.view;

import bowling.domain.value.Pins;

import java.util.Scanner;

public class InputView {

    private static final String INPUT_NAME_MSG = "플레이어 이름은(3 english letters)?: ";

    private static Scanner sc = new Scanner(System.in);

    public static String inputUserName() {

        System.out.print(INPUT_NAME_MSG);

        return sc.nextLine();
    }

    public Pins getBowlPinCount(int frameNo) {

        System.out.print(String.format("%d 프레임 투구 : ", frameNo + 1));

        String input = sc.nextLine();

        return new Pins(input);
    }

}

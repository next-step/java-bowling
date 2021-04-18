package bowling.view;

import java.util.Scanner;

public class InputView {

    private final Scanner in;

    public InputView() {
        this.in = new Scanner(System.in);
    }

    public String inputUserName() {
        System.out.print("플레이어 이름은(3 english letters)?: ");
        return in.nextLine();
    }

    public String inputPitch(int frameNumber) {
        String pin = in.nextLine();
        System.out.printf("%s 프레임 투구 : %s ", frameNumber, pin);
        return pin;
    }
}

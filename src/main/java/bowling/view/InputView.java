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

    public String inputPitch() {
        String pin = in.nextLine();
        System.out.printf("1프레임 투구 : %s " + pin);
        return pin;
    }
}

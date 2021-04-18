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

    public int inputPitch(int frameNumber) {
        System.out.printf("%d 프레임 투구 : ", frameNumber);
        String pin = in.nextLine();
        return Integer.parseInt(pin);
    }
}

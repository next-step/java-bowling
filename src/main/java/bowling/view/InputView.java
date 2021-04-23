package bowling.view;

import java.util.Scanner;

import static java.lang.System.out;

public class InputView {

    public static final String GUIDE_BOWLING_INPUT_PLAYER = "플레이어 이름은(3 english letters)?: ";
    public static final String GUIDE_BOWLING_INPUT_PITCH = "%d 프레임 투구 : ";
    private final Scanner in;

    public InputView() {
        this.in = new Scanner(System.in);
    }

    public String inputUserName() {
        out.print(GUIDE_BOWLING_INPUT_PLAYER);
        return in.nextLine();
    }

    public int inputPitch(final int frameNumber) {
        out.printf(GUIDE_BOWLING_INPUT_PITCH, frameNumber);
        String pin = in.nextLine();
        return Integer.parseInt(pin);
    }
}

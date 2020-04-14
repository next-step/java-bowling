package bowling.ui;

import java.util.Scanner;

public class InputView {
    private final static String GET_NAME_MESSAGE = "플레이어 이름은 (3 english letters)?: ";
    private final static String SHOT_FORMAT = "%d프레임 투구 : ";

    private Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public String getPlayer() {
        System.out.print(GET_NAME_MESSAGE);
        return scanner.nextLine();
    }

    public int getShot(int frameNumber) {
        System.out.print(String.format(SHOT_FORMAT, frameNumber));
        return Integer.parseInt(scanner.nextLine());
    }
}

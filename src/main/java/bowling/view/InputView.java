package bowling.view;

import java.io.InputStream;
import java.util.Scanner;

public class InputView {
    private static final String WHAT_IS_YOUR_NAME = "플레이어 이름은(3 english letters)?: ";

    private Scanner scanner;

    public InputView() {
        this(System.in);
    }

    public InputView(final InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
    }

    public String inputPlayerName() {
        System.out.print(WHAT_IS_YOUR_NAME);
        return scanner.nextLine();
    }
}

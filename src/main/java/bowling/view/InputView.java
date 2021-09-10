package bowling.view;

import java.io.InputStream;
import java.util.Objects;
import java.util.Scanner;

public class InputView {

    private final Scanner scanner;

    public InputView(InputStream inputStream) {
        this.scanner = new Scanner(Objects.requireNonNull(inputStream));
    }

    public String getPlayerName() {
        return scanner.nextLine();
    }
}

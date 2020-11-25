package bowling.view;

import java.io.PrintWriter;
import java.util.Scanner;

public class InputView {

    private final static String MEMBER_NAME_PHRASE = "플레이어 이름은(3 english letters)?: ";
    private final static String SCORE_PHRASE = "%d프레임 투구 : ";
    private final Scanner scanner;
    private final PrintWriter output;

    public InputView(Scanner scanner, PrintWriter output) {
        this.scanner = scanner;
        this.output = output;
    }

    public String enterMemberName() {
        output.println(MEMBER_NAME_PHRASE);
        return scanner.next();
    }

    public int enterScore(int frameNumber) {
        output.println(String.format(SCORE_PHRASE, frameNumber));
        return scanner.nextInt();
    }
}

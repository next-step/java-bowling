package bowling.view;

import java.io.PrintWriter;
import java.util.Scanner;

public class InputView {

    private static final String MEMBER_COUNT_PHRASE = "How many people? ";
    private static final String MEMBER_NAME_PHRASE = "플레이어 %d의 이름은(3 english letters)?: ";
    private static final String SCORE_PHRASE = "%s's turn : ";
    private final Scanner scanner;
    private final PrintWriter output;

    public InputView(Scanner scanner, PrintWriter output) {
        this.scanner = scanner;
        this.output = output;
    }

    public int enterMemberCount() {
        output.println(MEMBER_COUNT_PHRASE);
        return Integer.parseInt(scanner.nextLine().trim());
    }

    public String enterMemberName(int memberNumber) {
        output.println(String.format(MEMBER_NAME_PHRASE, memberNumber));
        return scanner.nextLine().trim();
    }

    public int enterScore(String memberName) {
        output.println(String.format(SCORE_PHRASE, memberName));
        return Integer.parseInt(scanner.nextLine().trim());
    }
}

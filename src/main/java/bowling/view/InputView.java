package bowling.view;

import bowling.controller.dto.BowlingGameRequest;

import java.util.Scanner;

public class InputView {

    private final Scanner scanner;

    public InputView() {
        scanner = new Scanner(System.in);
    }

    public BowlingGameRequest inputParticipant() {
        System.out.print("플레이어 이름은(3 english letters)?: ");
        return new BowlingGameRequest(scanner.next());
    }
}

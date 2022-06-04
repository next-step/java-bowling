package bowling.view;

import bowling.domain.Player;
import bowling.exception.InvalidNameException;
import bowling.exception.NotSupportInstanceException;

import java.util.Scanner;

public class InputView {

    private static final String MASSAGE_PLAYER_NAME_INPUT = "플레이어 이름은 (3 english letters)? : ";

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
        throw new NotSupportInstanceException();
    }

    public static Player playerNameView() {
        System.out.print(MASSAGE_PLAYER_NAME_INPUT);
        return inputValidPlayerName();
    }
    private static Player inputValidPlayerName() {
        try {
            return validatePlayerName();
        } catch (InvalidNameException e) {
            System.out.println(e.getMessage());
            return inputValidPlayerName();
        }
    }
    private static Player validatePlayerName() {
        return new Player(scanner.nextLine());
    }
}

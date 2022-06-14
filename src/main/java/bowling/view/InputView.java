package bowling.view;

import bowling.domain.Player;
import bowling.domain.frame.Frame;
import bowling.exception.InvalidNameException;
import bowling.exception.NotSupportInstanceException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputView {

    private static final String MASSAGE_PLAYER_NAME_INPUT = "플레이어 이름은 (3 english letters)? : ";
    private static final String MESSAGE_HIT_COUNT_INPUT = "%d 프레임 투구 : ";
    private static final String MESSAGE_INVALID_NUMBER_FORMAT = "숫자만 입력 가능합니다.";
    public static final String MESSAGE_PLAYER_NUMBER_INPUT = "How many people? ";

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

    public static int hitCountView(Frame frame) {
        System.out.print(String.format(MESSAGE_HIT_COUNT_INPUT, frame.frameNo().toInt()));
        return inputInt();
    }

    public static int playerNumberView() {
        System.out.print(MESSAGE_PLAYER_NUMBER_INPUT);
        return validPlayerNumber();
    }

    private static int validPlayerNumber() {
        try {
            return validatePlayerNumber();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return validPlayerNumber();
        }
    }

    private static int validatePlayerNumber() {
        int playerNumber = inputInt();
        if (playerNumber < 1) {
            throw new IllegalArgumentException("참가자 인원은 1명 이상이여야 합니다.");
        }
        return playerNumber;
    }

    private static int inputInt() {
        try {
            int input = scanner.nextInt();
            scanner.nextLine();
            return input;
        } catch (InputMismatchException me) {
            System.out.println(MESSAGE_INVALID_NUMBER_FORMAT);
            scanner.nextLine();
            return inputInt();
        }
    }
}

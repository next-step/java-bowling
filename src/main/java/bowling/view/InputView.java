package bowling.view;

import bowling.domain.Player;
import bowling.domain.frame.Frame;
import bowling.exception.InvalidNameException;
import bowling.exception.NotSupportInstanceException;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String MESSAGE_PLAYER_NUMBER_INPUT = "How many people? ";
    private static final String MASSAGE_PLAYER_NAME_INPUT = "플레이어 %d의 이름은? (3 english letters) : ";
    private static final String MESSAGE_HIT_COUNT_INPUT = "%d 프레임 투구 : ";
    private static final String MESSAGE_INVALID_NUMBER_FORMAT = "숫자만 입력 가능합니다.";
    private static final String MESSAGE_DUPLICATE_NAME = "이미 등록된 이름입니다.";

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
        throw new NotSupportInstanceException();
    }

    public static List<Player> playerNameView(int playerNumber) {
        List<Player> players = new ArrayList<>();
        for (int i = 1; i <= playerNumber; i++) {
            System.out.print(String.format(MASSAGE_PLAYER_NAME_INPUT, i));
            Player player = inputValidPlayerName(players);
            players.add(player);
        }
        return players;
    }
    private static Player inputValidPlayerName(List<Player> players) {
        try {
            return validatePlayerName(players);
        } catch (InvalidNameException e) {
            System.out.println(e.getMessage());
            return inputValidPlayerName(players);
        }
    }
    private static Player validatePlayerName(List<Player> players) {
        Player player = new Player(scanner.nextLine());
        if (players.contains(player)) {
            throw new InvalidNameException(MESSAGE_DUPLICATE_NAME);
        }
        return player;
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

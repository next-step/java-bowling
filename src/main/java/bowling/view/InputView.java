package bowling.view;

import bowling.domain.player.Player;
import bowling.domain.player.PlayerName;
import bowling.exception.BowlingException;
import bowling.exception.PlayerNameValidationException;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class InputView {

    private final Scanner scanner;

    public InputView() {
        this(new Scanner(System.in));
    }

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String inputPlayerName(int playerNumber) {
        System.out.print("플레이어 " + playerNumber + "의 이름을 입력해주세요.(영문 대소문자 3자이내): ");
        return scanner.nextLine();
    }

    public int inputDownPin(Player player) {
        System.out.print(player.playerName() + "'s turn : ");
        return inputInteger();
    }

    public int inputPlayerCount() {
        System.out.print("플레이어가 몇 명 인가요? ");
        return inputInteger();
    }

    private int inputInteger() {
        final String input = scanner.nextLine();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new BowlingException("유효하지 않은 숫자입니다. 입력한 값: " + input);
        }
    }

    public List<PlayerName> inputPlayerNames(int playerCount) {
        return IntStream.rangeClosed(1, playerCount)
                .mapToObj(this::playerName)
                .collect(Collectors.toList());
    }

    private PlayerName playerName(int playerCount) {
        try {
            return PlayerName.valueOf(inputPlayerName(playerCount));
        } catch (PlayerNameValidationException e) {
            System.err.println(e.getMessage());
            return playerName(playerCount);
        }
    }
}

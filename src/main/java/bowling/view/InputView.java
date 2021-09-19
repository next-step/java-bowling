package bowling.view;

import bowling.domain.player.Player;
import bowling.domain.player.Players;

import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InputView {
    private static final String PLAYER_COUNT_MSG = "How many people? ";
    private static final String PLAYER_NAME_MSG = "플레이어 %d의 이름은?(3 english letters) : ";
    private static final String FRAME_MSG = "%d) %s's turn : ";
    private static final Scanner scanner = new Scanner(System.in);

    public int playerCount() {
        System.out.print(PLAYER_COUNT_MSG);
        return scanner.nextInt();
    }

    public Players players(int playerNo) {
        return new Players(IntStream.range(1, playerNo + 1)
                .mapToObj(this::playerName)
                .collect(Collectors.toList()));
    }

    private String playerName(int playerNo) {
        System.out.printf(PLAYER_NAME_MSG, playerNo);
        return scanner.next();
    }

    public int next(Player player) {
        System.out.printf(FRAME_MSG, player.nextFrameNo(), player.getPlayerName());
        return scanner.nextInt();
    }
}

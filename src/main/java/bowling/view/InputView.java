package bowling.view;

import bowling.domain.player.Player;

import java.util.Scanner;

public class InputView {
    private static final String PLAYER_NAME_MSG = "플레이어 이름은(3 english letters)?:";
    private static final String FRAME_MSG = "%d프레임 투구 : ";
    private static final Scanner scanner = new Scanner(System.in);

    public String playerName() {
        System.out.print(PLAYER_NAME_MSG);
        return scanner.next();
    }

    public int next(Player player) {
        System.out.printf(FRAME_MSG, player.getFrames().currentFrameNo());
        return scanner.nextInt();
    }
}

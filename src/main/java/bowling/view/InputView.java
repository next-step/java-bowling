package bowling.view;

import bowling.domain.frame.FrameNumber;
import bowling.domain.pin.BowlCount;
import bowling.domain.player.Player;
import bowling.domain.player.PlayerCount;

import java.io.InputStream;
import java.util.Scanner;

public class InputView {
    private static final String WHAT_IS_YOUR_NAME = "플레이어 %d의 이름은(3 english letters)?: ";
    private static final String FRAME_BOWLING_MESSAGE = "%s 프레임 투구 : ";
    private static final String HOW_MANY_PEOPLE = "How many people? ";

    private Scanner scanner;

    public InputView() {
        this(System.in);
    }

    public InputView(final InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
    }

    public Player inputPlayerName(final int index) {
        System.out.print(String.format(WHAT_IS_YOUR_NAME, index));
        return Player.of(scanner.nextLine());
    }

    public BowlCount inputBowlCount(final FrameNumber frameNumber) {
        System.out.print(String.format(FRAME_BOWLING_MESSAGE, frameNumber.toString()));
        return new BowlCount(scanner.nextLine());
    }

    public PlayerCount inputPlayerCount() {
        System.out.print(HOW_MANY_PEOPLE);
        return PlayerCount.valueOf(scanner.nextLine());
    }
}

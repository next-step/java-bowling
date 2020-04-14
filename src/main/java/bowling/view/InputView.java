package bowling.view;

import bowling.domain.frame.FrameNumber;
import bowling.domain.pin.BowlCount;
import bowling.domain.player.Player;

import java.io.InputStream;
import java.util.Scanner;

public class InputView {
    private static final String WHAT_IS_YOUR_NAME = "플레이어 이름은(3 english letters)?: ";
    private static final String FRAME_BOWLING_MESSAGE = "%s 프레임 투구 : ";

    private Scanner scanner;

    public InputView() {
        this(System.in);
    }

    public InputView(final InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
    }

    public Player inputPlayerName() {
        System.out.print(WHAT_IS_YOUR_NAME);
        return Player.of(scanner.nextLine());
    }

    public BowlCount inputBowlCount(final FrameNumber frameNumber) {
        System.out.print(String.format(FRAME_BOWLING_MESSAGE, frameNumber.toString()));
        return new BowlCount(scanner.nextLine());
    }
}

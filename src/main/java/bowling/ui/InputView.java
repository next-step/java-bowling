package bowling.ui;

import bowling.domain.FallingPinCount;
import bowling.domain.player.Player;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InputView {

    private static final String INPUT_SHOULD_INTEGER = "자연수로 입력 해 주세요.";
    private static final String PLEASE_INPUT_PLAYER_NAME = "플레이어 이름은(3 english letters)?: ";
    private static final String PLEASE_INPUT_N_FRAME_PITCH_RESULT = "%d프레임 투구 : ";
    private static final Scanner SCANNER = new Scanner(System.in);

    public static Player getPlayer() {
        System.out.print(PLEASE_INPUT_PLAYER_NAME);
        return new Player(getStringValue());
    }

    public static FallingPinCount getCurrentFramePitch(int currentFrameSequence) {
        System.out.print(String.format(PLEASE_INPUT_N_FRAME_PITCH_RESULT, currentFrameSequence));
        return FallingPinCount.fromFallingCount(getIntValue());
    }

    private static int getIntValue() {
        try {
            return SCANNER.nextInt();
        } catch (InputMismatchException e) {
            throw new IllegalArgumentException(INPUT_SHOULD_INTEGER, e);
        } finally {
            SCANNER.nextLine();
        }
    }

    private static String getStringValue() {
        return SCANNER.nextLine();
    }
}

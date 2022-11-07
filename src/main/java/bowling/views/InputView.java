package bowling.views;

import bowling.domain.BowlingGameFrame;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final int PLAYER_LENGTH = 3;

    private InputView() {
    }

    public static String inputPlayerName() {
        System.out.print("플레이어 이름은(3 english letters)?: ");
        String playerName = SCANNER.nextLine();
        validatePlayerName(playerName);
        return playerName;
    }

    private static void validatePlayerName(String playerName) {
        if (playerName.length() != PLAYER_LENGTH) {
            throw new IllegalArgumentException(String.format("플레이어 이름은 %d자를 입력해야 합니다.", PLAYER_LENGTH));
        }
    }

    public static int inputNumberOfPinsHit(int indexOfFrame, int remainedPins) {
        System.out.printf("%d프레임 투구 : ", (indexOfFrame + 1));
        int hit = Integer.parseInt(SCANNER.nextLine());
        validateHit(hit, remainedPins);
        return hit;
    }

    private static void validateHit(int hit, int remainedPins) {
        if (hit < BowlingGameFrame.MIN_NUMBER_OF_BOWLING_PINS) {
            throw new IllegalArgumentException(String.format("%d 보다 큰 정수 값을 입력해야 합니다.", BowlingGameFrame.MIN_NUMBER_OF_BOWLING_PINS));
        }
        if (hit > remainedPins) {
            throw new IllegalArgumentException(String.format("남아 있는 볼링 핀의 개수는 %d 개 입니다.", remainedPins));
        }
    }

}

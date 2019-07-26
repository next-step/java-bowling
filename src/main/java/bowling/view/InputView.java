package bowling.view;

import bowling.Player;
import java.util.Scanner;

public class InputView {

  private static final String QUESTION_OF_PLAYER_NAME = "플레이어 이름은(3 english letters)?:";
  private static final String QUESTION_OF_FALL_DOWN_COUNT = "프레임 투구 ?";

  static Scanner scanner = new Scanner(System.in);

  public static Player askPlayerName() {
    System.out.println(QUESTION_OF_PLAYER_NAME);
    return Player.of(scanner.next());
  }

  public static int askFallDownCount(int frameNo) {
    System.out.println(frameNo + QUESTION_OF_FALL_DOWN_COUNT);
    return scanner.nextInt();
  }

}

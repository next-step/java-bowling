package bowling.view;

import bowling.Player;
import java.util.Scanner;

public class InputView {

  private static final String QUESTION_OF_PLAYER_NAME = "플레이어 이름은(3 english letters)?:";
  private static final String QUESTION_OF_FALL_DOWN_COUNT = "'turn :";
  private static final String QUESTION_OF_HOW_MANY_PEOPLE = "How many people?";

  static Scanner scanner = new Scanner(System.in);

  public static Player askPlayerName() {
    System.out.println(QUESTION_OF_PLAYER_NAME);
    return Player.of(scanner.next());
  }

  public static int askFallDownCount(String frameNo) {
    System.out.println(frameNo + QUESTION_OF_FALL_DOWN_COUNT);
    return scanner.nextInt();
  }

  public static int askHowManyPeople() {
    System.out.println(QUESTION_OF_HOW_MANY_PEOPLE);
    return scanner.nextInt();
  }

}

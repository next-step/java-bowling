package bowling_step4.view;

import bowling_step4.domain.Player;
import java.util.Scanner;

public class InputView {

  private static final String INPUT_NAME = "플레이어 이름은(3 english letters)?: ";
  private static final String INPUT_PLAYER_COUNT = "How many people? ";
  private static final String INPUT_PIN_COUNT_BY_PLAYER = "%s's turn : ";
  private final Scanner scanner = new Scanner(System.in);

  public String inputName() {
    System.out.print(INPUT_NAME);
    return scanner.next();
  }

  public int inputPlayerCount() {
    System.out.print(INPUT_PLAYER_COUNT);
    return scanner.nextInt();
  }

  public int inputPinCountByPlayer(Player player) {
    System.out.print(String.format(INPUT_PIN_COUNT_BY_PLAYER, player.getName()));
    return scanner.nextInt();
  }
}

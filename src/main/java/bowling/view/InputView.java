package bowling.view;

import bowling.domain.player.Name;
import java.util.Scanner;

public class InputView {

  public static int inputCountOfPlayer() {
    System.out.print("How many people? ");
    return new Scanner(System.in).nextInt();
  }

  public static String inputOfPlayerName(int playerCount) {
    System.out.printf("플레이어 %d의 이름은(3 english letters)?: ", playerCount + 1);
    return new Scanner(System.in).nextLine();
  }

  public static int inputCountOfHitPin(Name name) {
    System.out.printf("%s's turn : ", name.name());
    return new Scanner(System.in).nextInt();
  }
}

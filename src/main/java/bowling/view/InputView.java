package bowling.view;

import java.util.Scanner;

public class InputView {

  public static String inputOfPlayerName() {
    System.out.print("플레이어 이름은(3 english letters)?: ");
    return new Scanner(System.in).nextLine();
  }
}

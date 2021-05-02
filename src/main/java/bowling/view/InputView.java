package bowling.view;

import java.util.Scanner;

public class InputView {

  private static final Scanner SCANNER = new Scanner(System.in);

  public String getName() {
    System.out.print("플레이어 이름은(3 english letters)?: ");
    return SCANNER.nextLine();
  }

  public int getHitPinsCount(int frameNumber) {
    System.out.printf("%s프레임 투구 : ", frameNumber);
    return Integer.parseInt(SCANNER.nextLine());
  }

}

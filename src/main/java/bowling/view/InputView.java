package bowling.view;

import java.util.Scanner;

public class InputView {

  public static final Scanner SCANNER = new Scanner(System.in);

  public static String getName() {
    System.out.print("플레이어 이름은(3 english letters)?: ");
    return SCANNER.nextLine();
  }

  public static int getHitPinsCount(int frameNumber) {
    System.out.printf("%d프레임 투구 : ", frameNumber);
    return Integer.parseInt(SCANNER.nextLine());
  }

  public static int getBonusHitPinsCount() {
    System.out.print("보너스 투구 : ");
    return Integer.parseInt(SCANNER.nextLine());
  }

}

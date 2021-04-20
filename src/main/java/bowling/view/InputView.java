package bowling.view;

import bowling.domain.Frame;
import java.util.Scanner;

public class InputView {

  public static final Scanner SCANNER = new Scanner(System.in);

  public static String getName() {
    System.out.print("플레이어 이름은(3 english letters)?: ");
    return SCANNER.nextLine();
  }

  public static int getHitPinsCount(Frame frame) {
    System.out.printf("%d프레임 투구 : ", frame.getNumber());
    return Integer.parseInt(SCANNER.nextLine());
  }

}

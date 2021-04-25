package bowling.view;

import bowling.domain.frame.Frames;
import java.util.Scanner;

public class InputView {

  public static String inputOfPlayerName() {
    System.out.print("플레이어 이름은(3 english letters)?: ");
    return new Scanner(System.in).nextLine();
  }

  public static int inputCountOfHitPin(Frames frames) {
    System.out.printf("%d프레임 투구 : ", frames.round());
    return new Scanner(System.in).nextInt();
  }
}

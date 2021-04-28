package bowling.view;

import bowling.domain.Bowling;
import java.util.Scanner;

public class InputView {

  private static final Scanner SCANNER = new Scanner(System.in);
  private static final String BONUS = "bonus";

  public static String getName() {
    System.out.print("플레이어 이름은(3 english letters)?: ");
    return SCANNER.nextLine();
  }

  public static int getHitPinsCount(int frameNumber) {
    System.out.printf("%s프레임 투구 : ", getFrameNumber(frameNumber));
    return Integer.parseInt(SCANNER.nextLine());
  }

  private static String getFrameNumber(int frameNumber) {
    if (frameNumber > Bowling.BASIC_FRAME) {
      return BONUS;
    }
    return String.valueOf(frameNumber);
  }

  public static int getBonusHitPinsCount() {
    System.out.print("보너스 투구 : ");
    return Integer.parseInt(SCANNER.nextLine());
  }

}

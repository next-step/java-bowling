package bowling.view;

import java.util.Scanner;

public class BowlingInput {

  private final static Scanner scanner = new Scanner(System.in);

  public static String getPlayerNameInput() {
    BowlingView.printPlayerNameInputMsg();

    return scanner.nextLine();
  }

  public static int getKnockDownNumberInput(int currentFrameNumber) {
    BowlingView.printKnockDownNumInputMsg(currentFrameNumber);

    int result = scanner.nextInt();
    scanner.nextLine();

    return result;
  }

  public static int getBonusFrameInput() {
    BowlingView.printBonusFrameInputMsg();

    int result = scanner.nextInt();
    scanner.nextLine();

    return result;
  }
}

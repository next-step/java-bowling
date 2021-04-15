package bowling.view;

import bowling.domain.frame.Frame;
import java.util.Scanner;

public class InputView {

  private final Scanner scanner = new Scanner(System.in);
  private static final String INPUT_NAME = "플레이어 이름은(3 english letters)?: ";

  public String inputName() {
    System.out.print(INPUT_NAME);
    return scanner.nextLine();
  }

  public int inputPinCount(Frame frame) {
    System.out.print(frame.getPlayCount() + "프레임 투구 : ");
    return scanner.nextInt();
  }
}

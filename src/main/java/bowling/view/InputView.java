package bowling.view;

import bowling.domain.frame.Frame;
import java.util.Scanner;

public class InputView {

  private final Scanner scanner = new Scanner(System.in);
  private static final String INPUT_NAME = "플레이어 이름은(3 english letters)?: ";
  private static final String INPUT_PIN_COUNT = "%s 프레임투구 : ";

  public String inputName() {
    System.out.print(INPUT_NAME);
    return scanner.nextLine();
  }

  public int inputPinCount(Frame frame) {
    System.out.print(String.format(INPUT_PIN_COUNT, frame.getPlayCount()));
    return scanner.nextInt();
  }
}

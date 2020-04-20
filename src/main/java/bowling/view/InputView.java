package bowling.view;

import bowling.domain.Frame;
import bowling.domain.Player;
import java.util.Scanner;

public class InputView {
  private static final String PLAYER_NAME_QUESTION = "플레이어 이름은(3 english letters)?: ";
  private static final String FRAME_BOWLING_MESSAGE = "%d 프레임 투구 : ";

  private Scanner scanner;

  public InputView() {
    scanner = new Scanner(System.in);
  }

  public Player acceptPlayer() {
    printWithNewLine(PLAYER_NAME_QUESTION);
    String name = scanner.nextLine();

    return new Player(name);
  }

  public int inputPinCount(Frame frame) {
    printWithNewLine(String.format(FRAME_BOWLING_MESSAGE, frame.getRound()));
    return Integer.parseInt(scanner.nextLine());
  }

  private void printWithNewLine(String sentence) {
    System.out.println();
    System.out.println(sentence);
  }
}

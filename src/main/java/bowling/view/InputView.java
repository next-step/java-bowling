package bowling.view;

import java.util.Scanner;

public class InputView {

  private final Scanner scanner;

  public InputView() {
    scanner = new Scanner(System.in);
  }

  public String requestName() {
    System.out.print("플레이어 이름은(3 english letters)?: ");

    return scanner.next();
  }

  public int requestPins(int number) {
    System.out.printf("\n%d프레임 투구 : ", number);
    return scanner.nextInt();
  }
}

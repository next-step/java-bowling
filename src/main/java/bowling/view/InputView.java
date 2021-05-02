package bowling.view;

import java.util.Scanner;

public class InputView {
  private final Scanner scanner;

  public InputView() {
    scanner = new Scanner(System.in);
  }

  public String setupPlayer(){
    System.out.print("플레이어 이름은(3 english letters)?: ");
    return scanner.next();
  }

  public int setupPins(int round){
    System.out.print(String.format("%d프레임 투구 : ", round));
    return scanner.nextInt();
  }
}

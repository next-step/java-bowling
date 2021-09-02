package bowling.view;

import java.util.Scanner;

public class InsertView {

  private static final String MSG_INSERT_NAME = "플레이어 이름은(3 english letters)? : ";
  private static final String MSG_BALL_COUNT = "프레임 투구 : ";

  private static final Scanner scanner = new Scanner(System.in);

  public static String inputName() {
    System.out.print(MSG_INSERT_NAME);
    return scanner.nextLine();
  }

  public static int throwBall(int round) {
    System.out.print(round + MSG_BALL_COUNT);
    return scanner.nextInt();
  }
}

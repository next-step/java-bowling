package bowling.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {}

    public static String userNameInput(){
        System.out.print("플레이어 이름은(3 english letters)?: ");
        return scanner.nextLine();
    }

    public static int framePithInput(int frame){
        System.out.print(frame + "프레임 투구 : ");
        return scanner.nextInt();
    }


}

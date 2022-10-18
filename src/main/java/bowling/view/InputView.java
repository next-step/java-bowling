package bowling.view;

import java.util.Scanner;

public class InputView {

    private static Scanner SCANNER = new Scanner(System.in);

    private InputView(){

    }

    public static String askPlayerName(){
        System.out.printf("플레이어 이름은(3 english letters)?:");
        return SCANNER.nextLine();
    }

}

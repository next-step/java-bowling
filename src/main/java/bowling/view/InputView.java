package bowling.view;

import java.util.Scanner;

public class InputView {
    private static Scanner scanner = new Scanner(System.in);

    private String getName(){
        System.out.printf("3 english letters?: ");
        return scanner.nextLine();
    }

}

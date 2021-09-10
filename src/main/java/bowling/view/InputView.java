package bowling.view;

import java.util.Scanner;

public class InputView {
    public static String ask(String message){
        Scanner scanner = new Scanner(System.in);
        System.out.print(message);
        return scanner.next();
    }
}

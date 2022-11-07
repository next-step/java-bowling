package bowling.view;

import java.util.Scanner;

public class InputView {

    private static Scanner SCANNER = new Scanner(System.in);

    private InputView(){

    }

    public static String askPlayerName(int i){
        System.out.printf("플레이어 "+ i +"의 이름은(3 english letters)?:");
        return SCANNER.nextLine();
    }

    public static int askHowManyPeople() {
        System.out.printf("How many people? : ");
        int many = SCANNER.nextInt();
        SCANNER.nextLine();
        return many;
    }
}

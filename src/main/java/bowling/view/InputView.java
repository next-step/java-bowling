package bowling.view;

import bowling.domain.Person;
import java.util.Scanner;

public class InputView {

    private InputView() {
    }

    public static Person user() {
        Scanner sc = new Scanner(System.in);
        System.out.println("플레이어 이름은 : ");
        String name = sc.nextLine();
        return Person.from(name);
    }

    public static int downPinsCount() {
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }
}

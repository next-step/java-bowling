package bowling.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public int peopleNumber() {
        System.out.print("How many people? ");
        return Integer.parseInt(scanner.nextLine());
    }

    public List<String> name(int peopleNumber) {
        List<String> userNames = new ArrayList<>();
        for (int i = 0; i < peopleNumber; i++) {
            System.out.print("플레이어 " + peopleNumber + "의 이름은?(3 english letters): ");
            userNames.add(scanner.nextLine());
        }

        return userNames;
    }

    public int pinNumber(String name) {
        System.out.print(name + "'s turn : ");
        return Integer.parseInt(scanner.nextLine());
    }
}
package bowling.view;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class InputView {
    public static int requestPlayerCount() {
        System.out.print("How many people?: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }


    public static List<String> requestPlayerNames(int count) {
        Scanner scanner = new Scanner(System.in);
        return Stream.generate(() -> {
            System.out.print("플레이어 이름은(3 english letters)?: ");
            return scanner.nextLine();
        })
                .limit(count)
                .collect(toList());
    }

    public static int requestFallingPins(String name) {
        System.out.printf("%s's turn : ", name);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}

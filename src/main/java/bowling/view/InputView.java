package bowling.view;

import bowling.domain.Pins;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static int inputUserNumber() {
        System.out.print("How many people?");
        return SCANNER.nextInt();
    }

    public static List<String> inputLetters(int number) {
        return IntStream.range(0, number)
                .mapToObj(i -> {
                    System.out.print("플레이어 " + (i +1) + "이름은(3 english letters)?");
                    return SCANNER.next();
                })
                .collect(Collectors.toList());
    }

    public static Pins inputBowl(String letters) {
        System.out.print(letters + "'s turn : ");
        return new Pins(SCANNER.nextInt());
    }

}

package bowling.view;

import bowling.domain.Pins;
import bowling.domain.User;
import bowling.domain.Users;

import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static int inputUserNumber() {
        System.out.print("How many people?");
        return SCANNER.nextInt();
    }

    public static Users inputLetters(int number) {
        SCANNER.nextLine();
        return new Users(IntStream.range(0, number)
                .mapToObj(i -> {
                    System.out.print("플레이어 " + (i +1) + "이름은(3 english letters)?");
                    return new User(SCANNER.nextLine());
                })
                .collect(Collectors.toList()));
    }

    public static Pins inputBowl(String letters) {
        System.out.print(letters + "'s turn : ");
        return new Pins(SCANNER.nextInt());
    }

}

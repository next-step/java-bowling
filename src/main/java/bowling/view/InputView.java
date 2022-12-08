package bowling.view;

import bowling.domain.Name;
import bowling.domain.PinCount;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static List<Name> readName(int count) {
        return IntStream.range(0, count)
                .peek(i -> System.out.print("플레이어 이름은(3 english letters)?: "))
                .mapToObj(i -> Name.of(SCANNER.nextLine()))
                .collect(Collectors.toList());
    }

    public static PinCount readPinCount(Name name) {
        System.out.println();
        System.out.printf("%s's turn : ", name);
        PinCount count = PinCount.of(SCANNER.nextInt());
        SCANNER.nextLine();
        return count;
    }

    public static int readPlayerCount() {
        System.out.print("How many people? ");
        int count = SCANNER.nextInt();
        System.out.println();
        SCANNER.nextLine();
        return count;
    }
}

package bowling.domain.view;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InputView {


    private static final Scanner SCANNER;

    static {
        SCANNER = new Scanner(System.in);
    }

    private InputView() {}

    public static List<String> enterUserNames() {
        return IntStream.range(0, 1)
                .mapToObj(index -> enterUserName())
                .collect(Collectors.toList());
    }

    private static String enterUserName() {
        System.out.print("플레이어 이름은(3 english letters)?: ");
        return SCANNER.nextLine();
    }

    public static int enterPinCount(int frameNumber) {
        System.out.printf("%s 프레임 투구 : ", frameNumber);
        return SCANNER.nextInt();
    }
}

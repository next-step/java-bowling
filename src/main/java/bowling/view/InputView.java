package bowling.view;

import bowling.domain.Name;
import bowling.domain.PinCount;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static Name readName() {
        System.out.print("플레이어 이름은(3 english letters)?: ");
        Name name = Name.of(SCANNER.nextLine());
        return name;

    }

    public static PinCount readPinCount(int roundIndex) {
        System.out.println();
        System.out.printf("%d 프레인 투구 : ", roundIndex);
        PinCount count = PinCount.of(SCANNER.nextInt());
        SCANNER.nextLine();
        return count;
    }
}

package bowling.domain.controller.view;

import bowling.domain.exception.ServiceException;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static List<String> getNames() {
        System.out.print("How many people? ");
        int sizeOfPeople = nextInt();

        return Stream.generate(InputView::getName)
                .limit(sizeOfPeople)
                .collect(Collectors.toList());
    }

    private static int nextInt() {
        return parseInt(SCANNER.nextLine());
    }

    private static int parseInt(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            throw new ServiceException("숫자만 가능합니다.");
        }
    }

    private static String getName() {
        System.out.print("플레이어 이름은(3 english letters)?: ");
        return SCANNER.nextLine();
    }

    public static int getHitCount(String name) {
        System.out.print(name + "'s turn: ");
        return nextInt();
    }
}

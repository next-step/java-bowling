package bowling.domain.controller.view;

import bowling.domain.exception.ServiceException;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static String getName() {
        System.out.print("플레이어 이름은(3 english letters)?: ");
        return SCANNER.nextLine();
    }

    public static int getHitCount(int numberOfFrame) {
        System.out.print(numberOfFrame + "프레임 투구: ");
        return parseInt(SCANNER.nextLine());
    }

    private static int parseInt(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            throw new ServiceException("숫자만 가능합니다.");
        }
    }
}

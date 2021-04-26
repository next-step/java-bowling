package bowling;

import java.util.Scanner;

public final class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {}

    public static String inputPlayerName() {
        System.out.print("플레이어의 이름을 입력해주세요.(영문 대소문자 3자이내): ");
        return scanner.nextLine();
    }
}

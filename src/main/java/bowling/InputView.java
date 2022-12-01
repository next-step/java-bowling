package bowling;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static int inputPlayerNumber() {
        System.out.print("How many people?");
        return SCANNER.nextInt();
    }

    public static List<UserName> inputUserNames(int count) {
        List<UserName> userNames = new ArrayList<>();
        for (int index = 1; index < count+1; index++) {
            System.out.printf("플레이어 %d의 이름은(3 english letters)?:", index);
            String input = SCANNER.next();
            userNames.add(new UserName(input));
        }
        return userNames;
    }

    public static int inputUserScore(UserName userName) {
        System.out.printf("%s's turn : ", userName.getName());
        return SCANNER.nextInt();
    }
}

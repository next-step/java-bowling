package bowling.view;

import bowling.domain.UserName;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final int MIN_USER = 1;

    public static UserName getUserName() {
        while (true) {
            try {
                return makeUserName();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static UserName makeUserName() {
        System.out.print("플레이어 이름은(3 english letters)?:");
        String name = SCANNER.next();
        UserName userName = new UserName(name);
        return userName;
    }
}

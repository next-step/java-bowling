package bowling.view;

import bowling.domain.User;

import java.util.Scanner;

/**
 * Created : 2020-12-16 오전 9:23
 * Developer : Seo
 */
public class InputView {
    public static final String ENTER_USERS = "플레이어 이름은(3 english letters)?: ";

    private static final Scanner scanner = new Scanner(System.in);

    public static User getUsers() {
        System.out.print(ENTER_USERS);
        return new User(scanner.next());
    }

    private InputView() {
    }
}

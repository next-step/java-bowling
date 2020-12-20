package bowling.view;

import bowling.domain.Pins;
import bowling.domain.User;
import bowling.domain.Users;

import java.util.Scanner;

/**
 * Created : 2020-12-16 오전 9:23
 * Developer : Seo
 */
public class InputView {
    public static final String ENTER_USERS = "How many people? ";
    public static final String ENTER_USER = "플레이어 %d의 이름은?(3 english letters): ";
    public static final String ENTER_PINS = "'s turn : ";

    private static final Scanner scanner = new Scanner(System.in);

    public static Users getUsers() {
        print(ENTER_USERS);

        int usersCount = scanner.nextInt();
        Users users = new Users();
        for (int i = 1; i < usersCount + 1; i++) {
            printf(ENTER_USER, i);
            users.add(new User(scanner.next()));
        }
        return users;
    }

    public static Pins getPins(String name) {
        print(name + ENTER_PINS);
        return new Pins(scanner.nextInt());
    }

    private static void print(String args) {
        System.out.print(args);
    }

    private static void printf(String format, int args) {
        System.out.printf(format, args);
    }

    private InputView() {
    }
}

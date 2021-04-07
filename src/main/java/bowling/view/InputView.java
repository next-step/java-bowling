package bowling.view;

import bowling.domain.Frames;
import bowling.domain.User;
import bowling.domain.Users;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class InputView {
    private static Scanner scanner = new Scanner(System.in);
    private static final int PLUS_SIZE_ONE = 1;

    public int userSize() {
        System.out.print("How many people? ");
        return Integer.parseInt(scanner.nextLine());
    }

    public User user() {
        System.out.print("플레이어 이름은(3 english letters)?:");
        return User.of(scanner.nextLine());
    }

    public int score(User user) {
        System.out.printf("%s, %d프레임 투구 :"
                , user.name()
                , framesSize(user.frames()));
        return scanner.nextInt();
    }

    private int framesSize(Frames frames) {
        if (frames.isPitch()) {
            return frames.frames().size() + PLUS_SIZE_ONE;
        };
        return frames.frames().size();
    }

    public Users users() {
        List<User> users = new ArrayList<>();
        int userSize = userSize();

        IntStream.range(0, userSize)
                .forEach(i -> users.add(user()));

        return Users.of(users);
    }
}

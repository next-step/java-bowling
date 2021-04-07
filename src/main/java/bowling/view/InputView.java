package bowling.view;

import bowling.domain.Frames;
import bowling.domain.User;

import java.util.Scanner;

public class InputView {
    private static Scanner scanner = new Scanner(System.in);
    private static final int PLUS_SIZE_ONE = 1;

    public User user() {
        System.out.print("플레이어 이름은(3 english letters)?:");
        return User.of(scanner.nextLine());
    }

    public int score(Frames frames) {
        System.out.printf("%d프레임 투구 :", framesSize(frames));
        return scanner.nextInt();
    }

    private int framesSize(Frames frames) {
        if (frames.isPitch()) {
            return frames.frames().size() + PLUS_SIZE_ONE;
        };
        return frames.frames().size();
    }
}

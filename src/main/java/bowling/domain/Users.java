package bowling.domain;

import java.util.List;

public class Users {
    private static final String MIN_SIZE_UNDER = "최소 참여자는 1명 입니다.";
    private static final int MIN_SIZE = 1;

    private final List<User> users;

    private Users(List<User> users) {
        this.users = users;
    }

    public static Users of(List<User> users) {
        validMinSize(users);
        return new Users(users);
    }

    public List<User> users() {
        return users;
    }

    public boolean isAllPlay() {
        return users.stream()
                .allMatch(user -> user.isPlay());
    }

    private static void validMinSize(List<User> users) {
        if (users.size() < MIN_SIZE) {
            throw new IllegalArgumentException(MIN_SIZE_UNDER);
        }
    }
}

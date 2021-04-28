package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Users {

    private static final int MAX_NUMBER = 6;

    private final List<User> users;

    public Users() {
        users = new ArrayList<>();
    }

    public List<User> getUsers() {
        return users;
    }

    public void add(User user) {
        users.add(user);
        if (users.size() > MAX_NUMBER) {
            throw new IllegalArgumentException("한 레인의 최대 인원 수는 6인 입니다.");
        }
    }
}
package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Users {
    private final List<User> users;

    public Users() {
        users = new ArrayList<>();
    }

    public void add(User user) {
        users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }

    public User get(int i) {
        return users.get(i);
    }

    public int size() {
        return users.size();
    }

    public void bowl(int frameNo) {
        for (User user : users) {
            user.bowl(frameNo);
        }
    }
}

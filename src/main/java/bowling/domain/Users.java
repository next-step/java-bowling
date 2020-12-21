package bowling.domain;

import bowling.view.ResultView;

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

    public User get(int i) {
        return users.get(i);
    }

    public int size() {
        return users.size();
    }

    public void bowl(int frameNo, ResultView resultView) {
        for (User user : users) {
            user.bowl(frameNo, resultView);
        }
    }
}

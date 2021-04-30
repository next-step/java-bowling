package bowling.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Users {

    private static final int MAX_NUMBER = 6;

    private final List<User> users;

    public Users(List<String> userNames) {
        users = userNames.stream()
                .map(User::new)
                .collect(Collectors.toList());

        isValidate();
    }

    public List<User> getUsers() {
        return users;
    }

    private void isValidate() {
        if (users.size() > MAX_NUMBER) {
            throw new IllegalArgumentException("한 레인의 최대 인원 수는 6인 입니다.");
        }
    }
}
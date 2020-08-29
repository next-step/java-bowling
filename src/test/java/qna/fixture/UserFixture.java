package qna.fixture;

import qna.domain.User;

public class UserFixture {
    private UserFixture() {}

    public static User make(Long id, String userId, String password, String name, String email) {
        return new User(id, userId, password, name, email);
    }
}

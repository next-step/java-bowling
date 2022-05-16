package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import qna.UnAuthorizedException;

public class UserTest {
    public static final User JAVAJIGI = new User(1L, "javajigi", "password", "name", "javajigi@slipp.net");
    public static final User SANJIGI = new User(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");

    @Test
    void update_성공() {
        User loginUser = new User("javajigi", "password", "name", "javajigi@slipp.net");
        User target = new User("javajigi", "password", "changedName", "changed@google.com");

        loginUser.update(JAVAJIGI, target);

        assertThat(loginUser.equalsNameAndEmail(target)).isTrue();
    }

    @Test
    void update_실패() {
        assertThatThrownBy(() -> JAVAJIGI.update(SANJIGI, SANJIGI))
            .isInstanceOf(UnAuthorizedException.class);
    }
}

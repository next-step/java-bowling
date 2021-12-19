package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {
    public static final User JAVAJIGI = new User(1L, "javajigi", "password", "name", "javajigi@slipp.net");
    public static final User SANJIGI = new User(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");

    @Test
    @DisplayName("로그인한 user와 비교하는 user가 같은지 체크한다.")
    void isEqualsUser() {
        assertThat(JAVAJIGI.isEqualsUser(JAVAJIGI)).isTrue();
        assertThat(JAVAJIGI.isEqualsUser(SANJIGI)).isFalse();
    }
}

package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {
    public static final User JAVAJIGI = new User(1L, "javajigi", "password", "name", "javajigi@slipp.net");
    public static final User SANJIGI = new User(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");

    @DisplayName("같은 사람인지 체크")
    @Test
    void checkIsSameUser() {
        assertThat(JAVAJIGI.isSameUser(JAVAJIGI)).isTrue();
        assertThat(JAVAJIGI.isSameUser(new User(1L, "javajigi", "password", "name", "javajigi@slipp.net"))).isTrue();
    }

    @DisplayName("다른 사람인지 체크")
    @Test
    void checkIsNotSameUser() {
        assertThat(JAVAJIGI.isSameUser(SANJIGI)).isFalse();
        assertThat(JAVAJIGI.isSameUser(new User(1L, "j", "password", "name", "javajigi@slipp.net"))).isFalse();
    }
}

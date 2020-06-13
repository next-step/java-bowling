package qna.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

    public static final User JAVAJIGI = new User(1L, "javajigi", "password", "name", "javajigi@slipp.net");
    public static final User SANJIGI = new User(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");

    @DisplayName("userId 가 같으면 동일한 사용자")
    @Test
    public void equals() {
        User other = new User(1L, "javajigi", "1111", "jj", "jj@gmail.com");

        assertThat(JAVAJIGI).isEqualTo(other);
    }
}

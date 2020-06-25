package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserTest {
    public static final User JAVAJIGI = new User(1L, "javajigi", "password", "name", "javajigi@slipp.net");
    public static final User SANJIGI = new User(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");

    @DisplayName("userId 가 같으면 동일한 사용자인지 테스트")
    @Test
    public void equals() {
        User other = new User(1L, "javajigi", "123", "javajigi", "javajigi@gmail.com");
        assertThat(JAVAJIGI).isEqualTo(other);
    }
}

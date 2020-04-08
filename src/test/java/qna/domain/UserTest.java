package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {
    public static User JAVAJIGI = new User(1L, "javajigi", "password", "name", "javajigi@slipp.net");
    public static User SANJIGI = new User(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");


    @DisplayName("유저 ID가 같으면 같은 유저이다.")
    @Test
    void same() {
        User other = new User(1L, "javajigi", "aa", "aa", "aa");

        assertThat(JAVAJIGI).isEqualTo(other);
    }
}

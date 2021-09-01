package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {
    public static final User JAVAJIGI = new User(1L, "javajigi", "password", "name", "javajigi@slipp.net");
    public static final User SANJIGI = new User(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");

    @Test
    @DisplayName("동일 유저인지 확인하는 테스트")
    void equalsTest() {
        assertThat(JAVAJIGI.equals(JAVAJIGI)).isTrue();

        assertThat(JAVAJIGI.equals(SANJIGI)).isFalse();
    }
}

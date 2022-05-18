package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {
    public static final User JAVAJIGI = new User(1L, "javajigi", "password", "name", "javajigi@slipp.net");
    public static final User SANJIGI = new User(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");
    public static final User BADAJIGI = new User(2L, "badajigi", "password", "name", "sanjigi@slipp.net");

    @Test
    @DisplayName("User의 이름과 이메일이 같으면 true 반환")
    void returnTrueWhenUsersHasSameNameAndEmail() {
        assertThat(JAVAJIGI.isSameUser(SANJIGI)).isFalse();
        assertThat(SANJIGI.isSameUser(BADAJIGI)).isTrue();
    }
}

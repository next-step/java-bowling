package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {
    public static final User JAVAJIGI = new User(1L, "javajigi", "password", "name", "javajigi@slipp.net");
    public static final User SANJIGI = new User(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");

    @Test
    @DisplayName("회원 비밀번호 일치여부")
    public void matchUserPassword() {
        assertThat(JAVAJIGI.matchPassword(JAVAJIGI.getPassword())).isTrue();
        assertThat(JAVAJIGI.matchPassword(SANJIGI.getPassword())).isTrue();
    }
    
    @Test
    @DisplayName("이름과 이메일 일치여부")
    public void equalsNameAndEmail() {
        assertThat(JAVAJIGI.equalsNameAndEmail(JAVAJIGI)).isTrue();
        assertThat(JAVAJIGI.equalsNameAndEmail(SANJIGI)).isFalse();
    }
}

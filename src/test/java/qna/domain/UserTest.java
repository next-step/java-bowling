package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.fixture.UserFixture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class UserTest {
    @DisplayName("사용자가 잘 생성 되는지 확인")
    @Test
    public void userTest() {
        // given
        Long id = 1L;
        String userId = "Userid";
        String name = "Name";
        String password = "Password";
        String email = "javajigi@slipp.net";

        // when
        User user = new User(id, userId, password, name, email);

        // then
        assertAll(
                () -> assertThat(user.getId()).isEqualTo(id),
                () -> assertThat(user.getUserId()).isEqualTo(userId),
                () -> assertThat(user.getName()).isEqualTo(name),
                () -> assertThat(user.getPassword()).isEqualTo(password),
                () -> assertThat(user.getEmail()).isEqualTo(email),
                () -> assertThat(user.matchPassword(password)).isTrue()
        );
    }

    @DisplayName("사용자가 잘 변경 되는지 확인")
    @Test
    public void updateTest() {
        // given
        String updatedName = "Updated Name";
        String updatedEmail = "Updated Email";
        User user = UserFixture.make(1L, "UserId", "Password", "Name", "Email");
        User target = UserFixture.make(1L, "UserId", "Password", updatedName, updatedEmail);

        // when
        user.update(user, target);

        // when
        assertAll(
                () -> assertThat(user.getName()).isEqualTo(updatedName),
                () -> assertThat(user.getEmail()).isEqualTo(updatedEmail)
        );
    }

    @DisplayName("사용자의 이름과 이메일 주소가 일치하는지 확인")
    @Test
    public void equalsNameAndEmailTest() {
        // given
        String name = "Name";
        String email = "Email";
        User user = UserFixture.make(1L, "UserId", "Password", name, email);
        User target = UserFixture.make(2L, "Other userId", "Password", name, email);


        // when & then
        assertAll(
                () -> assertThat(user.equalsNameAndEmail(target)).isTrue(),
                () -> assertThat(user.equalsNameAndEmail(null)).isFalse()
        );
    }
}

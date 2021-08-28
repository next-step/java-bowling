package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.UnAuthorizedException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class UserTest {
    public static final User JAVAJIGI = new User(1L, "javajigi", "password", "name", "javajigi@slipp.net");
    public static final User SANJIGI = new User(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");

    @DisplayName("create test")
    @Test
    void create() {
        User testUser = new User(2L, "sanjigi", "password123", "name123", "sanjigi123@slipp.net");

        assertThat(testUser).isEqualTo(new User(2L, "sanjigi", "password123", "name123", "sanjigi123@slipp.net"));
    }

    @DisplayName("getUserId test")
    @Test
    void getUserId() {
        User testUser = new User(2L, "sanjigi", "password123", "name123", "sanjigi123@slipp.net");
        assertThat(testUser.getUserId()).isEqualTo("sanjigi");
    }

    @DisplayName("setUserId test")
    @Test
    void setUserId() {
        User expectedUser = new User(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");
        User actualUser = new User(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");

        assertThat(actualUser.setUserId("sanjigi")).isEqualTo(expectedUser);
    }

    @DisplayName("getPassword test")
    @Test
    void getPassword() {
        User actualUser = new User(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");

        assertThat(actualUser.getPassword()).isEqualTo("password");
    }

    @DisplayName("setPassword test")
    @Test
    void setPassword() {
        User expectedUser = new User(2L, "sanjigi", "password123", "name", "sanjigi@slipp.net");
        User actualUser = new User(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");

        assertThat(actualUser.setPassword("password123")).isEqualTo(expectedUser);
    }

    @DisplayName("getName test")
    @Test
    void getName() {
        User actualUser = new User(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");

        assertThat(actualUser.getName()).isEqualTo("name");
    }

    @DisplayName("setName test")
    @Test
    void setName() {
        User expectedUser = new User(2L, "sanjigi", "password", "name123", "sanjigi@slipp.net");
        User actualUser = new User(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");

        assertThat(actualUser.setName("name123")).isEqualTo(expectedUser);
    }

    @DisplayName("getEmail test")
    @Test
    void getEmail() {
        User expectedUser = new User(2L, "sanjigi", "password123", "name123", "sanjigi@slipp.net");

        assertThat(expectedUser.getEmail()).isEqualTo("sanjigi@slipp.net");
    }

    @DisplayName("setEmail test")
    @Test
    void setEmail() {
        User expectedUser = new User(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");
        User actualUser = new User(2L, "sanjigi", "password", "name", "sanjigi123@slipp.net");

        assertThat(actualUser.setEmail("sanjigi@slipp.net")).isEqualTo(expectedUser);
    }

    @DisplayName("update test")
    @Test
    void update() {
        User updatedUser = new User(2L, "sanjigi", "password123", "name123", "test");
        User targetUser = new User(2L, "sanjigi", "password123", "name123", "sanjigi@slipp.net");
        User loginUser = new User(2L, "sanjigi", "password123", "name123", "sanjigi123@slipp.net");

        updatedUser.update(loginUser, targetUser);

        assertThat(updatedUser).isEqualTo(targetUser);
    }

    @DisplayName("update 시 인가된 사용자 아니면 Exception")
    @Test
    void unAuthorizedException() {
        User updatedUser = new User(2L, "sanjigi", "password123", "name123", "test");
        User targetUser = new User(2L, "sanjigi", "password123", "name123", "sanjigi@slipp.net");
        User loginUser = new User(2L, "sanjigi123", "password12356", "name123", "sanjigi123@slipp.net");

        assertThatExceptionOfType(UnAuthorizedException.class)
                .isThrownBy(() -> {
                    updatedUser.update(loginUser, targetUser);
                });
    }

    @Test
    void matchPassword() {
        User testUser = new User(2L, "sanjigi", "password123", "name123", "sanjigi123@slipp.net");

        assertThat(testUser.matchPassword("password123")).isTrue();

    }

    @Test
    void equalsNameAndEmail() {
        User targetUser = new User(2L, "sanjigi", "password123", "name123", "sanjigi123@slipp.net");
        User loginUser = new User(2L, "sanjigi123", "password12356", "name123", "sanjigi123@slipp.net");

        assertThat(loginUser.equalsNameAndEmail(targetUser)).isTrue();
    }

}

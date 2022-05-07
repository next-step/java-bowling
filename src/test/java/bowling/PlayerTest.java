package bowling;

import bowling.exception.InvalidUsernameException;
import bowling.model.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerTest {

    @Test
    @DisplayName("사용자의 객체를 생성한다")
    void create() {
        assertThat(Player.create("PBK")).isInstanceOf(Player.class);
    }

    @Test
    @DisplayName("사용자 이름은 3글자 이내만 허용한다")
    void throwExceptionAboutNameLength() {
        String username = "sample";
        assertThatThrownBy(() -> Player.create(username)).isInstanceOf(InvalidUsernameException.class)
                .hasMessageContaining("사용자 이름은 빈칸 또는 %d 글자는 불가능 합니다. (length : %d)", 3, username.length());
    }

    @Test
    @DisplayName("사용자 이름은 영어만 허용한다")
    void throwExceptionAboutUsernameAlphabetic() {
        String username = "한글";
        assertThatThrownBy(() -> Player.create(username)).isInstanceOf(InvalidUsernameException.class)
                .hasMessageContaining("사용자의 이름은 영문만 가능합니다. (username : %s)", username);
    }

}
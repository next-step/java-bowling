package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserTest {

    @DisplayName("이름을 바탕으로 사용자를 생성한다.")
    @ParameterizedTest
    @ValueSource(strings = {"KCH", "PJS"})
    void createUserTest(String name) {
        new User(name);
    }

    @DisplayName("이름이 3글자가 아니면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"포비", "포비포비"})
    void createUserTestFail(String name) {
        assertThatThrownBy(() -> new User(name))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

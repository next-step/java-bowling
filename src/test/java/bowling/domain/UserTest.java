package bowling.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class UserTest {

    @ParameterizedTest
    @ValueSource(strings = "")
    public void empty_user_name_test(String name) {
        assertThatThrownBy(() -> new User(name)).isInstanceOf(IllegalArgumentException.class)
                                                .hasMessageContaining("이름이 비어있습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"abcd", "abcde"})
    public void user_name_length_test(String name) {
        assertThatThrownBy(() -> new User(name)).isInstanceOf(IllegalArgumentException.class)
                                                .hasMessageContaining("이름은 3글자 이상을 넘길 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"테스트", "123"})
    public void user_name_not_english_test(String name) {
        assertThatThrownBy(() -> new User(name)).isInstanceOf(IllegalArgumentException.class)
                                                .hasMessageContaining("이름은 영어만 포함되야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"abc", "PJH"})
    public void user_name_passed_test(String name) {
        assertThatNoException().isThrownBy(() -> new User(name));
    }
}

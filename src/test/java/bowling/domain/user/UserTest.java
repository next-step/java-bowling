package bowling.domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class UserTest {

    public static User MIZ = User.from("miz");

    @DisplayName("생성 테스트")
    @Test
    void createTest() {
        assertThat(User.from("ydh")).isEqualTo(User.from("ydh"));
    }

    @DisplayName("3글자가 아니면 illegal exception")
    @ParameterizedTest
    @ValueSource(strings = {"ab", "abcd"})
    void nameLengthTest(String name) {
        assertThatIllegalArgumentException().isThrownBy(() -> User.from(name));
    }

    @DisplayName("영문자가 아니면 illegal exception")
    @ParameterizedTest
    @ValueSource(strings = {"a1b", "123", "양도혁", "ㄱㄴㄷ"})
    void nameAlphabetTest(String name) {
        assertThatIllegalArgumentException().isThrownBy(() -> User.from(name));
    }
}

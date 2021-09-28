package bowling.domain.user;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import bowling.exception.user.UsernameEnglishException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class UserTest {

    @ParameterizedTest
    @ValueSource(strings = {"영어", "123", "[]"})
    @DisplayName("유저 이름이 영어가 들어오지 않으면 Exception이 발생해야 한다.")
    void nameEnglishExceptionTest(String input) {

        // when & then
        assertThatExceptionOfType(UsernameEnglishException.class)
            .isThrownBy(() -> User.of(input))
            .withMessageMatching("유저의 이름은 영어만 들어와야 합니다.");
    }
}

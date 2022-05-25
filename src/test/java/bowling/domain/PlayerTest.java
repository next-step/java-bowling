package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bowling.exception.IllegalPlayerCriteriaException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class PlayerTest {
    @Test
    @DisplayName("플레이어 객체의 생성을 확인한다")
    void checkedPlayerObjectGenerate() {
        // given
        String name = "PJS";

        // when
        Player player = new Player(name);

        // then
        assertThat(player.toString()).isEqualTo(name);
    }

    @ParameterizedTest(name = "플레이어 이름의 길이가 3개의 영문자로 이루어지지 않는 {0} 경우, 예외처리를 한다.")
    @ValueSource(strings = {"P", "PJSPJS"})
    void exceptionPlayerNameLengthNotMatchedThree(String name) {
        // when & then
        assertThatThrownBy(() -> new Player(name))
                .isInstanceOf(IllegalPlayerCriteriaException.class);
    }

    @ParameterizedTest(name = "플레이어 이름이 {0}으로 입력된 경우, 예외처리를 한다.")
    @NullAndEmptySource
    void exceptionPlayerNameNullAndEmpty(String name) {
        // when & then
        assertThatThrownBy(() -> new Player(name))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

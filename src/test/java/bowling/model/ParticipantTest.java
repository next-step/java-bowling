package bowling.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

@DisplayName("참가자")
class ParticipantTest {

    @ParameterizedTest
    @DisplayName("3글자 영문으로 생성")
    @ValueSource(strings = {"hyh", "HYH"})
    void instance(String name) {
        assertThatNoException().isThrownBy(() -> Participant.from(name));
    }

    @ParameterizedTest
    @DisplayName("비어있거나 null 로 생성 불가")
    @NullAndEmptySource
    void instance_emptyName_thrownIllegalArgumentException(String name) {
        assertThatIllegalArgumentException().isThrownBy(() -> Participant.from(name));
    }

    @ParameterizedTest(name = "[{index}] {0} 값으로 생성 불가능")
    @DisplayName("3글자의 영문으로만 생성 가능")
    @ValueSource(strings = {"한글로", "123", "ab", "abcd"})
    void instance_invalidPattern_thrownIllegalArgumentException(String name) {
        assertThatIllegalArgumentException().isThrownBy(() -> Participant.from(name));
    }
}

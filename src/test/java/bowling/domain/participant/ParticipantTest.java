package bowling.domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ParticipantTest {

    @DisplayName("이름이 알파벳 세글자가 아니면 예외를 던진다.")
    @ParameterizedTest(name = "[{index}] name: {0}")
    @NullAndEmptySource
    @ValueSource(strings = {"감자", "a3a", "aaaa"})
    void create_illegalName_throwsException(String name) {
        assertThatThrownBy(() -> new Participant(name))
                .isInstanceOf(IllegalNameException.class);
    }

}

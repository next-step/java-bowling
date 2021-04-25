package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class ParticipantTest {

    @Test
    @DisplayName("참가자 생성")
    void create() {
        // given
        Participant lds = new Participant("LDS");

        // when then
        assertThat("LDS").isEqualTo(lds.getName());
        assertThat(new Participant("LDS")).isEqualTo(lds);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("참가자명 null 또는 공백 입력 불가")
    void nullOrEmpty(String param) {
        // given when then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Participant(param))
                .withMessageMatching("참가자명을 입력해 주세요.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"ABCD", "A", "AB"})
    @DisplayName("참가자명은 3자리여야 함")
    void nameLength(String param) {
        // given when then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Participant(param))
                .withMessageMatching("참가자명은 3 자리 이어야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"A12", "홍길동", "홍A동"})
    @DisplayName("참가자명은 알파벳으로 구성되어야 함")
    void name_alphabet(String param) {
        // given when then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Participant(param))
                .withMessageMatching("참가자명은 알파벳으로 구성되어야 합니다.");
    }
}
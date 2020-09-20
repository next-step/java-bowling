package bowling;

import bowling.domain.Participants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class ParticipantsTest {

    @DisplayName("참가자 이름이 3자가 아닐 경우 throw처리")
    @ParameterizedTest
    @ValueSource(strings = {"JAVA", "OF", "WHILE", "INTEGER", "STRING"})
    public void participantsLengthCheck(String name) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> Participants.of(name));
    }

    @DisplayName("참가자 이름이 테스트")
    @Test
    public void participantsTest() {
        Participants participants = Participants.of("SHY");
        assertThat(participants.getName()).isEqualTo("SHY");
    }


}

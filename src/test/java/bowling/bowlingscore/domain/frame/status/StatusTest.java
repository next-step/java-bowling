package bowling.bowlingscore.domain.frame.status;

import bowling.bowlingscore.domain.pitching.Pitching;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;

class StatusTest {

    @ParameterizedTest(name = "Status 반환 {0}, {1} : {2}")
    @CsvSource({"10, 0, STRIKE", "8, 2, SPARE", "7, 2, MISS"})
    void of(int firstPins, int secondPins, Status result) {
        // given
        Pitching firstPitching = Pitching.first(firstPins);
        Pitching secondPitching = firstPitching.next(secondPins);

        // when
        Status status = Status.of(firstPitching, secondPitching);

        // then
        assertThat(status).isEqualTo(result);
    }

    @Test
    @DisplayName("of NULL 반환")
    void of_null() {
        // given
        Pitching firstPitching = Pitching.first(7);

        // when
        Status status = Status.of(firstPitching, null);

        // then
        assertThat(status).isEqualTo(Status.NULL);
    }

}
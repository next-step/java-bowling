package seul.bowling.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameStatusTest {
    @ParameterizedTest
    @CsvSource(value = {"INIT:TRUE:STRIKE", "HOLD:TRUE:SPARE", "HOLD:FALSE:MISS", "STRIKE:TRUE:STRIKE"}, delimiter = ':')
    void judgmentStatus(FrameStatus value, boolean allClear, FrameStatus expected) {
        FrameStatus status = value.judgmentStatus(allClear);

        assertThat(status).isEqualTo(expected);
    }
}

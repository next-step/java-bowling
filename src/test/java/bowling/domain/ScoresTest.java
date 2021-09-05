package bowling.domain;

import bowling.domain.exception.AttemptsExceededException;
import bowling.domain.exception.IncorrectNumberOfPinsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ScoresTest {


    @Test
    @DisplayName("생성")
    void create() {
        assertDoesNotThrow(() -> new Scores(Arrays.asList(Score.FIVE, Score.FIVE)));
    }

    @Test
    @DisplayName("생성 - 핀의 개수가 10개 초과")
    void create_IncorrectNumberOfPins() {
        assertThrows(IncorrectNumberOfPinsException.class, () -> new Scores(Arrays.asList(Score.EIGHT, Score.EIGHT)));
    }

    @Test
    @DisplayName("생성 - 시도 회수가 2번 초과")
    void create_AttemptsExceeded() {
        assertThrows(AttemptsExceededException.class, () -> new Scores(Arrays.asList(Score.EIGHT, Score.EIGHT, Score.EIGHT)));
    }

}
package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PitchesTest {

    @DisplayName("처음 투구가 스트라이크이면 true 반환한다.")
    @Test
    void firstStrike() {
        Pitches pitches = new Pitches();
        pitches.add(new Pitch(10, Status.STRIKE));
        assertThat(pitches.firstStrike()).isTrue();
    }

    @DisplayName("노멀 프레임에서 2번 투구하면 max size 이므로 true 를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"3:4", "1:2"}, delimiter = ':')
    void isMaxSize_number(int first, int second) {
        Pitches pitches = new Pitches();
        pitches.add(new Pitch(first, Status.NUMBER));
        pitches.add(new Pitch(second, Status.NUMBER));
        assertThat(pitches.isMaxSize()).isTrue();
    }
}
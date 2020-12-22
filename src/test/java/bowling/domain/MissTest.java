package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class MissTest {

    @Test
    @DisplayName("미스샷인지 확인")
    void isMiss() {
        Pitch pitch1 = Pitch.from(8);
        Pitch pitch2 = Pitch.from(1);

        Miss miss = Miss.from(Arrays.asList(pitch1, pitch2));

        assertThat(miss.isMiss()).isTrue();
    }

    @Test
    @DisplayName("미스샷이 아닌지 확인")
    void isNotMiss() {
        Pitch pitch1 = Pitch.from(8);
        Pitch pitch2 = Pitch.from(2);

        Miss miss = Miss.from(Arrays.asList(pitch1, pitch2));

        assertThat(miss.isMiss()).isFalse();
    }

}
package bowling.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {

    @ParameterizedTest
    @CsvSource(value = {"0","300"})
    void 생성(int score) {
        assertThat(Score.of(score)).isEqualTo(Score.of(score));
    }

    @Test
    void 더하기() {
        Score score =Score.of(5).add(Score.of(5));
        assertThat(score).isEqualTo(Score.of(10));
    }

    @ParameterizedTest
    @CsvSource(value = {"-1","301"})
    void invalid(int score) {
        assertThatIllegalArgumentException().isThrownBy(() -> Score.of(score));
    }
}

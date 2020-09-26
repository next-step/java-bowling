package bowling.player;

import bowling.frame.Frames;
import bowling.global.exception.NotMatchingPlayerNameException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class BowlerTest {

    private Bowler bowler;
    private Frames frames;

    @BeforeEach
    void setUp() {
        frames = Frames.init();
    }

    @Test
    @DisplayName("플레이어 입력")
    void createPlayer() {
        bowler = Bowler.of("PJS");
        assertThat(bowler.getName()).isEqualTo("PJS");
    }

    @Test
    @DisplayName("플레이어 이름이 3글자가 넘어가면 Exception 발생")
    void validatePlayerNameLength() {
        assertThatExceptionOfType(NotMatchingPlayerNameException.class)
                .isThrownBy(() -> {
                    bowler = Bowler.of("ABCD");
                });
    }

    @ParameterizedTest
    @DisplayName("플레이어 이름이 null일 경우 Exception 발생")
    @NullAndEmptySource
    void validatePlayerNameIsNull(String name) {
        assertThatExceptionOfType(NotMatchingPlayerNameException.class)
                .isThrownBy(() -> {
                    bowler = Bowler.of(name);
                });
    }

}

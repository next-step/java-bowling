package bowling.player;

import bowling.frame.Frames;
import bowling.frame.NormalFrame;
import bowling.global.exception.NotMatchingPlayerNameException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class PlayerTest {

    private Player player;
    private Frames frames;

    @BeforeEach
    void setUp() {
        frames = Frames.saveScore(new LinkedList<>(), NormalFrame.first());
    }

    @Test
    @DisplayName("플레이어 입력")
    void createPlayer() {
        player = Player.of("PJS", frames);
        assertThat(player.getName()).isEqualTo("PJS");
    }

    @Test
    @DisplayName("플레이어 이름이 3글자가 넘어가면 Exception 발생")
    void validatePlayerNameLength() {
        assertThatExceptionOfType(NotMatchingPlayerNameException.class)
                .isThrownBy(() -> {
                    player = Player.of("ABCD", frames);
                });
    }

    @ParameterizedTest
    @DisplayName("플레이어 이름이 null일 경우 Exception 발생")
    @NullAndEmptySource
    void validatePlayerNameIsNull(String name) {
        assertThatExceptionOfType(NotMatchingPlayerNameException.class)
                .isThrownBy(() -> {
                    player = Player.of(name, frames);
                });
    }

}

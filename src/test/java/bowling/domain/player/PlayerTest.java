package bowling.domain.player;

import bowling.domain.frame.Pitch;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @ParameterizedTest
    @DisplayName("Player 생성 테스트")
    @MethodSource("createPlayerSource")
    void createPlayer(Name name, String expectedName) {
        assertThat(name.getName()).isEqualTo(expectedName);
    }

    static Stream<Arguments> createPlayerSource() {
        return Stream.of(
                Arguments.of(new Name("ddd"), "DDD"),
                Arguments.of(new Name("tdd"), "TDD"),
                Arguments.of(new Name("ATD"), "ATD")
        );
    }

    @Test
    @DisplayName("10 프레임을 넘었을때 10 프레임 값이 strike, spare 가 아니면 game over")
    void gameOverTest() {
        Player player = new Player(new Name("asd"));
        player.bowl(Pitch.from(10));
        player.bowl(Pitch.from(10));
        player.bowl(Pitch.from(10));
        player.bowl(Pitch.from(10));
        player.bowl(Pitch.from(10));
        player.bowl(Pitch.from(10));
        player.bowl(Pitch.from(10));
        player.bowl(Pitch.from(10));
        player.bowl(Pitch.from(10));
        //10 frame
        player.bowl(Pitch.from(1));
        player.bowl(Pitch.from(2));

        assertThat(player.gameOver()).isTrue();
    }

    @Test
    @DisplayName("10 프레임을 넘었을때 10 프레임 값이 strike game non over")
    void game_non_over_last_frame_score_then_strike() {
        Player player = new Player(new Name("asd"));
        player.bowl(Pitch.from(10));
        player.bowl(Pitch.from(10));
        player.bowl(Pitch.from(10));
        player.bowl(Pitch.from(10));
        player.bowl(Pitch.from(10));
        player.bowl(Pitch.from(10));
        player.bowl(Pitch.from(10));
        player.bowl(Pitch.from(10));
        player.bowl(Pitch.from(10));
        //10 frame
        player.bowl(Pitch.from(10));

        assertThat(player.gameOver()).isFalse();
    }
    @Test
    @DisplayName("10 프레임을 넘었을때 10 프레임 값이 spare game non over")
    void game_non_over_last_frame_score_then_spare() {
        Player player = new Player(new Name("asd"));
        player.bowl(Pitch.from(10));
        player.bowl(Pitch.from(10));
        player.bowl(Pitch.from(10));
        player.bowl(Pitch.from(10));
        player.bowl(Pitch.from(10));
        player.bowl(Pitch.from(10));
        player.bowl(Pitch.from(10));
        player.bowl(Pitch.from(10));
        player.bowl(Pitch.from(10));
        //10 frame
        player.bowl(Pitch.from(1));
        player.bowl(Pitch.from(9));

        assertThat(player.gameOver()).isFalse();
    }
}
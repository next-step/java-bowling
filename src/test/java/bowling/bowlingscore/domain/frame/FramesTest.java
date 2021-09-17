package bowling.bowlingscore.domain.frame;

import bowling.bowlingscore.domain.pitching.Pitching;
import bowling.bowlingscore.exception.CustomException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FramesTest {

    @Test
    @DisplayName("Frames 에 Pitching 을 통한 Frame 생성")
    void pitch() {
        // given
        Frames frames = new Frames();
        // when
        frames.pitch(10);
        // then
        assertThat(frames.frames().get(0))
                .isEqualTo(new Frame(Pitching.first(10)));
    }

    @Test
    @DisplayName("게임 종료 확인")
    void validate_game_over() {
        // given
        Frames frames = new Frames();
        frames.pitch(10);
        // when
        for (int i = 0; i < 9; i++) {
            frames.pitch(10);
        }

        frames.pitch(10);
        frames.pitch(10);

        // then
        assertThatThrownBy(() -> frames.pitch(10))
                .isInstanceOf(CustomException.class)
                .hasMessageContaining("이미 게임이 끝났습니다.");
    }
}
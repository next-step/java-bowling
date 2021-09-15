package bowling.bowlingdrawing.domain.frame;

import bowling.bowlingdrawing.domain.pitching.Pitching;
import bowling.bowlingdrawing.exception.CustomException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class FramesTest {

    @Test
    @DisplayName("Frames 에 Pitching 을 통한 Frame 생성")
    void pitch() {
        // given
        Frames frames = new Frames();
        Pitching pitching = Pitching.first(10);
        // when
        frames.pitch(pitching);
        // then
        assertThat(frames.frames().get(0))
                .isEqualTo(new NormalFrame(Pitching.first(10)));
    }

    @Test
    @DisplayName("Frames 에 Pitching 을 통한 Frame 생성")
    void pitch1() {
        // given
        Frames frames = new Frames();
        // when
        frames.pitch(10);
        // then
        assertThat(frames.frames().get(0))
                .isEqualTo(new NormalFrame(Pitching.first(10)));
    }

    @Test
    @DisplayName("게임 종료 확인")
    void validate_game_over() {
        // given
        Frames frames = new Frames();
        Pitching pitching = Pitching.first(10);
        List<Pitching> pitchings = new ArrayList<>();
        frames.pitch(pitching);
        pitchings.add(pitching);
        // when
        for (int i = 0; i < 9; i++) {
            pitchings.add(pitchings.get(i).next(10));
            frames.pitch(pitchings.get(pitchings.size() - 1));
        }

        pitchings.add(pitchings.get(pitchings.size() - 1).next(10));
        frames.pitch(pitchings.get(pitchings.size()-1));
        pitchings.add(pitchings.get(pitchings.size() - 1).next(10));
        frames.pitch(pitchings.get(pitchings.size()-1));

        // then
        assertThatThrownBy(() -> frames.pitch(Pitching.of(10)))
                .isInstanceOf(CustomException.class)
                .hasMessageContaining("이미 게임이 끝났습니다.");
    }

    @Test
    @DisplayName("게임 종료 확인")
    void validate_game_over1() {
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
        assertThatThrownBy(() -> frames.pitch(Pitching.of(10)))
                .isInstanceOf(CustomException.class)
                .hasMessageContaining("이미 게임이 끝났습니다.");
    }
}
package bowling.bowlingdrawing.domain;

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
                .isEqualTo(Frame.of(Pitching.first(10)));
    }

    @Test
    @DisplayName("10개 Frame 초과 검증")
    void validate_over_ten_frames() {
        // given
        Frames frames = new Frames();
        Pitching pitching = Pitching.first(10);
        frames.pitch(pitching);
        // when
        for (int i = 0; i < 9; i++) {
            frames.pitch(Pitching.of(10));
        }
        // then
        assertThatThrownBy(() -> frames.pitch(Pitching.of(10)))
                .isInstanceOf(CustomException.class)
                .hasMessageContaining("최대 Frame(10) 초과");
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
            frames.pitch(pitchings.get(pitchings.size()-1));
        }

        pitchings.add(pitchings.get(pitchings.size() - 1).next(10));
        pitchings.add(pitchings.get(pitchings.size() - 1).next(10));

        // then
        assertThatThrownBy(() -> frames.pitch(Pitching.of(10)))
                .isInstanceOf(CustomException.class)
                .hasMessageContaining("이미 게임이 끝났습니다.");
    }
}
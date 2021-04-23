package bowling.domain.frame;

import bowling.domain.engine.Frame;
import bowling.domain.state.finish.Miss;
import bowling.domain.state.finish.Spare;
import bowling.domain.state.finish.Strike;
import bowling.domain.state.run.Hit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class FramesTest {

    @DisplayName("프레임 마지막 라운드 확인 테스트")
    @Test
    void testCase2() {
        // given
        Frames frames = new Frames();
        // when
        frames.bowl(5);
        frames.bowl(5);

        frames.bowl(5);
        frames.bowl(5);

        frames.bowl(5);
        frames.bowl(5);

        frames.bowl(5);
        frames.bowl(5);

        frames.bowl(5);
        frames.bowl(5);

        frames.bowl(5);
        frames.bowl(5);

        frames.bowl(5);
        frames.bowl(5);

        frames.bowl(5);
        frames.bowl(5);

        frames.bowl(5);
        frames.bowl(5);

        frames.bowl(5);
        frames.bowl(2);

        // then
        assertThat(frames.isLastFrame()).isTrue();
    }

    @DisplayName("프레임 상태 확인 테스트")
    @Test
    void testCase4() {
        // given
        Frames frames = new Frames();
        // when
        frames.bowl(10); // Strike

        frames.bowl(8); // Spare
        frames.bowl(2);

        frames.bowl(2); // Miss
        frames.bowl(2);

        frames.bowl(2); // Hit

        List<Frame> values = frames.values();
        // then
        assertAll(
                () -> assertThat(values.get(0).state()).isInstanceOf(Strike.class),
                () -> assertThat(values.get(1).state()).isInstanceOf(Spare.class),
                () -> assertThat(values.get(2).state()).isInstanceOf(Miss.class),
                () -> assertThat(values.get(3).state()).isInstanceOf(Hit.class)
        );

    }
}

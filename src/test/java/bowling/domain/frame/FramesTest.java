package bowling.domain.frame;

import bowling.domain.status.Status;
import bowling.domain.status.finished.Spare;
import bowling.domain.status.running.Pending;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class FramesTest {


    @Test
    @DisplayName("생성 테스트")
    void init() {
        assertThatCode(() -> Frames.init()).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("투구 테스트")
    void bowl() {
        Frames frames = Frames.init();
        Status status = frames.bowl(2);
        assertThat(status).isInstanceOf(Pending.class);

        status = frames.bowl(8);
        assertThat(status).isInstanceOf(Spare.class);

    }

    @Test
    @DisplayName("현재 프레임이 플레이 불가면 다음 프레임을 생성한다")
    void next() {
        Frames frames = Frames.init();

        frames.bowl(10);
        assertThat(frames.getIndex()).isEqualTo(0);

        frames.next();
        assertThat(frames.getIndex()).isEqualTo(1);

    }
}

package bowling.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import bowling.domain.Pin;
import bowling.domain.Round;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FramesTest {

    @Test
    @DisplayName("프레임의 최대 크기는 10라운드 이다.")
    void validTest() {
        List<Frame> frames = IntStream.range(0, 11)
            .boxed()
            .map(index -> new GeneralFrame())
            .collect(Collectors.toList());

        assertThatIllegalArgumentException().isThrownBy(() -> new Frames(frames));
    }

    @Test
    @DisplayName("게임이 정상적으로 진행되어야 한다.")
    void bowlTest() {
        Frames frames = Frames.create();

        assertThatCode(() -> {
            for (int round = 0; round < 10; round++) {
                Frame frame = frames.bowl(Round.of(round), Pin.of(10));

                while (frame.isOpened()) {
                    frame = frames.bowl(Round.of(round), Pin.of(10));
                }
            }
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("create() 호출시 9회의 GeneralFrame과, 1회의 FinalFrame이 생성된다.")
    void framesCreateTest() {
        Frames frames = Frames.create();

        long generalFrameCount = frames.getFrames().stream()
            .filter(frame -> frame instanceof GeneralFrame)
            .count();

        assertThat(generalFrameCount).isEqualTo(9L);

        long finalFrameCount = frames.getFrames().stream()
            .filter(frame -> frame instanceof FinalFrame)
            .count();

        assertThat(finalFrameCount).isEqualTo(1L);
    }

    @Test
    @DisplayName("frame 내 bowl을 시도하면, 내부 Frame 이 정상적으로 업데이트 된다.")
    void framesBowlModifyListTest() {
        Frames frames = Frames.create();
        Frame bowl = frames.bowl(Round.of(0), Pin.of(10));

        assertThat(bowl).isEqualTo(frames.getFrame(Round.of(0)));
    }
}
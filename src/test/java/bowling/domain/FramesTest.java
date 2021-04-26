package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.BDDAssertions.then;

class FramesTest {
    Frames frames;

    @BeforeEach
    void setUp() {
        frames = new Frames();

        String[] splitPinCounts = "0 0 1 0 0 1 1 9 9 1 10 10 10 10 1 9 10".split(" ");
        while (!frames.isAllFinished()) {
            Arrays.stream(splitPinCounts)
                    .forEach(pinCount -> frames.throwBowl(pinCount));
        }
    }

    @Test
    @DisplayName("프레임 인덱스를 인자로 받아 해당 프레임을 업데이트한다.")
    public void update() throws Exception {
        //given
        Frames frames = new Frames();
        NormalPinCounts normalPinCounts = new NormalPinCounts();
        normalPinCounts.knockDown(5);
        frames.update(new NormalFrame(1, normalPinCounts));

        //when
        Frame frame = frames.get(1);
        int index = frame.index();

        then(frame.pinCounts().pinCounts().get(0)).isEqualTo(new PinCount(5));
        then(index).isEqualTo(1);
    }

    @Test()
    @DisplayName("각 프레임 입력에 따른 결과 표식을 반환한다..")
    public void marks() throws Exception {
        String result = frames.marks()
                .stream()
                .map(FrameMark::marks)
                .map(marks -> String.join(" | ", marks))
                .collect(Collectors.joining(" | "));

        then(result).isEqualTo("- | - | 1 | - | - | 1 | 1 | / | 9 | / | X | X | X | X | 1 | / | X");
    }

    @Test()
    @DisplayName("각 프레임의 입력에 따른 점수를 반환한다.")
    public void scores() throws Exception {
        List<Integer> result = frames.scores();

        then(result).isEqualTo(Arrays.asList(0, 1, 1, 19, 20, 30, 30, 21, 20, 20));
    }
}
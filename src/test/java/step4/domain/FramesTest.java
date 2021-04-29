package step4.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
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

    @Test()
    @DisplayName("각 프레임 입력에 따른 결과 표식을 반환한다..")
    public void marks() throws Exception {
        String result = frames.marks()
                .stream()
                .map(symbols -> String.join(" | ", symbols))
                .collect(Collectors.joining(" | "));

        then(result).isEqualTo("-|- | 1|- | -|1 | 1|/ | 9|/ | X | X | X | X | 1|/|X");
    }
}
package bowling.model.player;

import bowling.model.frame.FrameResult;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.*;

class PlayerResultTest {

    @Test
    void of_두개_이상의_결과() {

        List<FrameResult> frameResults = Arrays.asList(
                FrameResult.from(new LinkedList<>()),
                FrameResult.from(new LinkedList<>()),
                FrameResult.from(new LinkedList<>()));

        assertThatIllegalArgumentException()
                .isThrownBy(() -> PlayerResult.of("TST", frameResults))
                .withMessage("결과는 2개의 타입만 가능합니다.");
    }

    @Test
    void of_결과가_한_개() {

        List<FrameResult> frameResults = Arrays.asList(FrameResult.from(new LinkedList<>()));

        assertThatIllegalArgumentException()
                .isThrownBy(() -> PlayerResult.of("TST", frameResults))
                .withMessage("결과는 2개의 타입만 가능합니다.");
    }
}
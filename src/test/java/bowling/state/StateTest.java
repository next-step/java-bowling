package bowling.state;

import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created By mand2 on 2020-12-19.
 */
public class StateTest {

    @Test
    @DisplayName("상태 전략 기본")
    void create() {
        Frame frame = NormalFrame.of(1);

        BowlingState bowlingState = Open.of(frame);
    }

    @Test
    @DisplayName("open 만 한 상태?")
    void test() {
        Frame frame = NormalFrame.of(1);
//        assertThat(frame.getScore().isOpen()).isTrue();
        System.out.println(frame.sum());
        frame.getScoreList().stream()
                .forEach(System.out::print);

        List<Integer> integers = frame.getScoreList();
        if (integers.isEmpty()) {
            System.out.println("empty");
        }
    }
}

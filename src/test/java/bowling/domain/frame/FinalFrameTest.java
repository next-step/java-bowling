package bowling.domain.frame;

import bowling.domain.state.FirstBowl;
import bowling.domain.state.Ready;
import bowling.domain.state.Spare;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class FinalFrameTest {

    private Frame ready;

    @BeforeEach
    void setUp(){
        ready = FinalFrame.readyFrame();
    }

    @DisplayName("정상 생성 테스트")
    @Test
    void createTest() {
        assertThat(FinalFrame.readyFrame()).isEqualTo(FinalFrame.of(Arrays.asList(Ready.getInstance())));
    }

    @DisplayName("2번째 굴린 뒤 State가 Spare면 한번 더 칠 수 있다")
    @Test
    void bonusTest() {
        FinalFrame spare = FinalFrame.of(Arrays.asList(new FirstBowl(Pin.from(5)), new Spare(Pin.from(5), Pin.from(5))));
        FinalFrame spareExpect = FinalFrame.of(Arrays.asList(
                        new FirstBowl(Pin.from(5)),
                        new Spare(Pin.from(5), Pin.from(5)),
                        new FirstBowl(Pin.from(5))
        ));
        assertThat(spare.bowl(Pin.from(5)))
                .isEqualTo(spareExpect);

//        FinalFrame strike =


    }

}

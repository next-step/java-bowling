package bowling.domain.state;

import bowling.domain.frame.Pin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class FinalFrameStateTest {

    private FinalFrameState ready;

    @BeforeEach
    void setUp() {
        ready = FinalFrameState.readyState();
    }

    @DisplayName("정상 생성 테스트")
    @Test
    void createTest() {
        assertThat(FinalFrameState.readyState()).isEqualTo(FinalFrameState.of(Arrays.asList(Ready.getInstance()), 3));
    }

    @DisplayName("공을 굴릴 때 마다 left가 하나 줄어든다.")
    @Test
    void bowlTest() {
        FinalFrameState ready = FinalFrameState.readyState();
        State firstBowl = ready.bowl(Pin.from(5));
        assertThat(firstBowl)
                .isEqualTo(FinalFrameState.of(Arrays.asList(Ready.getInstance(), new FirstBowl(Pin.from(5))), 2));
    }

    @DisplayName("Miss가 발생하면 left = 0 으로 변한다")
    @Test
    void missTest() {
        FinalFrameState ready = FinalFrameState.readyState();
        State miss = ready.bowl(Pin.from(5)).bowl(Pin.from(3));
        assertThat(miss)
                .isEqualTo(FinalFrameState.of(
                        Arrays.asList(
                                Ready.getInstance(),
                                new FirstBowl(Pin.from(5)),
                                new Miss(Pin.from(5), Pin.from(3))
                        ), 0));
    }

    @DisplayName("ready 상태에서는 isFinished() 가 false이다.")
    @Test
    void readyIsFinishedTest() {
        FinalFrameState ready = FinalFrameState.readyState();
        assertThat(ready.isFinished()).isFalse();
    }


    @DisplayName("FirstBowl 상태에서는 isFinished()가 false이다.")
    @Test
    void firstIsFinishedTest() {
        FinalFrameState first = FinalFrameState.of(
                Arrays.asList(
                        Ready.getInstance(),
                        new FirstBowl(Pin.from(5))
                ), 2);
        assertThat(first.isFinished()).isFalse();

        assertThat(ready.bowl(Pin.from(5)).isFinished()).isFalse();
    }

    @DisplayName("첫 Strike 상태에서는 isFinished()가 false이다.")
    @Test
    void oneStrikeIsFinishedTest() {
        FinalFrameState oneStrike = FinalFrameState.of(
                Arrays.asList(
                        Ready.getInstance(),
                        new Strike()
                ), 2);
        assertThat(oneStrike.isFinished()).isFalse();
        assertThat(ready.bowl(Pin.from(10)).isFinished()).isFalse();
    }

    @DisplayName("첫 spare 상태에서는 isFinished()가 false이다.")
    @Test
    void spareIsFinishedTest() {
        FinalFrameState spare = FinalFrameState.of(
                Arrays.asList(
                        Ready.getInstance(),
                        new FirstBowl(Pin.from(5)),
                        new Spare(Pin.from(5), Pin.from(5))
                ), 1);
        assertThat(spare.isFinished()).isFalse();

        assertThat(ready.bowl(Pin.from(5)).bowl(Pin.from(5)).isFinished()).isFalse();
    }

    @DisplayName("두번 Strike 상태에서는 isFinished()가 false이다.")
    @Test
    void twoStrikeIsFinishedTest() {
        FinalFrameState twoStrike = FinalFrameState.of(
                Arrays.asList(
                        Ready.getInstance(),
                        new Strike(),
                        Ready.getInstance(),
                        new Strike()
                ), 1);
        assertThat(twoStrike.isFinished()).isFalse();
        assertThat(ready.bowl(Pin.from(10)).bowl(Pin.from(10)).isFinished()).isFalse();
    }

    @DisplayName("miss 상태에서는 isFinished()가 true이다.")
    @Test
    void missIsFinishedTest() {
        FinalFrameState miss = FinalFrameState.of(
                Arrays.asList(
                        Ready.getInstance(),
                        new FirstBowl(Pin.from(5)),
                        new Miss(Pin.from(5), Pin.from(3))
                ), 0);
        assertThat(miss.isFinished()).isTrue();
    }

    @DisplayName("3번 친 상태에서는 isFinished()가 true이다.")
    @Test
    void threeTimesIsFinishedTest() {
        FinalFrameState spareAndBowl = FinalFrameState.of(
                Arrays.asList(
                        Ready.getInstance(),
                        new FirstBowl(Pin.from(5)),
                        new Spare(Pin.from(5), Pin.from(5)),
                        Ready.getInstance(),
                        new FirstBowl(Pin.from(5))
                ), 0);
        assertThat(spareAndBowl.isFinished()).isTrue();

        FinalFrameState spareAndStrike = FinalFrameState.of(
                Arrays.asList(
                        Ready.getInstance(),
                        new FirstBowl(Pin.from(5)),
                        new Spare(Pin.from(5), Pin.from(5)),
                        Ready.getInstance(),
                        new Strike()
                ), 0);
        assertThat(spareAndStrike.isFinished()).isTrue();

        FinalFrameState twoStrikeAndBowl = FinalFrameState.of(
                Arrays.asList(
                        Ready.getInstance(),
                        new Strike(),
                        Ready.getInstance(),
                        new Strike(),
                        Ready.getInstance(),
                        new FirstBowl(Pin.from(5))
                ), 0);
        assertThat(twoStrikeAndBowl.isFinished()).isTrue();

        FinalFrameState threeStrike = FinalFrameState.of(
                Arrays.asList(
                        Ready.getInstance(),
                        new Strike(),
                        Ready.getInstance(),
                        new Strike(),
                        Ready.getInstance(),
                        new Strike()
                ), 0);
        assertThat(threeStrike.isFinished()).isTrue();
    }


}
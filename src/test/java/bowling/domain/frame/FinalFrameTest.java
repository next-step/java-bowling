package bowling.domain.frame;

import bowling.domain.state.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class FinalFrameTest {

    private Frame ready;
    private Frame spareAndBowl;
    private Frame spareAndStrike;
    private Frame twoStrikeAndBowl;
    private Frame threeStrike;
    private Frame miss;
    private Frame oneStrikeAndMiss;
    private Frame oneStrikeAndSpare;

    @BeforeEach
    void setUp() {
        ready = FinalFrame.readyFrame();
        spareAndBowl = FinalFrame.of(
                Arrays.asList(
                        Ready.getInstance(),
                        new FirstBowl(Pin.from(5)),
                        new Spare(Pin.from(5), Pin.from(5)),
                        Ready.getInstance(),
                        new FirstBowl(Pin.from(5))
                ), 0);

        spareAndStrike = FinalFrame.of(
                Arrays.asList(
                        Ready.getInstance(),
                        new FirstBowl(Pin.from(5)),
                        new Spare(Pin.from(5), Pin.from(5)),
                        Ready.getInstance(),
                        new Strike()
                ), 0);

        twoStrikeAndBowl = FinalFrame.of(
                Arrays.asList(
                        Ready.getInstance(),
                        new Strike(),
                        Ready.getInstance(),
                        new Strike(),
                        Ready.getInstance(),
                        new FirstBowl(Pin.from(5))
                ), 0);

        threeStrike = FinalFrame.of(
                Arrays.asList(
                        Ready.getInstance(),
                        new Strike(),
                        Ready.getInstance(),
                        new Strike(),
                        Ready.getInstance(),
                        new Strike()
                ), 0);

        miss = FinalFrame.of(
                Arrays.asList(
                        Ready.getInstance(),
                        new FirstBowl(Pin.from(5)),
                        new Miss(Pin.from(5), Pin.from(3)),
                        Ready.getInstance()
                ), 0);
        oneStrikeAndMiss = FinalFrame.of(
                Arrays.asList(
                        Ready.getInstance(),
                        new Strike(),
                        Ready.getInstance(),
                        new FirstBowl(Pin.from(5)),
                        new Miss(Pin.from(5), Pin.from(3))
                ), 0);

        oneStrikeAndSpare = FinalFrame.of(
                Arrays.asList(
                        Ready.getInstance(),
                        new Strike(),
                        Ready.getInstance(),
                        new FirstBowl(Pin.from(5)),
                        new Spare(Pin.from(5), Pin.from(5))
                ), 0);
    }

    @DisplayName("정상 생성 테스트")
    @Test
    void createTest() {
        assertThat(ready).isEqualTo(FinalFrame.of(
                Arrays.asList(Ready.getInstance()), 3
        ));
    }

    @DisplayName("3번을 굴릴 수 있다.")
    @Test
    void bonusTest() {
        assertThat(ready.bowl(Pin.from(5)).bowl(Pin.from(5)).bowl(Pin.from(5)).isFinished()).isTrue();
        assertThat(ready.bowl(Pin.from(10)).bowl(Pin.from(5)).bowl(Pin.from(5)).isFinished()).isTrue();
        assertThat(ready.bowl(Pin.from(10)).bowl(Pin.from(5)).bowl(Pin.from(4)).isFinished()).isTrue();
        assertThat(ready.bowl(Pin.from(10)).bowl(Pin.from(10)).bowl(Pin.from(4)).isFinished()).isTrue();
        assertThat(ready.bowl(Pin.from(10)).bowl(Pin.from(10)).bowl(Pin.from(10)).isFinished()).isTrue();
    }


    @DisplayName("finish 상태에서 bowl을 호출 할 경우 illegal Exception")
    @Test
    void bowlFailTest() {
        assertThatIllegalArgumentException().isThrownBy(() ->
                ready.bowl(Pin.from(10)).bowl(Pin.from(10)).bowl(Pin.from(10)).bowl(Pin.from(5))
        );

        assertThatIllegalArgumentException().isThrownBy(() ->
                ready.bowl(Pin.from(5)).bowl(Pin.from(5)).bowl(Pin.from(5)).bowl(Pin.from(5))
        );

        assertThatIllegalArgumentException().isThrownBy(() ->
                ready.bowl(Pin.from(5)).bowl(Pin.from(4)).bowl(Pin.from(5))
        );
    }

    @DisplayName("FinalFrame이 끝난 경우 isGameEnd는 True를 반환한다.")
    @Test
    void isGameEndTrueTest() {
        assertThat(ready.bowl(Pin.from(5)).bowl(Pin.from(5)).bowl(Pin.from(5)).isGameEnd()).isTrue();
        assertThat(ready.bowl(Pin.from(10)).bowl(Pin.from(5)).bowl(Pin.from(5)).isGameEnd()).isTrue();
        assertThat(ready.bowl(Pin.from(10)).bowl(Pin.from(5)).bowl(Pin.from(4)).isGameEnd()).isTrue();
        assertThat(ready.bowl(Pin.from(10)).bowl(Pin.from(10)).bowl(Pin.from(4)).isGameEnd()).isTrue();
        assertThat(ready.bowl(Pin.from(10)).bowl(Pin.from(10)).bowl(Pin.from(10)).isGameEnd()).isTrue();
    }

    @DisplayName("FinalFrame이 끝나지 않은 경우 isGameEnd는 False를 반환한다.")
    @Test
    void isGameEndFalseTest() {
        assertThat(ready.bowl(Pin.from(5)).bowl(Pin.from(5)).isGameEnd()).isFalse();
        assertThat(ready.bowl(Pin.from(10)).bowl(Pin.from(5)).isGameEnd()).isFalse();
        assertThat(ready.bowl(Pin.from(10)).bowl(Pin.from(5)).isGameEnd()).isFalse();
        assertThat(ready.bowl(Pin.from(10)).bowl(Pin.from(10)).isGameEnd()).isFalse();
        assertThat(ready.bowl(Pin.from(10)).bowl(Pin.from(10)).isGameEnd()).isFalse();
    }

    @DisplayName("isEqualsRound() round가 같으면 true를 반환한다.")
    @Test
    void isEqualsRoundTest() {
        assertThat(ready.isEqualsRound(NormalFrame.readyFrame(Round.LAST))).isTrue();
        assertThat(ready.isEqualsRound(NormalFrame.readyFrame(Round.from(2)))).isFalse();
    }

    @DisplayName("round() frame의 round를 반환한다.")
    @Test
    void roundTest() {
        assertThat(ready.round()).isEqualTo(Round.LAST);
    }

    @DisplayName("공을 굴릴 때 마다 left가 하나 줄어든다.")
    @Test
    void bowlTest() {
        Frame firstBowl = ready.bowl(Pin.from(5));
        assertThat(firstBowl)
                .isEqualTo(FinalFrame.of(Arrays.asList(Ready.getInstance(), new FirstBowl(Pin.from(5))), 2));
    }

    @DisplayName("Miss가 발생하면 left = 0 으로 변한다")
    @Test
    void missTest() {
        assertThat(miss)
                .isEqualTo(FinalFrame.of(
                        Arrays.asList(
                                Ready.getInstance(),
                                new FirstBowl(Pin.from(5)),
                                new Miss(Pin.from(5), Pin.from(3)),
                                Ready.getInstance()
                        ), 0));
    }

    @DisplayName("ready 상태에서는 isFinished() 가 false이다.")
    @Test
    void readyIsFinishedTest() {
        assertThat(ready.isFinished()).isFalse();
    }

    @DisplayName("FirstBowl 상태에서는 isFinished()가 false이다.")
    @Test
    void firstIsFinishedTest() {
        FinalFrame first = FinalFrame.of(
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
        FinalFrame oneStrike = FinalFrame.of(
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
        FinalFrame spare = FinalFrame.of(
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
        FinalFrame twoStrike = FinalFrame.of(
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
        assertThat(miss.isFinished()).isTrue();
    }

    @DisplayName("3번 친 상태에서는 isFinished()가 true이다.")
    @Test
    void threeTimesIsFinishedTest() {
        assertThat(spareAndBowl.isFinished()).isTrue();
        assertThat(spareAndStrike.isFinished()).isTrue();
        assertThat(twoStrikeAndBowl.isFinished()).isTrue();
        assertThat(threeStrike.isFinished()).isTrue();
    }

    @DisplayName("종료된 Frame viewString() 정상 생성 테스트")
    @Test
    void finishFrameViewStringTest() {
        assertThat(spareAndBowl.viewString()).isEqualTo("5|/|5");
        assertThat(spareAndStrike.viewString()).isEqualTo("5|/|X");
        assertThat(twoStrikeAndBowl.viewString()).isEqualTo("X|X|5");
        assertThat(threeStrike.viewString()).isEqualTo("X|X|X");
        assertThat(miss.viewString()).isEqualTo("5|3");
        assertThat(oneStrikeAndMiss.viewString()).isEqualTo("X|5|3");
        assertThat(oneStrikeAndSpare.viewString()).isEqualTo("X|5|/");
    }
}

package bowling.domain.frame;

import bowling.domain.result.FrameResult;
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
    private Frame oneStrike;
    private Frame twoStrike;
    private Frame first;
    private Frame spare;

    @BeforeEach
    void setUp() {
        ready = FinalFrame.readyFrame();
        spareAndBowl = FinalFrame.of(
                Arrays.asList(
                        new Spare(Pin.from(5), Pin.from(5)),
                        new FirstBowl(Pin.from(5))
                ), 0);

        spareAndStrike = FinalFrame.of(
                Arrays.asList(
                        new Spare(Pin.from(5), Pin.from(5)),
                        new Strike()
                ), 0);

        twoStrikeAndBowl = FinalFrame.of(
                Arrays.asList(
                        new Strike(),
                        new Strike(),
                        new FirstBowl(Pin.from(5))
                ), 0);

        threeStrike = FinalFrame.of(
                Arrays.asList(
                        new Strike(),
                        new Strike(),
                        new Strike()
                ), 0);

        miss = FinalFrame.of(
                Arrays.asList(
                        new Miss(Pin.from(5), Pin.from(3))
                ), 0);
        oneStrikeAndMiss = FinalFrame.of(
                Arrays.asList(
                        new Strike(),
                        new Miss(Pin.from(5), Pin.from(3))
                ), 0);

        oneStrikeAndSpare = FinalFrame.of(
                Arrays.asList(
                        new Strike(),
                        new Spare(Pin.from(5), Pin.from(5))
                ), 0);

        oneStrike = FinalFrame.of(
                Arrays.asList(
                        new Strike()
                ), 2);

        first = FinalFrame.of(
                Arrays.asList(
                        new FirstBowl(Pin.from(5))
                ), 2);

        spare = FinalFrame.of(
                Arrays.asList(
                        new Spare(Pin.from(5), Pin.from(5))
                ), 1);

        twoStrike = FinalFrame.of(
                Arrays.asList(
                        new Strike(),
                        new Strike()
                ), 1);
    }

    @DisplayName("정상 생성 테스트")
    @Test
    void createTest() {
        assertThat(ready).isEqualTo(FinalFrame.of(
                Arrays.asList(Ready.getInstance()), 3
        ));
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
        assertThat(spareAndBowl.isGameEnd()).isTrue();
        assertThat(oneStrikeAndSpare.isGameEnd()).isTrue();
        assertThat(oneStrikeAndMiss.isGameEnd()).isTrue();
        assertThat(twoStrikeAndBowl.isGameEnd()).isTrue();
        assertThat(threeStrike.isGameEnd()).isTrue();
    }

    @DisplayName("FinalFrame이 끝나지 않은 경우 isGameEnd는 False를 반환한다.")
    @Test
    void isGameEndFalseTest() {
        assertThat(spare.isGameEnd()).isFalse();
        assertThat(twoStrike.isGameEnd()).isFalse();
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
        assertThat(ready.bowl(Pin.from(5)))
                .isEqualTo(FinalFrame.of(Arrays.asList(new FirstBowl(Pin.from(5))), 2));
    }

    @DisplayName("Miss가 발생하면 left = 0 으로 변한다")
    @Test
    void missTest() {
        ready.bowl(Pin.from(5)).bowl(Pin.from(3));
        assertThat(ready)
                .isEqualTo(FinalFrame.of(
                        Arrays.asList(
                                new Miss(Pin.from(5), Pin.from(3))
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

        assertThat(first.isFinished()).isFalse();

        assertThat(ready.bowl(Pin.from(5)).isFinished()).isFalse();
    }

    @DisplayName("첫 Strike 상태에서는 isFinished()가 false이다.")
    @Test
    void oneStrikeIsFinishedTest() {
        assertThat(oneStrike.isFinished()).isFalse();
        assertThat(ready.bowl(Pin.from(10)).isFinished()).isFalse();
    }

    @DisplayName("첫 spare 상태에서는 isFinished()가 false이다.")
    @Test
    void spareIsFinishedTest() {
        assertThat(spare.isFinished()).isFalse();

        assertThat(ready.bowl(Pin.from(5)).bowl(Pin.from(5)).isFinished()).isFalse();
    }

    @DisplayName("두번 Strike 상태에서는 isFinished()가 false이다.")
    @Test
    void twoStrikeIsFinishedTest() {
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

    @DisplayName("createResult() 정상 생성 테스트")
    @Test
    void finishFrameViewStringTest() {
        assertThat(spareAndBowl.createResult()).isEqualTo(
                FrameResult.ofFinalFrame(Arrays.asList(
                        new Spare(Pin.from(5), Pin.from(5)), new FirstBowl(Pin.from(5))
                        ), Score.of(15, 0)
                )
        );
        assertThat(spareAndStrike.createResult()).isEqualTo(
                FrameResult.ofFinalFrame(Arrays.asList(
                        new Spare(Pin.from(5), Pin.from(5)), new Strike()
                ), Score.of(20, 0))
        );
        assertThat(twoStrikeAndBowl.createResult()).isEqualTo(
                FrameResult.ofFinalFrame(Arrays.asList(
                        new Strike(), new Strike(), new FirstBowl(Pin.from(5))
                ), Score.of(25, 0)));
        assertThat(threeStrike.createResult()).isEqualTo(
                FrameResult.ofFinalFrame(Arrays.asList(
                        new Strike(), new Strike(), new Strike()
                ), Score.of(30, 0)));
        assertThat(miss.createResult()).isEqualTo(
                FrameResult.ofFinalFrame(Arrays.asList(
                        new Miss(Pin.from(5), Pin.from(3))
                ), Score.of(8, 0)));
        assertThat(oneStrikeAndMiss.createResult()).isEqualTo(
                FrameResult.ofFinalFrame(Arrays.asList(
                        new Strike(), new Miss(Pin.from(5), Pin.from(3))
                ), Score.of(18, 0)));
        assertThat(oneStrikeAndSpare.createResult()).isEqualTo(
                FrameResult.ofFinalFrame(Arrays.asList(
                        new Strike(), new Spare(Pin.from(5), Pin.from(5))
                ), Score.of(20, 0)));
        assertThat(oneStrike.createResult()).isEqualTo(
                FrameResult.ofFinalFrame(Arrays.asList(
                        new Strike()
                ), Score.of(-1, 0)));
        assertThat(twoStrike.createResult()).isEqualTo(
                FrameResult.ofFinalFrame(Arrays.asList(
                        new Strike(), new Strike()
                ), Score.of(-1, 0)));
        assertThat(spare.createResult()).isEqualTo(
                FrameResult.ofFinalFrame(Arrays.asList(
                        new Spare(Pin.from(5), Pin.from(5))
                ), Score.of(-1, 0)));
        assertThat(first.createResult()).isEqualTo(
                FrameResult.ofFinalFrame(Arrays.asList(
                        new FirstBowl(Pin.from(5))
                ), Score.of(-1, 0)));
    }

    @DisplayName("score() 정상 생성 테스트")
    @Test
    void scoreTest() {
        assertThat(spareAndBowl.score()).isEqualTo(Score.of(15, 0));
        assertThat(spareAndStrike.score()).isEqualTo(Score.of(20, 0));
        assertThat(twoStrikeAndBowl.score()).isEqualTo(Score.of(25, 0));
        assertThat(threeStrike.score()).isEqualTo(Score.of(30, 0));
        assertThat(miss.score()).isEqualTo(Score.of(8, 0));
        assertThat(oneStrikeAndMiss.score()).isEqualTo(Score.of(18, 0));
        assertThat(oneStrikeAndSpare.score()).isEqualTo(Score.of(20, 0));
        assertThat(oneStrike.score()).isEqualTo(Score.noScore());
        assertThat(twoStrike.score()).isEqualTo(Score.noScore());
        assertThat(spare.score()).isEqualTo(Score.noScore());
        assertThat(first.score()).isEqualTo(Score.noScore());
    }

    @DisplayName("calculateAdditionalScore()은 이전 프레임이 strike이면 두번 점수를 더해서 반환한다.")
    @Test
    void strikeAddTest() {
        NormalFrame frame = NormalFrame.readyFrame(Round.from(9));

        Frame finalFrame = frame.bowl(Pin.TEN);
        finalFrame.bowl(Pin.from(5));
        assertThat(frame.score()).isEqualTo(Score.noScore());
        finalFrame.bowl(Pin.from(5));
        assertThat(frame.score()).isEqualTo(Score.of(20, 0));
    }

    @DisplayName("calculateAdditionalScore()은 이전 프레임이 spare이면 한번 점수를 더해서 반환한다.")
    @Test
    void spareCalculateAdditionalScoreTest() {
        NormalFrame frame = NormalFrame.readyFrame(Round.from(9));

        Frame finalFrame = frame.bowl(Pin.from(5)).bowl(Pin.from(5));
        finalFrame.bowl(Pin.from(5));
        finalFrame.bowl(Pin.from(5));
        finalFrame.bowl(Pin.from(5));
        assertThat(frame.score()).isEqualTo(Score.of(15, 0));
    }

    @DisplayName("calculateAdditionalScore()은 이전 프레임이 strike이면 한번 점수를 더해서 반환한다.")
    @Test
    void strikeCalculateAdditionalScoreTest() {
        NormalFrame frame = NormalFrame.readyFrame(Round.from(9));

        Frame finalFrame = frame.bowl(Pin.from(10));
        finalFrame.bowl(Pin.from(5));
        finalFrame.bowl(Pin.from(5));
        finalFrame.bowl(Pin.from(5));
        assertThat(frame.score()).isEqualTo(Score.of(20, 0));
    }


    @DisplayName("next() 마지막 프레임은 null을 반환한다.")
    @Test
    void nextNullTest() {
        assertThat(ready.next()).isNull();
    }

    @DisplayName("hasNext() 마지막 프레임은 false를 반환한다.")
    @Test
    void hasNextTest() {
        assertThat(ready.hasNext()).isFalse();
    }
}

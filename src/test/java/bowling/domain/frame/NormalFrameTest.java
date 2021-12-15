package bowling.domain.frame;

import bowling.domain.state.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class NormalFrameTest {

    private NormalFrame readyFrame;

    @BeforeEach
    void setUp() {
        readyFrame = NormalFrame.readyFrame(Round.FIRST);
    }

    @DisplayName("정상 생성 테스트")
    @Test
    void createTest() {
        assertThat(NormalFrame.readyFrame(Round.from(1))).isEqualTo(NormalFrame.of(Round.from(1), Ready.getInstance()));
    }

    @DisplayName("bowl State가 종료될 경우 일 경우 다음 round의 ready상태 프레임을 반환한다.")
    @Test
    void bowlToNextFrameTest() {
        //Strike
        assertThat(NormalFrame.readyFrame(Round.FIRST).bowl(Pin.from(10))).isEqualTo(NormalFrame.of(Round.from(2), Ready.getInstance()));

        //Spare
        assertThat(NormalFrame.readyFrame(Round.FIRST).bowl(Pin.from(8)).bowl(Pin.from(2))).isEqualTo(NormalFrame.of(Round.from(2), Ready.getInstance()));

        //miss
        assertThat(NormalFrame.readyFrame(Round.FIRST).bowl(Pin.from(8)).bowl(Pin.from(1))).isEqualTo(NormalFrame.of(Round.from(2), Ready.getInstance()));
    }

    @DisplayName("bowl 후 strike가 아닐 경우 같은 round의 firstBowl 상태 프레임을 반환한다.")
    @Test
    void bowlToTest() {
        assertThat(readyFrame.bowl(Pin.from(5))).isEqualTo(NormalFrame.of(Round.from(1), new FirstBowl(Pin.from(5))));
    }

    @DisplayName("finish 상태에서 bowl을 호출 할 경우 illegal Exception")
    @Test
    void bowlFailTest() {
        assertThatIllegalArgumentException().isThrownBy(() ->
                NormalFrame.of(Round.from(1), new Miss(Pin.from(3), Pin.from(3))).bowl(Pin.from(2))
        );

        assertThatIllegalArgumentException().isThrownBy(() ->
                NormalFrame.of(Round.from(1), new Spare(Pin.from(3), Pin.from(7))).bowl(Pin.from(2))
        );

        assertThatIllegalArgumentException().isThrownBy(() ->
                NormalFrame.of(Round.from(1), new Strike()).bowl(Pin.from(2))
        );
    }

    @DisplayName("nomalFrame인 경우는 isGameEnd는 false를 반환한다.")
    @Test
    void isEndGameTest() {
        assertThat(readyFrame.isGameEnd()).isFalse();
    }

    @DisplayName("isEqualsRound() round가 같으면 true를 반환한다.")
    @Test
    void isEqualsRoundTest() {
        assertThat(readyFrame.isEqualsRound(NormalFrame.readyFrame(Round.FIRST))).isTrue();
        assertThat(readyFrame.isEqualsRound(NormalFrame.readyFrame(Round.from(2)))).isFalse();
    }

    @DisplayName("round() frame의 round를 반환한다.")
    @Test
    void roundTest() {
        assertThat(readyFrame.round()).isEqualTo(Round.FIRST);
    }

    @DisplayName("next() 다음 프레임이 있으면 다음 프레임을 반환하고 없으면 null을 반환한다.")
    @Test
    void nextNullTest() {
        assertThat(readyFrame.next()).isNull();
        readyFrame.bowl(Pin.from(10));
        assertThat(readyFrame.next()).isEqualTo(NormalFrame.of(Round.from(2), Ready.getInstance()));
    }

    @DisplayName("hasNext() 다음 프레임이 있으면 true 없으면 false를 반환한다.")
    @Test
    void hasNextTest() {
        assertThat(readyFrame.hasNext()).isFalse();
        readyFrame.bowl(Pin.from(10));
        assertThat(readyFrame.hasNext()).isTrue();
    }

    @DisplayName("Frame의 상태가 miss 일 경우 정상적으로 score를 반환한다.")
    @Test
    void missScoreTest() {
        readyFrame.bowl(Pin.from(5)).bowl(Pin.from(3));
        assertThat(readyFrame.score()).isEqualTo(Score.of(8, 0));
    }

    @DisplayName("score 를 반환할 수 없을 경우 score(-1,0)을 반환한다.")
    @Test
    void noCalculateScoreTest() {
        readyFrame.bowl(Pin.from(5));
        assertThat(readyFrame.score()).isEqualTo(Score.of(-1, 0));
    }

    @DisplayName("strike 이후 두 점수를 더해서 반환한다.")
    @Test
    void strikeScoreTest() {
        Frame next = readyFrame.bowl(Pin.from(10)).bowl(Pin.from(5));
//        assertThat(readyFrame.score()).isEqualTo(Score.of(-1, 0));
        next.bowl(Pin.from(5));
        assertThat(readyFrame.score()).isEqualTo(Score.of(20, 0));
    }

}

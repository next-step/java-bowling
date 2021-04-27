package bowling.domain.concrete.frame.state;

import bowling.domain.RollResult;
import bowling.domain.engine.frame.Score;
import bowling.domain.engine.frame.state.CannotCompleteScoreException;
import bowling.domain.engine.frame.state.CannotInitializeScoreException;
import bowling.domain.engine.frame.state.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PlayingTest {

    private State playingState;
    private RollResult firstRoll;

    @BeforeEach
    void setUp() {
        firstRoll = RollResult.of(8);
        playingState = new Playing(firstRoll);
    }

    @Test
    @DisplayName("나머지 핀을 쓰러트리면 Spare 상태로 이전된다.")
    void transitToSpare() {
        assertThat(playingState.transit(RollResult.of(2))).isInstanceOf(Spare.class);
    }

    @Test
    @DisplayName("핀이 남으면 Miss 상태로 이전된다.")
    void transitToMiss() {
        assertThat(playingState.transit(RollResult.of(1))).isInstanceOf(Miss.class);
    }

    @Test
    @DisplayName("두 번의 투구에서 쓰러트린 핀의 수가 10 보다 크면 예외 처리한다.")
    void throwExceptionIfNumberOfPinsExceedTen() {
        assertThatThrownBy(() -> playingState.transit(RollResult.of(3)))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("완료 상태를 확인할 때는 항상 false 를 반환한다.")
    void returnFalseIfCallIsFinished() {
        assertThat(playingState.isFinished()).isFalse();
    }

    @Test
    @DisplayName("Playing 상태에서는 첫 번째 결과만 내보낸다.")
    void export() {
        assertThat(playingState.export()).isEqualTo("8");
    }

    @Test
    @DisplayName("Playing 상태에서는 점수를 생성할 수 없다.")
    void cannotCreateScoreInReady() {
        assertThatThrownBy(() -> playingState.createScore()).isInstanceOf(CannotInitializeScoreException.class);
    }

    @Test
    @DisplayName("Playing 상태에서는 Score 에 추가 점수를 한 번만 넣어 줄 수 있다.")
    void canGiveScoreOneTimeInPlaying() {
        Score spareScore = Score.initSpareScore();

        assertThatCode(() -> playingState.addScoreTo(spareScore)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Score 를 완성하기 위해 두 번의 추가 점수가 필요하다면 예외 처리한다.")
    void cannotGiveScoreTwoTimesInPlaying() {
        Score strikeScore = Score.initStrikeScore();

        assertThatThrownBy(() -> playingState.addScoreTo(strikeScore)).isInstanceOf(CannotCompleteScoreException.class);
    }
    
}

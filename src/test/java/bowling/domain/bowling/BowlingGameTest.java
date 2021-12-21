package bowling.domain.bowling;

import bowling.domain.bowl.FinalBowl;
import bowling.domain.frame.Frame;
import bowling.domain.pin.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

class BowlingGameTest {
    
    @DisplayName("차례가 끝났을 때 턴이 잘 변경되는지")
    @Test
    void pitch_turnChanged() {
        //given
        Bowling bowlingAAA = new Bowling("AAA");
        Bowling bowlingBBB = new Bowling("BBB");
        Bowling bowlingCCC = new Bowling("CCC");

        BowlingGame bowlingGame = new BowlingGame(asList(bowlingAAA, bowlingBBB, bowlingCCC));

        //when
        // A: 스트라이크
        bowlingGame.pitch(Pin.allHitPin());

        // B: 스트라이크
        bowlingGame.pitch(Pin.allHitPin());

        //then
        assertThat(bowlingGame).isEqualTo(new BowlingGame(asList(bowlingCCC, bowlingAAA, bowlingBBB)));
    }

    @DisplayName("모든 플레이어의 턴이 끝났다면 게임이 종료되어야한다")
    @Test
    void pitch() {
        //given
        Bowling bowlingAAA = new Bowling("AAA", lastFrame());
        Bowling bowlingBBB = new Bowling("BBB", lastFrame());
        Bowling bowlingCCC = new Bowling("CCC", lastFrame());

        BowlingGame bowlingGame = new BowlingGame(asList(bowlingAAA, bowlingBBB, bowlingCCC));
        //when

        // A: 스페어 + 5
        bowlingGame.pitch(pin(2));
        bowlingGame.pitch(pin(8));
        bowlingGame.pitch(pin(5));

        // B: 스트라이크 * 3
        bowlingGame.pitch(Pin.allHitPin());
        bowlingGame.pitch(Pin.allHitPin());
        bowlingGame.pitch(Pin.allHitPin());

        // C: 거터
        bowlingGame.pitch(Pin.noneHitPin());
        boolean canPitch = bowlingGame.pitch(Pin.noneHitPin());

        //then
        assertThat(canPitch).isFalse();
    }

    private Pin pin(int hitCount) {
        return Pin.from(hitCount);
    }


    private Frame lastFrame() {
        return new Frame(10, new FinalBowl());
    }

}

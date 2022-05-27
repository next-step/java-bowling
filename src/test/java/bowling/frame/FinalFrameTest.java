package bowling.frame;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.exception.CannotPitchException;
import bowling.domain.pin.Pins;
import bowling.domain.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FinalFrameTest {

    /*
     * calculateAdditionalScore 테스트
     * */
    @Test
    @DisplayName("스트라이크에서 보너스 점수 계산요청")
    void 스트라이크_프레임에서_보너스점수_계산(){
        Frame finalFrame = new FinalFrame(10);

        finalFrame.pitch(new Pins(10));
        finalFrame.pitch(new Pins(2));

        assertThat(finalFrame.calculateAdditionalScore(Score.spare(9,1))).isEqualTo(20);
        assertThat(finalFrame.calculateAdditionalScore(Score.strike())).isEqualTo(22);
    }

    @Test
    @DisplayName("스페어일때 보너스 점수 계산요청")
    void 마지막프레임이_스페어_보너스점수_계산(){
        Frame finalFrame = new FinalFrame(10);

        finalFrame.pitch(new Pins(2));
        finalFrame.pitch(new Pins(8));

        assertThat(finalFrame.calculateAdditionalScore(Score.spare(1, 9))).isEqualTo(12);
        assertThat(finalFrame.calculateAdditionalScore(Score.strike())).isEqualTo(20);
    }

    @Test
    @DisplayName("거터일 때  보너스 점수 계산요청")
    void 마지막프레임이_거터_보너스점수_계산(){
        Frame finalFrame = new FinalFrame(10);

        finalFrame.pitch(new Pins(0));
        finalFrame.pitch(new Pins(0));

        assertThat(finalFrame.calculateAdditionalScore(Score.spare(1, 9))).isEqualTo(10);
        assertThat(finalFrame.calculateAdditionalScore(Score.strike())).isEqualTo(10);
    }

    @Test
    @DisplayName("미스일 때  보너스 점수 계산요청")
    void 마지막프레임이_미스_보너스점수_계산(){
        Frame finalFrame = new FinalFrame(10);

        finalFrame.pitch(new Pins(2));
        finalFrame.pitch(new Pins(5));

        assertThat(finalFrame.calculateAdditionalScore(Score.spare(1, 9))).isEqualTo(12);
        assertThat(finalFrame.calculateAdditionalScore(Score.strike())).isEqualTo(17);
    }

    @Test
    @DisplayName("First일 때  보너스 점수 계산요청")
    void 마지막프레임이_First_보너스점수_계산(){
        Frame finalFrame = new FinalFrame(10);

        finalFrame.pitch(new Pins(2));

        assertThat(finalFrame.calculateAdditionalScore(Score.spare(1, 9))).isEqualTo(12);
        assertThat(finalFrame.calculateAdditionalScore(Score.strike())).isEqualTo(-1);
    }

    @Test
    @DisplayName("9번째 프레임에서 보너스점수 계산 요청할때 계산할 수 없으면 -1을 반환한다.")
    void 실패_마지막프레임에서_보너스점수_계산(){
        Frame finalFrame = new FinalFrame(10);

        finalFrame.pitch(new Pins(5));

        assertThat(finalFrame.calculateAdditionalScore(Score.strike())).isEqualTo(-1);
    }

    @Test
    @DisplayName("보너스 점수 계산 요청할 때 계산할 수 없으면 -1을 반환한다.")
    void 실패_마지막_프레임_점수_계산(){
        Frame finalFrame = new FinalFrame(10);

        assertThat(finalFrame.calculateAdditionalScore(Score.spare(2, 8))).isEqualTo(-1);
    }


    /*
    * score 테스트
    * */
    @ParameterizedTest
    @DisplayName("스타라이크일때 점수계산")
    @CsvSource(value = {
            "10, 2, 8, 20",
            "10, 10, 10, 30",
            "10, 2, 1, 13"
    })
    void 마지막프레임이_스트라이크_점수_계산(int first, int second, int thrid, int res){
        Frame finalFrame = new FinalFrame(10);

        finalFrame.pitch(new Pins(first));
        finalFrame.pitch(new Pins(second));
        finalFrame.pitch(new Pins(thrid));

        assertThat(finalFrame.score()).isEqualTo(res);
    }


    @Test
    @DisplayName("스타라이크일때 보너스 투구 하지않았으면 점수계산했을 때 -1리턴한다.")
    void 마지막프레임이_스트라이크_점수_계산_실패(){
        Frame finalFrame = new FinalFrame(10);

        finalFrame.pitch(new Pins(10));

        assertThat(finalFrame.score()).isEqualTo(-1);
    }

    @ParameterizedTest
    @DisplayName("스페어일때 점수계산")
    @CsvSource(value = {
            "7, 3, 1, 11",
            "1, 9, 1, 11",
            "2, 8, 10, 20"
    })
    void 스페어_점수_계산(int first, int second, int bonus, int res){
        Frame finalFrame = new FinalFrame(10);

        finalFrame.pitch(new Pins(first));
        finalFrame.pitch(new Pins(second));
        finalFrame.pitch(new Pins(bonus));

        assertThat(finalFrame.score()).isEqualTo(res);
    }

    @Test
    @DisplayName("스페어일 때 보너스 투구를 하지않았으면 점수를 구할 수 없다.")
    void 스페어_점수_구할수_없다(){
        Frame finalFrame = new FinalFrame(10);

        // 스페어
        finalFrame.pitch(new Pins(1));
        finalFrame.pitch(new Pins(9));

        assertThat(finalFrame.score()).isEqualTo(-1);
    }


    @Test
    @DisplayName("10번째 프레임이 거터일때 점수계산")
    void 마지막프레임_거터_점수_계산(){
        Frame finalFrame = new FinalFrame(10);

        finalFrame.pitch(new Pins(0));
        finalFrame.pitch(new Pins(0));

        assertThat(finalFrame.score()).isEqualTo(0);
    }

    @ParameterizedTest
    @DisplayName("10번째 프레임이 미스일때 점수계산")
    @CsvSource(value = {
            "4, 3, 7",
            "1, 3, 4",
            "2, 1, 3"
    })
    void 마지막프레임_미스_점수_계산(int first, int second, int res){
        Frame finalFrame = new FinalFrame(10);

        finalFrame.pitch(new Pins(first));
        finalFrame.pitch(new Pins(second));

        assertThat(finalFrame.score()).isEqualTo(res);
    }


    @Test
    @DisplayName("마지막 프레임이 끝나지 않았을 때 점수를 구하면 -1을 반환한다.")
    void 마지막프레임_끝나지않을때_점수구하기2(){
        Frame finalFrame = new FinalFrame(10);

        finalFrame.pitch(new Pins(4));

        assertThat(finalFrame.score()).isEqualTo(-1);
    }


    @Test
    @DisplayName("마지막 프레임에서 스트라이크면 한 번을 더 투구할 수 있다.")
    void 마지막_프레임_투구_한번더_스트라이크(){
        Frame finalFrame = new FinalFrame(10);
        finalFrame.pitch(new Pins(10));

        finalFrame.pitch(new Pins(4));
    }

    @Test
    @DisplayName("마지막 프레임에서 스트라이크시 총 4번 투구하면 예외가 생긴다.")
    void 마지막_프레임_투구_두번더_스트라이크(){
        Frame finalFrame = new FinalFrame(10);
        finalFrame.pitch(new Pins(10));

        finalFrame.pitch(new Pins(2));
        finalFrame.pitch(new Pins(3));

        assertThatThrownBy(()->{
            finalFrame.pitch(new Pins(4));
        }).isInstanceOf(CannotPitchException.class);
    }

    @Test
    @DisplayName("마지막 프레임에서 스페어시 한 번을 더 투구할 수 있다.")
    void 마지막_프레임_투구_한번더_스페어(){
        Frame finalFrame = new FinalFrame(10);

        finalFrame.pitch(new Pins(4));
        finalFrame.pitch(new Pins(6));

        finalFrame.pitch(new Pins(5));
    }

    @ParameterizedTest
    @DisplayName("마지막 프레임에서 스페어시 두번더 투구하면 예외가 생긴다.")
    @CsvSource(value = {
            "0, 10",
            "1, 9",
            "2, 8",
            "3, 7",
            "4, 6",
            "5, 5"
    })
    void 마지막_프레임_투구_두번더_스페어(int first, int second){
        Frame finalFrame = new FinalFrame(10);

        finalFrame.pitch(new Pins(first));
        finalFrame.pitch(new Pins(second));

        finalFrame.pitch(new Pins(1));
        assertThatThrownBy(()->{
            finalFrame.pitch(new Pins(2));
        }).isInstanceOf(CannotPitchException.class);
    }


    @Test
    @DisplayName("마지막 프레임에서 Miss일때 한 번더 투구하면 예외를 던진다.")
    void 마지막_프레임_투구_예외_miss(){
        Frame finalFrame = new FinalFrame(10);

        finalFrame.pitch(new Pins(5));
        finalFrame.pitch(new Pins(0));

        assertThatThrownBy(()->{
            finalFrame.pitch(new Pins(5));
        }).isInstanceOf(CannotPitchException.class);
    }

    @Test
    @DisplayName("마지막 프레임에서 gutter 일때 한 번더 투구하면 예외를 던진다.")
    void 마지막_프레임_투구_예외_gutter(){
        Frame finalFrame = new FinalFrame(10);

        finalFrame.pitch(new Pins(0));
        finalFrame.pitch(new Pins(0));

        assertThatThrownBy(()->{
            finalFrame.pitch(new Pins(5));
        }).isInstanceOf(CannotPitchException.class);
    }
}
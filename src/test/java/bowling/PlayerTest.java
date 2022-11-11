package bowling;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import bowling.exception.InvalidNameException;
import bowling.frame.Frame;
import bowling.frame.NormalFrame;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    void 알파벳으로_세글자_이름을_가진_플레이어_생성() {
        assertDoesNotThrow(() -> new Player("KJY"));
    }

    @Test
    void 세글자가_아니거나_알파벳외의_문자_포함한_이름을_가진_플레이어_생성_시_예외_발생() {
        assertAll(
                () -> assertThatExceptionOfType(InvalidNameException.class)
                        .isThrownBy(() -> new Player("KING")),
                () -> assertThatExceptionOfType(InvalidNameException.class)
                        .isThrownBy(() -> new Player("AB1"))
        );
    }

    @Test
    void 투구_첫번째_시도() {
        Player player = new Player("KJY");
        Frame frame = new NormalFrame();

        frame = player.bowlBall(frame, FallenPins.of(10));

        assertThat(frame.getResult())
                .isEqualTo(ResultMark.STRIKE.getMark() + ResultMark.EMPTY.getMark());
    }


    @Test
    void 투구_두번째_시도() {
        Player player = new Player("KJY");
        Frame frame = new NormalFrame();

        frame = player.bowlBall(frame, FallenPins.of(7));
        frame = player.bowlBall(frame, FallenPins.of(0));

        assertThat(frame.getResult())
                .isEqualTo("7" + Frame.RESULT_DELIMITER + ResultMark.GUTTER.getMark());
    }

}

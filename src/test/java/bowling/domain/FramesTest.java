package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-20 13:33
 */
public class FramesTest {
    @DisplayName("모든 게임 종료 - 상태(강제로 12번 스트라이크 처리)")
    @Test
    void 새로운_프레임_추가() {
        Frames frames = new Frames();
        for (int i = 0; i < 12; i++) {
            frames.bowl(10);
        }
        assertThat(frames.bowl(1)).isFalse();
    }

    @DisplayName("마지막 투구 - 상태(강제로 11번 스트라이크 처리)")
    @Test
    void 마지막_투구_가능() {
        Frames frames = new Frames();
        for (int i = 0; i < 11; i++) {
            frames.bowl(10);
        }
        assertThat(frames.bowl(1)).isTrue();
    }

    @DisplayName("결과 출력 검증 - 10번 투구 (추가 투구 X)")
    @ParameterizedTest
    @CsvSource({
            "0,X,30",
            "1,X,30",
            "2,X,30",
            "3,X,30",
            "4,X,30",
            "5,X,30",
            "6,X,30",
            "7,X,30",
            "8,X,20",
            "9,X,10",
    })
    void 결과_출력_검증_1(int index, String display, int score) {
        Frames frames = new Frames();
        for (int i = 0; i < 10; i++) {
            frames.bowl(10);
        }
        assertAll(

                () -> assertThat(frames.displayState().get(index)).isEqualTo(display),
                () -> assertThat(frames.displayScore().get(index)).isEqualTo(score)
        );
    }

    @DisplayName("결과 출력 검증 - 1번 추가 투구")
    @ParameterizedTest
    @CsvSource({
            "0,X,30",
            "1,X,30",
            "2,X,30",
            "3,X,30",
            "4,X,30",
            "5,X,30",
            "6,X,30",
            "7,X,30",
            "8,X,30",
            "9,X|X,20",
    })
    void 결과_출력_검증_2(int index, String display, int score) {
        Frames frames = new Frames();
        for (int i = 0; i < 11; i++) {
            frames.bowl(10);
        }
        assertAll(

                () -> assertThat(frames.displayState().get(index)).isEqualTo(display),
                () -> assertThat(frames.displayScore().get(index)).isEqualTo(score)
        );
    }

    @DisplayName("결과 출력 검증 - 2번 추가투구")
    @ParameterizedTest
    @CsvSource({
            "0,X,30",
            "1,X,30",
            "2,X,30",
            "3,X,30",
            "4,X,30",
            "5,X,30",
            "6,X,30",
            "7,X,30",
            "8,X,30",
            "9,X|X|X,30",
    })
    void 결과_출력_검증_3(int index, String display, int score) {
        Frames frames = new Frames();
        for (int i = 0; i < 12; i++) {
            frames.bowl(10);
        }
        assertAll(

                () -> assertThat(frames.displayState().get(index)).isEqualTo(display),
                () -> assertThat(frames.displayScore().get(index)).isEqualTo(score)
        );
    }
}

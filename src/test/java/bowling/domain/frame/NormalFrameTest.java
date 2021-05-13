package bowling.domain.frame;

import bowling.domain.score.Score;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class NormalFrameTest {

    @DisplayName("NormalFrame 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {
        // when
        Frame normalFrame = new NormalFrame(1);

        // then
        assertAll(
                () -> assertThat(normalFrame).isNotNull(),
                () -> assertThat(normalFrame).isInstanceOf(NormalFrame.class)
        );
    }

    @DisplayName("NormalFrame 인스턴스가 bowl 실행(제자리) 테스트")
    @Test
    void 투구_bowl_제자리() {
        // when
        Frame normalFrame = new NormalFrame(1);
        Frame returnedFrame = normalFrame.bowl(9);
        // then
        assertAll(
                () -> assertThat(normalFrame.isFinish()).isFalse(),
                () -> assertThat(returnedFrame).isSameAs(normalFrame)
        );
    }

    @DisplayName("NormalFrame 인스턴스가 bowl 실행(다음으로 이동) 테스트")
    @Test
    void 투구_bowl_다음으로_이동() {
        // when
        Frame normalFrame = new NormalFrame(1);
        Frame nextFrame = normalFrame.bowl(10);

        // then
        assertAll(
                () -> assertThat(normalFrame.isFinish()).isTrue(),
                () -> assertThat(nextFrame).isNotSameAs(normalFrame)
        );
    }

    @DisplayName("NormalFrame 인스턴스가 알맞은 Score를 반환하는지 테스트")
    @Test
    void 반환_score() {
        // when
        Frame normalFrame = new NormalFrame(1);
        normalFrame.bowl(10);
        Score score = normalFrame.score();

        // then
        assertThat(score.score()).isEqualTo(10);
    }

    @DisplayName("NormalFrame 인스턴스가 sequence 를 반환하는지 테스트")
    @Test
    void 반환_sequence() {
        // when
        Frame normalFrame = new NormalFrame(1);
        Frame nextFrame = normalFrame.bowl(10);

        // then
        assertAll(
                () -> assertThat(normalFrame.sequence()).isEqualTo(1),
                () -> assertThat(nextFrame.sequence()).isEqualTo(2)
        );

    }

    @DisplayName("ready 일 때, NormalFrame 인스턴스가 description 을반환하는지 테스트")
    @Test
    void 반환_description_ready_일_때() {
        // when
        Frame normalFrame = new NormalFrame(1);
        String actual = normalFrame.description();

        // then
        assertThat(actual).isEqualTo(Strings.EMPTY);
    }

    @DisplayName("FirstBowl 이면서 일반적인 값일 때, NormalFrame 인스턴스가 description 을반환하는지 테스트")
    @Test
    void 반환_description_FirstBowl_일반적일_때() {
        // when
        Frame normalFrame = new NormalFrame(1);
        normalFrame.bowl(1);
        String actual = normalFrame.description();

        // then
        assertThat(actual).isEqualTo("1");
    }

    @DisplayName("FirstBowl 이면서 거터일 때, NormalFrame 인스턴스가 description 을반환하는지 테스트")
    @Test
    void 반환_description_FirstBowl_거터일_때() {
        // when
        Frame normalFrame = new NormalFrame(1);
        normalFrame.bowl(0);
        String actual = normalFrame.description();

        // then
        assertThat(actual).isEqualTo("-");
    }

    @DisplayName("FirstBowl 이면서 일반적인 값일 때, NormalFrame 인스턴스가 description 을반환하는지 테스트")
    @Test
    void 반환_description_Spare_일반적일_때() {
        // when
        Frame normalFrame = new NormalFrame(1);
        normalFrame.bowl(1);
        normalFrame.bowl(9);
        String actual = normalFrame.description();

        // then
        assertThat(actual).isEqualTo("1|/");
    }

    @DisplayName("Spare 이면서 거터일 때, NormalFrame 인스턴스가 description 을반환하는지 테스트")
    @Test
    void 반환_description_Spare_거터일_때() {
        // when
        Frame normalFrame = new NormalFrame(1);
        normalFrame.bowl(0);
        normalFrame.bowl(10);
        String actual = normalFrame.description();

        // then
        assertThat(actual).isEqualTo("-|/");
    }

    @DisplayName("Miss 이면서 일반적인 값일 때, NormalFrame 인스턴스가 description 을반환하는지 테스트")
    @Test
    void 반환_description_Miss_일반적일_때() {
        // when
        Frame normalFrame = new NormalFrame(1);
        normalFrame.bowl(1);
        normalFrame.bowl(8);
        String actual = normalFrame.description();

        // then
        assertThat(actual).isEqualTo("1|8");
    }

    @DisplayName("Miss 이면서 거터일 때, NormalFrame 인스턴스가 description 을반환하는지 테스트")
    @Test
    void 반환_description_Miss_거터일_때_() {
        // when
        Frame normalFrame = new NormalFrame(1);
        normalFrame.bowl(0);
        normalFrame.bowl(0);
        String actual = normalFrame.description();

        // then
        assertThat(actual).isEqualTo("-|-");
    }

    @DisplayName("strike 일 때, NormalFrame 인스턴스가 description 을반환하는지 테스트")
    @Test
    void 반환_description_Strike_거터일_때_() {
        // when
        Frame normalFrame = new NormalFrame(1);
        normalFrame.bowl(10);
        String actual = normalFrame.description();

        // then
        assertThat(actual).isEqualTo("X");
    }
}
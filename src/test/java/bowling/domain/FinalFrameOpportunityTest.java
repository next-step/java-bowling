package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FinalFrameOpportunityTest {

    @DisplayName("FinalFrameOpportunity 인스턴스 생성 여부 태스트")
    @Test
    void 생성() {
        // when
        FinalFrameOpportunity finalFrameOpportunity = FinalFrameOpportunity.initialize();

        // then
        assertThat(finalFrameOpportunity).isNotNull();
    }

    @DisplayName("FinalFrameOpportunity 인스턴스의 기회 종료 여부 테스트")
    @Test
    void 반환_종료_여부_FALSE() {
        // when
        FinalFrameOpportunity finalFrameOpportunity = FinalFrameOpportunity.initialize();

        // then
        assertThat(finalFrameOpportunity.isFinish(false)).isFalse();
    }

    @DisplayName("FinalFrameOpportunity 인스턴스가 내부 값을 증가시키는지 테스트")
    @Test
    void 증가_보너스_없는_경우() {
        // given
        FinalFrameOpportunity finalFrameOpportunity = FinalFrameOpportunity.initialize();

        // when
        finalFrameOpportunity.next();
        finalFrameOpportunity.next();

        // then
        assertThat(finalFrameOpportunity.isFinish(false)).isTrue();
    }

    @DisplayName("FinalFrameOpportunity 인스턴스가 내부 값을 증가시키는지 테스트")
    @Test
    void 증가_보너스_있는_경우() {
        // given
        FinalFrameOpportunity finalFrameOpportunity = FinalFrameOpportunity.initialize();

        // when
        finalFrameOpportunity.next();
        finalFrameOpportunity.next();

        // then
        assertThat(finalFrameOpportunity.isFinish(true)).isFalse();
    }

}
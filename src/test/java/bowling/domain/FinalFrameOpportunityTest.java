package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FinalFrameOpportunityTest {

    @DisplayName("FinalFrameOpportunity 인스턴스 생서 여부 태스트")
    @Test
    void 생성() {
        // when
        FinalFrameOpportunity finalFrameOpportunity = FinalFrameOpportunity.initialize();

        // then
        assertThat(finalFrameOpportunity).isNotNull();
    }

}
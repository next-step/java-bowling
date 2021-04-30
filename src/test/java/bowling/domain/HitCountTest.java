package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class HitCountTest {

    @DisplayName("HitCount 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {
        // given
        int hit = 10;

        // when
        HitCount hitCount = HitCount.valueOf(hit);

        // then
        assertThat(hitCount).isNotNull();
    }
}
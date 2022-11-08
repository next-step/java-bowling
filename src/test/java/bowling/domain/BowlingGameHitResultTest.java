package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingGameHitResultTest {

    @DisplayName("일반 프레임에 1회 투구를 할 때, 기록에 맞는 투구 결과를 반환해야 한다.")
    @ParameterizedTest
    @CsvSource({
            "0,GUTTER",
            "1,NONE",
            "10,STRIKE",
    })
    void from_givenOneHit(int hit, BowlingGameHitResult result) {
        List<Integer> hits = new ArrayList<>();
        hits.add(hit);
        assertThat(BowlingGameHitResult.from(hits)).isEqualTo(result);
    }

    @DisplayName("일반 프레임에 2회 투구를 할 때, 기록에 맞는 투구 결과를 반환해야 한다.")
    @ParameterizedTest
    @CsvSource({
            "0,0,GUTTER,GUTTER",
            "0,1,GUTTER,MISS",
            "0,10,GUTTER,SPARE",
            "1,0,NONE,GUTTER",
            "1,2,NONE,MISS",
            "1,9,NONE,SPARE"
    })
    void from_givenTwoHits(int hit1, int hit2, BowlingGameHitResult result1, BowlingGameHitResult result2) {
        List<Integer> hits = new ArrayList<>();
        hits.add(hit1);
        assertThat(BowlingGameHitResult.from(hits)).isEqualTo(result1);

        hits.add(hit2);
        assertThat(BowlingGameHitResult.from(hits)).isEqualTo(result2);
    }

    @DisplayName("마지막 프레임에 3회 투구를 할 때, 기록에 맞는 투구 결과를 반환해야 한다.")
    @ParameterizedTest
    @CsvSource({
            "0,10,0,GUTTER,SPARE,GUTTER",
            "0,10,1,GUTTER,SPARE,NONE",
            "0,10,10,GUTTER,SPARE,STRIKE",
            "1,9,0,NONE,SPARE,GUTTER",
            "1,9,1,NONE,SPARE,NONE",
            "1,9,10,NONE,SPARE,STRIKE",
            "10,0,0,STRIKE,GUTTER,GUTTER",
            "10,0,1,STRIKE,GUTTER,MISS",
            "10,0,10,STRIKE,GUTTER,SPARE",
            "10,1,0,STRIKE,NONE,GUTTER",
            "10,1,2,STRIKE,NONE,MISS",
            "10,1,9,STRIKE,NONE,SPARE",
            "10,10,0,STRIKE,STRIKE,GUTTER",
            "10,10,1,STRIKE,STRIKE,NONE",
            "10,10,10,STRIKE,STRIKE,STRIKE",
    })
    void from_givenThreeHits(int hit1, int hit2, int hit3, BowlingGameHitResult result1, BowlingGameHitResult result2, BowlingGameHitResult result3) {
        List<Integer> hits = new ArrayList<>();

        hits.add(hit1);
        assertThat(BowlingGameHitResult.from(hits)).isEqualTo(result1);

        hits.add(hit2);
        assertThat(BowlingGameHitResult.from(hits)).isEqualTo(result2);

        hits.add(hit3);
        assertThat(BowlingGameHitResult.from(hits)).isEqualTo(result3);
    }

}

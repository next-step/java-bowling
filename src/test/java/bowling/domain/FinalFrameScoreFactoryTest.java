package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FinalFrameScoreFactoryTest {

    @DisplayName("핀마킹이 완료되지 않았으면 unknown score 를 return 한다")
    @Test
    void unknownScore() {
        FrameScore score = FinalFrameScoreFactory.create(10, notCompleted);
        assertThat(score).isEqualTo(FrameScore.unknown);
    }

    @DisplayName("핀마킹이 완료되었으면 핀마킹된 핀수의 합이 점수가 된다")
    @Test
    void score() {
        FrameScore score = FinalFrameScoreFactory.create(30, completed);
        assertThat(score).isEqualTo(FrameScore.of(30));
    }


    PinMarkerState completed = new PinMarkerState() {
        @Override
        public PinMarkerState mark(PinMark pinMark) {
            return null;
        }

        @Override
        public boolean isCompleted() {
            return true;
        }

        @Override
        public List<PinMarkSymbol> toSymbols() {
            return null;
        }
    };

    PinMarkerState notCompleted = new PinMarkerState() {
        @Override
        public PinMarkerState mark(PinMark pinMark) {
            return null;
        }

        @Override
        public boolean isCompleted() {
            return false;
        }

        @Override
        public List<PinMarkSymbol> toSymbols() {
            return null;
        }
    };

}
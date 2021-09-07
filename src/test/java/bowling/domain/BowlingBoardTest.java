package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingBoardTest {

    @Test
    void write() {
        // Given
        BowlingBoard bowlingBoard = BowlingBoard.create();

        Frame round1 = NormalFrame.create();
        Frame round2 = round1.roll(5);
        round2.roll(5);
        bowlingBoard.write(round1);
        bowlingBoard.write(round2);

        // When
        Frame givenFrame1 = bowlingBoard.get(0);
        Frame givenFrame2 = bowlingBoard.get(1);

        // Then
        assertThat(givenFrame1.pins().numberOfPinDowns()).isEqualTo(5);
        assertThat(givenFrame2.pins().numberOfPinDowns()).isEqualTo(10);
    }
}
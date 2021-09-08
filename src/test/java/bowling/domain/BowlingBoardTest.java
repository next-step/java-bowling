package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BowlingBoardTest {

    @Test
    void write() {
        // Given
        Person givenPerson = Person.from("Phobi");
        BowlingBoard bowlingBoard = BowlingBoard.create();

        // Normal Frame
        for (int i = 0; i < 9; i++) {
            Frame frame = NormalFrame.create();
            while (frame.hasNextFrame()) {
                bowlingBoard.write(givenPerson, frame);
                frame = frame.roll(5);
            }
        }

        // Final Frame
        Frame finalFrame = FinalFrame.create();
        while (finalFrame.hasNextFrame()) {
            bowlingBoard.write(givenPerson, finalFrame);
            finalFrame = finalFrame.roll(5);
        }

        // When
        Frames actualFrames = bowlingBoard.get(givenPerson);

        // Then
        assertThat(actualFrames.size()).isEqualTo(21);
    }
}
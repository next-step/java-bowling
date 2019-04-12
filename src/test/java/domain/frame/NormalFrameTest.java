package domain.frame;

import domain.base.BaseTest;
import domain.game.BowlingGame;
import domain.pin.Pin;
import domain.player.PlayerName;
import domain.score.CannotCalculateException;
import domain.status.FirstBowlFinished;
import domain.status.Open;
import domain.status.Spare;
import domain.status.Strike;
import org.junit.Test;

import static domain.frame.Frames.LAST_FRAME;
import static domain.frame.Frames.START_FRAME;
import static domain.pin.Pin.MAXIMUM_PINS;
import static domain.pin.Pin.MINIMUM_PINS;
import static org.assertj.core.api.Assertions.assertThat;

public class NormalFrameTest extends BaseTest {

    @Test
    public void constructor_for_not_strike() {
        for (int curFrameNumber : getFrameNumbers(START_FRAME, LAST_FRAME - 1)) {
            for (Pin curPin : getPins(MINIMUM_PINS, MAXIMUM_PINS - 1)) {
                Frame frame = new NormalFrame(curFrameNumber, curPin);

                assertThat(frame.statuses.size()).isEqualTo(1);
                assertThat(frame.statuses.getLastStatus()).isInstanceOf(FirstBowlFinished.class);
                assertThat(frame.pins.size()).isEqualTo(1);
                assertThat(frame.pins.get(0)).isEqualTo(curPin);
            }
        }
    }

    @Test
    public void constructor_for_strike() {
        for (int curFrameNumber : getFrameNumbers(START_FRAME, LAST_FRAME - 1)) {
            Frame frame = new NormalFrame(curFrameNumber, Pin.ofStrike());

            assertThat(frame.statuses.size()).isEqualTo(1);
            assertThat(frame.statuses.getLastStatus()).isInstanceOf(Strike.class);
            assertThat(frame.pins.size()).isEqualTo(1);
            assertThat(frame.pins.get(0)).isEqualTo(Pin.ofStrike());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_for_less_than_start_frame_number() {
        new NormalFrame(START_FRAME - 1, Pin.of(MAXIMUM_PINS));
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_for_last_frame_number() {
        new NormalFrame(LAST_FRAME, Pin.of(5));
    }

    @Test
    public void bowl_for_spare() {
        for (int curFrameNumber : getFrameNumbers(START_FRAME, LAST_FRAME - 1)) {
            for (Pin firstBowl : getPins(MINIMUM_PINS, MAXIMUM_PINS - 1)) {
                Pin secondBowl = Pin.ofSpare(firstBowl);
                Frame frame = new NormalFrame(curFrameNumber, firstBowl);
                frame.bowl(secondBowl);

                assertThat(frame.statuses.size()).isEqualTo(2);
                assertThat(frame.statuses.get(0)).isInstanceOf(FirstBowlFinished.class);
                assertThat(frame.statuses.getLastStatus()).isInstanceOf(Spare.class);
                assertThat(frame.pins.get(0)).isEqualTo(firstBowl);
                assertThat(frame.pins.get(1)).isEqualTo(secondBowl);
            }
        }
    }

    @Test
    public void bowl_for_open() {
        for (int curFrameNumber : getFrameNumbers(START_FRAME, LAST_FRAME - 1)) {
            for (Pin firstBowl : getPins(MINIMUM_PINS, MAXIMUM_PINS - 1)) {
                for (Pin secondBowl : getPins(MINIMUM_PINS, MAXIMUM_PINS - firstBowl.getPin() - 1)) {
                    Frame frame = new NormalFrame(curFrameNumber, firstBowl);
                    frame.bowl(secondBowl);

                    assertThat(frame.statuses.size()).isEqualTo(2);
                    assertThat(frame.statuses.get(0)).isInstanceOf(FirstBowlFinished.class);
                    assertThat(frame.statuses.getLastStatus()).isInstanceOf(Open.class);
                    assertThat(frame.pins.get(0)).isEqualTo(firstBowl);
                    assertThat(frame.pins.get(1)).isEqualTo(secondBowl);
                    assertThat(frame.isScoreCalculationFinished()).isEqualTo(true);
                }
            }
        }
    }

    @Test
    public void bowl_for_new_frame() {
        Pin firstBowl = Pin.of(5);
        Pin secondBowl = Pin.of(5);
        Pin thirdBowl = Pin.of(9);

        Frame frame = new NormalFrame(START_FRAME, firstBowl);
        frame = frame.bowl(secondBowl);
        Frame newFrame = frame.bowl(thirdBowl);

        assertThat(newFrame).isNotEqualTo(frame);
        assertThat(newFrame.getNumber()).isEqualTo(frame.getNumber() + 1);
        assertThat(newFrame.getLastStatus()).isInstanceOf(FirstBowlFinished.class);
        assertThat(newFrame.getLastStatus().isNormalFrameFinished()).isEqualTo(false);
    }

    @Test
    public void isFinished_for_not_strike() {
        for (Pin firstBowl : getPins(MINIMUM_PINS, MAXIMUM_PINS - 1)) {
            Frame frame = new NormalFrame(START_FRAME, firstBowl);

            assertThat(frame.isFinished()).isEqualTo(false);
        }
    }

    @Test
    public void isFinished_for_strike() {
        Frame frame = new NormalFrame(START_FRAME, Pin.ofStrike());

        assertThat(frame.isFinished()).isEqualTo(true);
    }

    @Test
    public void getScore_for_unfinished_start_frame() {
        for (Pin firstBowl : getPins(MINIMUM_PINS, MAXIMUM_PINS - 1)) {
            Frame frame = new NormalFrame(START_FRAME, firstBowl);

            assertThat(frame.getScore()).isEqualTo(firstBowl.getPin());
        }
    }

    @Test(expected = CannotCalculateException.class)
    public void getScore_for_strike_start_frame() {
        Pin firstBowl = Pin.ofStrike();
        Frame frame = new NormalFrame(START_FRAME, firstBowl);

        frame.getScore();
    }

    @Test(expected = CannotCalculateException.class)
    public void getScore_for_spare_start_frame() {
        for (Pin firstBowl : getPins(MINIMUM_PINS, MAXIMUM_PINS - 1)) {
            Pin secondBowl = Pin.ofSpare(firstBowl);
            Frame frame = new NormalFrame(START_FRAME, firstBowl);
            frame.bowl(secondBowl);

            frame.getScore();
        }
    }

    @Test
    public void getScore_for_open_start_frame() {
        for (Pin firstBowl : getPins(MINIMUM_PINS, MAXIMUM_PINS - 1)) {
            for (Pin secondBowl : getPins(MINIMUM_PINS, MAXIMUM_PINS - firstBowl.getPin() - 1)) {
                Frame frame = new NormalFrame(START_FRAME, firstBowl);
                frame.bowl(secondBowl);

                assertThat(frame.getScore()).isEqualTo(firstBowl.add(secondBowl).getPin());
            }
        }
    }

    @Test(expected = CannotCalculateException.class)
    public void getScore_for_double() {
        Pin strike = Pin.ofStrike();
        Frame startFrame = new NormalFrame(START_FRAME, strike);
        startFrame.bowl(strike);

        startFrame.getScore();
    }

    @Test
    public void getScore_for_double_and_started_third_frame() {
        for (Pin thirdFrameBowl : getAllPins()) {
            Pin strike = Pin.ofStrike();
            Frame startFrame = new NormalFrame(START_FRAME, strike);
            Frame secondFrame = startFrame.bowl(strike);
            secondFrame.bowl(thirdFrameBowl);

            assertThat(startFrame.getScore()).isEqualTo(MAXIMUM_PINS * 2 + thirdFrameBowl.getPin());
        }
    }

    @Test
    public void getScore_for_spare_start_frame_and_started_second_frame() {
        for (Pin firstBowl : getPins(MINIMUM_PINS, MAXIMUM_PINS - 1)) {
            for (Pin secondFrameBowl : getAllPins()) {
                Pin secondBowl = Pin.ofSpare(firstBowl);

                Frame startFrame = new NormalFrame(START_FRAME, firstBowl);
                Frame secondFrame = startFrame.bowl(secondBowl);
                secondFrame.bowl(secondFrameBowl);

                assertThat(startFrame.getScore()).isEqualTo(MAXIMUM_PINS + secondFrameBowl.getPin());
            }
        }
    }

    @Test(expected = CannotCalculateException.class)
    public void getScore_for_strike_ninth_frame_and_started_last_frame() {
        for (Pin lastBowl : getPins(MINIMUM_PINS, MAXIMUM_PINS)) {
            PlayerName playerName = new PlayerName("pdy");
            Frames frames = getStrikeNormalFrames();

            BowlingGame game = new BowlingGame(playerName, frames);
            game.play(lastBowl.getPin());

            game.getFrame(LAST_FRAME - 2).getScore();
        }
    }

    @Test
    public void getScore_for_strike_ninth_frame_and_two_strikes_last_frame() {
        PlayerName playerName = new PlayerName("pdy");

        Frames frames = getStrikeNormalFrames();

        BowlingGame game = new BowlingGame(playerName, frames);
        game.play(MAXIMUM_PINS);
        game.play(MAXIMUM_PINS);

        assertThat(game.getFrame(LAST_FRAME - 2).getScore()).isEqualTo(30);
    }

    private Frames getStrikeNormalFrames() {
        Frames frames = new Frames();
        Frame curFrame = new NormalFrame(START_FRAME, Pin.ofStrike());
        frames.add(curFrame);

        for (int frameNumber : getFrameNumbers(START_FRAME + 1, LAST_FRAME - 1)) {
            curFrame = new NormalFrame(frameNumber, Pin.ofStrike(), curFrame);
            frames.add(curFrame);
        }
        return frames;
    }

    @Test
    public void getScore_for_strike_ninth_frame_and_spare_last_frame() {
        for (Pin firstBowlInLastFrame : getPins(MINIMUM_PINS, MAXIMUM_PINS - 1)) {
            Pin secondBowlInLastFrame = Pin.ofSpare(firstBowlInLastFrame);

            PlayerName playerName = new PlayerName("pdy");
            Frames frames = getStrikeNormalFrames();

            BowlingGame game = new BowlingGame(playerName, frames);
            game.play(firstBowlInLastFrame.getPin());
            game.play(secondBowlInLastFrame.getPin());

            assertThat(game.getFrame(LAST_FRAME - 2).getScore())
                    .isEqualTo(MAXIMUM_PINS + firstBowlInLastFrame.getPin() + secondBowlInLastFrame.getPin());
        }
    }

    @Test
    public void getScore_for_spare_ninth_frame_and_spare_last_frame() {
        for (Pin firstBowlInNinthFrame : getPins(MINIMUM_PINS, MAXIMUM_PINS - 1)) {
            for (Pin firstBowlInLastFrame : getPins(MINIMUM_PINS, MAXIMUM_PINS - 1)) {
                Pin secondBowlInNinthFrame = Pin.ofSpare(firstBowlInNinthFrame);
                Pin secondBowlInLastFrame = Pin.ofSpare(firstBowlInLastFrame);

                PlayerName playerName = new PlayerName("pdy");
                Frames frames = new Frames();
                for (int frameNumber : getFrameNumbers(START_FRAME, LAST_FRAME - 2)) {
                    frames.add(new NormalFrame(frameNumber, Pin.ofStrike()));
                }

                BowlingGame game = new BowlingGame(playerName, frames);
                game.play(firstBowlInNinthFrame.getPin());
                game.play(secondBowlInNinthFrame.getPin());
                game.play(firstBowlInLastFrame.getPin());
                game.play(secondBowlInLastFrame.getPin());

                assertThat(game.getFrame(LAST_FRAME - 2).getScore())
                        .isEqualTo(MAXIMUM_PINS + firstBowlInLastFrame.getPin());
            }
        }
    }

    @Test
    public void isScoreCalculationFinished_for_strike() {
        assertThat(new NormalFrame(START_FRAME, Pin.ofStrike()).isScoreCalculationFinished()).isEqualTo(false);
    }

    @Test
    public void isScoreCalculationFinished_for_open() {

        for (int curFrameNumber : getFrameNumbers(START_FRAME, LAST_FRAME - 1)) {
            for (Pin firstBowl : getPins(MINIMUM_PINS, MAXIMUM_PINS - 1)) {
                for (Pin secondBowl : getPins(MINIMUM_PINS, MAXIMUM_PINS - firstBowl.getPin() - 1)) {
                    Frame frame = new NormalFrame(curFrameNumber, firstBowl);
                    frame.bowl(secondBowl);

                    assertThat(frame.isScoreCalculationFinished()).isEqualTo(true);
                }
            }
        }
    }

    @Test
    public void isScoreCalculationFinished_for_spare_and_finished_in_the_next_frame() {
        for (int curFrameNumber : getFrameNumbers(START_FRAME, LAST_FRAME - 1)) {
            for (Pin firstBowl : getPins(MINIMUM_PINS, MAXIMUM_PINS - 1)) {
                for (Pin thirdBowl : getPins(MINIMUM_PINS, MAXIMUM_PINS)) {
                    Pin secondBowl = Pin.ofSpare(firstBowl);
                    Frame frame = new NormalFrame(curFrameNumber, firstBowl);
                    Frame secondFrame = frame.bowl(secondBowl);

                    assertThat(frame.isScoreCalculationFinished()).isEqualTo(false);

                    secondFrame.bowl(thirdBowl);

                    assertThat(frame.isScoreCalculationFinished()).isEqualTo(true);
                }
            }
        }
    }

    @Test
    public void isScoreCalculationFinished_for_strike_and_open() {
        for (int curFrameNumber : getFrameNumbers(START_FRAME, LAST_FRAME - 1)) {
            for (Pin secondBowl : getPins(MINIMUM_PINS, MAXIMUM_PINS - 1)) {
                Pin thirdBowl = Pin.of(MAXIMUM_PINS - secondBowl.getPin() - 1);

                Frame frame = new NormalFrame(curFrameNumber, Pin.ofStrike());

                assertThat(frame.isScoreCalculationFinished()).isEqualTo(false);

                Frame secondFrame = frame.bowl(secondBowl);

                assertThat(frame.isScoreCalculationFinished()).isEqualTo(false);

                secondFrame.bowl(thirdBowl);

                assertThat(frame.isScoreCalculationFinished()).isEqualTo(true);
            }
        }
    }

    @Test
    public void isScoreCalculationFinished_for_strike_and_spare() {
        for (int curFrameNumber : getFrameNumbers(START_FRAME, LAST_FRAME - 1)) {
            for (Pin secondBowl : getPins(MINIMUM_PINS, MAXIMUM_PINS - 1)) {
                Pin thirdBowl = Pin.ofSpare(secondBowl);

                Frame frame = new NormalFrame(curFrameNumber, Pin.ofStrike());

                assertThat(frame.isScoreCalculationFinished()).isEqualTo(false);

                Frame secondFrame = frame.bowl(secondBowl);

                assertThat(frame.isScoreCalculationFinished()).isEqualTo(false);

                secondFrame.bowl(thirdBowl);

                assertThat(frame.isScoreCalculationFinished()).isEqualTo(true);
            }
        }
    }

    @Test
    public void isScoreCalculationFinished_for_double() {
        for (int curFrameNumber : getFrameNumbers(START_FRAME, LAST_FRAME - 1)) {
            for (Pin thirdBowl : getPins(MINIMUM_PINS, MAXIMUM_PINS - 1)) {
                Frame frame = new NormalFrame(curFrameNumber, Pin.ofStrike());

                assertThat(frame.isScoreCalculationFinished()).isEqualTo(false);

                Frame secondFrame = frame.bowl(Pin.ofStrike());

                assertThat(frame.isScoreCalculationFinished()).isEqualTo(false);

                secondFrame.bowl(thirdBowl);

                assertThat(frame.isScoreCalculationFinished()).isEqualTo(true);
            }
        }
    }

    @Test
    public void isScoreCalculationFinished_for_turkey() {
        for (int curFrameNumber : getFrameNumbers(START_FRAME, LAST_FRAME - 1)) {
            Frame frame = new NormalFrame(curFrameNumber, Pin.ofStrike());

            assertThat(frame.isScoreCalculationFinished()).isEqualTo(false);

            Frame secondFrame = frame.bowl(Pin.ofStrike());

            assertThat(frame.isScoreCalculationFinished()).isEqualTo(false);

            secondFrame.bowl(Pin.ofStrike());

            assertThat(frame.isScoreCalculationFinished()).isEqualTo(true);
        }
    }

    @Test
    public void isScoreCalculationFinished_for_strike_ninth_frame() {
        for (Pin firstBowl : getAllPins()) {
            for (Pin secondBowl : getPins(MINIMUM_PINS, MAXIMUM_PINS - firstBowl.getPin())) {
                PlayerName playerName = new PlayerName("pdy");
                Frames frames = new Frames();
                for (int frameNumber : getFrameNumbers(START_FRAME, LAST_FRAME - 1)) {
                    frames.add(new NormalFrame(frameNumber, Pin.ofStrike()));
                }

                BowlingGame game = new BowlingGame(playerName, frames);
                Frame targetFrame = game.getFrame(LAST_FRAME - 2);
                assertThat(targetFrame.isScoreCalculationFinished()).isEqualTo(false);

                game.play(firstBowl.getPin());
                assertThat(targetFrame.isScoreCalculationFinished()).isEqualTo(false);

                game.play(secondBowl.getPin());
                assertThat(targetFrame.isScoreCalculationFinished()).isEqualTo(true);
            }
        }
    }

    @Test
    public void isScoreCalculationFinished_for_spare_ninth_frame_and_spare_last_frame() {
        for (Pin firstBowlInNinthFrame : getPins(MINIMUM_PINS, MAXIMUM_PINS - 1)) {
            for (Pin firstBowlInLastFrame : getPins(MINIMUM_PINS, MAXIMUM_PINS - 1)) {
                Pin secondBowlInNinthFrame = Pin.ofSpare(firstBowlInNinthFrame);
                Pin secondBowlInLastFrame = Pin.ofSpare(firstBowlInLastFrame);

                PlayerName playerName = new PlayerName("pdy");
                Frames frames = new Frames();
                for (int frameNumber : getFrameNumbers(START_FRAME, LAST_FRAME - 2)) {
                    frames.add(new NormalFrame(frameNumber, Pin.ofStrike()));
                }

                BowlingGame game = new BowlingGame(playerName, frames);
                game.play(firstBowlInNinthFrame.getPin());
                game.play(secondBowlInNinthFrame.getPin());

                Frame targetFrame = game.getFrame(LAST_FRAME - 2);
                assertThat(targetFrame.isScoreCalculationFinished()).isEqualTo(false);

                game.play(firstBowlInLastFrame.getPin());
                assertThat(targetFrame.isScoreCalculationFinished()).isEqualTo(true);

                game.play(secondBowlInLastFrame.getPin());
                assertThat(targetFrame.isScoreCalculationFinished()).isEqualTo(true);
            }
        }
    }
}
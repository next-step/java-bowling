package bowling.domain.controller;

import bowling.domain.bowling.Bowling;
import bowling.domain.controller.view.InputView;
import bowling.domain.controller.view.OutputView;
import bowling.domain.participant.Participant;

public class BowlingController {

    private BowlingController() {
    }

    public static void main(String[] args) {
        Participant participant = new Participant(InputView.getName());
        Bowling bowling = new Bowling(participant);

        boolean continued = true;
        while (continued) {
            int numberOfFrame = bowling.numberOfFrame();
            int hitCount = InputView.getHitCount(numberOfFrame);
//            continued = bowling.pitch(Pin.from(hitCount));

            OutputView.showBowling(bowling);
        }
    }

}

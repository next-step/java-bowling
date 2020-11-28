package bowling.controller;

import bowling.domain.Player;
import bowling.domain.Roll;
import bowling.view.InputView;

class ModelMapper {
    private ModelMapper() {}

    static Player getPlayer() {
        return new Player(InputView.askName()
                .getName()
        );
    }

    static Roll getRoll(int frameNo) {
        return Roll.of(InputView.askCountOfPins(frameNo)
                .getCountOfPins());
    }
}

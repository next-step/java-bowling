package bowling.controller;

import bowling.domain.CountOfPins;
import bowling.domain.Player;
import bowling.view.InputView;

class ModelMapper {
    private ModelMapper() {}

    static Player getPlayer() {
        return new Player(InputView.askName()
                .getName());
    }

    static CountOfPins getCountOfPins(int frameNo) {
        return CountOfPins.of(InputView.askCountOfPins(frameNo)
                .getCountOfPins());
    }

}

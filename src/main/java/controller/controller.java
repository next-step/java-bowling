package controller;

import dto.FalledPinsDTO;
import dto.NameDTO;
import view.InputView;
import view.ResultView;

public class controller {

    private static final InputView inputView = new InputView();
    private static final ResultView resultView = new ResultView();

    public static void main(String[] args) {
        NameDTO nameDTO = inputView.inputName();
        FalledPinsDTO falledPinsDTO = inputView.inputFalledPins(1);
    }
}

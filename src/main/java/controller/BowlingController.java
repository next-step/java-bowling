package controller;

import view.InputView;

public class BowlingController {

	private BowlingController() {

	}

	public static void start() {
		InputView.receiveUserName();
		InputView.receiveFallenBowlingPins(3);
	}


}

package bowling;

import bowling.view.InputView;

public class BowlingController {

    public static void main(String[] args) {
        String name = InputView.scanPlayer();
        System.out.println(name);

    }

    public void start() {
        String name = InputView.scanPlayer();
        System.out.println(name);
    }
}

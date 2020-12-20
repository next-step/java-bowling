package bowling;

import bowling.controller.Controller;

public class Bowling {

    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.createPlayer();
        controller.play();
    }
}

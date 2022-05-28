package bowling.controller;

import bowling.domain.game.Game;
import bowling.view.InputView;

public class BowlingController {

    public static void game() {

        // 플레이어 이름 입력
        String name = InputView.inputName();

        // 게임 준비
        Game game = Game.init(name);

        while (game.isNext()) {
            game.print();
            System.out.println();
            game.playing();
        }

        game.print();

    }

    public static void main(String[] args) {
        game();
    }
}

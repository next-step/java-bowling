package bowling.controller;

import bowling.domain.Pin;
import bowling.domain.Player;
import bowling.domain.bowl.Bowl;
import bowling.dto.FrameNumberDto;
import bowling.dto.GameDto;
import bowling.dto.PlayerDto;
import bowling.dto.PlayerStatusDto;
import bowling.exception.BadSizeOfPlayersException;
import bowling.view.View;

import java.util.LinkedHashMap;
import java.util.Map;

import static bowling.asset.Const.MAX_FRAME_NO;

class Game {
    private final Map<Player, Bowl> map = new LinkedHashMap<>();
    private int frameNumber = 1;

    Game() {
        // int sizeOfPlayers = View.askSizeOfPlayers().getSize();
        int sizeOfPlayers = 1;
        if (sizeOfPlayers < 1) {
            throw new BadSizeOfPlayersException("플레이어는 한명 이상이어야 합니다.");
        }
        for (int i = 0; i < sizeOfPlayers; i++) {
            map.put(getPlayer(), new Bowl());
        }
    }

    void play() {
        while (frameNumber <= MAX_FRAME_NO) {
            map.forEach(this::play);
            frameNumber++;
        }
    }

    private void play(Player player, Bowl bowl) {
        while (bowl.isPlayable(frameNumber)) {
            bowl.addPin(
                    // getPin(player)
                    getPin(frameNumber)
            );
            View.printGame(exportGameDto());
        }
    }

    private Player getPlayer() {
        return new Player(View.askName()
                .getName()
        );
    }

    private Pin getPin(int frameNumber) {
        return Pin.of(View.askPin(new FrameNumberDto(frameNumber))
                .getCountOfPins());
    }

    private Pin getPin(Player player) {
        return Pin.of(View.askPin(player.exportPlayerDto())
                .getCountOfPins());
    }

    private GameDto exportGameDto() {
        Map<PlayerDto, PlayerStatusDto> game = new LinkedHashMap<>();
        map.forEach((player, status) -> game.put(
                player.exportPlayerDto(),
                status.exportPlayerStatusDto()
        ));
        return new GameDto(game);
    }
}

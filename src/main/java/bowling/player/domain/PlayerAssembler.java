package bowling.player.domain;

import bowling.player.dto.PlayerDTO;

public class PlayerAssembler {

    private PlayerAssembler() {
        // block
    }

    public static PlayerDTO assemble(Player player) {
        return new PlayerDTO(player.getPlayerName());
    }
}

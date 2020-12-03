package bowling.view;

import bowling.dto.FrameNoDto;
import bowling.dto.PlayerDto;

class AskRollPrintable extends Printable {
    AskRollPrintable(FrameNoDto frameNoDto) {
        append(lineSeparator);
        append(String.format("%d프레임 투구 : ", frameNoDto.getFrameNo()));
    }

    AskRollPrintable(PlayerDto playerDto) {
        append(lineSeparator);
        append(String.format("%s's turn : ", playerDto.getName()));
    }
}

package bowling.view;

import bowling.dto.FrameNumberDto;
import bowling.dto.PlayerDto;

class AskRollPrintable extends Printable {
    AskRollPrintable(FrameNumberDto frameNumberDto) {
        append(lineSeparator);
        append(String.format("%d프레임 투구 : ", frameNumberDto.getFrameNumber()));
    }

    AskRollPrintable(PlayerDto playerDto) {
        append(lineSeparator);
        append(String.format("%s's turn : ", playerDto.getName()));
    }
}

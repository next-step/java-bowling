package bowling.view;

import bowling.dto.FrameNumberDto;
import bowling.dto.PlayerDto;

class AskPinPrintable extends Printable {
    AskPinPrintable(FrameNumberDto frameNumberDto) {
        append(lineSeparator);
        append(String.format("%d프레임 투구 : ", frameNumberDto.getFrameNumber()));
    }

    AskPinPrintable(PlayerDto playerDto) {
        append(lineSeparator);
        append(String.format("%s's turn : ", playerDto.getName()));
    }
}

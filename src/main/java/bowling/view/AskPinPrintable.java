package bowling.view;

import bowling.dto.AskPinDto;

class AskPinPrintable extends Printable {
    AskPinPrintable(AskPinDto askPinDto) {
        append(lineSeparator);
        // append(String.format("%d프레임 투구 : ", askPinDto.getFrameNumber()));
        append(String.format("%s's turn : ", askPinDto.getName()));
    }
}

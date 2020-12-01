package bowling.view;

import bowling.dto.FrameNoDto;

class AskRollPrintable extends Printable {
    AskRollPrintable(FrameNoDto frameNoDto) {
        append(lineSeparator);
        append(String.format("%d프레임 투구 : ", frameNoDto.getFrameNo()));
    }
}

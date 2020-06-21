package bowling.domain.dto;

import bowling.exception.message.ErrorMessage;
import bowling.util.StringUtil;

public class FrameResult {

    private final String desc;

    private FrameResult(final String desc) {
        validate(desc);
        this.desc = desc;
    }

    private void validate(final String desc) {
        if (StringUtil.isEmpty(desc)) {
            throw new IllegalArgumentException(ErrorMessage.IS_NULL_OR_EMPTY);
        }
    }

    public static FrameResult of(final String desc) {
        return new FrameResult(desc);
    }

    public String getDesc() {
        return desc;
    }
}

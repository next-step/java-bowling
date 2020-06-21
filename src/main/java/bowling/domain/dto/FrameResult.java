package bowling.domain.dto;

import bowling.exception.message.ErrorMessage;
import bowling.util.StringUtil;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FrameResult)) return false;
        FrameResult that = (FrameResult) o;
        return Objects.equals(desc, that.desc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(desc);
    }
}

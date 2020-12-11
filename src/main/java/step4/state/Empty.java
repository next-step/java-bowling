package step4.state;

import step4.domain.BowlingPoint;
import step4.domain.dto.PointDTO;
import step4.type.ResultPitchesType;

import java.util.Objects;

public class Empty implements Symbol{
    public static ResultPitchesType type = ResultPitchesType.NONE;
    public static final String NO_MARK = "";
    private static final BowlingPoint point = BowlingPoint.EMPTY_BOWLING_POINT;

    public Empty(int i) { }

    @Override
    public String getSymbol() {
        return NO_MARK;
    }

    @Override
    public int getPoint() {
        return point.getPoint();
    }

    @Override
    public ResultPitchesType getType() {
        return type;
    }

    public static boolean supported(PointDTO dto) {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empty emptySymbol = (Empty) o;
        return emptySymbol.getPoint() == Empty.point.getPoint() &&
                Objects.equals(point.getPoint(), emptySymbol.getPoint());
    }

    @Override
    public int hashCode() {
        return Objects.hash(point);
    }
}

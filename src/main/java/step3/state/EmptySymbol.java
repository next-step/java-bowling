package step3.state;

import step3.domain.dto.PointDTO;

import java.util.Objects;

public class EmptySymbol implements Symbol{
    public static final String NO_MARK = "";
    private static final int point = -1;

    public EmptySymbol(int i) { }

    @Override
    public String getSymbol() {
        return NO_MARK;
    }

    @Override
    public int getPoint() {
        return point;
    }

    public static boolean supported(PointDTO dto) {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmptySymbol emptySymbol = (EmptySymbol) o;
        return emptySymbol.getPoint() == EmptySymbol.point &&
                Objects.equals(point, emptySymbol.getPoint());
    }

    @Override
    public int hashCode() {
        return Objects.hash(point);
    }
}

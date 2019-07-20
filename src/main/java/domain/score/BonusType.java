package domain.score;

import java.util.Objects;

public class BonusType {

    private final static int STRIKE_TYPE = 2;
    private final static int SPARE_TYPE = 1;
    private final static int NORMAL_TYPE = 0;
    private final static int LIMIT = 0;

    private int type;

    private BonusType(int type) {
        this.type = type;
    }

    public static BonusType normal() {
        return new BonusType(NORMAL_TYPE);
    }

    public static BonusType strike() {
        return new BonusType(STRIKE_TYPE);
    }

    public static BonusType spare() {
        return new BonusType(SPARE_TYPE);
    }

    BonusType next() {
        if (hasBonus()) {
            return new BonusType(decrease());
        }
        return new BonusType(NORMAL_TYPE);
    }

    public boolean hasBonus() {
        return type != LIMIT;
    }

    private int decrease() {
        return type - 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BonusType type1 = (BonusType) o;
        return type == type1.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }

    @Override
    public String toString() {
        return "BonusType{" +
                "type=" + type +
                '}';
    }
}

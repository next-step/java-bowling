package bowling.domain.frame.state;

import bowling.domain.pin.Pins;

public class Gutter implements State {
    private final Pins first;
    private final Pins second;

    Gutter(final Pins first, final Pins second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public State roll(final Pins knockOverPins) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isTurnOver() {
        return Boolean.TRUE;
    }

    @Override
    public String toResult() {
        if (GutterExpression.isFirstGutter(first, second)) {
            return GutterExpression.LEFT.toExpression(second);
        }

        if (GutterExpression.isSecondGutter(first, second)) {
            return GutterExpression.RIGHT.toExpression(first);
        }
        return GutterExpression.BOTH.toExpression(Pins.GUTTER_PINS);
    }

    private enum GutterExpression {
        LEFT {
            @Override
            String toExpression(final Pins second) {
                return "-|" +  second.count();
            }
        },
        RIGHT {
            @Override
            String toExpression(final Pins first) {
                return first.count() + "|-";
            }
        },
        BOTH {
            @Override
            String toExpression(final Pins pins) {
                return "-|-";
            }
        };

        public static boolean isFirstGutter(final Pins first, final Pins second) {
            return first.isGutter() && !second.isGutter();
        }

        public static boolean isSecondGutter(final Pins first, final Pins second) {
            return !first.isGutter() && second.isGutter();
        }

        abstract String toExpression(Pins pins);
    }
}

package bowling.engine;

import java.util.Objects;

public class TestObject {
    public static final TestObject OBJ1 = new TestObject(1);
    public static final TestObject OBJ2 = new TestObject(2);

    private final int value;
    private boolean flag;

    private TestObject(final int value) {
        this.value = value;
        this.flag = true;
    }

    public void down() {
        flag = false;
    }

    public boolean isFlag() {
        return flag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestObject that = (TestObject) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "TestObject{" +
                "value=" + value +
                '}';
    }
}

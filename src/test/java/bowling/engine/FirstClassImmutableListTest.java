package bowling.engine;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class FirstClassImmutableListTest {
    static final class TestObject {
        public static final TestObject OBJ1 = new TestObject(1);
        public static final TestObject OBJ2 = new TestObject(2);

        private final int value;
        private boolean flag;

        private TestObject(final int value) {
            this.value = value;
            this.flag = true;
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

    static final class TestImmutableList extends FirstClassImmutableList<TestObject> {
        public TestImmutableList(List<TestObject> collection) {
            super(collection);
        }

        public static TestImmutableList of(TestObject ... elements) {
            return new TestImmutableList(Arrays.asList(elements));
        }

        @Override
        public boolean equals(Object o) {
            return super.equals(o);
        }
    }

    @Test
    public void create() {
        assertThat(TestImmutableList.of(TestObject.OBJ1, TestObject.OBJ2)).isEqualTo(TestImmutableList.of(TestObject.OBJ1, TestObject.OBJ2));
    }

    @Test
    public void collect() {
        assertThat(TestImmutableList.of(TestObject.OBJ1, TestObject.OBJ2).collect()).containsExactly(TestObject.OBJ1, TestObject.OBJ2);
    }

    @Test
    public void stream() {
        assertThat(TestImmutableList.of(TestObject.OBJ1, TestObject.OBJ2).stream()).containsExactly(TestObject.OBJ1, TestObject.OBJ2);
    }

    @Test
    public void foreach() {
        TestImmutableList testCollection = TestImmutableList.of(TestObject.OBJ1, TestObject.OBJ2);
        testCollection.forEach(obj -> obj.flag = false);
        assertThat(testCollection.stream().filter(obj -> obj.flag).findAny()).isEmpty();
    }

    @Test
    public void size() {
        assertThat(TestImmutableList.of(TestObject.OBJ1, TestObject.OBJ2).size()).isEqualTo(2);
    }

    @Test
    public void elementOf() {
        assertThat(TestImmutableList.of(TestObject.OBJ1, TestObject.OBJ2).elementOf(0)).isEqualTo(TestObject.OBJ1);
    }

    @ParameterizedTest(name = "get failed: {arguments}")
    @ValueSource(ints = {-1, 5})
    public void elementOfFailed(int index) {
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> TestImmutableList.of(TestObject.OBJ1, TestObject.OBJ2).elementOf(index));
    }

    @Test
    public void indexOf() {
        assertThat(TestImmutableList.of(TestObject.OBJ1, TestObject.OBJ2).indexOf(TestObject.OBJ1)).isEqualTo(0);
    }

    @Test
    public void append() {
        final FirstClassList<TestObject> testList = TestImmutableList.of(TestObject.OBJ1);
        assertThat(testList.append(TestObject.OBJ2).size()).isEqualTo(2);
        assertThat(testList.append(TestObject.OBJ2).collect()).containsExactly(TestObject.OBJ1, TestObject.OBJ2);
        assertThat(testList.append(TestObject.OBJ2)).isEqualTo(TestImmutableList.of(TestObject.OBJ1, TestObject.OBJ2));
    }
}


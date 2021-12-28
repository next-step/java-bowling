package bowling.engine;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import bowling.engine.collection.FirstClassImmutableList;
import bowling.engine.collection.FirstClassList;
import bowling.engine.collection.FirstClassMutableList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class FirstClassMutableListTest {
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

    static final class TestMutableList extends FirstClassMutableList<TestObject> {
        public TestMutableList(List<TestObject> collection) {
            super(collection);
        }

        public static TestMutableList of(TestObject ... elements) {
            return new TestMutableList(Arrays.asList(elements));
        }
    }

    @Test
    public void create() {
        assertThat(TestMutableList.of(TestObject.OBJ1, TestObject.OBJ2)).isEqualTo(TestMutableList.of(TestObject.OBJ1, TestObject.OBJ2));
    }

    @Test
    public void collect() {
        assertThat(TestMutableList.of(TestObject.OBJ1, TestObject.OBJ2).collect()).containsExactly(TestObject.OBJ1, TestObject.OBJ2);
    }

    @Test
    public void stream() {
        assertThat(TestMutableList.of(TestObject.OBJ1, TestObject.OBJ2).stream()).containsExactly(TestObject.OBJ1, TestObject.OBJ2);
    }

    @Test
    public void foreach() {
        TestMutableList testCollection = TestMutableList.of(TestObject.OBJ1, TestObject.OBJ2);
        testCollection.forEach(obj -> obj.flag = false);
        assertThat(testCollection.stream().filter(obj -> obj.flag).findAny()).isEmpty();
    }

    @Test
    public void size() {
        assertThat(TestMutableList.of(TestObject.OBJ1, TestObject.OBJ2).size()).isEqualTo(2);
    }

    @Test
    public void elementOf() {
        assertThat(TestMutableList.of(TestObject.OBJ1, TestObject.OBJ2).elementOf(0)).isEqualTo(TestObject.OBJ1);
    }

    @ParameterizedTest(name = "get failed: {arguments}")
    @ValueSource(ints = {-1, 5})
    public void elementOfFailed(int index) {
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> TestMutableList.of(TestObject.OBJ1, TestObject.OBJ2).elementOf(index));
    }

    @Test
    public void elementOfOptional() {
        final TestMutableList testList = TestMutableList.of(TestObject.OBJ1, TestObject.OBJ2);
        assertThat(testList.elementOfOptional(0)).isPresent();
        assertThat(testList.elementOfOptional(0)).contains(TestObject.OBJ1);
    }

    @ParameterizedTest(name = "get failed: {arguments}")
    @ValueSource(ints = {-1, 5})
    public void elementOfFailedOptional(int index) {
        final TestMutableList testList = TestMutableList.of(TestObject.OBJ1, TestObject.OBJ2);
        assertThat(testList.elementOfOptional(index)).isEmpty();
    }

    @Test
    public void indexOf() {
        assertThat(TestMutableList.of(TestObject.OBJ1, TestObject.OBJ2).indexOf(TestObject.OBJ1)).isEqualTo(0);
    }

    @Test
    public void append() {
        final FirstClassList<TestObject> testList = TestMutableList.of(TestObject.OBJ1);
        final FirstClassList<TestObject> appended = testList.append(TestObject.OBJ2);
        assertThat(appended.size()).isEqualTo(2);
        assertThat(appended.collect()).containsExactly(TestObject.OBJ1, TestObject.OBJ2);
        assertThat(appended).isEqualTo(TestMutableList.of(TestObject.OBJ1, TestObject.OBJ2));

        assertThat(testList).isEqualTo(TestMutableList.of(TestObject.OBJ1, TestObject.OBJ2));
        assertThat(testList).isEqualTo(appended);
        assertThat(testList == appended).isTrue();
    }
}


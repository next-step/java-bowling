package qna.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FirstClassListTest {
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

    static final class TestCollection extends FirstClassCollection<TestObject> {
        public TestCollection(List<TestObject> collection) {
            super(collection);
        }

        public static TestCollection of(TestObject ... elements) {
            return new TestCollection(Arrays.asList(elements));
        }
    }

    @Test
    public void create() {
        assertThat(TestCollection.of(TestObject.OBJ1, TestObject.OBJ2)).isEqualTo(TestCollection.of(TestObject.OBJ1, TestObject.OBJ2));
    }

    @Test
    public void collect() {
        assertThat(TestCollection.of(TestObject.OBJ1, TestObject.OBJ2).collect()).containsExactly(TestObject.OBJ1, TestObject.OBJ2);
    }

    @Test
    public void stream() {
        assertThat(TestCollection.of(TestObject.OBJ1, TestObject.OBJ2).stream()).containsExactly(TestObject.OBJ1, TestObject.OBJ2);
    }

    @Test
    public void foreach() {
        TestCollection testCollection = TestCollection.of(TestObject.OBJ1, TestObject.OBJ2);
        testCollection.forEach(obj -> obj.flag = false);
        assertThat(testCollection.stream().filter(obj -> obj.flag).findAny()).isEmpty();
    }

    @Test
    public void size() {
        assertThat(TestCollection.of(TestObject.OBJ1, TestObject.OBJ2).size()).isEqualTo(2);
    }
}

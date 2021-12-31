package bowling.engine;

import java.util.Arrays;
import java.util.List;

import bowling.engine.collection.FirstClassImmutableList;
import bowling.engine.collection.FirstClassList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class FirstClassImmutableListTest {
    static final class TestImmutableList extends FirstClassImmutableList<TestObject> {
        public TestImmutableList(List<TestObject> collection) {
            super(collection);
        }

        public static TestImmutableList of(TestObject ... elements) {
            return new TestImmutableList(Arrays.asList(elements));
        }
    }

    @Test
    public void append() {
        final FirstClassList<TestObject> testList = TestImmutableList.of(TestObject.OBJ1);
        assertThat(testList.append(TestObject.OBJ2).size()).isEqualTo(2);
        assertThat(testList.append(TestObject.OBJ2).collect()).containsExactly(TestObject.OBJ1, TestObject.OBJ2);
        assertThat(testList.append(TestObject.OBJ2)).isEqualTo(TestImmutableList.of(TestObject.OBJ1, TestObject.OBJ2));
    }

    @ParameterizedTest(name = "append failed: {arguments}")
    @NullSource
    public void appendFailed(TestObject obj) {
        final FirstClassList<TestObject> testList = TestImmutableList.of(TestObject.OBJ1);
        assertThatIllegalArgumentException().isThrownBy(() -> testList.append(obj));
    }
}


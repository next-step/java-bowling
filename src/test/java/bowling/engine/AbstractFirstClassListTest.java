package bowling.engine;

import java.util.Arrays;
import java.util.List;

import bowling.engine.collection.AbstractFirstClassList;
import bowling.engine.collection.FirstClassList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

public class AbstractFirstClassListTest {
    static final class TestList extends AbstractFirstClassList<TestObject> {
        public TestList(List<TestObject> collection) {
            super(collection);
        }

        public static TestList of(TestObject... elements) {
            return new TestList(Arrays.asList(elements));
        }

        @Override
        public FirstClassList<TestObject> append(TestObject testObject) {
            throw new UnsupportedOperationException("this class is for test");
        }
    }

    @Test
    public void create() {
        assertThat(TestList.of(TestObject.OBJ1, TestObject.OBJ2)).isEqualTo(TestList.of(TestObject.OBJ1, TestObject.OBJ2));
    }

    @Test
    public void collect() {
        assertThat(TestList.of(TestObject.OBJ1, TestObject.OBJ2).collect()).containsExactly(TestObject.OBJ1, TestObject.OBJ2);
    }

    @Test
    public void stream() {
        assertThat(TestList.of(TestObject.OBJ1, TestObject.OBJ2).stream()).containsExactly(TestObject.OBJ1, TestObject.OBJ2);
    }

    @Test
    public void foreach() {
        TestList testCollection = TestList.of(TestObject.OBJ1, TestObject.OBJ2);
        testCollection.forEach(TestObject::down);
        assertThat(testCollection.stream().filter(TestObject::isFlag).findAny()).isEmpty();
    }

    @Test
    public void size() {
        assertThat(TestList.of(TestObject.OBJ1, TestObject.OBJ2).size()).isEqualTo(2);
    }

    @Test
    public void elementOf() {
        assertThat(TestList.of(TestObject.OBJ1, TestObject.OBJ2).elementOf(0)).isEqualTo(TestObject.OBJ1);
    }

    @ParameterizedTest(name = "get failed: {arguments}")
    @ValueSource(ints = {-1, 5})
    public void elementOfFailed(int index) {
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> TestList.of(TestObject.OBJ1, TestObject.OBJ2).elementOf(index));
    }

    @Test
    public void elementOfOptional() {
        final TestList testList = TestList.of(TestObject.OBJ1, TestObject.OBJ2);
        assertThat(testList.elementOfOptional(0)).isPresent();
        assertThat(testList.elementOfOptional(0)).contains(TestObject.OBJ1);
    }

    @ParameterizedTest(name = "get failed: {arguments}")
    @ValueSource(ints = {-1, 5})
    public void elementOfFailedOptional(int index) {
        final TestList testList = TestList.of(TestObject.OBJ1, TestObject.OBJ2);
        assertThat(testList.elementOfOptional(index)).isEmpty();
    }

    @Test
    public void indexOf() {
        assertThat(TestList.of(TestObject.OBJ1, TestObject.OBJ2).indexOf(TestObject.OBJ1)).isEqualTo(0);
    }

    @Test
    public void headAndLast() {
        assertThat(TestList.of(TestObject.OBJ1, TestObject.OBJ2).head()).isEqualTo(TestObject.OBJ1);
        assertThat(TestList.of(TestObject.OBJ1, TestObject.OBJ2).last()).isEqualTo(TestObject.OBJ2);
    }

    @Test
    public void lastOptional() {
        assertThat(TestList.of(TestObject.OBJ1, TestObject.OBJ2).lastOptional()).isPresent();
        assertThat(TestList.of(TestObject.OBJ1, TestObject.OBJ2).lastOptional()).contains(TestObject.OBJ2);
        assertThat(TestList.of().lastOptional()).isEmpty();
    }

    @Test
    public void nextOfRing() {
        assertThat(TestList.of(TestObject.OBJ1, TestObject.OBJ2).nextOfRing(TestObject.OBJ1)).isEqualTo(TestObject.OBJ2);
        assertThat(TestList.of(TestObject.OBJ1, TestObject.OBJ2).nextOfRing(TestObject.OBJ2)).isEqualTo(TestObject.OBJ1);
    }

    @Test
    public void nextOfRingFailed() {
        assertThatIllegalStateException().isThrownBy(() -> TestList.of(TestObject.OBJ1).nextOfRing(TestObject.OBJ2));
        assertThatIllegalStateException().isThrownBy(() -> TestList.of().nextOfRing(TestObject.OBJ2));
    }

    @Test
    public void notEmpty() {
        assertThat(TestList.of().notEmpty()).isFalse();
        assertThat(TestList.of(TestObject.OBJ1).notEmpty()).isTrue();
    }
}

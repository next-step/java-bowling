package qna.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class AnswersTest {
    @Test
    public void constructor_ShouldThrow_WhenNull() {
        assertThatNullPointerException().isThrownBy(() -> new Answers(null));
    }

    @Test
    public void add_get() {
        Answer answer = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        Answers answers = new Answers();
        assertThat(answers.add(answer).get(0)).isEqualTo(answer);
        assertThat(answers.size()).isEqualTo(1);
    }

    @Test
    public void add_ShouldThrow_WhenNull() {
        assertThatNullPointerException().isThrownBy(() -> new Answers().add(null));
    }

    @Test
    public void get_ShouldThrow_WhenOutOfBounds() {
        Answers answers = new Answers();
        Answer answer = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        assertThatThrownBy(() -> answers.add(answer).get(2)).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    public void deleteByUser_isAllDeleted() throws CannotDeleteException {
        Answer answer1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        Answer answer2 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents2");
        Answer answer3 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents3");
        Answers answers = new Answers()
                .add(answer1)
                .add(answer2)
                .add(answer3);

        List<DeleteHistory> deleteHistories = answers.deleteByUser(UserTest.JAVAJIGI);
        assertThat(deleteHistories)
                .contains(new DeleteHistory(ContentType.ANSWER, answer1.getId(), UserTest.JAVAJIGI, LocalDateTime.now()))
                .contains(new DeleteHistory(ContentType.ANSWER, answer2.getId(), UserTest.JAVAJIGI, LocalDateTime.now()))
                .contains(new DeleteHistory(ContentType.ANSWER, answer3.getId(), UserTest.JAVAJIGI, LocalDateTime.now()));
        assertThat(answers.isAllDeleted()).isTrue();
        assertThatFullyDeleted(answers);
    }

    @Test
    public void deleteByUser_ShouldThrow_WhenNotOwner() {
        Answer answer1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        Answer answer2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
        Answer answer3 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents3");
        Answers answers = new Answers()
                .add(answer1)
                .add(answer2)
                .add(answer3);

        assertThatThrownBy(() -> answers.deleteByUser(UserTest.JAVAJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void deleteByUser_ShouldThrow_WhenNull() {
        assertThatNullPointerException().isThrownBy(() -> new Answers().deleteByUser(null));
    }

    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 3, 4, 5 })
    public void size(int size) {
        Answers answers = new Answers();
        for (int i = 0; i < size; i++) {
            answers.add(new Answer());
        }
        assertThat(answers.size()).isEqualTo(size);
    }

    private static void assertThatFullyDeleted(Answers answers) {
        for (int i = 0; i < answers.size(); i++) {
            assertThat(answers.get(i).isDeleted()).isTrue();
        }
    }
}

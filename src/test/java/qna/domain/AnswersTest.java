package qna.domain;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class AnswersTest {
    public static Answers ANSWERS_A1A2 = Answers.of(List.of(AnswerTest.A1, AnswerTest.A2));
    public static Answers ANSWERS_A1 = Answers.of(List.of(AnswerTest.A1));

    @Test
    public void create() {
        assertThat(Answers.of(List.of(AnswerTest.A1, AnswerTest.A2))).isEqualTo(Answers.of(List.of(AnswerTest.A1, AnswerTest.A2)));
    }

    @ParameterizedTest(name = "create failed: {arguments}")
    @NullSource
    public void createFailed(List<Answer> answers) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Answers.of(answers))
                .withMessageContaining("cannot be null");
    }

    @Test
    public void collect() {
        assertThat(ANSWERS_A1A2.collect()).containsExactly(AnswerTest.A1, AnswerTest.A2);
    }

    @Test
    public void append() {
        assertThat(ANSWERS_A1.append(AnswerTest.A2)).isEqualTo(ANSWERS_A1A2);
    }

    @ParameterizedTest(name = "append failed: {arguments}")
    @NullSource
    public void appendFailed(Answer answer) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> ANSWERS_A1.append(answer))
                .withMessageContaining("cannot be null");
    }

    @Test
    public void deletable() {
        assertThat(ANSWERS_A1.deletable(AnswerTest.A1.getWriter())).isTrue();
    }

    @Test
    public void deletableFailed() {
        assertThat(ANSWERS_A1A2.deletable(AnswerTest.A1.getWriter())).isFalse();
    }

    @Test
    public void delete() throws CannotDeleteException {
        Answer testAnswer = new Answer(101L, UserTest.JAVAJIGI, QuestionTest.Q1, "c1");
        Answers answers = Answers.of(List.of(testAnswer));
        List<DeleteHistory> histories = answers.delete(UserTest.JAVAJIGI).collect();
        assertThat(answers.collect().stream().map(Answer::isDeleted).reduce(true, (a, b) -> a & b)).isTrue();
        assertThat(histories.stream().map(DeleteHistory::contentId)).containsExactly(testAnswer.getId());
    }
}

package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import qna.CannotDeleteException;

public class AnswersTest {

    private Answer answer;
    private Answer otherAnswer;

    @BeforeEach
    void setUp() {
        answer = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        otherAnswer = new Answer(UserTest.SANJIGI, QuestionTest.Q2, "Answers Contents2");
    }

    @DisplayName("Answers 생성 테스트")
    @Test
    public void generateAnswersTest() {
        assertThatCode(() -> Answers.create(AnswerTest.A1, AnswerTest.A2)).doesNotThrowAnyException();
    }

    @DisplayName("Answers 생성 후 반환 테스트")
    @Test
    public void getAnswers() {
        List<Answer> answerList = Arrays.asList(answer, otherAnswer);

        assertThat(new Answers(answerList).getAnswers())
            .containsExactly(answer, otherAnswer);
    }

    @DisplayName("Answers 전체 삭제 테스트")
    @Test
    public void deleteAnswersTest() {
        Answers answers = Answers.create(AnswerTest.A1, AnswerTest.A1);
        assertThatCode(() -> answers.deleteAll(UserTest.JAVAJIGI)).doesNotThrowAnyException();
    }

    @DisplayName("Answers 전체 삭제 테스트 - 삭제 불가")
    @Test
    public void cannotDeleteAnswersTest() {
        Answers cannotDeleteAnswers = Answers.create(AnswerTest.A1, AnswerTest.A2);
        assertThatThrownBy(() -> cannotDeleteAnswers.deleteAll(UserTest.JAVAJIGI))
            .isInstanceOf(CannotDeleteException.class);
    }
}
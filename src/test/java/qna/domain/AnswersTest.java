package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

class AnswersTest {

    private Answer answer1;
    private Answer answer2;

    @BeforeEach
    void beforeEach() {
        answer1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        answer2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    }

    @Test
    @DisplayName("답변을 모두 삭제할 수 있다.")
    void deleteAnswersTest() {

        // given
        List<Answer> inputs = Arrays.asList(answer1);
        Answers answers = new Answers(inputs);
        User writer = UserTest.JAVAJIGI;
        List<DeleteHistory> expected = Arrays.asList(new DeleteHistory(answer1));

        // when
        List<DeleteHistory> results = answers.deleteAll(writer);

        // then
        assertThat(results).isEqualTo(expected);
    }

    @Test
    @DisplayName("답변 중 작성자와 일치하지 않는 답변이 있어 Exception이 발생해야 한다.")
    void deleteAnswersFailTest() {

        // given
        List<Answer> inputs = Arrays.asList(answer1, answer2);
        Answers answers = new Answers(inputs);
        User writer = UserTest.JAVAJIGI;

        // then
        assertThatExceptionOfType(CannotDeleteException.class)
            .isThrownBy(() -> answers.deleteAll(writer))
            .withMessageMatching("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

}
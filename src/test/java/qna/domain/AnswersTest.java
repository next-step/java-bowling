package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswersTest {

    private Question question;
    private Answer answer;

    @BeforeEach
    public void setUp() throws Exception {
        question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
        answer = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        question.addAnswer(answer);
    }

    @DisplayName("답변이 잘 삭제 되는지 확인")
    @Test
    public void deleteAllTest() {
        // when
        question.getAnswers().deleteAll();

        // then
        assertThat(answer.isDeleted()).isTrue();
    }

    @DisplayName("질문자와 다른 답변자가 없는지 확인")
    @Test
    public void containOthersFalseTest() {
        // when
        boolean result = question.getAnswers().containOthers(UserTest.JAVAJIGI);

        // then
        assertThat(result).isFalse();
    }

    @DisplayName("질문자와 다른 답변자가 있는지 확인")
    @Test
    public void containOthersTrueTest() {
        // given
        Answer other = new Answer(11L, UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents1");
        question.addAnswer(other);

        // when
        boolean result = question.getAnswers().containOthers(UserTest.JAVAJIGI);

        // then
        assertThat(result).isTrue();
    }
}

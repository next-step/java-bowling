package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswersTest {
    @DisplayName("답변이 잘 삭제 되는지 확인")
    @Test
    public void deleteAllTest() {
        // given
        Answer answer = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        Answers answers = new Answers(Arrays.asList(answer));

        // when
        answers.delete();

        // then
        assertThat(answer.isDeleted()).isTrue();
    }

    @DisplayName("질문자와 다른 답변자가 없는지 확인")
    @Test
    public void containOthersFalseTest() {
        // given
        Answer answer = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        Answers answers = new Answers(Arrays.asList(answer));

        // when
        boolean result = answers.containOthers(UserTest.JAVAJIGI);

        // then
        assertThat(result).isFalse();
    }

    @DisplayName("질문자와 다른 답변자가 있는지 확인")
    @Test
    public void containOthersTrueTest() {
        // given
        Answer answer = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        Answer other = new Answer(11L, UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents1");
        Answers answers = new Answers(Arrays.asList(answer, other));

        // when
        boolean result = answers.containOthers(UserTest.JAVAJIGI);

        // then
        assertThat(result).isTrue();
    }
}

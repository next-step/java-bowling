package qna.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import qna.CannotDeleteException;

public class AnswersTest {

    @Test
    void 질문자_모든_답자_다름_유효성_테스트() {
        Answer answer1 = new Answer(1L, UserTest.JAVAJIGI, QuestionTest.Q1, "contents1");
        Answer answer2 = new Answer(2L, UserTest.SANJIGI, QuestionTest.Q1, "contents2");
        Answers answers = new Answers(Arrays.asList(answer1, answer2));
        assertThatThrownBy(() -> answers.deleteAll(UserTest.JAVAJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void 삭제_테스트() throws CannotDeleteException {
        Answer answer1 = new Answer(1L, UserTest.JAVAJIGI, QuestionTest.Q1, "contents1");
        Answer answer2 = new Answer(2L, UserTest.JAVAJIGI, QuestionTest.Q1, "contents2");
        Answers answers = new Answers(Arrays.asList(answer1, answer2));

        answers.deleteAll(UserTest.JAVAJIGI);
        answers.answers().forEach(answer -> {
            assertThat(answer.isDeleted()).isTrue();
        });
    }
}

package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;
import qna.ForbiddenException;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {

    @DisplayName("로그인 유저와 질문자와 답변자가 모두 같으면 답변 삭제 상태")
    @Test
    public void delete_success_test() {
        List<Answer> answerList = Arrays.asList(AnswerTest.A1, AnswerTest.A3);
        Answers answers = new Answers(answerList);
        answers.delete(UserTest.JAVAJIGI);
        assertThat(answers.isDeleted()).isEqualTo(true);
    }


    @DisplayName("로그인 유저와 질문자와 답변자가 다르면 에러")
    @Test
    public void delete_answers_error_test() {
        List<Answer> answerList = Arrays.asList(AnswerTest.A1, AnswerTest.A2);
        Answers answers = new Answers(answerList);
        assertThatThrownBy(() -> answers.delete(UserTest.JAVAJIGI)).isInstanceOf(ForbiddenException.class);
    }
}

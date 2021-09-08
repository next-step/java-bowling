package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswersTest {

    @DisplayName("로그인 유저와 질문자와 답변자가 모두 같으면 답변 삭제 상태")
    @Test
    public void delete_success_test(){
        List<Answer> answerList = Arrays.asList(AnswerTest.A1, AnswerTest.A3);
        Answers answers = new Answers(answerList);
        assertThat(answers.delete(UserTest.JAVAJIGI).isDeleted()).isEqualTo(true);
    }
}

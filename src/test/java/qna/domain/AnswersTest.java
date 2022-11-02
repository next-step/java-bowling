package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;

class AnswersTest {

    @Test
    @DisplayName("기본생성자가 정상적으로 작동되어지는지에 대한 테스트")
    void 기본생성자_테스트() {
        Answers answers = new Answers();
        assertThat(answers).isNotNull();
    }

    @Test
    @DisplayName("기본생성자로 List를 넣었을 때 정상적으로 작동되어지는지에 대한 테스트")
    void 기본생성자_테스트_List값() {
        Answers answers = new Answers(new ArrayList<>());
        assertThat(answers).isNotNull();
    }

    @Test
    @DisplayName("삭제가 정상적으로 작동되어지는지에 대한 테스트")
    void Delete메소드_테스트() throws CannotDeleteException {
        Answers answers = new Answers();
        Answer answer = AnswerTest.A1;
        answers.add(answer);
        assertThat(answers.delete(UserTest.JAVAJIGI)).isInstanceOf(DeleteHistories.class);
        assertThat(answer.isDeleted()).isEqualTo(true);
    }

}
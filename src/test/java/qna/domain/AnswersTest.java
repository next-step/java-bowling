package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AnswersTest {

    @Test
    public void delete_실패_유저와_답변쓴이_다름() {
        // given
        User loginUser = UserTest.JAVAJIGI;

        List<Answer> answerList = new ArrayList<Answer>() {{
            add(AnswerTest.A1);
            add(AnswerTest.A2);
        }};
        Answers answers = new Answers(answerList);

        // then
        assertThatThrownBy(() -> {
            answers.delete(loginUser);
        })
        .isInstanceOf(CannotDeleteException.class)
        .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    public void getAnswers_복사된_list가_return_됨() {
        // given
        List<Answer> answerList = new ArrayList<>();
        answerList.add(AnswerTest.A1);
        Answers answers = new Answers(answerList);

        // when
        List<Answer> returnedAnswers = answers.getAnswers();

        // then
        assertThat(returnedAnswers == answerList).isFalse();
    }

}

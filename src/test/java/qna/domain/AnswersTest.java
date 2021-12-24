package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {

    @Test
    @DisplayName("add 테스트 - 정상 동작")
    void add() {
        Answers answers = new Answers(AnswerTest.A1);
        answers.add(AnswerTest.A2);

        List<Answer> answerList = answers.getAnswers();

        assertThat(AnswerTest.A1).isEqualTo(answerList.get(0));
        assertThat(AnswerTest.A2).isEqualTo(answerList.get(1));
    }

    @Test
    @DisplayName("deleteAll 테스트 - 모든 answers가 loginUser가 작성한 경우 삭제 완료")
    void deleteAll() {
        Answer answer1 = AnswerTest.copy(AnswerTest.A1);
        Answer answer2 = AnswerTest.copy(AnswerTest.A1);
        Answer answer3 = AnswerTest.copy(AnswerTest.A1);

        Answers answers = new Answers(answer1, answer2, answer3);
        List<DeleteHistory> deleteHistories = answers.deleteAll(AnswerTest.A1.getWriter());

        assertThat(answers.getAnswers().get(0).isDeleted()).isTrue();
        assertThat(answers.getAnswers().get(1).isDeleted()).isTrue();
        assertThat(answers.getAnswers().get(2).isDeleted()).isTrue();
        assertThat(deleteHistories.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("deleteAll 테스트 - 모든 answers가 loginUser가 작성한 것이 아닌 경우 CannotDeleteException 발생")
    void deleteAll_실패() {
        Answer answer1 = AnswerTest.copy(AnswerTest.A1);
        Answer answer2 = AnswerTest.copy(AnswerTest.A1);
        Answer answer3 = AnswerTest.copy(AnswerTest.A2);

        Answers answers = new Answers(answer1, answer2, answer3);

        assertThatThrownBy(() -> answers.deleteAll(answer1.getWriter()))
                .isInstanceOf(CannotDeleteException.class);
    }

}

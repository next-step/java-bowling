package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Created : 2020-12-14 오후 12:18
 * Developer : Seo
 */
class AnswersTest {
    private List<Answer> answerList;
    private Answers answers;
    private Answer answer;

    @BeforeEach
    void setUp() {
        answerList = new ArrayList<>();
        answer = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        answerList.add(answer);
        answers = new Answers(answerList);
    }

    @Test
    void constructAndAdd() {
        assertThat(answer).isNotNull();
        assertThat(answers.size()).isEqualTo(1);

        Answer answer2 = new Answer(11L, UserTest.SANJIGI, QuestionTest.Q2, "Answers Contents2");
        answers.add(answer2);
        assertThat(answers.size()).isEqualTo(2);
    }

    @Test
    void givenAnotherUser_thenThrowException() {
        assertThatThrownBy(() -> answers.isOwner(UserTest.SANJIGI))
                .withFailMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.")
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void answersDelete() {
        List<DeleteHistory> deleteHistories = new ArrayList<>(answers.delete());
        assertThat(deleteHistories).hasSize(1);
    }
}
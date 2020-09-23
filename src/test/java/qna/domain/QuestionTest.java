package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    void checkOwner_작성자가_다를때(){
        Question question = Q1;

        assertThatThrownBy(()->{
            question.delete(Q2.getWriter());
        }).isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("권한이 없습니다");
    }

    @Test
    void checkAnswerOwner_작성자와_질문자가_다를때(){
        Question question = Q1;
        Q1.addAnswer(AnswerTest.A2);

        assertThatThrownBy(()->{
            question.delete(Q1.getWriter());

        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void recordDeleteHistory_삭제기록테스트(){
        Question question = Q2;
        Q2.addAnswer(AnswerTest.A2);
        Q2.addAnswer(AnswerTest.A2);

        List<DeleteHistory> deleteHistories = Q2.recordForDeletedQnAHistory(1);

        assertThat(deleteHistories.size()).isEqualTo(3);

        DeleteHistory del1= deleteHistories.get(0);
        DeleteHistory del2= deleteHistories.get(1);
        DeleteHistory del3= deleteHistories.get(2);

        assertThat(del1.equals(del2)).isFalse();
        assertThat(del2.equals(del3)).isTrue();

    }
}

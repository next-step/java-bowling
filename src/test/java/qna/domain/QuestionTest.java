package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("Question 에 등록된 Answers 들의 Owner 가 존재하는지 테스트")
    @Test
    void isAnswersHaveOwner(){
        Q1.addAnswer2(AnswerTest.A1);
        Q1.addAnswer2(AnswerTest.A2);

        assertThat(Q1.isAnswerHaveOwner(UserTest.JAVAJIGI)).isTrue();
    }

    @DisplayName("Question 에 등록된 Answers 들의 Owner 가 존재하지 않을 때 테스트")
    @Test
    void isNotAnswersHaveOwner(){
        Q1.addAnswer2(AnswerTest.A1);

        assertThat(Q1.isAnswerHaveOwner(UserTest.SANJIGI)).isFalse();
    }

    @DisplayName("Question 에 등록된 Answers 들이 Deleted 상태 인지 테스트")
    @Test
    void setDeleted(){
        Q1.addAnswer2(AnswerTest.A1);
        Q1.addAnswer2(AnswerTest.A2);

        Q1.setDeleted(true);

        assertThat(AnswerTest.A1.isDeleted()).isTrue();
    }

    @DisplayName("getDeleted 테스트")
    @Test
    void getDeleted(){
        Q1.addAnswer2(AnswerTest.A1);
        Q1.addAnswer2(AnswerTest.A2);

        Q1.setDeleted(true);

        List<DeleteHistory> deleteHistories = Q1.getDeleted();

        assertThat(deleteHistories).isEqualTo(Arrays.asList(new DeleteHistory(ContentType.ANSWER, AnswerTest.A1.getId(), AnswerTest.A1.getWriter(), LocalDateTime.now())
                , new DeleteHistory(ContentType.ANSWER, AnswerTest.A2.getId(), AnswerTest.A2.getWriter(), LocalDateTime.now())
                , new DeleteHistory(ContentType.QUESTION, Q1.getId(), Q1.getWriter(), LocalDateTime.now())
        ));
    }

    @DisplayName("setDeleted false 였을 때 테스트")
    @Test
    void getEmptyDeleted(){
        Q1.addAnswer2(AnswerTest.A1);
        Q1.addAnswer2(AnswerTest.A2);

        Q1.setDeleted(false);

        List<DeleteHistory> deleteHistories = Q1.getDeleted();

        assertThat(deleteHistories).isEqualTo(Collections.EMPTY_LIST);
    }
}

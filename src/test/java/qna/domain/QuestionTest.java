package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = Question.of(1L,"title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = Question.of(2L,"title2", "contents2").writeBy(UserTest.SANJIGI);

    @BeforeEach
    void setUp() {
        Q1.addAnswer(AnswerTest.A1);
        Q2.addAnswer(AnswerTest.A2);
    }

    @Test
    public void delete_성공() throws Exception {
        assertThat(Q1.isDeleted()).isFalse();
        List<DeleteHistory> deleteHistories = Q1.deleted(UserTest.JAVAJIGI);
        deleteHistories.forEach(System.out::println);
        assertThat(Q1.isDeleted()).isTrue();
        assertThat(deleteHistories).hasSize(2);
    }

    @Test
    public void delete_다른_사람이_쓴_글() throws Exception {
        assertThatThrownBy(() -> {
            Q2.deleted(UserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }
}

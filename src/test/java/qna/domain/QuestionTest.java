package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @BeforeEach
    public void beforeEach() {
        Q1.addAnswer(AnswerTest.A1);
        Q2.addAnswer(AnswerTest.A1);
    }

    @Test
    public void checkIsOwner_다른사람이_쓴_글() throws Exception {
        // given

        // when

        // then
        assertThatThrownBy(() -> {
            Q1.checkIsOwner(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
        assertThatThrownBy(() -> {
            Q2.checkIsOwner(UserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void deleteQuestionAndAnswers() throws Exception {
        // given
        List<DeleteHistory> expectDeleteHistoriesQ1 = new ArrayList<>();
        expectDeleteHistoriesQ1.add(new DeleteHistory(Q1, UserTest.JAVAJIGI));
        expectDeleteHistoriesQ1.add(new DeleteHistory(AnswerTest.A1, UserTest.JAVAJIGI));

        // when
        List<DeleteHistory> resultDeleteHistoriesQ1 = Q1.deleteQuestionAndAnswers(UserTest.JAVAJIGI);

        // then
        Assertions.assertThat(resultDeleteHistoriesQ1).isEqualTo(expectDeleteHistoriesQ1);
        assertThatThrownBy(() -> {
            Q2.deleteQuestionAndAnswers(UserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }


}


package qna.domain;

import org.junit.Test;
import qna.CannotDeleteException;
import qna.ForbiddenException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.domain.Fixtures.*;

public class AnswerTest {
    @Test
    public void deleteAnswer() throws CannotDeleteException {
        //given
        Answer answer = aAnswer1();

        //when
        answer.delete(aUser1());

        //then
        assertThat(answer.isDeleted()).isTrue();
    }

    @Test
    public void deleteAnswerFail() throws CannotDeleteException {
        //given
        Answer answer = aAnswer1();

        //when, then
        assertThatThrownBy(() -> {
            answer.delete(aUser2());
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void makeDeleteHistory() throws CannotDeleteException {
        //given
        Answer answer = aAnswer1();
        answer.delete(aUser1());

        //when
        DeleteHistory deleteHistory = answer.makeDeleteHistory();

        //then
        assertThat(deleteHistory).isNotNull();
    }

    @Test
    public void makeDeleteHistoryFail() throws CannotDeleteException {
        //given
        Answer answer = aAnswer1();

        //given, when, then
        assertThatThrownBy(() -> {
            DeleteHistory deleteHistory = answer.makeDeleteHistory();
        }).isInstanceOf(ForbiddenException.class);
    }
}

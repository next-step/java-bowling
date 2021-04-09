package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class QuestionTest {

    public Question Q1;
    public Question Q2;

    @BeforeEach
    void init (){
        Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);
    }

    @DisplayName("Question을 삭제하면 데이터상태가 삭제상태인 true로 변한다.")
    @Test
    void testCase1() {
        Q1.delete(DeleteHistorys.of());

        assertThat(Q1.isDeleted()).isTrue();
    }

    @DisplayName("로그인 사용자와 질문한 사람이 같다.")
    @Test
    void testCase2() {
        assertThat(Q1.isOwner(UserTest.JAVAJIGI)).isTrue();
    }

    @DisplayName("로그인 사용자와 질문한 사람이 같지 않으면 예외가 발생한다.")
    @Test
    void testCase3() {
        assertThatThrownBy(()-> {
            Q1.isOwner(UserTest.SANJIGI);
        }).isInstanceOf(RuntimeException.class);
    }

    @DisplayName("질문이 삭제되면 답변도 삭제히스토리에 같이 저장된다.")
    @Test
    void testCase4() {
        DeleteHistorys delete = Q1.delete(DeleteHistorys.of());

        assertThat(delete.size()).isEqualTo(1);
    }

    
}

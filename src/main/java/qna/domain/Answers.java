package qna.domain;

import org.hibernate.annotations.Where;
import qna.CannotDeleteException;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@Embeddable
public class Answers {

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private final List<Answer> answers;

    private Answers(List<Answer> answers) {
        this.answers = answers;
    }

    protected Answers() {
        this.answers = new ArrayList<>();
    }

    public static Answers of(List<Answer> answers){
        return new Answers(answers);
    }

    public static Answers of(){
        return Answers.of(new ArrayList<>());
    }


    public void add(Answer answer) {
        answers.add(answer);
    }

    public boolean isOwner(User user) {
        if(!(answers.size() == writeCount(user))){
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
        return true;
    }

    private int writeCount(User user) {
        return (int) answers.stream()
                .filter(answer -> answer.isOwner(user))
                .count();
    }

    public DeleteHistorys delete(DeleteHistorys deleteHistorys) {
           answers.stream()
                    .map(Answer::delete)
                    .forEach(answer -> deleteHistorys.add(ContentType.ANSWER, answer.getId(), answer.getWriter()));
        return deleteHistorys;
    }
}

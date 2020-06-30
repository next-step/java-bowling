package qna.domain;

import org.hibernate.annotations.Where;
import qna.CannotDeleteException;
import qna.ExistTheOthersAnswerException;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Embeddable
public class Answers {

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private List<Answer> answers = new ArrayList<>();

    public void add(Answer answer) {
        answers.add(answer);
    }

    public Answers(){}

    public Answers(List<Answer> answers) {
        this.answers = Collections.unmodifiableList(answers);
    }

    public int countAnswers(){
        return answers.size();
    }

    public void delete(User loginUser, List<DeleteHistory> deleteHistories) throws CannotDeleteException {
        checkAnswers(loginUser);
        for (Answer answer : answers) {
            answer.setDeleted(true);
            deleteHistories.add(new DeleteHistory.Builder()
                                                 .contentType(ContentType.ANSWER)
                                                 .contentId(answer.getId())
                                                 .deletedBy(answer.getWriter())
                                                 .createDate(LocalDateTime.now())
                                                 .build());
        }
    }

    private void checkAnswers(User loginUser) throws CannotDeleteException {
        if (isExistOthersAnswer(loginUser)) {
            throw new ExistTheOthersAnswerException();
        }
    }

    private boolean isExistOthersAnswer(User loginUser) {
        return answers.stream()
                      .anyMatch(answer -> !answer.isOwner(loginUser));
    }
}

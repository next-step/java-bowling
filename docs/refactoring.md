# 3단계 - 볼링 점수판(리팩토링)

## 기능 요구사항
* 기능 요구사항은 2단계와 같다.
* 객체 설계 힌트를 참고해 재구현해본다.


## 점수 구하기 힌트
* Strike, Spare, Miss 상태에서 각각의 점수에 대한 부분을 추상화한 Score 객체를 추가해 본다.
```java
public class Score {
    private int score; // 현재까지 점수
    private int left; // 남은 시도 횟수
    
    public Score(int score, int left) {
        this.score = score;
        this.left = left;
    }
    
    public Score bowl(int countOfPins) {
        return new Score(score += countOfPins, left - 1);
    }
    
    public int getScore() {
        if (!canCalucateScore()) {
            throw new CannotCalculateException();
        }
        return this.score;
    }
    
    public boolean canCalucateScore() {
        return left == 0;
    }
    
    [...]
}
```

* Frame은 현재 자신의 Frame 상태에 따른 Score를 생성해 점수를 구한다.

```java
Score miss = new Score(firstPin + secondPin, 0);
Score spare = new Score(10, 1);
Score strike = new Score(10, 2);
```  
* 위와 같이 10, 1, 2를 하드코딩하는 것이 싫다면 Score에 각 상태에 따른 팩토리 메소드를 추가하는 것도 좋겠다.

```java
Score miss = Score.ofMiss(firstPin + secondPin);
Score spare = Score.ofSpare();
Score strike = Score.ofStrike();
```
* 각 Frame에서는 Score의 계산 가능 여부에 따라 다음 Frame에 점수 계산을 위임할 것인지 판단해 점수를 계산한다.

```java
public int getScore() {
    Score score = createScore();
    if (score.canCalucateScore()) {
        return score.getScore();
    }
    return next.cacluateAdditionalScore(score);
}

private int cacluateAdditionalScore(Score beforeScore) {
    // TODO beforeScore에 현재 Frame의 쓰러진 Pin을 추가해 점수를 구하는 로직 구현
}
```
      
## 다형성을 활용해 if/else 제거 힌트
> 다음 2개의 규칙도 같이 고려해서 해결
> 
> 규칙 6: 모든 엔티티를 작게 유지한다., 규칙 7: 2개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.
  
* Frame의 각 상태를 자바 객체로 추상화한다.
* 현재 상태에서 다음 상태의 객체를 생성하는 역할을 현재 상태가 담당하도록 한다.

```java
public class Ready extends State {
    public State bowl(int countOfPin) {
        if (countOfPin == 10) {
            return new Strike();
        }
        
        return new FirstBowl(countOfPin);
    }
}
```
```java
class FirstBowl extends State {
    private int countOfPin;
    
    FirstBowl(int countOfPin) {
        this.countOfPin = countOfPin;
    }
    
    @Override
    public State bowl(int countOfPin) {
        if (this.countOfPin + countOfPin == 10) {
            return new Spare(this.countOfPin, countOfPin);
        }
    
        return new Miss(this.countOfPin, countOfPin);
    }
}
```
* 위와 같이 각 상태를 담당하는 객체를 추가하면 구현에서 발생하던 많은 if/else를 상태 객체로 분리할 수 있다.


## 규칙 3: 모든 원시값과 문자열을 포장한다.
* 쓰러진 볼링 핀이 int 값으로 전달된다. 이 int 값을 추상화한 Pin 객체를 추가한다.

```java
public class Pin {
    private static final int MIN_PINS = 0;
    private static final int MAX_PINS = 10;
    
    private int falledPins;
    
    private Pin(int falledPins) {
        validPins(falledPins);
        this.falledPins = falledPins;
    }
    
    private static void validPins(int falledPins) {
        if (falledPins > MAX_PINS) {
            throw new IllegalArgumentException(
                String.format("볼링 핀은 최대 10을 넘을 수 없습니다. 현재 쓰러진 핀 수는 %d", falledPins));
        }
      
        if (falledPins < MIN_PINS) {
            throw new IllegalArgumentException(
                String.format("볼링 핀은 최초 0 미만이 될 수 없습니다. 현재 쓰러진 핀 수는 %d", falledPins));
        }
    }
    
    [...]
}
```
* 위와 같은 Pin 클래스를 추가한 후 최초 int 값을 Pin으로 감싼 후 프로그램 전반에서 사용해본다.
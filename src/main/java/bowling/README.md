## 볼링 미션 학습 목표
- 경험해야할 학습 목표
    - Q&A 서비스를 활용해 레거시 코드를 리팩토링 하는 경험
    - 지금까지 학습한 내용을 기반으로 TDD, 클린코드, 객체지향 프로그래밍 하는 경험

- 객체지향 생활체조 원칙
    - 한 메서드에 오직 한 단계의 들여쓰기만 한다.
    - else 예약어를 쓰지 않는다.
    - 모든 원시값과 문자열을 포장한다.
    - 한 줄에 점 하나만 찍는다.
    - 줄여쓰지 않는다(축약금지)
    - 모든 엔티티를 작게 유지한다.
    - 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.
    - 일급 콜렉션을 쓴다.
    - 게터/세터/프로퍼티를 쓰지 않는다.

- GRASP - General Responsibility Assignment Software Patterns
    - 책임 기반 객체지향 관점에서 객체에 책임을 할당하기 위한 패턴을 정리한 것을 의미
    - [GRASP 패턴 : 객체의 책임 구별은 어떻게 할까?](https://vandbt.tistory.com/9)
    - [책임 할당에 기반한 객체 설계 원칙](http://contents.kocw.or.kr/KOCW/document/2014/Seowon/SongHaesang/08.pdf) 
    - [GRASP](http://www.kamilgrzybek.com/design/grasp-explained/)
- Information Expert
    - 역할을 수행할 수 있는 정보를 가지고 있는 객체에 역할을 부여하자.
    - Problem : What is a basic principle by which to assign responsibilities to objects?
    - Solution : Assign a responsibility to the class tht has the information needed to fulfill it.
    
- 객체지향 5원칙(SOLID)
    - SRP(단일 책임 원칙 : Single Responsibility Principle)
        - 작성된 클래스는 하나의 기능만 가지며 클래스가 제공하는 모든 서비스는 그 하나의 책임(변화의 축)을 수행하는 데 집중되어
        있어야 한다.
    - OCP(개방 폐쇄의 원칙 : Open Close Principle)
        - 소프트웨어의 구성요소(컴포넌트, 클래스, 모듈, 함수)는 확장에는 열려있고, 변경에는 닫혀 있어야 한다.
    - LSP(리스코브 치환의 원칙 : The Liskov Substitution Principle )
        - 서브 타입은 언제나 기반 타입으로 교체할 수 있어야 한다. 즉 서브타입은 언제나 기반 타입과 호환될 수 있어야 한다.
    - ISP(인터페이스 분리의 원칙 : Interface Segregation Principle)
        - 한 클래스는 자신이 사용하지 않는인터페이스는 구현하지 말아야 한다.
    - DIP(의존성역전의 원칙 : Dependency Inversion Principle)
        - 구조적 디자인에서 발생하던 하웨 레벨 모듈의 변경이 상위 레벨 모듈의 변경을 요구하는 위계관계를 끊는 의미의 역전원칙이다.
        
## 요구사항
- 기능 요구사항
    - 최종 목표는 볼링 점수를 계산하는 프로그램을 구현한다. 
    - 1단계 목표는 점수 계산을 제외한 볼링 게임 점수판을 구현하는 것이다.
    - 각 프레임이 스트라이크면 "X" , 스페어이면 "9|/" , 미스이면 "8|1",과 같이 출력하도록 구현한다.
        - 스트라이크 : 프레임의 첫번째 투구에서 모든 핀(10개)을 쓰러뜨린 상태
        - 스페어 : 프레임의 두번째 투구에서 모든 핀(10개)을 쓰러뜨린 상태
        - 미스 : 프레임의 두번째 투구에서도 모든 핀이 쓰러지지 않은 상태
        - 거터 : 핀을 하나도 쓰러트리지 못한 상태. 거터는 "-"로 표시
    - 10 프레임은 스트라이크거나 스페어이면 한번을 더 투구할 수 있다.

- 힌트
    - 객체 단위를 가장 작은 단위까지 극단적으로 분리하는 시도를 해본다.
    - 1~9 프레임을 NormalFrame, 10프레임을 Final Frame과 같은 구조로 구현한후 Frame을 추가해 중복을 제거해 본다.
    - 다음 Frame을 현재 Frame 외부에서 생성하기 보다 현재 Frame에서 다음 Frame을 생성하는 방식으로 구현해보고 어느
    구현이 더 좋을지 검토해 본다.
      
      
## 객체 나눠보기
- Player
- Frame : Scores , Type (스트라이크 거터 등등) 를 가진 하나의 프레임
- Frames : Frame들을 가진 프레임s , 최대 10개를 가진다.   
- Score : 점수를 담당
- Scores :  FirstScore , SecondScore 를 가진 스코어 담당
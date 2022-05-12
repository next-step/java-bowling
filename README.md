# 볼링 게임 점수판 

### 기능 목록 정리


#### 1. pin 패키지

---

* PinNo: 투구 한 번에 쓰러뜨린 핀의 개수를 나타내는 클래스입니다. 캐싱을 통해 재사용 됩니다. PinStatus 클래스에서
PinNo 두 개의 합을 통해 STRIKE, SPARE, MISS 를 판단할 수 있도록 plus() 메서드가 있습니다.

* PinNumbers: PinNo 두 개로 이루어진 클래스입니다. 한 프레임에서 두 번의 투구 결과 쓰러뜨린 핀의 개수들을 나타냅니다. 

* PinStatus: PinNo 두 개를 받아 두 번의 투구 결과에 맞는 결과를 반홥합니다. (STRIKE, SPARE, MISS)

#### 2. frame 패키지

---

* Frame: 볼링의 10프레임 중 한 프레임을 나타냅니다. PinNumbers 를 인스턴스 변수로 가지고 다음 프레임을 반환하는 next() 추상
메서드를 가집니다. 

    * NormalFrame: 볼링의 10프레임 중 1~9 프레임을 나타냅니다. next() 에서 프레임 번호를 래핑한 NormalFrameNo를 통해 프레임 번호를
      확인하고 9 프레임일 때는 FinalFrame을 반환합니다.
  
    * FinalFrame: 볼링의 10프레임 중 마지막 10프레임을 나타냅니다. next() 메서드는 호출하면 예외를 던집니다. 마지막 프레임은 조건이 맞다면
      한 번더 투구할 수 있기 때문에 addExtra() 메서드를 통해 조건을 검사하고 추가 PinNo를 세팅할 수 있도록 했습니다.
    
* NormalFrameNo: 프레임 번호를 래핑한 클래스입니다. 캐싱을 통해 재사용 됩니다.

* Frames: 9개의 NormalFrame과 1개의 FinalFrame을 래핑한 클래스입니다. NormalFrame의 List 사이즈를 확인하여
  9개의 NormalFrame이 모두 채워진 다음에 FinalFrame을 추가할 수 있도록 했습니다.

#### 3. game 패키지

---

* Player: 참가자 이름을 나타냅니다. 영문 3글자만 허용합니다.

* Bowling: Player와 Frames를 통해 참가자 한 명의 볼링 게임 결과를 나타냅니다. Main 사용하는 클래스이며 모든 메서드는
  위임을 통해 처리됩니다.

#### 4. 그 외

---

* InputView: 사용자 입력 담당

* OutputView: 결과 출력 담당

* Main: 실행 클래스

  
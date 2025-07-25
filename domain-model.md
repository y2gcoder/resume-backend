# Resu:me 도메인 모델

## 도메인 모델 만들기
1. 듣고 배우기
2. '중요한 것'들 찾기 (개념 식별)
3. '연결 고리' 찾기 (관계 정의)
4. '것'들을 설명하기 (속성 및 기본 행위 명시)
5. 그려보기 (시각화)
6. 이야기 하고 다듬기 (반복)

## Resu:me 도메인
- Resu:me 는 이력서를 관리할 수 있는 온라인 서비스다
- Resu:me 는 자신의 이력서를 간단하게, 구조적으로 작성하고 간편하게 공유하는 서비스를 목표로 한다
  - 서비스명은 resume + me 로 하여 나를 소개하는 이력서를 더 강조하고자 했다
- 이력서를 관리하기 위해서는 회원으로 등록해야 한다
  - 회원이 되기 전에도 이력서 템플릿 및 예시를 볼 수 있다
  - 이력서를 작성하고 관리하기 위해서는 등록을 완료한 회원이어야 한다
  - 등록 신청한 후 정해진 요건을 충족하면 등록이 완료된다
  - 회원은 탈퇴할 수 있다
  - 등록 시간, 등록 완료 시간, 탈퇴한 시간을 기록한다
- 회원은 이력서를 관리할 수 있다
  - 회원은 이력서를 작성할 수 있다
  - 회원은 작성한 이력서 목록을 확인할 수 있다
  - 회원은 작성한 이력서를 수정할 수 있다
  - 회원은 작성한 이력서를 삭제할 수 있다
  - 회원은 작성한 이력서를 복제할 수 있다 
- 이력서는 1개 이상의 섹션을 가진다
  - 섹션은 기본 정보 섹션과 추가 섹션들로 나뉜다. 기본 정보 섹션은 모든 이력서에 반드시 1개 포함되어야 하는 필수 섹션이며, 나머지 섹션은 선택적으로 추가/삭제할 수 있다.
  - 기본정보 섹션은 프로필 이미지, 이름, 직업, 이메일, 휴대폰 번호, 한줄 소개로 이루어져 있다
  - 추가 섹션은 추가하거나 제외할 수 있다
  - 추가 섹션들은 경력, 포트폴리오, 프로젝트, 교육, 대외활동, 자격증, 외국어, 자기소개 섹션들이 있다
    - 경력 섹션은 회원의 경력사항을 작성할 수 있다
    - 포트폴리오 섹션은 회원이 진행했던 포트폴리오를 작성할 수 있다
    - 교육 섹션은 회원이 수료하거나 졸업한 교육 활동을 작성할 수 있다
    - 자격증 섹션은 회원이 취득한 자격증을 작성할 수 있다
    - 외국어 섹션은 회원이 취득한 외국어 자격증을 작성할 수 있다
    - 자기소개 섹션은 회원이 본 이력서에서 작성할 자기소개를 작성할 수 있다
- 템플릿은 이력서를 보여주는 형식을 말한다
  - 템플릿에 따라 이력서의 각 섹션을 배치하는 방식이 달라진다
  - 회원은 템플릿이 적용된 이력서를 PDF로 만들기 전 미리보기로 확인할 수 있다
  - 회원은 템플릿이 적용된 이력서를 PDF로 출력할 수 있다

## 도메인 모델

---

### 회원(Member)
_Entity_
#### 속성
- `id`: `Long`
- `email`: `Email` 이메일 - Natural ID
- `nickname`: 닉네임
- `passwordHash`: 비밀번호(해시)
- `status`: `MemberStatus`: 회원 상태

#### 행위
- `register()`: 회원 등록 email, nickname, password, passwordEncoder
- `activate()`: 등록 완료
- `deactivate()`: 탈퇴
- `verifyPassword()`: 비밀번호를 검증한다
- `changeNickname()`
- `changePassword()`

#### 규칙
- 회원 등록 후 회원 상태는 등록 대기
- 어떤 조건을 만족하면 등록 완료할 수 있다
- 등록 대기 상태에서만 등록 완료할 수 있다
- 등록 완료 상태에서만 탈퇴할 수 있다
- 회원의 비밀번호는 해싱해서 저장한다

### 회원 상태(MemberStatus)
_Enum_
#### 상수
- `PENDING`: 등록 대기
- `ACTIVE`: 등록 완료
- `DEACTIVATED`: 탈퇴

### DuplicateEmailException
_Exception_

### 비밀번호 인코더(PasswordEncoder)
_Domain Service_
#### 행위
- `encode()`: 비밀번호 해싱(암호화)
- `matches()`: 비밀번호가 일치하는지 확인

---

### 이메일
_Value Object_
#### 속성
- `address`: 이메일 주소

---

### 이력서(Resume)
_Entity_
#### 속성
- `writer`: `Member`, 작성자 
- `title`: 제목
- `name`: 이름
- `email`: `Email` 이메일
- `phoneNumber`: `PhoneNumber` 휴대폰번호
- `subtitle`: 부제
- `profileImageUrl`: 프로필 이미지 URL
- `bio`: 한줄 소개

#### 행위
- `create()`: 이력서 생성: writer, title, name, email, callingCode, nationalNumber
- `updateTitle()`
- `updateName()`
- `updateEmail()`
- `updatePhoneNumber()`
- `updateSubtitle()`
- `updateProfileImageUrl()`
- `updateBio()`

#### 규칙
- 이력서를 생성할 때 작성자, 제목, 이름, 이메일, 국제 전화 식별 코드, 국내 번호는 필수

### 휴대폰 번호(PhoneNumber)
_Value Object_
#### 속성
- `callingCode`: 국제 전화 식별 코드
- `nationalNumber`: 국내 번호

---

### 섹션

### 템플릿









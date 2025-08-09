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
      - 경력은 직장명, 기간, 재직형태, 직무, 직책, 주요 성과를 입력해야 한다.
      - 재직중인 경력은 기간 중 시작일자만 존재한다
      - 재직형태는 정규직, 계약직, 인턴, 프리랜서, 개인사업 등이 있다. 
      - 이력서는 여러 개의 경력을 입력할 수 있다
    - 포트폴리오 섹션은 회원이 진행했던 포트폴리오를 작성할 수 있다
      - 포트폴리오는 링크 섹션과 첨부파일 섹션으로 나뉜다
      - 링크 섹션은 포트폴리오 URL과 해당 포트폴리오 URL에 대한 간단한 설명이 포함된다
      - 첨부파일 섹션은 첨부파일을 업로드할 수 있다
      - 첨부파일은 다운로드할 수 있다
    - 교육 섹션은 회원이 경험했던 교육 활동을 작성할 수 있다
      - 교육은 교육기관명, 전공, 교육 종류, 교육 상태, 기간 이 있다
      - 교육 종류는 고등학교, 대학교(전문학사), 대학교(학사), 대학교(석사), 대학교(박사), 사설 교육이 있다
      - 교육 상태는 졸업, 재학 중, 중퇴, 수료가 있다
      - 재학 중인 교육은 기간 중 시작일자만 존재한다
      - 이력서는 여러 개의 교육을 입력할 수 있다
    - 자격증 섹션은 회원이 취득한 자격증을 작성할 수 있다
      - 자격증은 자격증명, 점수/급, 취득년월, 세부내용을 입력할 수 있다
    - 외국어 섹션은 회원이 사용할 수 있는 외국어에 대한 정보를 작성할 수 있다
      - 외국어는 언어, 수준, 그리고 관련 어학 시험 을 포함한다
      - 수준은 일상 회화 / 비즈니스 / 네이티브로 나눌 수 있다 
      - 하나의 외국어에는 여러 개의 어학 시험을 추가할 수 있다
        - 어학 시험은 시험명, 점수/등급, 취득년월을 입력할 수 있다
      - 이력서에는 외국어를 여러 개 추가할 수 있다
    - 자기소개 섹션은 회원이 본 이력서에서 작성할 자기소개를 작성할 수 있다
  - 이력서는 생성일시를 기록한다
- 템플릿은 이력서를 보여주는 형식을 말한다
  - 템플릿에 따라 이력서의 각 섹션을 배치하는 방식이 달라진다
  - 회원은 템플릿이 적용된 이력서를 PDF로 만들기 전 미리보기로 확인할 수 있다
  - 회원은 템플릿이 적용된 이력서를 PDF로 출력할 수 있다

## 도메인 모델

---

### **회원 애그리거트**

### 회원(Member)
_Aggregate Root_
#### 속성
- `id`: `Long`
- `email`: `Email` 이메일 - Natural ID
- `nickname`: 닉네임
- `passwordHash`: 비밀번호(해시)
- `status`: `MemberStatus`: 회원 상태
- `detail`: `MemberDetail`: 1:1

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

### 회원 상세(MemberDetail)
_Entity_
#### 속성
- `id`: `Long`
- `registeredAt`: 등록일시
- `activatedAt`: 등록 완료 일시
- `deactivatedAt`: 탈퇴 일시
#### 행위
- `create()`: 회원 등록, 현재 시간을 등록 일시로 저장한다
- `activate()`: 등록 완료와 관련된 작업 수행. 등록 완료 일시를 저장함
- `deactivate()`: 탈퇴와 관련된 작업을 수행. 탈퇴 일시를 저장함

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

### **이력서 애그리거트**

### 이력서(Resume)
_Aggregate Root_
#### 속성
- `id`: `Long`
- `writer`: `Member`, 작성자 
- `title`: 제목
- `name`: 이름
- `email`: `Email` 이메일
- `phoneNumber`: `PhoneNumber` 휴대폰번호
- `subtitle`: 부제
- `profileImageUrl`: 프로필 이미지 URL
- `bio`: 한줄 소개
- `createdAt`: 생성일시
- `workExperiences`: `MutableList<WorkExperience>`: 경력 섹션
- `portfolio`: `Portfolio`: 포트폴리오 섹션

#### 행위
- `create()`: 이력서 생성: writer, title, name, email, callingCode, nationalNumber. 생성 시 생성일시를 기록한다.
- `updateTitle()`
- `updateName()`
- `updateEmail()`
- `updatePhoneNumber()`
- `updateSubtitle()`
- `updateProfileImageUrl()`
- `updateBio()`
- `addWorkExperience()`: 경력 추가
- `createPortfolio()`: 포트폴리오 생성
- `updatePortfolioLinks()`: 포트폴리오 링크 아이템 목록 업데이트
- `updatePortfolioAttachments()`: 포트폴리오 첨부파일 아이템 목록 업데이트

#### 규칙
- 이력서를 생성할 때 작성자, 제목, 이름, 이메일, 국제 전화 식별 코드, 국내 번호는 필수
- 이력서를 생성하기 위해서는 회원이 등록 완료한 상태여야 함
- 이력서를 생성했을 때 포트폴리오 섹션이 존재하지 않을 수도 있다
- 포트폴리오는 내부에 빈 값으로 생성할 수 있다

### 휴대폰 번호(PhoneNumber)
_Value Object_
#### 속성
- `callingCode`: 국제 전화 식별 코드
- `nationalNumber`: 국내 번호

### 경력(WorkExperience)
_Entity_
#### 속성
- `id`: `Long`
- `companyName`: 직장명 
- `period`: `Period`: 재직기간
- `employmentType`: `EmploymentType`: 재직 형태
- `role`: 직무
- `position`: 직책
- `achievement`: 주요 성과 

#### 행위
- `create()`: 경력 생성: companyName, startedAt, endedAt, employmentType, role, position, achievement
- `isCurrentJob()`: 현재 재직중인지 체크

#### 규칙
- 재직기간 - 종료일시가 없으면 해당 경력은 재직중인 상태임 

### 재직 형태(EmploymentType)
_Enum_
#### 상수
- `FULL_TIME`: 정규직
- `CONTRACT`: 계약직
- `INTERN`: 인턴
- `FREELANCE`: 프리랜서
- `SELF_EMPLOYED`: 개인사업

### 포트폴리오(Portfolio)
_Entity_
#### 속성
- `id`: `Long`
- `links`: `MutableList<LinkItem>`: 링크 아이템 목록
- `attachments`: `MutableList<AttachmentItem>`: 첨부파일 아이템 목록
#### 행위
- `create()`: 포트폴리오 생성: links, attachments
- `replaceLinks()` 링크 목록 교체
- `replaceAttachments()` 첨부파일 목록 교체
#### 규칙
- 이력서에서 생성할 때 포트폴리오의 내부 각 목록들은 비어있을 수 있다
- 링크 아이템 목록은 항상 전체를 교체한다
- 첨부파일 아이템 목록은 항상 전체를 교체한다

### 링크 아이템(LinkItem)
_Value Object_
#### 속성
- `url`: 링크 URL
- `description`: 링크에 대한 설명

#### 행위

### 첨부파일 아이템(AttachmentItem)
_Value Object_
#### 속성
- `fileKey`: 파일 식별자. 파일 다운로드 를 하기 위함
- `filename`: 파일명
- `contentType`: MIME 타입
- `fileSize`: 파일 크키(bytes)
- `uploadedAt`: 파일 업로드 일시
#### 행위

### 교육(Education)
_Entity_
#### 속성
- `id`: `Long`
- `institutionName`: 교육기관명
- `major`: 전공
- `type`: : `EducationType`: 교육 종류
- `status`: `EducationStatus`: 교육 상태
- `period`: `Period`: 교육 기간

#### 행위
- `create()`: 교육 생성. 교육기관명, 전공, 교육 종류, 교육 상태, 교육 기간
- `isCurrentlyEnrolled()`: 현재 재학 중


#### 규칙
- 재학 중인 교육은 교육 기간 중 종료일시가 없음

### 교육 종류(EducationType)
_Enum_
#### 상수
- `HIGH_SCHOOL`: 고등학교
- `ASSOCIATE_DEGREE`: 대학교(전문학사)
- `BACHELOR_DEGREE`: 대학교(학사)
- `MASTER_DEGREE`: 대학교(석사)
- `DOCTORAL_DEGREE`: 대학교(박사)
- `PRIVATE_EDUCATION`: 사설 교육

### 교육 상태(EducationStatus)
_Enum_
#### 상수
- `GRADUATED`: 졸업
- `ENROLLED`: 재학중
- `DROPPED_OUT`: 중퇴
- `COMPLETED`: 수료

### 기간(Period)
_Value Object_
#### 속성
- `startedAt`: 시작일시 YYYY-MM
- `endedAt`: 종료일시 YYYY-MM. 없을 수도 있음

#### 행위
- `isOngoing()`: 현재 기간이 진행중인지 체크

#### 규칙
- 종료일시는 시작일시와 같거나 미래여야 한다
- 종료일시가 없으면 현재 진행중인 기간

---

### 섹션

### 템플릿









# 0. 현재 제작중인 프로젝트
- [헥사고 기반 MSA 프로젝트 - 플랫폼 ](https://github.com/ZipTe/ZipTe_Platform)
- [헥사고 기반 MSA 프로젝트 - 알림센터 ](https://github.com/ZipTe/ZipTe_Notification)
- [헥사고 기반 MSA 프로젝트 - 유저 서비스 ](https://github.com/ZipTe/ZipTe_Member)
- [헥사고 기반 MSA 프로젝트 - 커뮤니티 ](https://github.com/ZipTe/ZipTe_Community)

# 1. Project Overview (프로젝트 개요)
- 프로젝트 이름: ZIPTE
- 프로젝트 설명: 1. 개인별 상황에 맞는 적절한 거주지를 추천합니다. 2.아파트에 대한 리뷰와 이야기를 나누는 커뮤니티 서비스를 제공합니다. 3. 같은 거주지에 거주하는 사람들을 위한 아파트별 쇼핑몰을 제공합니다
- 프로젝트 기간 : 2024.10.27 ~ 2025.01.05 (1인 개발)
  
# 2. Team Member (팀원 소개)
| 이도연 |
|:------:|
| <img src="https://github.com/user-attachments/assets/653c94e3-5837-4e40-8ee9-b0ff135b59e7" alt="이도연" width="150"> | 
| BE |
| [GitHub](https://github.com/doup2001) | 


# 3. Key Features (주요 기능)
- **회원가입**:
   - Oauth2(네이버) & JWT 사용 회원가입
   - Oauth로 새롭게 가입하게되면 Oauth_User
   - 회원가입 이후, 주소를 추가하면 Oauth_User에서 User로 업그레이드
   - 주문(구독제)을 통하여 MemberRole upgrade 가능 → VIP, VVIP
   - 회원정보 수정 가능
   - 역할별 접근가능한 페이지 존재

- **로그인**:
  - 소셜 로그인 정보를 통해 로그인합니다.
  - JWT 토큰을 통해 유저의 정보가 맞는지 체크합니다.

- **아파트**:
  - FastAPI를 통해, 아파트의 정보 및 유사한 아파트 데이터 받기
  - FastAPI를 통해, 개인별 선호도에 따른 아파트 데이터 받기
  - FastAPI를 통해, 지난 N년간 아파트별 실거래가를 받기
  - FastAPI를 통해, 지난 N년간 특정 법정동별 가격 정보(평당) 데이터 받기

- **아파트 리뷰**:
  - 리뷰 내용 입력 (사진포함)
  - 아파트에 대한 평점 입력 기능
  - 이용자 별 리뷰 확인 가능
  - 호출시, 조회수 증가
  - 조회수별,평점순, 최신순 목록 조회(페이징처리)

- **상품 보기(카테고리)** :
  - 상품 정보 내용 입력 (사진,카테고리 포함)
  - 카테고리 별 상품 조회 가능(목록 조회 페이징처리)
  - 아이템 상세보기에서 수량을 선택하여 장바구니로 추가 가능
    
- **상품 매니저** :
  - 상품별 할인율,할인시작일과 종료일, 활성화 여부 설정 가능

- **장바구니**:
  - 한번에 여러개의 상품 구매 가능하도록 장바구니 기능 구현
  - 개수에 따른 가격 정보 표시

- **상품 및 결제**:
  - 장바구니를 통해 여러개 아이템 구매 혹은 상품보기에서 하나의 상품만 구매 가능
  - 토스의 결제 위젯을 사용하여, 상품을 결제
  - 토스를 위한 가주문(Order) 테이블과, 실제 결제를 완료하면 생기는 Payment 테이블로 나눠서 관리
  - 결제가 완료된 주문에 대해서 상세 주문 정보 확인 가능

- **배송**:
  - 나만의 배송지를 만들어서, 해당 배송지를 재사용하여 주문을 할 수 있도록 설정
  - 새로운 배송지로 설정도 가능
  - 결제가 완료된 주문의 배송지를 수정 가능 

- **게시글(카테고리)**:
  - 게시글 내용 입력 (사진,카테고리 포함)
  - 카테고리별로 조회 가능(계층형으로 아래 카테고리 조회 가능)
  - 게시글 호출시마다 조회수 증가

- **댓글**:
  - 댓글 및 대댓글 등록가능
  - 글작성자가 댓글 작성시, Writer로 Boolean 표현

- **좋아요/싫어요**:
  - 댓글과 게시글 좋아요/싫어요 기능 구현. 좋아요 또는 싫어요만 가능
  - 좋아요 -> 싫어요하려면 좋아요 취소하고 싫어요를 눌러야한다.


# 4. Technology Stack (기술 스택)
## Backend
|  |  |  |
|-----------------|-----------------|-----------------|
| SpringBoot    |  <img src="https://github.com/user-attachments/assets/43d80a85-2060-4475-95a8-fc402f837aa0" alt="SpringBoot" width="100">    | 3.3.4  |
| SpringSecurity    |  <img src="https://github.com/user-attachments/assets/7eafc435-a1b9-47ab-bbc2-6ac02d21a4f0" alt="SpringSecurity" width="100">    |  |


## 5. Cooperation
|  |  |
|-----------------|-----------------|
| Git    |  <img src="https://github.com/user-attachments/assets/483abc38-ed4d-487c-b43a-3963b33430e6" alt="git" width="100">    |
| Notion    |  <img src="https://github.com/user-attachments/assets/34141eb9-deca-416a-a83f-ff9543cc2f9a" alt="Notion" width="100">    |


# 6. Project Structure (프로젝트 구조)
```plaintext
│   │       ├── main
│   │       │   └── org
│   │       │       └── gdg
│   │       │           └── zipte
│   │       │               ├── ZipteGdgApplication.class
│   │       │               ├── api
│   │       │               │   ├── controller
│   │       │               │   │   ├── admin
│   │       │               │   │   │   ├── board
│   │       │               │   │   │   ├── order
│   │       │               │   │   │   ├── product
│   │       │               │   │   │   └── user
│   │       │               │   │   ├── apt
│   │       │               │   │   │   └── apt
│   │       │               │   │   ├── board
│   │       │               │   │   │   ├── board
│   │       │               │   │   │   ├── comment
│   │       │               │   │   │   ├── commentLike
│   │       │               │   │   │   └── like
│   │       │               │   │   ├── order
│   │       │               │   │   │   ├── cart
│   │       │               │   │   │   ├── delivery
│   │       │               │   │   │   ├── order
│   │       │               │   │   │   └── toss
│   │       │               │   │   ├── product
│   │       │               │   │   │   └── product
│   │       │               │   │   ├── review
│   │       │               │   │   │   └── review
│   │       │               │   │   └── user
│   │       │               │   │       ├── member
│   │       │               │   │       └── savedAddress
│   │       │               │   ├── response
│   │       │               │   └── service
│   │       │               │       ├── apt
│   │       │               │       │   └── apt
│   │       │               │       ├── board
│   │       │               │       │   ├── board
│   │       │               │       │   ├── boardReaction
│   │       │               │       │   ├── category
│   │       │               │       │   ├── comment
│   │       │               │       │   └── commentReaction
│   │       │               │       ├── order
│   │       │               │       │   ├── cart
│   │       │               │       │   ├── delivery
│   │       │               │       │   ├── order
│   │       │               │       │   ├── payment
│   │       │               │       │   └── toss
│   │       │               │       ├── product
│   │       │               │       │   ├── category
│   │       │               │       │   ├── categorySet
│   │       │               │       │   ├── product
│   │       │               │       │   └── productManger
│   │       │               │       ├── review
│   │       │               │       │   └── review
│   │       │               │       └── user
│   │       │               │           ├── member
│   │       │               │           └── savedAddress
│   │       │               ├── domain
│   │       │               │   ├── apt
│   │       │               │   │   ├── apt
│   │       │               │   │   ├── area
│   │       │               │   │   ├── own
│   │       │               │   │   └── subway
│   │       │               │   ├── board
│   │       │               │   │   ├── board
│   │       │               │   │   ├── category
│   │       │               │   │   ├── categorySet
│   │       │               │   │   ├── comment
│   │       │               │   │   └── reaction
│   │       │               │   ├── order
│   │       │               │   │   ├── cart
│   │       │               │   │   ├── delivery
│   │       │               │   │   ├── order
│   │       │               │   │   ├── orderItem
│   │       │               │   │   └── payment
│   │       │               │   ├── page
│   │       │               │   │   ├── request
│   │       │               │   │   └── response
│   │       │               │   ├── product
│   │       │               │   │   ├── category
│   │       │               │   │   ├── categorySet
│   │       │               │   │   ├── product
│   │       │               │   │   └── productManger
│   │       │               │   ├── review
│   │       │               │   │   ├── rating
│   │       │               │   │   └── review
│   │       │               │   └── user
│   │       │               │       ├── member
│   │       │               │       └── savedAddress
│   │       │               └── security
│   │       │                   ├── config
│   │       │                   ├── jwt
│   │       │                   │   ├── filter
│   │       │                   │   ├── handler
│   │       │                   │   └── provider
│   │       │                   └── oauth
│   │       │                       ├── domain
│   │       │                       └── service
│   ├── resources
│   │   └── main
│   │       ├── application.yml
│   │       ├── static
│   │       │   ├── boardImage
│   │       │   ├── productImage
│   │       │   └── reviewImage
```


# 7. Development Workflow (개발 워크플로우)
## 브랜치 전략 (Branch Strategy)
우리의 브랜치 전략은 Git Flow를 기반으로 하며, 다음과 같은 브랜치를 사용합니다.

- Main Branch
  - 배포 가능한 상태의 코드를 유지합니다.
  - 모든 배포는 이 브랜치에서 이루어집니다.
 
- Devlop Branch
  - 만든 기능들이 작동하는지 코드를 합병합니다.
  
- {feat} Branch
  - 모든 기능 개발은 feat 브랜치에서 이루어집니다.

# 8. 커밋 컨벤션

## type 종류
```
feat : 새로운 기능 추가
fix : 버그 수정
docs : 문서 수정
style : 코드 포맷팅, 세미콜론 누락, 코드 변경이 없는 경우
refactor : 코드 리펙토링
test : 테스트 코드, 리펙토링 테스트 코드 추가
chore : 설정 추가
```

# 10. ERD
<img src="https://github.com/user-attachments/assets/2e260523-5524-4c9a-a8af-4675455614ab" alt="ERD">

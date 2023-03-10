## **프로젝트 주제**

- 커머스 프로젝트

## **프로젝트 구조**

![Untitled](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/5fca4247-af53-4fce-82b0-739c888955eb/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230306%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230306T170632Z&X-Amz-Expires=86400&X-Amz-Signature=c747b06f67eaeb564fda86c1124abbe3e41fe74b841532e04e9aea6f4d7f390c&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Untitled.png%22&x-id=GetObject)

## ERD

![Untitled](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/cb33c088-595c-4b1c-8cac-7a05cffe49e0/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230306%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230306T170550Z&X-Amz-Expires=86400&X-Amz-Signature=17f75810c08bf124086154b36eb83a5ba491a8d927029f7ef9e4d252ec66e0bd&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Untitled.png%22&x-id=GetObject)

## 사용 기술 스택
- SpringBoot
- Java
- MySQL
- JPA
- MyBatis
- Thymeleaf

## 프로젝트 기능

- 팀이 선택한 주제별 구현해야 하는 기능에 대해 상세 요구사항을 작성해주세요.

<aside>
💡 주제별 구현 기능

- **주제 3. 커머스 과제**
    - [ ]  상품명 검색 기능
    - [ ]  상품 장바구니 기능(상품 담기/장바구니 조회/장바구니 상품 삭제)
    - [ ]  로그인 / 로그 아웃에 따른 장바구니 접근 허가 기능 구현
</aside>

**회원가입과 로그인**

- 회원가입
    - 회원가입시 아이디, 이메일, 이름, 전화번호, 비밀번호, 주소지 정보가 필요하다
    - 회원가입 후 이메일 인증이 되어야 로그인이 가능하다
    - 회원가입시 이미 회원가입된 아이디와 이름으로 회원가입을 시도하면 에러를 발생한다

- 로그인
    - 로그인시 회원가입한적 없는 아이디를 이용하여 로그인을 시도하면 에러가 발생한다
    - 로그인시 비밀번호가 일치하지 않는다면 에러가 발생한다.
    - 이메일 인증이 되어있지 않으면 에러가 발생한다.

**상품 등록**

- 상품 등록은 관리자만 등록할 수 있다

**상품 목록, 상세 및 검색 조회**

- 관리자가 등록한 상품은 사용자가 로그인을 하지 않아도 조회가 가능하다
- 검색결과를 정렬하여 조회할 수 있다

**상품 수정, 삭제**

- 관리자는 상품 정보를 수정 삭제할 수 있다

**장바구니 상품 담기**

- 로그인한 사용자는 상품 상세페이지에서 수량을 선택하여 장바구니에 상품을 담을 수 있다
- 장바구니에 상품 수량이 넘어오면 상품 재고수량은 줄어든다

**장바구니 조회**

- 로그인한 사용자는 장바구니 목록 조회가 가능하고,  결제 예상금액을 볼 수 있다

**장바구니 상품 수정, 삭제**

- 로그인한 사용자는 장바구니 상품 수량 수정 및 삭제가 가능하다

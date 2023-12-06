# I Luv Book - GPT를 활용한 영어동화 창작 서비스
<img width="1000" alt="image" src="https://github.com/BokDoong/english-fairytale-backend/assets/102129660/e8c1a113-742d-4d84-ab1e-2b70653db5d7">


## Introduction
2023 한이음 ICT멘토링을 진행하며 멘토님의 지도 하에 팀원들과 진행한 프로젝트입니다.

### 👶 프로젝트 설명
아이들에게 영어를 가르칠 때 가장 중요한 요소는 무엇이라고 생각하나요?

바로 아이들의 흥미를 잃지않고 학습의 재미와 효과를 함께 주는 것이라고 생각합니다. 
실제로 이러한 점을 고려해서 현재 대부분의 아동 영어 교육시설에서는 기존의 영어동화를 이용하여 영어를 가르치고 있습니다.

**'I Luv Book'은 GPT를 활용해 아이들이 원하는 키워드를 활용한 새로운 영어 동화를 창작해 제공하는 교육 플랫폼입니다.** 'I Luv Book'으로 아들의 교육의 효과를 증대시키는 데에서 더 나아가, 교육 종사자분들의 아이들을 교육하는 데에 있어 더욱 수고를 덜어내고자 합니다.

### 🏋️ 프로젝트 기간
* 기획 및 디자인: 2023.04 ~ 2023.08
* 개발: 2023.09 ~ 2023.11

### 🧑‍🤝‍🧑 팀 구성
* 송춘광 멘토님: 기획 및 멘토
* 이진: 팀장, Springboot 서버 개발, Notion 프로젝트 협업 페이지 제작 및 운영
* 신재희: 클라우드 아키텍쳐링
* 엄재웅, 나민혁: 안드로이드 개발

<br/><br/>
## BE Skills
<img width="700" height="450" alt="image" src="https://github.com/BokDoong/english-fairytale-backend/assets/102129660/b30a3e3e-aaff-463a-aa2e-07a4539f4d70">

* JDK 11 
* Springboot 2.8.3
* MariaDB
* Spring Data JPA
* Spring Data Redis
* AWS 인프라(EC2, RDS, ElastiCache, S3, CodeDeploy)
* Docker, Github Action
* ChatGPT Open API
* Notion, Git, Slack

<br/><br/>
## Main Functions
### 1️⃣ ChatGPT를 활용한 동화 창작 및 열람
* 자신이 원하는 키워드를 입력하면, 해당 키워드와 관련된 영어 동화를 ChatGPT OpenAPI를 이용해 창작합니다.
* 창작된 동화의 영어본 뿐 아닌, 한글 자막본을 확인할 수 있으며 TTS를 통해 발음을 들으며 공부할 수 있는 기능을 제공합니다.

### 2️⃣ 회원제 서비스
* 이메일 및 인증을 통해 회원가입을 진행합니다.
* 유저 정보 관리 및 생성 동화, 커뮤니티의 찜한 동화 관리 및 확인할 수 있습니다.

### 3️⃣ 커뮤니티 서비스
* 자신이 창작한 동화를 커뮤니티에 올리고 다른 회원들의 동화를 확인할 수 있습니다.
* 좋아요 기능을 통해 동화들을 좋아요순으로 확인할 수 있습니다.

<br/><br/>
## 문서
### 📜 ERD
![한이음](https://github.com/BokDoong/english-fairytale-backend/assets/102129660/32d4d986-be0b-4814-94f8-9847a78262f9)

### 🤝 [Notion 팀 협업페이지를 통한 대회 문서 및 프로젝트 운영 관리](https://rigorous-lentil-508.notion.site/b6c6e4104de24b1583214612894b2653?pvs=4)
<img width="1297" alt="image" src="https://github.com/BokDoong/english-fairytale-backend/assets/102129660/8d21bfc6-1104-44cb-9ae4-e1c44b6133d2">

### ✅ [API 명세서(Notion)](https://rigorous-lentil-508.notion.site/API-8d1782af488147659333ae8d80113ce5?pvs=4)
<img width="1458" alt="image" src="https://github.com/BokDoong/english-fairytale-backend/assets/102129660/5edfdbf3-9b7d-4e81-88c8-9a1e9b39b808">

<br/><br/>
## Acheivement
### 📝 학습 기록 및 트러블 슈팅과 리팩토링
* [도메인 주도 개발에 대한 학습 기록](https://rigorous-lentil-508.notion.site/DDD-Domain-Drive-Design-693b5c9412c84869b3d1ead9f050fbff?pvs=4)
* [Spring의 예외 처리](https://ctce7226.tistory.com/7)
* [원활한 개발과 유지보수를 가능케 하는 에외(에러코드) 커스텀화](https://ctce7226.tistory.com/8)
* [표준 예외 vs 사용자 정의 예외](https://ctce7226.tistory.com/6)

### ⚠️ 에러코드 문서화
<img width="1273" alt="image" src="https://github.com/BokDoong/english-fairytale-backend/assets/102129660/2f87b486-3652-4297-8a00-f7a735c3d447">

### 시연 영상
<[https://youtu.be/Tjqp_n2_jeY](https://youtu.be/NAEq48KNTXs?si=M1LqGmm4WCv0PLOS)>

<img width="368" alt="image" src="https://github.com/BokDoong/english-fairytale-backend/assets/102129660/b990ca44-c82c-421b-96fa-9c374c911673">


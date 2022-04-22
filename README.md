# S1
스프링 시큐리티를 사용한 로그인 게시판 프로젝트

JAVA, Spring boot, JPA, thymeleaf, Mustache, AWS EC2 RDS, MariaDB

서비스 주소 : https://bit.ly/3rlvLqF


# 책 부분 : ~ 24시간 배포 스크립트 완성 ver 1.0.0
@ branch 'main' commit '프로젝트 배포완료'


@ as is
- mustache, h2

ver 1.0.1
# 템플릿 엔진 mustache > thymeleaf ver 1.0.1
@ branch 'custom' commit '타임리프 수정1'

@ to be
- mustache 작성 내용 전부 as_is 폴더로 이동
- 작성된 3 페이지 thymeleaf로 변경
- https://fadet-coding.tistory.com/27

# 템플릿 엔진 mustache > thymeleaf ver 1.0.2
@ branch 'custom' commit '스크립트 수정2'

@to be
- index.html 디자인 수정
- 관련 링크 추가

# 템플릿 레이아웃 분할, 페이징 기능 추가 ver 1.0.3
@ branch 'custom' commit '~페이징 테스트코드'

@to be
- 공통 layout 생성
- 글목록 페이지 추가(/posts-list)
- css 파일 분리
- 페이징 기능 추가(기능 추가 및 테스트코드 작성)
- yml 설정으로 DB에 더미데이터 추가

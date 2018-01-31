/* 프리미엄 공연장 대관가능 시간*/
insert into premium_stage_reser
insert into premium_stage_time values(0, 3);


/* 사용자 */
insert into USERS values('hong1653', '홍길동', 'ewwe12345', '경기도 안산시', '01011112222', 'hong448@naver.com');
insert into USERS values('kim4845', '김경민', 'fdsf84848', '경상도 부산시', '01015849584', 'kim4852@naver.com');
insert into USERS values('beck4848', '백강민', 'asd1234', '전라도 전주시', '01015968447', 'beck4488@naver.com');
insert into USERS values('nam8118', '남승민', '555eee', '충청도 충주시', '01026598554', 'nam5995@naver.com');
insert into USERS values('lee534', '이승민', 'lee888844', '경기도 성남시', '01015622258', 'lee523@naver.com');
insert into USERS values('sim1', '심원', 'sdf8485', '경기도 오산시', '01015985632', 'sim33@gmail.com');
insert into USERS values('yunee33', '윤현', 'sd8899', '경기도 이천시', '01013835896', 'yun88@naver.com');
insert into USERS values('kimp123', '김민혁', 'et18453', '서울시', '01023485126', 'kimp3@nate.com');
insert into USERS values('suck1598', '석민수', '848err2', '인천시', '01015633467', 'suckf111@naver.com');
insert into USERS values('kimjr322', '김진혁', 'ad484as', '경상도 대구시', '01009384852', 'kimjr838@gmail.com');
insert into USERS values('choi22', '최경석', 'sdfsd484894', '전라도 광주시', '01012363059', 'choi85@naver.com');
insert into USERS values('no33432', '노석준', 'fdfd84841', '강원도 원주시', '01095363095', 'noee3@nate.com');
insert into USERS values('kimbo88', '김보남', 'erer88555', '강원도 강릉시', '01095685873', 'kimb22@naver.com');
insert into USERS values('kimdo327', '김회윤', 'qwwq2222', '경기도 평택시', '01009364596', 'kimu33@gmail.com');
insert into USERS values('kimm990', '김원진', 'cvf5558', '경기도 남양주시', '01012650485', 'kimm99855@naver.com');
insert into USERS values('id1','갓동엽','1234','홍대입구','01029292929','acb@anb.com');
insert into USERS values('id2','홍길동','2345','홍대입구','01012392929','adb@anb.com');
insert into USERS values('id3','김영희','3456','홍대입','01029342929','aeb@anb.com');
insert into USERS values('id4','김영수','5678','홍대입','01029288829','a3b@anb.com');
insert into USERS values('id5','제라드','2324','천안','01029218209','a2cb@anb.com');
insert into USERS values('id6','토레스','2495','대구','01012347269','ad2b@anb.com');
insert into USERS values('id7','호날두','RJ4848','분당','01029123929','ae2b@anb.com');
insert into USERS values('id8','로이스','QL2222','인천','01029999829','a32b@anb.com');

/* 권한 */
insert into AUTHORITY values('', '');

/* 팔로우 */
insert into FOLLOW values('hong1653', 'kim4845');
insert into FOLLOW values('beck4848', 'kim4845');
insert into FOLLOW values('suck1598', 'kim4845');
insert into FOLLOW values('sim1', 'kim4845');
insert into FOLLOW values('sim1', 'yunee33');

/* 프리미엄 대관 공급자 */
insert into STAGE_SUPPLIER values(11234567890, 'id1',1123456789,'홍대 놀이터','홍대 어딘가',100,'c://img/img8');
insert into STAGE_SUPPLIER values(22234567890, 'id2',2223456789,'서현 라이브 카페','서현역 5번 출구',100,'c://img/img9');
insert into STAGE_SUPPLIER values(3334567890, 'id3',3333456789,'야탑 라이브','야탑역 2번 출구',100,'c://img/img0');

delete from STAGE_SUPPLIER where STAGE_SUPPLIER.OPERATOR_USER_ID='id1';


/* 아티스트 */
insert into ARTIST values('kim4845', '메기 매운탕', '모던록밴드', '좋은 음악을 하는 밴드 입니다.', 'c://img/img1', '김경민, 김명민, 김겅민, 김엉민', 'sns1');
insert into ARTIST values('kimp123', '노랑머리', '어쿠스틱듀오', '발라드 합니다.', 'c://img/img2', '김민혁, 김밀현', 'sns2');
insert into ARTIST values('choi22', 'mc경', '힙합', 'wtf', 'c://img/img3', '최경석', 'sns3');
insert into ARTIST values('yunee33', '노래 개잘핵', '발라드', '추천받아요', 'c://img/img4', '윤현', 'sns4');
insert into ARTIST values('id1','busker','hiphop','첵첵 아임 더 코리안','c://img/img5','', 'sns5');
insert into ARTIST values('id2','busker2','j-pop','혼모노데쓰','c://img/img6','', 'sns6');
insert into ARTIST values('id3','동엽신','k-pop','소원을 말해봐~','c://img/img7','규석쓰, 태봉이, 수찬쓰', 'sns7');


/* 공연장(공급자 게시물) */
insert into STAGE values('1', '홍대 놀이터', '홍대 어딘가', '200000', '100', '베이스기타, 일렉기타, 드럼, 건반, 앰프', '매우 쾌적한 환경을 조성하고 있습니다.', 1, 0, 1, 0, 1, 'hong1653','2017-11-11');
insert into STAGE values('2', '서현 라이브 카페', '서현역 5번 출구', '100000', '85', '드럼, 건반, 앰프', '쾌적한 환경을 조성.', 0, 0, 0, 0, 1, 'sim1','2017-11-12');
insert into STAGE values('3', '야탑 라이브', '야탑역 2번 출구', '150000', '120', '일렉기타, 건반, 앰프', '좋아요.', 0, 0, 0, 0, 0, 'suck1598','2017-11-13');
insert into STAGE values('4', '부산 놀이터', '부산 해운대', '120000', '100', '드럼, 건반', '오세요.', 1, 1, 1, 0, 1, 'kimm990','2017-11-14');
insert into STAGE values('5', '전주 놀이터', '전주 어딘가', '300000', '180', '베이스기타, 일렉기타, 드럼, 건반, 앰프', '매우매우매우 쾌적한 환경을 조성하고 있습니다.', 1, 1, 1, 0, 1, 'lee534','2017-11-15');
insert into STAGE values(6,'홍대 홍대관','홍대',100000,300,'guitar','hello',1,1,0,0,1,'id1','2017-11-16');
insert into STAGE values(7,'리쌍 광대관','강남',200000,200,'guitar1','hello1',0,1,1,0,1,'id2','2017-11-17');
insert into STAGE values(8,'군대 부대관','군대',300000,100,'guitar2','hello2',1,0,0,1,1,'id3','2017-11-18');

insert into STAGE values(1, '테스트용', 'kosta', 200000, 100, '드럼, 건반, 앰프 3개, 스탠딩마이크 3개', '좋습니다.', 1, 1, 1, 1, 1, 'hjyj4841', '2017-01-01');
/* 대관 예약(주문) */
insert into STAGE_RESERVATION values(1,'id1',1,'2017-11-11',1,'2017-11-18');
insert into STAGE_RESERVATION values(2,'id2',2,'2018-03-10',1,'2017-11-19');
insert into STAGE_RESERVATION values(3,'id3',3,'2018-07-19',1,'2017-12-11');

/* 공연장 사진 */
insert into STAGE_IMAGE values();

/* 공연장 리뷰 */
insert into STAGE_REVIEW values(1,'id1',5,'너무 멋져요','2017-12-12');
insert into STAGE_REVIEW values(4,'id2',4,'괘찮네','2017-12-13');
insert into STAGE_REVIEW values(2,'id1',2,'너무 구려요','2017-12-14');
insert into STAGE_REVIEW values(3,'id2',3,'멋져','2017-12-15');
insert into STAGE_REVIEW values(2,'id3',4,'동엽짱짱맨','2017-12-16');
insert into STAGE_REVIEW values(1,'id4',1,'너무 멋져요','2017-12-17');
insert into STAGE_REVIEW values(4,'hong1653',3,'하하하','2017-12-18');
insert into STAGE_REVIEW values(5,'kim4845',4,'너무 구려요','2017-12-19');
insert into STAGE_REVIEW values(3,'beck4848',5,'갓태경','2017-12-20');
insert into STAGE_REVIEW values(5,'lee534',2,'동엽신','2017-12-21');

/* 공연정보(아티스트 게시물) */
insert into PERFORMANCE values('2', '야탑 공연', '야탑 정기 공연','야탑 라이브', '2017-11-28', 125, '많이 놀러오세요.', 'c://img/img1','kim4845', '3','2017-12-22');
insert into PERFORMANCE values('3', '부산 공연', '붓산 사나이','부산 놀이터', '2017-12-22', 30, '보러오세요.', 'c://img/img1','yunee33', '4','2017-12-23');
insert into PERFORMANCE values(4,'컬투쇼','안녕하세요','홍대입구','2017/12/12',3,'안녕하시오','c://img/img1','id1',1,'2017-12-24');
insert into PERFORMANCE values(5,'리버풀쇼','리버풀 수비 할줄 몰라','홍대입구','2017/12/12',4,'안녕하시오','c://img/img1','id2',2,'2017-12-25');
insert into PERFORMANCE values(6,'맨유쇼','맹9','홍대입구','2017/12/12',2,'안녕하시오','c://img/img1','id3',3,'2017-12-26');
insert into PERFORMANCE (performance_no, performance_name, performance_title, performance_location, performance_date, performance_content, performance_image, performance_user_id, performance_reg_time)
values(99, '99', '99', '99', '2017-12-12', '111111', 'img', 'id1','2017-12-20');

delete from STAGE_RESERVATION
/* 공연정보 댓글 */
insert into PERFORMANCE_COMMENT values(1,1,'id4','푸하하하하하','2017-12-22');
insert into PERFORMANCE_COMMENT values(1,2,'id4','푸하하하하하ㅋㅋㅋㅋ','2017-12-23');
insert into PERFORMANCE_COMMENT values(2,1,'id4','푸ㅋ하ㅋ하ㅋ하ㅋ하ㅋ하ㅋ','2017-12-24');
insert into PERFORMANCE_COMMENT values(3,1,'id5','푸ㅈ하ㅈ하ㅈ하ㅈ하ㅈ하ㅈ','2017-12-25');
insert into PERFORMANCE_COMMENT values(4,1,'id6','안녕하세요','2017-12-26');
insert into PERFORMANCE_COMMENT values(5,1,'id5','와... 역시 갓버풀 멋집니다... 우승할거 같네요','2017-12-27');
insert into PERFORMANCE_COMMENT values(6,1,'id6','ㅋㅋㅋㅋㅋ허더스필드한테 짐 ㅋㅋㅋㅋㅋ','2017-12-28');

select * from PERFORMANCE_COMMENT

/* 공연정보 좋아요 */
insert into PERFORMANCE_LIKE values('1', 'hong1653');
insert into PERFORMANCE_LIKE values('1', 'kim4845');
insert into PERFORMANCE_LIKE values('1', 'beck4848');
insert into PERFORMANCE_LIKE values('1', 'kimm990');
insert into PERFORMANCE_LIKE values('1', 'no33432');
insert into PERFORMANCE_LIKE values('1', 'yunee33');
insert into PERFORMANCE_LIKE values('2', 'kimjr322');
insert into PERFORMANCE_LIKE values('2', 'kimm990');
insert into PERFORMANCE_LIKE values('2', 'kimdo327');



/* 동영상(게시물) */
insert into VIDEO values(1, '공연영상1', 'www.youtube1.com', '경기도 성남시', '좋은 공연이었습니다.', '2017-10-12', '메기 매운탕','','hjyj4841','2017-12-24');
insert into VIDEO values(2, '공연영상2', 'www.youtube2.com', '경기도 하남시', '재밌는 공연이었습니다.', '2017-11-12', 'mc경','','','2017-12-25');
insert into VIDEO values(3, '공연영상3', 'www.youtube3.com', '경상도 부산시', '유쾌한 공연이었습니다.', '2017-09-02', '메기 매운탕, 노래 개잘핵','','no33432','2017-12-26');

/* 동영상 댓글 */
insert into VIDEO_COMMENT values(1,1,'id4','ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ','2017-10-20');
insert into VIDEO_COMMENT values(1,2,'id4','ㅎㅎㅎㅎㅎㅎㅎ','2017-10-21');
insert into VIDEO_COMMENT values(2,1,'id4','재밌어서 또 보러 옴','2017-10-22');
insert into VIDEO_COMMENT values(3,1,'id5','푸ㅈ하ㅈ하ㅈ하ㅈ하ㅈ하ㅈ','2017-10-23');
insert into VIDEO_COMMENT values(4,1,'id6','ㅈㅈㅈㅈㅈㅈㅈㅈ','2017-10-24');
insert into VIDEO_COMMENT values(5,1,'id5','리버풀 우승각','2017-10-25');
insert into VIDEO_COMMENT values(6,1,'id6','동팡카쿠 ㅋㅋㅋ','2017-10-26');

/* 동영상 좋아요 */
insert into VIDEO_LIKE values('1', 'hong1653');
insert into VIDEO_LIKE values('1', 'no33432');
insert into VIDEO_LIKE values('1', 'kim4845');
insert into VIDEO_LIKE values('1', 'kimm990');
insert into VIDEO_LIKE values('1', 'kimdo327');
insert into VIDEO_LIKE values('1', 'kimjr322');
insert into VIDEO_LIKE values('2', 'kim4845');
insert into VIDEO_LIKE values('2', 'no33432');
insert into VIDEO_LIKE values('3', 'hong1653');

/* 중고상품(게시글) */
insert into USED_GOODS values(1, '기타 사세요', '기타1', '데임', 170000, 'c://img/img1', 1, '2017-01-12', '좋은 기타입니다. 사주세요.', 'kimjr322','2017-10-23');
insert into USED_GOODS values(2, '기타 좀 사주세요', '기타2', '스윙', 80000, 'c://img/img2', 1, '2017-11-10', '팔아요', 'suck1598','2017-10-24');
insert into USED_GOODS values(3, '베이스 팔아요', '베이스1', '데임', 210000, 'c://img/img3', 1, '2017-09-21', '사세요.', 'hong1653','2017-10-25');
insert into USED_GOODS values(4, '앰프 상태 상', '앰프1', '애플', 30000, 'c://img/img4', 4, '2017-08-02', '좋아요', 'kimdo327','2017-10-26');
insert into USED_GOODS values(5, '건반 거래 합니다', '건반1', '야마하', 300000, 'c://img/img5', 1, '2017-02-28', '깨끗', 'kimjr322','2017-10-27');

/* 주문 */
insert into ORDERS values(1, 1, 'sim1', 0,'2017-10-25');
insert into ORDERS values(2, 5, 'suck1598', 1,'2017-10-26');
insert into ORDERS values(3, 3, 'nam8118', 0,'2017-10-27');
insert into ORDERS values(4, 4, 'sim1', 0,'2017-10-28');

/* 주문정보(배송정보) */
insert into ORDER_DETAIL values(1, '경기도 광주시 오포읍', 1, 170000, '01032362569');
insert into ORDER_DETAIL values(2, '경기도 성남시 분당구', 1, 300000, '01011111111');
insert into ORDER_DETAIL values(3, '경기도 오산시', 1, 210000, '01099558866');
insert into ORDER_DETAIL values(4, '강원도 강릉시', 3, 90000, '01022222224');

/* 장바구니 */
insert into BASKET values(4, 'lee534', 1);
insert into BASKET values(3, 'no33432', 1);
insert into BASKET values(4, 'yunee33', 2);

/* 중고상품 댓글 */
insert into USED_COMMENT values(1,1,'id4','정말 좋아요','2017-10-29');
insert into USED_COMMENT values(1,2,'id4','정말 구려요','2017-09-10');
insert into USED_COMMENT values(2,1,'id4','에누리 되나요','2017-09-11');
insert into USED_COMMENT values(3,1,'id5','팔렸나요 ?','2017-09-12');
insert into USED_COMMENT values(4,1,'id6','개좋다...','2017-09-13');
insert into USED_COMMENT values(5,1,'id5','이거 사면 리버풀 우승각?','2017-09-14');
insert into USED_COMMENT values(6,1,'id6','이거 사도 맨유 우승 못해','2017-09-15');

/* 구매희망 */
insert into USED_GOODS_WISH values(1, '판매 부탁드려요', '폴앤폴 250', '구매원함', 'sim1','2017-09-16');
insert into USED_GOODS_WISH values(2, '급구', '릴리즈 70', '파시오', 'yunee33','2017-09-17');
insert into USED_GOODS_WISH values(3, '구해요', '펜더 재즈 usa', '급구', 'kimm990','2017-09-18');
insert into USED_GOODS_WISH values(4, '구매 원함', '릴리즈 70 콘서트', '빨리좀', 'kimbo88','2017-09-10');
insert into USED_GOODS_WISH values(5, 'please', '데임 폴앤폴 350', '올려요', 'kimbo88','2017-09-10');


/* 구인 - 인재구함 */
insert into EMPLOY values(1,'기타 치는 놈 구해요','말 그대로','분당','2017-10-28',500000,'기타 중급','id1','2017-09-10');
insert into EMPLOY values(2,'드럼 치는 놈 구해요','말 그대로','인천','2017-10-28',400000,'드럼 숙련자','id2','2017-09-17');
insert into EMPLOY values(3,'바이올린 치는 놈 구해요','말 그대로','홍대','2017-10-28',300000,'바이올린 전문가','id3','2017-09-15');
insert into EMPLOY values(4,'첼로 치는 놈 구해요','말 그대로','강남','2017-10-28',200000,'첼로 전문가','id4','2017-09-13');
insert into EMPLOY values(5,'마술 좀 하는 사람 구해요','말 그대로','천안','2017-10-28',1000000,'마술 숙련자','sim1','2017-09-12');

/* 구직 - 구직희망 - 상품에서 주문테이블 */
insert into APPLICANT values(1,'내가 기타좀 칩니다', 'kimm990','2017-09-15');
insert into APPLICANT values(2,'내가 드럼좀 칩니다', 'lee534','2017-09-20');
insert into APPLICANT values(1,'기타 더 잘 칩니다', 'hong1653','2017-09-30');
insert into APPLICANT values(3,'바이올린 ㅈ고수입니다', 'choi22','2017-09-22');
insert into APPLICANT values(4,'첼로 빡고수', 'yunee33','2017-09-23');
insert into APPLICANT values(5,'마술로 님 사라지게 해드림', 'no33432','2017-09-24');

delete from APPLICANT where APPLICANT_ID='hong1653';

/* 레슨 - 선생님 */
insert into TEACHERS values(1,'기타 배울 사람~','기타 가르쳐주마','인천','2018-01-01',200000,'기타 2개','kimm990','2017-08-10');
insert into TEACHERS values(2,'드럼 배울 사람~','드럼 가르쳐주마','분당','2018-02-02',300000,'드럼 2세트','lee534','2017-08-11');
insert into TEACHERS values(3,'마술 배울 사람~','마술 가르쳐주마','천안','2018-03-03',400000,'트럼프 카드','kimp123','2017-08-10');
insert into TEACHERS values(4,'춤 배울 사람~','춤 가르쳐주마','대구','2018-04-04',150000,'연습장','kimjr322','2017-08-12');
insert into TEACHERS values(5,'랩 배울 사람~','랩 가르쳐주마','부산','2018-05-05',250000,'','yunee33','2017-08-13');

/* 레슨 - 학생 */
insert into STUDENTS values(1,'GGGG', 'id5','2017-08-14');
insert into STUDENTS values(2,'ZZZZ', 'id6','2017-08-15');
insert into STUDENTS values(1,'AAAA', 'id7','2017-08-16');
insert into STUDENTS values(3,'DDDD', 'id8','2017-08-17');
insert into STUDENTS values(4,'RRRR', 'nam8118','2017-08-18');
insert into STUDENTS values(5,'KKKK', 'kimbo88','2017-08-19');

/* 고객센터 */
insert into HELP values(1, '신고게시판', '신고합니다', '부적절한 게시물을 올렸습니다.', '', '', 'yoonkyu','2017-08-10');
insert into HELP values(2, '문의게시판', '문의합니다', '문의', 'c://img/img2', 'c://img/img2-1', 'yoonkyu','2017-08-20');
insert into HELP values(3, '문의게시판', '문문비행운', '좋아요좋아요', '', '', 'yoonkyu','2017-08-25');
insert into HELP values(4, '문의게시판', '좋네요', '정말좋아요', 'c://img/img4', 'c://img/img4-1', 'yunee33','2017-08-30');
insert into HELP values(5, '신고게시판', '너신고', '신고합니다', 'c://img/img5', 'c://img/img5-1', 'kimbo88','2017-08-27');
insert into HELP values(6, '문의게시판', '아아아아아아', '안되요', 'c://img/img6', 'c://img/img6-1', 'sim1','2017-08-18');

select * from help;
/* 고객센터 댓글 */
insert into HELP_COMMENT values(1, 1, '네네네네네네네', 'yunee33','2017-07-10');
insert into HELP_COMMENT values(2, 2, 'sp2sp2', 'no33432','2017-07-11');
insert into HELP_COMMENT values(3, 3, 'okokok', 'sim1','2017-07-12');
insert into HELP_COMMENT values(4, 4, 'gogogogogo', 'no33432','2017-07-22');
insert into HELP_COMMENT values(5, 1, 'backback', 'lee534','2017-07-13');
insert into HELP_COMMENT values(6, 5, '그래그래그래', 'kimdo327','2017-07-16');
insert into HELP_COMMENT values(7, 2, '아니야아니야', 'kim4845','2017-07-17');
insert into HELP_COMMENT values(8, 6, '몰라몰라몰라', 'kim4845','2017-07-18');
insert into HELP_COMMENT values(9, 3, 'goodgood', 'yunee33','2017-07-19');
insert into HELP_COMMENT values(10, 2, 'nicenice', 'yunee33','2017-07-20');



select * from PERFORMANCE;
















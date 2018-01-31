/*-------------------- 공연장  ------------------------------------------------*/
/* 공연장 목록조회  */
	select STAGE_NO, STAGE_NAME,STAGE_LOCATION from STAGE; /* 별점도 넣어야하나? */ 
	select * from stage;
	
	select count(*) 
		from stage
		where stage_rental_date >= '2017-12-10'
		and stage_rental_date <= '2017-12-20'
		
	select *
			from (	select *
					from stage
					where stage_rental_date >= '2017-12-10'
					and stage_rental_date <= '2017-12-20'
					)
	where stage_instrument like '%기타%'
	
	
/* 공연장 검색 후 조회수 */

	select * from performance
	/* 리버풀쇼 이라는 공연의 공연장을 검색(키워드로 검색) */
	select PERFORMANCE.PERFORMANCE_NAME, STAGE.* from STAGE,PERFORMANCE where STAGE.STAGE_NO = PERFORMANCE.STAGE_NO and PERFORMANCE_NAME='리버풀쇼';

	/* 주소로 검색 */
	select * from STAGE where STAGE_LOCATION like '%홍대%';

	/* 음주가 불가능한 공연장 검색 */
	select * from STAGE where STAGE_FOOD_SELL=0;

	/* 주차장 o, 음주 x, 음식 x, 외부음식 o 인 공연장 검색 */
	select * from STAGE where STAGE_PARKING=1 and not STAGE_DRINKING=1 and not STAGE_FOOD_SELL=1 and STAGE_FOOD_RESTRICTION=1;
	
	/* 구비된 악기로 검색(기타가 구비된 공연장) */
	select * from STAGE where STAGE_INSTRUMENT like '%기타%';

	/* 별점이 높은 순서대로 목록 조회 */
	select SS.SS_AVG, STAGE.STAGE_NO, STAGE.STAGE_NAME, STAGE.STAGE_LOCATION
	from STAGE, ( select STAGE_NO, avg(STAR_SCORE) SS_AVG
			  from STAGE_REVIEW
			  group by STAGE_NO ) SS
	where SS.STAGE_NO = STAGE.STAGE_NO
	order by SS.SS_AVG DESC;
	


/* 공연장 상세정보조회 */
	
	/* 홍대관(공연장명)의 상세정보 */ 
	select * from STAGE where STAGE_NAME like '%홍대관';

/* 별점등록 후 아이디가 작성한 댓글 검색 -> 마이페이지에서 내가 등록한 리뷰 로 하면 될듯 */
	select STAGE_REVIEW.STAGE_NO, STAGE_REVIEW.STAR_SCORE,STAGE_REVIEW.STAGE_COMMENT  from STAGE_REVIEW,USERS 
			where STAGE_REVIEW.STAGE_REVIEW_USER_ID = USERS.USER_ID and USER_ID='id1';
	

/* 대관신청(예약) 후 신청 확인 (select) */
	select RENTAL_NO, RENTAL_USER_ID, STAGE_NO, RENTAL_DATE
	from STAGE_RESERVATION,USERS
	where STAGE_RESERVATION.RENTAL_USER_ID=USERS.USER_ID and USER_ID='id2';

/*-----------------------------공연정보(아티스트 게시판)---------------------------------*/
/* 공연정보 목록 조회 */
select p.*, pl.like_count /* like_count = 좋아요 수 */
from PERFORMANCE p, (
	select performance_like_no, count(*) like_count
	from performance_like
	group by performance_like_no
	) pl
where p.performance_no = pl.performance_like_no(+);

/* 공연정보 검색 */
select p.*, pl.like_count /* like_count = 좋아요 수 */
from PERFORMANCE p, (
	select performance_like_no, count(*) like_count
	from performance_like
	group by performance_like_no
	) pl
where p.performance_no = pl.performance_like_no
/*and p.performance_name like '%서현%';*/ /* 공연이름으로 검색 */
/*and p.performance_location like '%서현%';*/ /* 공연장소로 검색 */
and p.performance_date >= '2017-11-23' /* 공연날짜로 검색 */		/* 시작 날짜 */
and p.performance_date <= '2017-11-28';						/* 종료 날짜 */

/* 공연자 정보 조회 */
select a.*
from PERFORMANCE p, ARTIST a
where p.performance_user_id = a.artist_user_id /* 공연정보에 게시글을 올린 아티스트만 조회됨 */
and p.performance_no = 1; /* 조회할 공연정보 id */

/* 공연장 정보 조회 */
select s.*
from PERFORMANCE p, STAGE s
where p.stage_no = s.stage_no
and p.performance_no = 3; /* 조회할 공연정보 id */

/* 공연자 영상 조회 */
select v.*
from VIDEO v, (
	select a.artist_name
	from PERFORMANCE p, ARTIST a
	where p.performance_user_id = a.artist_user_id
	and p.performance_no = 2 /* 조회할 공연정보 id */
	)
where v.video_artist like '%'||artist_name||'%';

select * from performance

/********************** 공연 댓글 *********************************************/

/* 해당 공연 정보에 대한 댓글 목록 */
SELECT PERFORMANCE_COMMENT_NO, PERFORMANCE_COMMENT_USER_ID, PERFORMANCE_COMMENT
FROM PERFORMANCE_COMMENT
WHERE PERFORMANCE_NO=1
ORDER BY PERFORMANCE_COMMENT_NO;

/* ID1이 단 댓글 조회  -> 이건 마이페이지에 필요할 거 같아서 일단 마이페이지에도 넣어뒀음*/
SELECT * FROM PERFORMANCE_COMMENT WHERE PERFORMANCE_COMMENT_USER_ID='id1';

/***********************************중고거래*************************************************/
/* 거래등록글 조회 */
select *
from USED_GOODS;

/* 거래 상세 정보 조회 */
select *
from USED_GOODS
where used_goods_no = 3; /* 조회할 게시글 id */

/* 장바구니 조회 */
select u.used_goods_model, u.used_goods_brand, u.used_goods_cost, u.used_goods_image,
	u.used_goods_seller_id, b.basket_ea
from USED_GOODS u, BASKET b
where u.used_goods_no = b.used_goods_no
and b.basket_consumer_id = 'lee534'; /* 조회할 사용자 id */

/* 주문 정보 조회 */
select o.used_goods_image, o.used_goods_model, o.used_goods_brand, o.used_goods_seller_id,
	d.shipping_address, d.order_ea, d.PURCHASE_COST, d.ADDRESSEE_PHONE_NUM,
	o.cancel_code
from ORDER_DETAIL d, (
	select *
	from ORDERS o, USED_GOODS u
	where o.used_goods_no = u.used_goods_no
	) o
where d.order_no = o.order_no
and o.consumer_id = 'sim1'; /* 조회할 구매자 id */

/* 구매 희망 글 목록 조회 */
select *
from USED_GOODS_WISH;

/* 구매 희망 검색 후 목록 조회 */
select *
from USED_GOODS_WISH
where used_goods_wish_title like '%구매%' /* 글제목으로 검색 또는 */
or used_goods_wish_model like '%폴앤폴%'; /* 모델명으로 검색*/

/* 구매 희망 글 상세 조회 */
select *
from USED_GOODS_WISH
where used_goods_wish_no = 1; /* 조회할 게시판 번호 */

/*--------------------------------중고거래 댓글--------------------------------------*/

/* 해당 공연 정보에 대한 댓글 목록 */
SELECT USED_GOODS_COMMENT_NO, USED_GOODS_COMMENT_ID, USED_GOODS_COMMENT
FROM USED_COMMENT
WHERE USED_GOODS_NO=1
ORDER BY USED_GOODS_COMMENT_NO;

/* ID1이 단 댓글 조회  -> 이건 마이페이지에 필요할 거 같아서 일단 마이페이지에도 넣어뒀음*/
SELECT * FROM USED_COMMENT WHERE USED_GOODS_COMMENT_ID='id4';


/*********************************** 구인  *************************************************/

/* 구인 글 목록 조회 */
SELECT E.EMPLOY_NO, E.EMPLOY_TITLE, E.EMPLOY_LOCATION, E.EMPLOY_USER_ID FROM EMPLOY E,USERS U WHERE E.EMPLOY_USER_ID = U.USER_ID;

/* 필요한 재능으로 검색  */
SELECT * FROM EMPLOY WHERE EMPLOY_TALENT like '%기타%';

/* 가격 범위로 검색 */
SELECT * FROM EMPLOY WHERE EMPLOY_COST > 300000 AND EMPLOY_COST <700000;

/* 게시자의 프로필 검색 */
SELECT USERS.USER_ID, USERS.USER_NAME, USERS.USER_ADDRESS, USERS.USER_PHONE_NUM  FROM EMPLOY,USERS WHERE EMPLOY.EMPLOY_USER_ID = USERS.USER_ID AND EMPLOY_USER_ID = 'sim1';

/* 게시자가 신청 확인  */
SELECT * FROM APPLICANT WHERE APPLICANT_ID = (
	SELECT USER_ID FROM USERS WHERE USER_ID='lee534'
);

/* 거래번호로 신청확인 */
SELECT APPLICANT.* FROM EMPLOY, APPLICANT 
WHERE APPLICANT.EMPLOY_NO = EMPLOY.EMPLOY_NO AND APPLICANT.EMPLOY_NO=1;

/* 신청자 프로필 확인 */
SELECT USERS.USER_ID, USERS.USER_NAME, USERS.USER_ADDRESS, USERS.USER_PHONE_NUM
FROM APPLICANT, USERS WHERE APPLICANT.APPLICANT_ID = USERS.USER_ID AND USER_ID='yunee33';



/*********************************** 레슨 *************************************************/

/* 레슨 글 목록 조회 */
SELECT T.TEACHERS_NO, T.TEACHERS_TITLE, T.TEACHERS_LOCATION, T.TEACHERS_ID from TEACHERS T, USERS U WHERE T.TEACHERS_ID = U.USER_ID;

/* 1번 게시글 상세정보  조회 */
SELECT * from TEACHERS T, USERS U WHERE T.TEACHERS_ID = U.USER_ID AND T.TEACHERS_NO=1;

/* 아이디로 신청확인 */
SELECT * FROM STUDENTS WHERE STUDENTS_ID = (
	SELECT USER_ID FROM USERS WHERE USER_ID='id5'
);

/* 거래번호로 신청확인 */
SELECT STUDENTS.* FROM STUDENTS, TEACHERS 
WHERE TEACHERS.TEACHERS_NO = STUDENTS.TEACHERS_NO AND TEACHERS.TEACHERS_NO=1;

/* 신청자 프로필 확인 */
SELECT USERS.USER_ID, USERS.USER_NAME, USERS.USER_ADDRESS, USERS.USER_PHONE_NUM
FROM STUDENTS, USERS WHERE STUDENTS.STUDENTS_ID = USERS.USER_ID AND USER_ID='id8';

/*************************마이페이지******************************************/

/* 대관정보 전체 조회(id로 등록한(공급자)) */
select stage_name, stage_location, stage_cost, stage_area, stage_resurvation
from STAGE
where stage_seller_id = 'id3'; /* 조회할 공급자 id */

/* 대관정보 전체 조회(신청한 장소) */
select s.stage_name, s.stage_location, s.stage_cost, s.stage_area, s.stage_seller_id,
	r.rental_date, r.rental_state_code
from STAGE s, STAGE_RESERVATION r
where s.stage_no = r.stage_no
and r.rental_user_id = 'id2'; /* 조회할 신청자 id */

/* 공연 정보 조회(id로 등록한) */
select p.*, pl.like_count /* like_count = 좋아요 수 */
from PERFORMANCE p, (
	select performance_like_no, count(*) like_count
	from performance_like
	group by performance_like_no
	) pl
where p.performance_no = pl.performance_like_no(+)
and p.performance_user_id = 'id2';
/* 조회할 게시자 id */

/* 중고거래 정보 조회(장바구니) */
select u.used_goods_model, u.used_goods_brand, u.used_goods_cost, u.used_goods_image,
	u.used_goods_seller_id, b.basket_ea
from USED_GOODS u, BASKET b
where u.used_goods_no = b.used_goods_no
and b.basket_consumer_id = 'lee534'; /* 조회할 구매자 id */

/* 중고거래 정보 조회(주문내역) */
select o.used_goods_image, o.used_goods_model, o.used_goods_brand, o.used_goods_seller_id,
	d.shipping_address, d.order_ea, d.PURCHASE_COST, d.ADDRESSEE_PHONE_NUM,
	o.cancel_code
from ORDER_DETAIL d, (
	select *
	from ORDERS o, USED_GOODS u
	where o.used_goods_no = u.used_goods_no
	) o
where d.order_no = o.order_no
and o.consumer_id = 'sim1'; /* 조회할 구매자 id */

/* 중고거래 정보 조회(등록한 상품 조회) */
select *
from USED_GOODS
where used_goods_seller_id = 'kimjr322'; /* 조회할 등록자 id */

/* 중고거래 정보 조회(등록한 구매희망 조회) */
select *
from USED_GOODS_WISH
where used_goods_wish_user_id = 'kimbo88' /* 조회할 등록자 id */

/* 내가 팔로우한 회원 정보 조회 */
select u.user_name, u.user_address, u.user_address, u.user_phone_num, u.user_email
from FOLLOW f, USERS u
where f.follower_id = u.user_id
and f.following_id = 'sim1'; /* 팔로우한 회원 id */

/* 내가 팔로우한 회원 숫자 조회 */
select count(*)
from FOLLOW
where  following_id = 'sim1'; /* 조회할 회원 id */

/* 날 팔로우한 회원 숫자 조회 */
select count(*)
from FOLLOW 
where follower_id = 'kim4845'; /* 조회할 회원 id */

/* 내가 팔로우한 아티스트 정보 조회 */
select a.artist_name, a.artist_performance, a.artist_profile, a.artist_image, a.artist_members
from FOLLOW f, ARTIST a
where f.follower_id = a.Artist_user_id
and f.following_id = 'sim1'; /* 조회할 회원 id */

/* 나를 팔로우한 회원 정보 조회 */
select u.user_name, u.user_address, u.user_phone_num, u.user_email
from FOLLOW f, USERS u
where f.following_id = u.user_id
and f.follower_id = 'kim4845'; /* 조회할 회원 id */

/* 회원 정보 조회(회원) */
select user_id, user_name, user_address, user_phone_num, user_email
from USERS
where user_id = 'suck1598'; /* 조회할 회원 id */

/* 회원 정보 조회(아티스트) */
select a.artist_name, a.artist_performance, a.artist_profile, a.artist_image, a.artist_members
from USERS u, ARTIST a
where u.user_id = a.artist_user_id
and u.user_id = 'kim4845'; /* 조회할 회원 id */

/* 회원 정보 조회(대관공급자) */
select *
from USERS u, STAGE_SUPPLIER s
where u.user_id = s.operator_user_id
and u.user_id = 'id2'; /* 조회할 회원 id */

/* 공연영상(아티스트 프로필) 조회 */
select v.*
from VIDEO v,
	(
	select a.artist_name
	from USERS u, ARTIST a
	where u.user_id = a.artist_user_id
	and u.user_id = 'kim4845' /* 조회할 유저(아티스트) id */
	)
where v.video_artist like '%'||artist_name||'%';

/* 내가(id1) 단 댓글 조회 */
SELECT * FROM PERFORMANCE_COMMENT WHERE PERFORMANCE_COMMENT_USER_ID='id1';

/* 좋아요한 공연영상 조회 */
select v.*
from VIDEO_LIKE vl, VIDEO v
where vl.video_like_no = v.video_id
and vl.video_like_user_id = 'hong1653'; /* 조회할 회원 id */

/* 좋아요한 공연정보 조회 */
select p.*
from PERFORMANCE p, PERFORMANCE_LIKE pl
where pl.performance_like_no = p.performance_no
and pl.performance_like_user_id = 'kimm990'; /* 조회할 회원 id */

/* 올린 고객센터 글 조회 */
select *
from HELP
where help_user_id = 'kimbo88'; /* 조회할 회원 id */


/**********************동영상(게시물)*****************************************/

/* 영상 전체 조회 */
select v.*, vl.like_count
from VIDEO v, (
	select video_like_no, count(*) like_count
	from VIDEO_LIKE
	group by video_like_no
	) vl
where v.video_id = vl.video_like_no(+);

/* 영상 검색 조회 */
select v.*, vl.like_count
from VIDEO v, (
	select video_like_no, count(*) like_count
	from VIDEO_LIKE
	group by video_like_no
	) vl
where v.video_id = vl.video_like_no(+)
and video_artist like '%메기%'; /*아티스트 이름으로 조회 */
/*and video_title like '%영상1%';*/ /* 제목으로 조회 */

/* 영상 상세 조회 */
select v.*, vl.like_count
from VIDEO v, (
	select video_like_no, count(*) like_count
	from VIDEO_LIKE
	group by video_like_no
	) vl
where v.video_id = vl.video_like_no(+)
and v.video_id = 1; /* 조회할 게시판 id */

/*------------------------동영상 댓글---------------------------------------*/

/* 해당 공연 정보에 대한 댓글 목록 */
SELECT VIDEO_COMMENT_NO, VIDEO_COMMENT_USER_ID, VIDEO_COMMENT
FROM VIDEO_COMMENT
WHERE VIDEO_NO=1
ORDER BY VIDEO_COMMENT_NO;

/* ID1이 단 댓글 조회  -> 이건 마이페이지에 필요할 거 같아서 일단 마이페이지에도 넣어뒀음*/
SELECT * FROM VIDEO_COMMENT WHERE VIDEO_COMMENT_USER_ID='id4';

/**********************************고객센터***********************************/

/* 전체 목록 조회 */
select *
from HELP;

/* 카테고리로 조회 */
select *
from HELP
where help_category = '신고게시판'; /* 카테고리 이름 */

/* 글제목으로 조회 */
select *
from HELP
where help_title like '%신고%'; /* 검색할 내용 */

/* 상세보기 */
select *
from HELP
where help_no = 1; /* 조회할 게시글 id */

/* 해당게시물 댓글 목록 조회 */
select hc.*
from HELP h, HELP_COMMENT hc
where h.help_no = hc.help_no
and h.help_no = 1; /* 조회갈 게시글 id */












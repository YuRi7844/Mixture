update users
set user_password = '$2a$10$9Mx5vV4LRJThJhqcxrwFL.CO64itUT387QxcBdv1m7hkae8oTQIlq'
delete from VIDEO where video_no=61
select*from authority;
select*from video
WHERE VIDEO_CATEGORY = 'practice' AND VIDEO_USER_ID like'%1%'
select*from video
WHERE VIDEO_CATEGORY = 'practice' AND VIDEO_CONTENT like'%잘%'
select * from performance;
select*from PREMIUM_STAGE;
select*from PREMIUM_STAGE_RESERVATION;
alter premium_stage_option set stge_rental_date at='yyyy-mm-dd';
select*from premium_stage_option;
select*from premium_stage_Time;
SELECT     video_no,   video_title,   video_link,   video_location,   video_content,   video_date,   
video_artist,   video_category,   video_user_id,   video_reg_time,   video_hits      
FROM VIDEO      WHERE VIDEO_CATEGORY = 'practice' AND VIDEO_CONTENT like '%잘%'
insert into PREMIUM_STAGE_OPTION values(256, '2017-12-16', 1, 300000, 1111);
insert into PREMIUM_STAGE_OPTION values(257, '2018-01-18', 1, 230000, 1111);
insert into PREMIUM_STAGE_OPTION values(324, '2018-02-03', 1, 610000, 1111);
insert into PREMIUM_STAGE_OPTION values(400, '2017-12-25', 1, 550000, 1111);
SELECT PERFORMANCE_LIKE_NO, COUNT(*)
FROM PERFORMANCE_LIKE
GROUP BY PERFORMANCE_LIKE_NO
ORDER BY COUNT(*)


/*-----------------------------공연정보(아티스트 게시판)---------------------------------*/
/* 공연정보 목록 조회 */
select p.*, pl.like_count /* like_count = 좋아요 수 */
from PERFORMANCE p, (
	select performance_like_no, count(*) like_count
	from PERFORMANCE_LIKE
	group by performance_like_no
	) pl
where p.performance_no = pl.performance_like_no(+);

/*  공연정보 게시판 글 읽기 (디테일 뷰)*/
select * from performance where performance_no = 99;

/* 공연 정보 게시물 조회 조회수 1 증가 */
update performance
set performance_hits = performance_hits+1
where performance_no = 1;

/* 공연정보 검색 */
select p.*, pl.like_count /* like_count = 좋아요 수 */
from PERFORMANCE p, (
	select performance_like_no, count(*) like_count
	from PERFORMANCE_LIKE
	group by performance_like_no
	) pl
where p.performance_no = pl.performance_like_no(+)
and p.performance_date >= '2017-11-23' /* 공연날짜로 검색 */		/* 시작 날짜 */
and p.performance_date <= '2017-12-12';						/* 종료 날짜 */
/*and p.performance_title like '%요%';*/ /* 공연제목으로 검색 */
/*and p.performance_name like '%쇼%';*/ /* 공연이름으로 검색 */
/*and p.performance_location like '%구%';*/ /* 공연장소로 검색 */
select p.*, pl.like_count /* like_count = 좋아요 수 */
from PERFORMANCE p, (
	insert into performance_no, performance_name, 
		performance_title, 
		performance_location, 
		performance_date, 
		performance_content, 
		performance_image, 
		performance_user_id
	from PERFORMANCE
	) pl
where p.performance_no = pl.performance_like_no(+)
and p.performance_date >= '2017-11-23' /* 공연날짜로 검색 */		/* 시작 날짜 */
and p.performance_date <= '2017-12-12';			

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

/* 공연자(공연장) 영상 조회 */
select v.*
from VIDEO v, (
	select a.artist_name
	from PERFORMANCE p, ARTIST a
	where p.performance_user_id = a.artist_user_id
	and p.performance_no = 2 /* 조회할 공연정보 id */
	)
where v.video_artist like '%'||artist_name||'%';

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

/**********************************마이페이지******************************************/
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
and p.performance_user_id = 'id2'; /* 조회할 게시자 id */

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

/* 구인구직(등록한 레슨 목록보기) */
select *
from TEACHERS
where teachers_id = 'kimm990' /* 조회할 회원 id */

/* 구인구직(등록한 레슨의 학생 목록 보기) */
select s.*
from TEACHERS t, (
	select s.students_comment, u.user_name, u.user_address, u.user_phone_num, u.user_email, s.teachers_no
	from STUDENTS s, USERS u
	where s.students_id = u.user_id
	) s
where t.teachers_no = s.teachers_no
and t.teachers_no = 4;

/* 구인구직(신청한 레슨 보기) */
select t.*, s.students_comment
from TEACHERS t, STUDENTS s
where t.teachers_no = s.teachers_no
and s.students_id = 'id5'; /* 조회할 회원 id */

/* 구인구직(올린 구인글 보기) */
select *
from EMPLOY
where employ_user_id = 'id1'; /* 조회할 회원 id */

/* 구인구직(올린 구인글의 구직자 보기) */
select a.*
from EMPLOY e, (
	select a.applicant_profile, u.user_name, u.user_address, u.user_phone_num, u.user_email, a.employ_no
	from APPLICANT a, USERS u
	where a.applicant_id = u.user_id
	) a
where e.employ_no = a.employ_no
and e.employ_no = 2; /* 조회할 게시글 id */

/* 구인구직(신청한 구직글 보기) */
select e.*, a.applicant_profile
from EMPLOY e, APPLICANT a
where e.employ_no = a.employ_no
and a.applicant_id = 'no33432'; /* 조회할 회원 id */

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
where user_id = 'id-1'; /* 조회할 회원 id */

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

/***********************************동영상(게시물)*****************************************/
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





/*help test*/
insert into HELP (
		 help_no,
		 help_category,
		 help_title,
		 help_content,
		 help_user_id,
		 help_reg_time) 
values (3, 
	'카테2', 
	'제목2', 
	'내용2', 
	'admin3', 
	sysdate);
	select * from help;
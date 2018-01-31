/*
마이페이지
대관정보전체조회 - id로 등록한장소조회, 신청한 장소조회
공연정보조회 - id로 등록한 공연조회
중고거래정보조회 - 장바구니, 주문내역, 등록한 상품조회
재능거래정보 - 등록한 레슨, 구인구직
아티스트 프로필 조회 - 팔로우한 아티스트 정보 조회
회원정보조회 - 회원 / 아티스트/ 대관공급자 정보 수정
기존회원에서 아티스트 / 대관공급자 등록
*/
insert into AUTHORITY (user_id, AUTHORITY) values ('id-11','ROLE_ADMIN') 

SELECT * FROM PERFORMANCE
select * from users
SELECT * FROM VIDEO_LIKE 
select * from artist
select * from authority
select * from stage_supplier
select * from video
select * from video_comment
select * from premium_stage --189291
select * from premium_stage_option
select * from premium_stage_image
select * from follow

insert into video_like (VIDEO_LIKE_NO,VIDEO_LIKE_USER_ID) values (3,'id-2')

-- 검색 TEST
insert into PREMIUM_STAGE_OPTION (OPTION_NO, STAGE_RENTAL_DATE, STAGE_STATE, ESTABLISH_NO) values (1,'2017-12-10',0,189291);
insert into premium_stage_option (OPTION_NO, STAGE_RENTAL_DATE, STAGE_STATE, ESTABLISH_NO)values(2,'2017-12-12',0,398719);

insert into stage

select * from performance group by performance_no

delete from authority where user_id = 'id-10';
delete from artist where artist_id

		SELECT  P.PERFORMANCE_NO, COUNT(PERFORMANCE_USER_ID)
		FROM	PERFORMANCE P , PERFORMANCE_LIKE L
		GROUP BY P.PERFORMANCE_NO
		WHERE	P.PERFORMANCE_NO = L.PERFORMANCE_LIKE_NO
		ORDER BY COUNT(PERFORMANCE_USER_ID) DESC;



SELECT PERFORMANCE_LIKE_NO, COUNT(*)
FROM PERFORMANCE_LIKE
GROUP BY PERFORMANCE_LIKE_NO
ORDER BY COUNT(*) DESC

		SELECT 	FOLLOWING_ID, COUNT(*)
		FROM	FOLLOW
		GROUP BY FOLLOWING_ID
		ORDER BY COUNT(*) DESC


UPDATE VIDEO SET VIDEO_LINK=
'<div style="position:relative;height:0;padding-bottom:56.21%"><iframe src="https://www.youtube.com/embed/IsR5mcQRqjM?ecver=2" style="position:absolute;width:100%;height:100%;left:0" width="641" height="360" frameborder="0" gesture="media" allow="encrypted-media" allowfullscreen></iframe></div>'
WHERE video_user_id = 'id3';


SELECT 
video_no,
		video_title,
		video_link,
		video_location,
		video_content,
		video_date,
		video_artist,
		video_category,
		video_user_id
FROM	VIDEO
WHERE VIDEO_CATEGORY = 'user'

		SELECT 
			u.USER_ID,
			u.USER_NAME,
			u.USER_PASSWORD,
			u.USER_ADDRESS,
			u.USER_PHONE_NUM,
			u.USER_EMAIL 
		FROM VIDEO_LIKE v, USERS u 
		WHERE v.VIDEO_LIKE_NO = 1 AND v.USER_ID = u.USER_ID

INSERT INTO USERS (USER_ID, USER_NAME, USER_PASSWORD, USER_ADDRESS, USER_PHONE_NUM, USER_EMAIL)
VALUES ('id-1','엄엄1','111','경기도성남시','01000000000','A@A.COM');
INSERT INTO USERS (USER_ID, USER_NAME, USER_PASSWORD, USER_ADDRESS, USER_PHONE_NUM, USER_EMAIL)
VALUES ('id-2','엄엄2','222','경기도성남시','01011111111','B@B.COM');
INSERT INTO USERS (USER_ID, USER_NAME, USER_PASSWORD, USER_ADDRESS, USER_PHONE_NUM, USER_EMAIL)
VALUES ('id-3','엄엄3','222','경기도성남시','01022222222','c@c.COM');
INSERT INTO USERS (USER_ID, USER_NAME, USER_PASSWORD, USER_ADDRESS, USER_PHONE_NUM, USER_EMAIL)
VALUES ('id-4','엄엄4','222','경기도성남시','01011111111','d@d.com');

INSERT INTO EMPLOY(
	EMPLOY_NO,
	EMPLOY_TITLE,
	EMPLOY_CONTENT,
	EMPLOY_LOCATION,
	EMPLOY_DATE,
	EMPLOY_COST,
	EMPLOY_TALENT,
	EMPLOY_USER_ID
) VALUES (
	'1','제목1','내용1','지역1',SYSDATE,'100','재능1','id-3'
);

INSERT INTO TEACHERS(
	TEACHERS_NO,
	TEACHERS_TITLE, /* 글제목 */
	TEACHERS_COMMENT, /* 내용 */
	TEACHERS_LOCATION, /* 장소 */
	TEACHERS_DATE, /* 날짜 */
	TEACHERS_COST, /* 가격 */
	TEACHERS_INSTRUMENT, /* 갖춰진 장비 */
	TEACHERS_ID /* 사용자아이디 */
) VALUES (
	1,'제목1','내용1','장소1',SYSDATE,10000,'장비1','id-4'
);



SELECT * FROM EMPLOY E,USERS U WHERE E.EMPLOY_USER_ID = U.USER_ID;

SELECT * from TEACHERS T, USERS U WHERE T.TEACHERS_ID = U.USER_ID;

INSERT INTO APPLICANT(
	EMPLOY_NO,
	APPLICANT_PROFILE,
	APPLICANT_ID
) VALUES (
	1,'프로필1','id-1'
);

INSERT INTO STUDENTS (
	TEACHERS_NO,
	STUDENTS_COMMENT,
	STUDENTS_ID
) VALUES (
	1,'추가입력1','id-2'
);

SELECT * FROM APPLICANT WHERE APPLICANT_ID = (
	SELECT USER_ID FROM USERS WHERE USER_ID='id-1'
);

SELECT * FROM STUDENTS WHERE STUDENTS_ID = (
	SELECT USER_ID FROM USERS WHERE USER_ID='id-2'
);


INSERT INTO VIDEO_LIKE
		(VIDEO_LIKE_NO, VIDEO_LIKE_USER_ID)
		VALUES
		(1, 'hjyj4841')



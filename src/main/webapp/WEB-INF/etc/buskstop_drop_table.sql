/*--------사용자------------------------------------------------------------------------*/

/* 사용자 */
DROP TABLE USERS 
	CASCADE CONSTRAINTS;
/*------권한테이블--------------------------------------------------------------------------*/

/* 권한테이블 */
DROP TABLE AUTHORITY 
	CASCADE CONSTRAINTS;

/*-----팔로우--------------------------------------------------------------------------*/

/* 팔로우 */
DROP TABLE FOLLOW 
	CASCADE CONSTRAINTS;
/*---------아티스트----------------------------------------------------------------------*/

/* 아티스트 */
DROP TABLE ARTIST 
	CASCADE CONSTRAINTS;
	
/*---------프리미엄 대관 공급자-----------------------------------------------------------------*/

/* 프리미엄 대관 공급장 */
DROP TABLE PREMIUM_STAGE 
	CASCADE CONSTRAINTS;
	
/*-------공연장 대관 신청---------------------------------------------------------------------*/

/* 공연장 대관 신청 */
DROP TABLE PREMIUM_STAGE_RESERVATION 
	CASCADE CONSTRAINTS;
	
DROP SEQUENCE PREMIUM_RESERVATION_NO_SEQ;
	
/*--------프리미엄 공급장 예약 옴션---------------------------------------------------------------------------*/

/* 프리미엄 공급장 예약 옴션 */
DROP TABLE PREMIUM_STAGE_OPTION 
	CASCADE CONSTRAINTS;

DROP SEQUENCE PREMIUM_OPTION_NO_SEQ;

/*----------------------------------------------------------------------------*/

/* 시간 */
DROP TABLE PREMIUM_STAGE_TIME 
	CASCADE CONSTRAINTS;
	
/*-----프리미엄 공연장 사진--------------------------------------------------------------------*/

/* 프리미엄 공연장 사진 */
DROP TABLE PREMIUM_STAGE_IMAGE 
	CASCADE CONSTRAINTS;
	
DROP SEQUENCE PREMIUM_STAGE_IMAGE_NO_SEQ;

/*------공연장(공급자 게시물)-----------------------------------------------------------------*/

/* 공연장(공급자 게시물) */
DROP TABLE STAGE 
	CASCADE CONSTRAINTS;
	
DROP SEQUENCE STAGE_NO_SEQ;
/*----대관 예약(주문)--------------------------------------------------------------------*/

/* 대관 예약(주문) */
DROP TABLE STAGE_RESERVATION 
	CASCADE CONSTRAINTS;
	
DROP SEQUENCE RENTAL_NO_SEQ;

/*---공연장 사진-----------------------------------------------------------------------*/

/* 공연장 사진 */
DROP TABLE STAGE_IMAGE 
	CASCADE CONSTRAINTS;

DROP SEQUENCE STAGE_IMAGE_NO_SEQ;
/*-공연장 리뷰----------------------------------------------------------------------*/

/* 공연장 리뷰 */
DROP TABLE STAGE_REVIEW 
	CASCADE CONSTRAINTS;
/*-------공연장 대관 시간코드--------------------------------------------------------------------------*/

/* 공연장 대관 시간코드 */
DROP TABLE STAGE_RESERVATION_TIME 
	CASCADE CONSTRAINTS;


/*---공연정보(아티스트 게시물)---------------------------------------------------------------*/


/* 공연정보(아티스트 게시물) */
DROP TABLE PERFORMANCE 
	CASCADE CONSTRAINTS;

DROP SEQUENCE PERFORMANCE_NO_SEQ;
/*--공연정보 댓글-----------------------------------------------------------------------*/

/* 공연정보 댓글 */
DROP TABLE PERFORMANCE_COMMENT 
	CASCADE CONSTRAINTS;
	
DROP SEQUENCE PERFORMANCE_COMMENT_NO_SEQ;
/*--- 공연정보 좋아요----------------------------------------------------------------------*/

/* 공연정보 좋아요 */
DROP TABLE PERFORMANCE_LIKE 
	CASCADE CONSTRAINTS;
/*------------------------------------------------------------------------------------*/




/*---동영상(게시물)-----------------------------------------------------------------------*/

/* 동영상(게시물) */
DROP TABLE VIDEO 
	CASCADE CONSTRAINTS;

DROP SEQUENCE VIDEO_NO_SEQ;
/*---동영상 댓글-------------------------------------------------------------------*/

/* 동영상 댓글 */
DROP TABLE VIDEO_COMMENT 
	CASCADE CONSTRAINTS;

DROP SEQUENCE VIDEO_COMMENT_NO_SEQ;
/*-동영상 좋아요-----------------------------------------------------------------------*/

/* 동영상 좋아요 */
DROP TABLE VIDEO_LIKE 
	CASCADE CONSTRAINTS;

/*------------------------------------------------------------------------------------*/




/*---중고상품(게시글)---------------------------------------------------------------------*/

/* 중고상품(게시글) */
DROP TABLE USED_GOODS 
	CASCADE CONSTRAINTS;

/*----주문---------------------------------------------------------------------------*/

/* 주문 */
DROP TABLE ORDERS 
	CASCADE CONSTRAINTS;
/*----주문정보(배송정보)----------------------------------------------------------------------*/

/* 주문정보(배송정보) */
DROP TABLE ORDER_DETAIL 
	CASCADE CONSTRAINTS;

/*--장바구니------------------------------------------------------------------------*/

/* 장바구니 */
DROP TABLE BASKET 
	CASCADE CONSTRAINTS;

/*---중고상품 댓글------------------------------------------------------------------------*/

/* 중고상품 댓글 */
DROP TABLE USED_COMMENT 
	CASCADE CONSTRAINTS;

/*-----구매희망-----------------------------------------------------------------------*/

/* 구매희망 */
DROP TABLE USED_GOODS_WISH 
	CASCADE CONSTRAINTS;

/*------------------------------------------------------------------------------------*/




/*---구인 - 인재구함-----------------------------------------------------------------------*/

/* 구인 - 인재구함 */
DROP TABLE EMPLOY 
	CASCADE CONSTRAINTS;

/*----구직 - 구직희망 - 상품에서 주문테이블------------------------------------------------------------------*/

/* 구직 - 구직희망 - 상품에서 주문테이블 */
DROP TABLE APPLICANT 
	CASCADE CONSTRAINTS;

/*----- 레슨 - 선생님-----------------------------------------------------------------------*/

/* 레슨 - 선생님 */
DROP TABLE TEACHERS 
	CASCADE CONSTRAINTS;
/*----- 레슨-학생------------------------------------------------------------------------*/

/* 레슨-학생 */
DROP TABLE STUDENTS 
	CASCADE CONSTRAINTS;

/*------------------------------------------------------------------------------------*/


/*---고객센터----------------------------------------------------------------------*/

/* 고객센터 */
DROP TABLE HELP 
	CASCADE CONSTRAINTS;
	
DROP SEQUENCE HELP_NO_SEQ;

/*---고객센터댓글------------------------------------------------------------------------*/

/* 고객센터댓글 */
DROP TABLE HELP_COMMENT 
	CASCADE CONSTRAINTS;


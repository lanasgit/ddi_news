INSERT INTO t_site_mng (SITE_NM, SITE_PATH, CLASS_NM, REG_DATE) VALUES ('never 검색1', 'https://search.naver.com/search.naver?where=news&ie=utf8&sm=nws_hty&query=', '.news_tit', CURRENT_TIMESTAMP);
INSERT INTO t_site_mng (SITE_NM, SITE_PATH, CLASS_NM, REG_DATE) VALUES ('never 검색2', 'https://search.naver.com/search.naver?where=news&ie=utf8&sm=nws_hty&query=', '.elss sub_tit', CURRENT_TIMESTAMP);
INSERT INTO t_site_mng (SITE_NM, SITE_PATH, CLASS_NM, REG_DATE) VALUES ('daum 사회', 'https://news.daum.net/society#1', '.link_txt', CURRENT_TIMESTAMP);
INSERT INTO t_site_mng (SITE_NM, SITE_PATH, CLASS_NM, REG_DATE) VALUES ('daum 경제', 'https://news.daum.net/economic#1', '.link_txt', CURRENT_TIMESTAMP);
INSERT INTO t_site_mng (SITE_NM, SITE_PATH, CLASS_NM, REG_DATE) VALUES ('google 검색', 'https://news.google.com/search?q=', '', CURRENT_TIMESTAMP);

INSERT INTO t_user_mng (EMAIL, PASSWORD, NAME, REG_DATE) VALUES ('test@test.com', '$2a$10$X784/iHbDn55zGu2m.6msOSBkF.PF8/tZfXqlFtyOHQ89ilFftb7i', '테스트용비밀번호1 ', CURRENT_TIMESTAMP);

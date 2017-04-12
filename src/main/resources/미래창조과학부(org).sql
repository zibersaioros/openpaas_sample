
INSERT INTO org_tbl VALUES (NULL, '미래창조과학부', '우리나라는 지난 60여 년 간 세계가 주목하는 눈부신 경제발전을 이루어 온 가운데, 이제 그간의 성장을 바탕으로 국민의 창의적 아이디어를 과학기술과 ICT에 접목하여 새로운 부가가치와 일자리를 창출하는 창조경제를 통해 국가경제의 새로운 도약을 이루어 나가고자 합니다.', 'http://www.msip.go.kr/', SYS_TIMESTAMP, SYS_TIMESTAMP);


SELECT
	id,
	label,
	[desc],
	url,
	created,
	modified
FROM
	org_tbl
WHERE
	ROWNUM BETWEEN 1 AND 100;

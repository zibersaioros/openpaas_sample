-- tier 0 '미래창조과학부'
INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', NULL, '장관', '미래창조과학부 장관 최양희', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', 'http://www.msip.go.kr/web/msipContents/contents.do?mId=MTYw', SYS_TIMESTAMP, SYS_TIMESTAMP	);

	-- tier 1 '장관'
	INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '장관', '장관정책보좌관', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
	INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '장관', '대변인', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
	INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '장관', '감사관', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
	INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '장관', '제1차관', '미래창조과학부 1차관 홍남기', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', 'http://www.msip.go.kr/web/msipContents/contents.do?mId=MTYx', SYS_TIMESTAMP, SYS_TIMESTAMP	);
	INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '장관', '제2차관', '미래창조과학부 2차관 최재유', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', 'http://www.msip.go.kr/web/msipContents/contents.do?mId=MTYy', SYS_TIMESTAMP, SYS_TIMESTAMP	);
	
		-- tier 2 '대변인'
		INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '대변인', '홍보담당관', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
		-- tier 2 '감사관'
		INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '감사관', '감사담당관', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
		-- tier 2 '제1차관'
		INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '제1차관', '운영지원과', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
		INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '제1차관', '창조경제조정관', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
		INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '제1차관', '기획조정실', '미래창조과학부내 각종 정책과 계획을 수립·조정하고 과학기술 및 ICT분야 해외진출 지원·국제협력 강화와 부내 소관 업무의 재난관리 및 비상업무를 총괄합니다.', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', 'http://www.msip.go.kr/web/msipContents/contents.do?mId=ODM=', SYS_TIMESTAMP, SYS_TIMESTAMP	);
		INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '제1차관', '연구개발정책실', '미래 성장기반 확충을 위한 기초·원천연구개발, 산·학·연 협력을 통한 기술사업화 촉진과 출연연구기관·국제과학비즈니스벨트·연구개발특구 육성 그리고 국가 전략분야인 우주, 원자력 분야 기술개발 및 산업화를 추진하고 있습니다.', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', 'http://www.msip.go.kr/web/msipContents/contents.do?mId=ODQ=', SYS_TIMESTAMP, SYS_TIMESTAMP	);
		INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '제1차관', '과학기술전략본부', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
		INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '제1차관', '창조경제기획국', '창조경제 실현을 위한 범부처 기본전략을 수립·조정 및 성과를 점검하고, 아이디어 사업화 플랫폼 구축·운영, 지역 창조경제 활성화 및 창조경제 문화확산 등 창조경제 생태계 조성을 위한 정책을 총괄합니다.', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', 'http://www.msip.go.kr/web/msipContents/contents.do?mId=MTI5', SYS_TIMESTAMP, SYS_TIMESTAMP	);
		INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '제1차관', '미래인재정책국', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
		-- tier 2 '제2차관'
		INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '제2차관', '정보통신정책실', '정보통신과 방송분야 정책의 총괄ㆍ조정을 통해 관련 기업의 창업 및 성장기반을 조성하고, 소프트웨어산업 육성ㆍ지원 정책의 수립 및 방송서비스ㆍ산업 진흥 관련 정책을 수립하여 추진하고 있습니다.', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', 'http://www.msip.go.kr/web/msipContents/contents.do?mId=MTI4', SYS_TIMESTAMP, SYS_TIMESTAMP	);
		INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '제2차관', '방송진흥정책국', '국가정보화 정책 총괄부서로서 국가정보화 기본계획 및 시행계획, 창조 비타민 프로젝트 추진, 빅데이터 및 DB산업 육성, 민간분야의 해킹 및 침해사고 대응, 정보보호 산업육성, 인터넷 중독 예방 및 해소, 정보격차 해소 등의 정책업무를 추진하고 있습니다.', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', 'http://www.msip.go.kr/web/msipContents/contents.do?mId=MTM0', SYS_TIMESTAMP, SYS_TIMESTAMP	);
		INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '제2차관', '통신정책국', '통신시장의 공정경쟁을 통한 통신사업 발전을 도모하고, 공공복리 및 통신서비스 이용자 이익 증진에 이바지하기 위해, 통신사업 관련 중장기 기본정책의 수립, 통신시장 경쟁정책, 통신서비스 이용제도, 통신자원 관리업무를 총괄합니다.', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', 'http://www.msip.go.kr/web/msipContents/contents.do?mId=MTM1', SYS_TIMESTAMP, SYS_TIMESTAMP	);
		INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '제2차관', '전파정책국', '한정된 전파자원을 확보(국제분배)하여 주파수 할당 등의 주파수 공급정책과 전파 혼간섭 방지를 위한 무선국 허가·검사, 국민이 안전한 전자파 환경 조성을 위한 방송통신기자재 적합성평가 및 전자파 인체보호 등의 전파관리 정책업무를 수행합니다.', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', 'http://www.msip.go.kr/web/msipContents/contents.do?mId=MTM2', SYS_TIMESTAMP, SYS_TIMESTAMP	);

			-- tier 3 '기획조정실'
			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '기획조정실', '정책기획관', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '기획조정실', '국제협력관', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '기획조정실', '비상안전기획관', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
			-- tier 3 '연구개발정책실'
			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '연구개발정책실', '기초원천연구정책관', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '연구개발정책실', '거대공공연구정책관', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '연구개발정책실', '연구성과혁신정책관', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
			-- tier 3 '과학기술전략본부'
			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '과학기술전략본부', '과학기술정책관', '과학기술기본법에 따라 국가과학기술심의회를 정점으로, 범부처 국가과학기술 혁신 정책 분야를 총괄 기획·조정합니다.', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', 'http://www.msip.go.kr/web/msipContents/contents.do?mId=MTMw', SYS_TIMESTAMP, SYS_TIMESTAMP	);
			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '과학기술전략본부', '연구개발투자심의관', '국가연구개발사업 예산의 배분·조정, 연도별 정부R&D 투자방향 및 기준 설정 등에 업무를 수행합니다.', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', 'http://www.msip.go.kr/web/msipContents/contents.do?mId=MTMx', SYS_TIMESTAMP, SYS_TIMESTAMP	);
			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '과학기술전략본부', '성과평가혁신관', '국가연구개발사업과 출연연구기관에 대한 평가를 수행하고 국가과학기술지식정보서비스(NTIS)를 통한 국가연구개발 정보의 제공 및 연구시설장비의 체계적 관리, 국가연구개발 제도 개선 업무를 담당합니다.', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', 'http://www.msip.go.kr/web/msipContents/contents.do?mId=MTMy', SYS_TIMESTAMP, SYS_TIMESTAMP	);
			-- tier 3 '창조경제기획국'
			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '창조경제기획국', '창조경제기획과', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '창조경제기획국', '창조경제기반과', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '창조경제기획국', '창조융합기획과', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '창조경제기획국', '미래성장전략과', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '창조경제기획국', '창조경제진흥과', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
			-- tier 3 '미래인재정책국'
			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '미래인재정책국', '미래인재정책과', '창조경제의 핵심주역인 창의적 과학기술·ICT인력 양성정책 수립, 한국과학기술원과 같은 과학기술특성화대학을 육성·지원, 모든 국민들이 과학을 쉽게 이해하고 체험할 수 있도록 과학기술문화를 확산하고 연구실 안전환경을 조성하는 업무를 수행하고 있습니다.', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', 'http://www.msip.go.kr/web/msipContents/contents.do?mId=MTMz', SYS_TIMESTAMP, SYS_TIMESTAMP	);
			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '미래인재정책국', '미래인재양성과', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '미래인재정책국', '미래인재기반과', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '미래인재정책국', '연구환경안전팀', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
			-- tier 3 '정보통신정책실'
			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '정보통신정책실', '인터넷융합정책관', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '정보통신정책실', '정보통신산업정책관', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '정보통신정책실', '소프트웨어정책관', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '정보통신정책실', '정보보호정책관', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
			-- tier 3 '방송진흥정책국'
			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '방송진흥정책국', '방송산업정책과', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '방송진흥정책국', '뉴미디어정책과', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '방송진흥정책국', '디지털방송정책과', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
			-- tier 3 '통신정책국'
			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '통신정책국', '통신정책기획과', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '통신정책국', '통신경쟁정책과', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '통신정책국', '통신이용제도과', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '통신정책국', '통신자원정책과', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
			-- tier 3 '전파정책국'
			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '전파정책국', '전파정책기획과', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '전파정책국', '전파방송관리과', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '전파정책국', '주파수정책과', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '전파정책국', '전파기반과', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
			
				-- tier 4 '정책기획관'
	 			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '정책기획관', '기획재정담당관', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
	 			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '정책기획관', '창조행정담당관', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
	 			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '정책기획관', '규제개혁법무담당관', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
	 			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '정책기획관', '정보화담당관', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);	 			
				-- tier 4 '국제협력관'
	 			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '국제협력관', '국제협력총괄담당관', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
	 			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '국제협력관', '미주아시아협력담당관', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
	 			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '국제협력관', '구주아프리카협력담당관', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
	 			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '국제협력관', '다자협력담당관', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
				-- tier 4 '기초원천연구정책관'
	 			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '기초원천연구정책관', '연구개발정책과', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
	 			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '기초원천연구정책관', '기초연구진흥과', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
	 			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '기초원천연구정책관', '원천기술과', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
	 			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '기초원천연구정책관', '생명기술과', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
	 			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '기초원천연구정책관', '융합기술과', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
				-- tier 4 '거대공공연구정책관'
	 			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '거대공공연구정책관', '거대공공연구정책과', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
	 			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '거대공공연구정책관', '우주기술과', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
	 			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '거대공공연구정책관', '원자력진흥정책과', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
	 			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '거대공공연구정책관', '거대공공연구협력과', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
				-- tier 4 '연구성과혁신정책관'
	 			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '연구성과혁신정책관', '연구성과혁신기획과', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
	 			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '연구성과혁신정책관', '연구성과활용정책과', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
	 			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '연구성과혁신정책관', '지역연구진흥과', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
	 			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '연구성과혁신정책관', '연구기관지원팀', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
				-- tier 4 '과학기술정책관'
	 			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '과학기술정책관', '과학기술정책과', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
	 			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '과학기술정책관', '미래전략기획과', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
	 			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '과학기술정책관', '과학기술전략과', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
	 			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '과학기술정책관', '과학기술정책조정과', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
				-- tier 4 '연구개발투자심의관'
	 			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '연구개발투자심의관', '연구예산총괄과', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
	 			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '연구개발투자심의관', '연구개발투자기획과', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
	 			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '연구개발투자심의관', '공공에너지조정과', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
	 			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '연구개발투자심의관', '기계정보통신조정과', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
	 			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '연구개발투자심의관', '생명기초조정과', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
				-- tier 4 '성과평가혁신관'
	 			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '성과평가혁신관', '성과평가혁신총괄과', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
	 			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '성과평가혁신관', '성과평가과', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
	 			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '성과평가혁신관', '연구제도과', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
				-- tier 4 '인터넷융합정책관'
	 			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '인터넷융합정책관', '정책총괄과', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
	 			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '인터넷융합정책관', '인터넷제도혁신과', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
	 			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '인터넷융합정책관', '융합신산업과', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
	 			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '인터넷융합정책관', '정보화기획과', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
	 			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '인터넷융합정책관', '네트워크진흥팀', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
				-- tier 4 '정보통신산업정책관'
	 			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '정보통신산업정책관', '정보통신정책과', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
	 			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '정보통신산업정책관', '정보통신방송기술정책과', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
	 			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '정보통신산업정책관', '정보통신방송기반과', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
	 			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '정보통신산업정책관', '정보통신산업과', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
				-- tier 4 '소프트웨어정책관'
	 			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '소프트웨어정책관', '소프트웨어정책과', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
	 			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '소프트웨어정책관', '소프트웨어산업과', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
	 			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '소프트웨어정책관', '소프트웨어진흥과', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
	 			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '소프트웨어정책관', '디지털콘텐츠과', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
				-- tier 4 '정보보호정책관'
	 			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '정보보호정책관', '정보보호기획과', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
	 			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '정보보호정책관', '사이버침해대응과', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
	 			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '정보보호정책관', '정보보호지원과', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
	 			INSERT INTO group_tbl VALUES (NULL, SELECT id FROM org_tbl WHERE org_tbl.label = '미래창조과학부', SELECT id FROM group_tbl WHERE group_tbl.label = '정보보호정책관', '정보활용지원팀', '', '', 'http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif', '', SYS_TIMESTAMP, SYS_TIMESTAMP	);
			
			

SELECT
	id,
	org_id,
	parent_id,
	label,
	[desc],
	thumb_img_name,
	thumb_img_path,
	url,
	created,
	modified
FROM
	group_tbl
WHERE
	ROWNUM BETWEEN 1 AND 200;


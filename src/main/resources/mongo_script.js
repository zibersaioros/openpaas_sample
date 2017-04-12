db.createCollection("Orgs")
db.createCollection("Groups")

id = ObjectId()
db.Orgs.insert({
	_id : id,
	label : "정부조직도",
	desc : "정부용조직도",
	url : "",
	created : id.getTimestamp(),
	modified : null
})




id = ObjectId()
db.Groups.insert({
	_id : id,
	orgId : db.Orgs.findOne({label:"정부조직도"})._id,
	parentId : null,
	label : "대통령",
	desc : "",
	thumbImgPath : "https://pbs.twimg.com/profile_images/3301977474/c1e67d53c915dbeca21ca7fa2db43590.jpeg",
	thumbImgName : "",
	url : "http://www.president.go.kr/",
	created : id.getTimestamp(),
	modified : null
})

id = ObjectId()
db.Groups.insert({
	_id : id,
	orgId : db.Orgs.findOne({label:"정부조직도"})._id,
	parentId : db.Groups.findOne({label:"대통령"})._id,
	label : "대통령비서실",
	desc : "",
	thumbImgPath : "https://pbs.twimg.com/profile_images/3301977474/c1e67d53c915dbeca21ca7fa2db43590.jpeg",
	thumbImgName : "",
	url : "http://www.president.go.kr/",
	created : id.getTimestamp(),
	modified : null
})

id = ObjectId()
db.Groups.insert({
	_id : id,
	orgId : db.Orgs.findOne({label:"정부조직도"})._id,
	parentId : db.Groups.findOne({label:"대통령"})._id,
	label : "국가안보실",
	desc : "",
	thumbImgPath : "https://pbs.twimg.com/profile_images/3301977474/c1e67d53c915dbeca21ca7fa2db43590.jpeg",
	thumbImgName : "",
	url : "http://www.president.go.kr/",
	created : id.getTimestamp(),
	modified : null
})

id = ObjectId()
db.Groups.insert({
	_id : id,
	orgId : db.Orgs.findOne({label:"정부조직도"})._id,
	parentId : db.Groups.findOne({label:"대통령"})._id,
	label : "대통령경호실",
	desc : "",
	thumbImgPath : "https://scontent.xx.fbcdn.net/hphotos-ash2/v/t1.0-9/487597_402372013224961_700435684_n.png?oh=0ffbf9788352c5758ffa97292e25f24e&oe=56D7DCB2",
	thumbImgName : "",
	url : "https://www.facebook.com/pages/%EB%8C%80%ED%86%B5%EB%A0%B9%EA%B2%BD%ED%98%B8%EC%8B%A4/401816969947132",
	created : id.getTimestamp(),
	modified : null
})

id = ObjectId()
db.Groups.insert({
	_id : id,
	orgId : db.Orgs.findOne({label:"정부조직도"})._id,
	parentId : db.Groups.findOne({label:"대통령"})._id,
	label : "국가정보원",
	desc : "",
	thumbImgPath : "http://www.nis.go.kr/resources/img/content-img/img-nis08-01.png",
	thumbImgName : "",
	url : "http://www.nis.go.kr/",
	created : id.getTimestamp(),
	modified : null
})

id = ObjectId()
db.Groups.insert({
	_id : id,
	orgId : db.Orgs.findOne({label:"정부조직도"})._id,
	parentId : db.Groups.findOne({label:"대통령"})._id,
	label : "감사원",
	desc : "",
	thumbImgPath : "http://www.bai.go.kr/bai/images/contents/symbol_type1.gif",
	thumbImgName : "",
	url : "http://www.bai.go.kr/",
	created : id.getTimestamp(),
	modified : null
})

id = ObjectId()
db.Groups.insert({
	_id : id,
	orgId : db.Orgs.findOne({label:"정부조직도"})._id,
	parentId : db.Groups.findOne({label:"대통령"})._id,
	label : "방송통신위원회",
	desc : "",
	thumbImgPath : "https://pbs.twimg.com/profile_images/378800000136401460/edc74e8ba04a27b7a530069c46650091.jpeg",
	thumbImgName : "",
	url : "http://www.kcc.go.kr/",
	created : id.getTimestamp(),
	modified : null
})

id = ObjectId()
db.Groups.insert({
	_id : id,
	orgId : db.Orgs.findOne({label:"정부조직도"})._id,
	parentId : db.Groups.findOne({label:"대통령"})._id,
	label : "국무총리",
	desc : "",
	thumbImgPath : "http://cphoto.asiae.co.kr/listimglink/6/2008060508540732682_2.jpg",
	thumbImgName : "",
	url : "http://www.pmo.go.kr/",
	created : id.getTimestamp(),
	modified : null
})

id = ObjectId()
db.Groups.insert({
	_id : id,
	orgId : db.Orgs.findOne({label:"정부조직도"})._id,
	parentId : db.Groups.findOne({label:"국무총리"})._id,
	label : "국무조정실",
	desc : "",
	thumbImgPath : "http://cphoto.asiae.co.kr/listimglink/6/2008060508540732682_2.jpg",
	thumbImgName : "",
	url : "http://www.pmo.go.kr/",
	created : id.getTimestamp(),
	modified : null
})

id = ObjectId()
db.Groups.insert({
	_id : id,
	orgId : db.Orgs.findOne({label:"정부조직도"})._id,
	parentId : db.Groups.findOne({label:"국무총리"})._id,
	label : "국무총리비서실",
	desc : "",
	thumbImgPath : "http://cphoto.asiae.co.kr/listimglink/6/2008060508540732682_2.jpg",
	thumbImgName : "",
	url : "http://www.pmo.go.kr/",
	created : id.getTimestamp(),
	modified : null
})

id = ObjectId()
db.Groups.insert({
	_id : id,
	orgId : db.Orgs.findOne({label:"정부조직도"})._id,
	parentId : db.Groups.findOne({label:"국무총리"})._id,
	label : "국민안전처",
	desc : "",
	thumbImgPath : "http://www.mpss.go.kr/images/main/intro/emblem_standard.png",
	thumbImgName : "",
	url : "http://www.mpss.go.kr/",
	created : id.getTimestamp(),
	modified : null
})

id = ObjectId()
db.Groups.insert({
	_id : id,
	orgId : db.Orgs.findOne({label:"정부조직도"})._id,
	parentId : db.Groups.findOne({label:"국무총리"})._id,
	label : "인사혁신처",
	desc : "",
	thumbImgPath : "https://lh3.googleusercontent.com/-77AZdg4ow4k/AAAAAAAAAAI/AAAAAAAAAAA/qN84Bp2h-RQ/photo.jpg",
	thumbImgName : "",
	url : "http://www.mpm.go.kr/",
	created : id.getTimestamp(),
	modified : null
})

id = ObjectId()
db.Groups.insert({
	_id : id,
	orgId : db.Orgs.findOne({label:"정부조직도"})._id,
	parentId : db.Groups.findOne({label:"국무총리"})._id,
	label : "법제처",
	desc : "",
	thumbImgPath : "https://yt3.ggpht.com/-IaF0xZOUP0Y/AAAAAAAAAAI/AAAAAAAAAAA/tiNp3PvDJ6s/s900-c-k-no/photo.jpg",
	thumbImgName : "",
	url : "http://www.moleg.go.kr/",
	created : id.getTimestamp(),
	modified : null
})

id = ObjectId()
db.Groups.insert({
	_id : id,
	orgId : db.Orgs.findOne({label:"정부조직도"})._id,
	parentId : db.Groups.findOne({label:"국무총리"})._id,
	label : "국가보훈처",
	desc : "",
	thumbImgPath : "http://www.upkorea.net/news/photo/201411/35067_27688_2630.jpg",
	thumbImgName : "",
	url : "http://www.mpva.go.kr/",
	created : id.getTimestamp(),
	modified : null
})

id = ObjectId()
db.Groups.insert({
	_id : id,
	orgId : db.Orgs.findOne({label:"정부조직도"})._id,
	parentId : db.Groups.findOne({label:"국무총리"})._id,
	label : "식품의약품안전처",
	desc : "",
	thumbImgPath : "https://pbs.twimg.com/profile_images/3425995117/cab0259a1397836962338b31ee2dede0.jpeg",
	thumbImgName : "",
	url : "http://www.mfds.go.kr/",
	created : id.getTimestamp(),
	modified : null
})

id = ObjectId()
db.Groups.insert({
	_id : id,
	orgId : db.Orgs.findOne({label:"정부조직도"})._id,
	parentId : db.Groups.findOne({label:"국무총리"})._id,
	label : "공정거래위원회",
	desc : "",
	thumbImgPath : "http://www.gc4989.com/data/file/condo_news/2948982407_fce42ca6_K-2.png",
	thumbImgName : "",
	url : "http://www.ftc.go.kr/",
	created : id.getTimestamp(),
	modified : null
})

id = ObjectId()
db.Groups.insert({
	_id : id,
	orgId : db.Orgs.findOne({label:"정부조직도"})._id,
	parentId : db.Groups.findOne({label:"국무총리"})._id,
	label : "금융위원회",
	desc : "",
	thumbImgPath : "http://www.fsc.go.kr/images/contents/img_popup_FscInfoCi.gif",
	thumbImgName : "",
	url : "http://fsc.go.kr/",
	created : id.getTimestamp(),
	modified : null
})

id = ObjectId()
db.Groups.insert({
	_id : id,
	orgId : db.Orgs.findOne({label:"정부조직도"})._id,
	parentId : db.Groups.findOne({label:"국무총리"})._id,
	label : "국민권익위원회",
	desc : "",
	thumbImgPath : "https://upload.wikimedia.org/wikipedia/ko/6/68/Acrc-logo.png",
	thumbImgName : "",
	url : "http://www.acrc.go.kr/",
	created : id.getTimestamp(),
	modified : null
})

id = ObjectId()
db.Groups.insert({
	_id : id,
	orgId : db.Orgs.findOne({label:"정부조직도"})._id,
	parentId : db.Groups.findOne({label:"국무총리"})._id,
	label : "원자력안전위원회",
	desc : "",
	thumbImgPath : "http://www.nssc.go.kr/nssc/img/c1/ciImg.gif",
	thumbImgName : "",
	url : "http://www.nssc.go.kr/",
	created : id.getTimestamp(),
	modified : null
})

id = ObjectId()
db.Groups.insert({
	_id : id,
	orgId : db.Orgs.findOne({label:"정부조직도"})._id,
	parentId : db.Groups.findOne({label:"국무총리"})._id,
	label : "기획재정부",
	desc : "",
	thumbImgPath : "http://www.mosf.go.kr/images/info/info02a_logo.gif",
	thumbImgName : "",
	url : "http://www.mosf.go.kr/",
	created : id.getTimestamp(),
	modified : null
})

id = ObjectId()
db.Groups.insert({
	_id : id,
	orgId : db.Orgs.findOne({label:"정부조직도"})._id,
	parentId : db.Groups.findOne({label:"국무총리"})._id,
	label : "교육부",
	desc : "",
	thumbImgPath : "http://www.moe.go.kr/site/images/2016/mi22.gif",
	thumbImgName : "",
	url : "http://www.moe.go.kr/",
	created : id.getTimestamp(),
	modified : null
})

id = ObjectId()
db.Groups.insert({
	_id : id,
	orgId : db.Orgs.findOne({label:"정부조직도"})._id,
	parentId : db.Groups.findOne({label:"국무총리"})._id,
	label : "미래창조과학부",
	desc : "",
	thumbImgPath : "http://www.msip.go.kr/cms/images/www/contents/mi-intro-img01.gif",
	thumbImgName : "",
	url : "http://www.msip.go.kr/",
	created : id.getTimestamp(),
	modified : null
})

id = ObjectId()
db.Groups.insert({
	_id : id,
	orgId : db.Orgs.findOne({label:"정부조직도"})._id,
	parentId : db.Groups.findOne({label:"국무총리"})._id,
	label : "외교부",
	desc : "",
	thumbImgPath : "https://pbs.twimg.com/profile_images/588882172439724033/afV83MGU.png",
	thumbImgName : "",
	url : "http://www.mofa.go.kr/",
	created : id.getTimestamp(),
	modified : null
})

id = ObjectId()
db.Groups.insert({
	_id : id,
	orgId : db.Orgs.findOne({label:"정부조직도"})._id,
	parentId : db.Groups.findOne({label:"국무총리"})._id,
	label : "통일부",
	desc : "",
	thumbImgPath : "https://pbs.twimg.com/profile_images/548273636201603072/uPzQBxK9.jpeg",
	thumbImgName : "",
	url : "http://www.unikorea.go.kr/",
	created : id.getTimestamp(),
	modified : null
})

id = ObjectId()
db.Groups.insert({
	_id : id,
	orgId : db.Orgs.findOne({label:"정부조직도"})._id,
	parentId : db.Groups.findOne({label:"국무총리"})._id,
	label : "법무부",
	desc : "",
	thumbImgPath : "http://www.moj.go.kr/HP/MOJ03/images/icsimages/img_06024_01.gif",
	thumbImgName : "",
	url : "http://www.moj.go.kr/",
	created : id.getTimestamp(),
	modified : null
})

id = ObjectId()
db.Groups.insert({
	_id : id,
	orgId : db.Orgs.findOne({label:"정부조직도"})._id,
	parentId : db.Groups.findOne({label:"국무총리"})._id,
	label : "국방부",
	desc : "",
	thumbImgPath : "http://www.bluekoreadot.com/news/photo/201305/10825_8810_4354.jpg",
	thumbImgName : "",
	url : "http://www.mnd.go.kr/",
	created : id.getTimestamp(),
	modified : null
})

id = ObjectId()
db.Groups.insert({
	_id : id,
	orgId : db.Orgs.findOne({label:"정부조직도"})._id,
	parentId : db.Groups.findOne({label:"국무총리"})._id,
	label : "행정자치부",
	desc : "",
	thumbImgPath : "http://www.pcnc.go.kr/images/maps/view_part02_list10.gif",
	thumbImgName : "",
	url : "http://www.mogaha.go.kr/",
	created : id.getTimestamp(),
	modified : null
})

id = ObjectId()
db.Groups.insert({
	_id : id,
	orgId : db.Orgs.findOne({label:"정부조직도"})._id,
	parentId : db.Groups.findOne({label:"국무총리"})._id,
	label : "문화체육관광부",
	desc : "",
	thumbImgPath : "https://www.arko.or.kr/home2005/files/contents/100041/mk_2.jpg",
	thumbImgName : "",
	url : "http://www.mcst.go.kr/",
	created : id.getTimestamp(),
	modified : null
})

id = ObjectId()
db.Groups.insert({
	_id : id,
	orgId : db.Orgs.findOne({label:"정부조직도"})._id,
	parentId : db.Groups.findOne({label:"국무총리"})._id,
	label : "농림축산식품부",
	desc : "",
	thumbImgPath : "http://pbs.twimg.com/profile_images/3665793702/0846ddc473b64a57f5e060bf4db3e960.jpeg",
	thumbImgName : "",
	url : "http://www.mafra.go.kr/",
	created : id.getTimestamp(),
	modified : null
})

id = ObjectId()
db.Groups.insert({
	_id : id,
	orgId : db.Orgs.findOne({label:"정부조직도"})._id,
	parentId : db.Groups.findOne({label:"국무총리"})._id,
	label : "산업통상자원부",
	desc : "",
	thumbImgPath : "http://www.motie.go.kr/resource/motie_add/img/contents/mi01.gif",
	thumbImgName : "",
	url : "http://www.motie.go.kr/",
	created : id.getTimestamp(),
	modified : null
})

id = ObjectId()
db.Groups.insert({
	_id : id,
	orgId : db.Orgs.findOne({label:"정부조직도"})._id,
	parentId : db.Groups.findOne({label:"국무총리"})._id,
	label : "보건복지부",
	desc : "",
	thumbImgPath : "https://pbs.twimg.com/profile_images/2288518611/xsl65ckwnirore0fayls_400x400.jpeg",
	thumbImgName : "",
	url : "http://www.mw.go.kr/",
	created : id.getTimestamp(),
	modified : null
})

id = ObjectId()
db.Groups.insert({
	_id : id,
	orgId : db.Orgs.findOne({label:"정부조직도"})._id,
	parentId : db.Groups.findOne({label:"국무총리"})._id,
	label : "환경부",
	desc : "",
	thumbImgPath : "http://kid.chosun.com/site/data/img_dir/2013/11/26/2013112602452_0.jpg",
	thumbImgName : "",
	url : "http://www.me.go.kr/",
	created : id.getTimestamp(),
	modified : null
})

id = ObjectId()
db.Groups.insert({
	_id : id,
	orgId : db.Orgs.findOne({label:"정부조직도"})._id,
	parentId : db.Groups.findOne({label:"국무총리"})._id,
	label : "고용노동부",
	desc : "",
	thumbImgPath : "http://dcollection.nl.go.kr/upload/2015/11/13/0078f7c0d01ca2128a31833112999ed8_DIBRARY9.jpg",
	thumbImgName : "",
	url : "http://www.moel.go.kr/",
	created : id.getTimestamp(),
	modified : null
})

id = ObjectId()
db.Groups.insert({
	_id : id,
	orgId : db.Orgs.findOne({label:"정부조직도"})._id,
	parentId : db.Groups.findOne({label:"국무총리"})._id,
	label : "여성가족부",
	desc : "",
	thumbImgPath : "http://www.ewomankorea.co.kr/news/photo/201507/1041_551_1850.jpg",
	thumbImgName : "",
	url : "http://www.mogef.go.kr/",
	created : id.getTimestamp(),
	modified : null
})

id = ObjectId()
db.Groups.insert({
	_id : id,
	orgId : db.Orgs.findOne({label:"정부조직도"})._id,
	parentId : db.Groups.findOne({label:"국무총리"})._id,
	label : "국토교통부",
	desc : "",
	thumbImgPath : "http://www.molit.go.kr/mltm/images/mi/mi_01_01.jpg",
	thumbImgName : "",
	url : "http://www.molit.go.kr/",
	created : id.getTimestamp(),
	modified : null
})

id = ObjectId()
db.Groups.insert({
	_id : id,
	orgId : db.Orgs.findOne({label:"정부조직도"})._id,
	parentId : db.Groups.findOne({label:"국무총리"})._id,
	label : "해양수산부",
	desc : "",
	thumbImgPath : "http://www.yachtpia.com/news/photo/201306/22110_53885_3053.jpg",
	thumbImgName : "",
	url : "http://www.mof.go.kr/",
	created : id.getTimestamp(),
	modified : null
})

id = ObjectId()
db.Groups.insert({
	_id : id,
	orgId : db.Orgs.findOne({label:"정부조직도"})._id,
	parentId : db.Groups.findOne({label:"기획재정부"})._id,
	label : "국세청",
	desc : "",
	thumbImgPath : "http://sstatic.naver.net/keypage/outside/government/2010100520551237662.jpg",
	thumbImgName : "",
	url : "http://www.nts.go.kr/",
	created : id.getTimestamp(),
	modified : null
})

id = ObjectId()
db.Groups.insert({
	_id : id,
	orgId : db.Orgs.findOne({label:"정부조직도"})._id,
	parentId : db.Groups.findOne({label:"기획재정부"})._id,
	label : "관세청",
	desc : "",
	thumbImgPath : "http://img.sbs.co.kr/sbscnbc/upload/2014/02/12/10000413993.jpg",
	thumbImgName : "",
	url : "http://www.customs.go.kr/",
	created : id.getTimestamp(),
	modified : null
})

id = ObjectId()
db.Groups.insert({
	_id : id,
	orgId : db.Orgs.findOne({label:"정부조직도"})._id,
	parentId : db.Groups.findOne({label:"기획재정부"})._id,
	label : "조달청",
	desc : "",
	thumbImgPath : "http://www.speconomy.com/news/photo/201411/40719_29025_4829.jpg",
	thumbImgName : "",
	url : "http://www.pps.go.kr/",
	created : id.getTimestamp(),
	modified : null
})

id = ObjectId()
db.Groups.insert({
	_id : id,
	orgId : db.Orgs.findOne({label:"정부조직도"})._id,
	parentId : db.Groups.findOne({label:"기획재정부"})._id,
	label : "통계청",
	desc : "",
	thumbImgPath : "https://pbs.twimg.com/profile_images/988605173/___ci_400x400.jpg",
	thumbImgName : "",
	url : "http://kostat.go.kr/",
	created : id.getTimestamp(),
	modified : null
})

id = ObjectId()
db.Groups.insert({
	_id : id,
	orgId : db.Orgs.findOne({label:"정부조직도"})._id,
	parentId : db.Groups.findOne({label:"법무부"})._id,
	label : "검찰청",
	desc : "",
	thumbImgPath : "http://www.5day.co.kr/logo/imgs/140704_034443_logo_0148_main.jpg",
	thumbImgName : "",
	url : "http://www.spo.go.kr/",
	created : id.getTimestamp(),
	modified : null
})

id = ObjectId()
db.Groups.insert({
	_id : id,
	orgId : db.Orgs.findOne({label:"정부조직도"})._id,
	parentId : db.Groups.findOne({label:"국방부"})._id,
	label : "병무청",
	desc : "",
	thumbImgPath : "http://www.namdongnews.co.kr/news/photo/201507/24059_11858_339.jpg",
	thumbImgName : "",
	url : "http://www.mma.go.kr/",
	created : id.getTimestamp(),
	modified : null
})

id = ObjectId()
db.Groups.insert({
	_id : id,
	orgId : db.Orgs.findOne({label:"정부조직도"})._id,
	parentId : db.Groups.findOne({label:"국방부"})._id,
	label : "방위사업청",
	desc : "",
	thumbImgPath : "https://yt3.ggpht.com/-l98AMNr0fY0/AAAAAAAAAAI/AAAAAAAAAAA/9cia94W2VPk/s900-c-k-no/photo.jpg",
	thumbImgName : "",
	url : "http://www.dapa.go.kr/",
	created : id.getTimestamp(),
	modified : null
})

id = ObjectId()
db.Groups.insert({
	_id : id,
	orgId : db.Orgs.findOne({label:"정부조직도"})._id,
	parentId : db.Groups.findOne({label:"행정자치부"})._id,
	label : "경찰청",
	desc : "",
	thumbImgPath : "https://pbs.twimg.com/profile_images/2912944582/04ed51a7ad4f3ec413be3b3f0b0a80d7_400x400.jpeg",
	thumbImgName : "",
	url : "http://www.police.go.kr/",
	created : id.getTimestamp(),
	modified : null
})

id = ObjectId()
db.Groups.insert({
	_id : id,
	orgId : db.Orgs.findOne({label:"정부조직도"})._id,
	parentId : db.Groups.findOne({label:"문화체육관광부"})._id,
	label : "문화재청",
	desc : "",
	thumbImgPath : "http://www.upkorea.net/news/photo/201411/34755_27394_4124.jpg",
	thumbImgName : "",
	url : "http://www.cha.go.kr/",
	created : id.getTimestamp(),
	modified : null
})

id = ObjectId()
db.Groups.insert({
	_id : id,
	orgId : db.Orgs.findOne({label:"정부조직도"})._id,
	parentId : db.Groups.findOne({label:"농림축산식품부"})._id,
	label : "농촌진흥청",
	desc : "",
	thumbImgPath : "http://cfile24.uf.tistory.com/image/1902E94E4FD207A10D6393",
	thumbImgName : "",
	url : "http://www.rda.go.kr/",
	created : id.getTimestamp(),
	modified : null
})

id = ObjectId()
db.Groups.insert({
	_id : id,
	orgId : db.Orgs.findOne({label:"정부조직도"})._id,
	parentId : db.Groups.findOne({label:"농림축산식품부"})._id,
	label : "산림청",
	desc : "",
	thumbImgPath : "https://pbs.twimg.com/profile_images/2288837357/kpo5tc69n8xhp60hjhx8_400x400.jpeg",
	thumbImgName : "",
	url : "http://www.forest.go.kr/",
	created : id.getTimestamp(),
	modified : null
})

id = ObjectId()
db.Groups.insert({
	_id : id,
	orgId : db.Orgs.findOne({label:"정부조직도"})._id,
	parentId : db.Groups.findOne({label:"산업통상자원부"})._id,
	label : "중소기업청",
	desc : "",
	thumbImgPath : "http://www.smba.go.kr/img/kr/introduce/c_logo.gif",
	thumbImgName : "",
	url : "http://smba.go.kr/",
	created : id.getTimestamp(),
	modified : null
})

id = ObjectId()
db.Groups.insert({
	_id : id,
	orgId : db.Orgs.findOne({label:"정부조직도"})._id,
	parentId : db.Groups.findOne({label:"산업통상자원부"})._id,
	label : "특허청",
	desc : "",
	thumbImgPath : "http://www.christianjournal.kr/n_news/peg/20141113084441_0857.jpg",
	thumbImgName : "",
	url : "http://www.kipo.go.kr/",
	created : id.getTimestamp(),
	modified : null
})

id = ObjectId()
db.Groups.insert({
	_id : id,
	orgId : db.Orgs.findOne({label:"정부조직도"})._id,
	parentId : db.Groups.findOne({label:"환경부"})._id,
	label : "기상청",
	desc : "",
	thumbImgPath : "http://msnews.co.kr/data/cheditor4/1503/20150320164131_mqddylyv.jpg",
	thumbImgName : "",
	url : "http://www.kma.go.kr/",
	created : id.getTimestamp(),
	modified : null
})

id = ObjectId()
db.Groups.insert({
	_id : id,
	orgId : db.Orgs.findOne({label:"정부조직도"})._id,
	parentId : db.Groups.findOne({label:"국토교통부"})._id,
	label : "행정중심복합도시건설청",
	desc : "",
	thumbImgPath : "http://image.ajunews.com/content/image/2015/02/24/20150224184539324606.jpg",
	thumbImgName : "",
	url : "http://www.macc.go.kr/",
	created : id.getTimestamp(),
	modified : null
})

id = ObjectId()
db.Groups.insert({
	_id : id,
	orgId : db.Orgs.findOne({label:"정부조직도"})._id,
	parentId : db.Groups.findOne({label:"국토교통부"})._id,
	label : "새만금개발청",
	desc : "",
	thumbImgPath : "https://pbs.twimg.com/profile_images/454608459221069824/c8Fj4Dwx_400x400.jpeg",
	thumbImgName : "",
	url : "http://www.saemangeum.go.kr/",
	created : id.getTimestamp(),
	modified : null
})

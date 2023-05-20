

-- member insert
insert into members(user_id, user_name, user_password, email_id, email_domain, admin) 
values ("ssafy", "싸피", "daa565e94a765520362474fe25806000f502042e1cfdcd08107d9467a6e5edc3", "ssafy", "ssafy.com", "Y"),
		("ssafy2", "싸피2", "30d9e5314e7209c542e97e6f352c2fde16a1a9da67fbfec3e8c2c5e200c20182", "ssafy2", "ssafy.com", "N"),
        ("ssafy3", "싸피3", "166a99e5f407223b1bd59109f0c62f5e583b45e517e38e9a5049272d4001d2fc", "ssafy3", "ssafy.com", "N");
        
-- info_board insert
insert into info_board(user_id, title, content)
values ("ssafy", "title1", "content1"),
       ("ssafy", "title2", "content2"),
       ("ssafy", "title3", "content3"),
       ("ssafy", "title4", "content4"),
       ("ssafy", "title5", "content5"),
       ("ssafy", "title6", "content6"),
       ("ssafy", "title7", "content7"),
       ("ssafy", "title8", "content8"),
       ("ssafy", "title9", "content9"),
       ("ssafy", "title10", "content10"),
       ("ssafy", "title11", "content11"),
       ("ssafy", "title12", "content12"),
       ("ssafy", "title13", "content13"),
       ("ssafy", "title14", "content14"),
       ("ssafy", "title15", "content15"),
       ("ssafy", "title16", "content16"),
       ("ssafy", "title17", "content17"),
       ("ssafy", "title18", "content18"),
       ("ssafy", "title19", "content19"),
       ("ssafy", "title20", "content20");

-- info_board_comment insert
insert into info_board_comment(user_id, info_board_id, content)
values ("ssafy2", 1, "content1"),
       ("ssafy2", 1, "content2"),
       ("ssafy3", 1, "content3"),
       ("ssafy2", 2, "content4"),
       ("ssafy3", 2, "content5"),
       ("ssafy3", 2, "content6");
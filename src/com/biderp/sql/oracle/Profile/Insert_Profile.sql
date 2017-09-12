CREATE OR REPLACE PROCEDURE Insert_Profile(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_AddUser_cur IS Select
		Name,
		LastName,
		LoginName,
		Password,
		Responsibility,
		moduser
		from table_AddUser
	 where objid=pnObjid 
	 and not exists (select *from table_AddUser where AddUser2Profile=pnObjid);



--Please Modify the cursor as you need

CURSOR m_Assistance_cur IS Select
		Name,
		Description,
		Email,
		PostDate,
		DetailAction,
		Status,
		moduser
		from table_Assistance
	 where objid=pnObjid 
	 and not exists (select *from table_Assistance where Assistance2Profile=pnObjid);



--Please Modify the cursor as you need

CURSOR m_ProfileSetting_cur IS Select
		Name,
		ZipCode,
		Distance,
		BizPolicy,
		moduser
		from table_ProfileSetting
	 where objid=pnObjid 
	 and not exists (select *from table_ProfileSetting where ProfileSetting2Profile=pnObjid);



--Please Modify the cursor as you need

CURSOR m_Experience_cur IS Select
		Name,
		Description,
		ProjectCode,
		MainCode,
		YearFrom,
		YearTo,
		Reference,
		moduser
		from table_Experience
	 where objid=pnObjid 
	 and not exists (select *from table_Experience where Experience2Profile=pnObjid);



--Please Modify the cursor as you need

CURSOR m_Subscription_cur IS Select
		Name,
		SubscribeCode,
		StartDate,
		EndDate,
		FullName,
		CardNo,
		MonthCode,
		YearCode,
		CardId,
		CardTypeCode,
		Status,
		moduser
		from table_Subscription
	 where objid=pnObjid 
	 and not exists (select *from table_Subscription where Subscription2Profile=pnObjid);


--variables
id	NUMBER:=0;
i_AddUser_cur	m_AddUser_cur%rowtype;
i_Assistance_cur	m_Assistance_cur%rowtype;
i_ProfileSetting_cur	m_ProfileSetting_cur%rowtype;
i_Experience_cur	m_Experience_cur%rowtype;
i_Subscription_cur	m_Subscription_cur%rowtype;

BEGIN
--opening cursor m_AddUser_cur
	id:=0;
Begin
	OPEN m_AddUser_cur;
	LOOP
	id := id-1;
	FETCH m_AddUser_cur INTO i_AddUser_cur;
	EXIT WHEN m_AddUser_cur%NOTFOUND;

--Insert records in AddUser

	INSERT INTO table_AddUser(
		Name,
		LastName,
		LoginName,
		Password,
		Responsibility,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_AddUser_cur.Name,
		i_AddUser_cur.LastName,
		i_AddUser_cur.LoginName,
		i_AddUser_cur.Password,
		i_AddUser_cur.Responsibility,
		id,
		pnObjid,
		i_AddUser_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_AddUser_cur;
 End;
--opening cursor m_Assistance_cur
	id:=0;
Begin
	OPEN m_Assistance_cur;
	LOOP
	id := id-1;
	FETCH m_Assistance_cur INTO i_Assistance_cur;
	EXIT WHEN m_Assistance_cur%NOTFOUND;

--Insert records in Assistance

	INSERT INTO table_Assistance(
		Name,
		Description,
		Email,
		PostDate,
		DetailAction,
		Status,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_Assistance_cur.Name,
		i_Assistance_cur.Description,
		i_Assistance_cur.Email,
		i_Assistance_cur.PostDate,
		i_Assistance_cur.DetailAction,
		i_Assistance_cur.Status,
		id,
		pnObjid,
		i_Assistance_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Assistance_cur;
 End;
--opening cursor m_ProfileSetting_cur
	id:=0;
Begin
	OPEN m_ProfileSetting_cur;
	LOOP
	id := id-1;
	FETCH m_ProfileSetting_cur INTO i_ProfileSetting_cur;
	EXIT WHEN m_ProfileSetting_cur%NOTFOUND;

--Insert records in ProfileSetting

	INSERT INTO table_ProfileSetting(
		Name,
		ZipCode,
		Distance,
		BizPolicy,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_ProfileSetting_cur.Name,
		i_ProfileSetting_cur.ZipCode,
		i_ProfileSetting_cur.Distance,
		i_ProfileSetting_cur.BizPolicy,
		id,
		pnObjid,
		i_ProfileSetting_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_ProfileSetting_cur;
 End;
--opening cursor m_Experience_cur
	id:=0;
Begin
	OPEN m_Experience_cur;
	LOOP
	id := id-1;
	FETCH m_Experience_cur INTO i_Experience_cur;
	EXIT WHEN m_Experience_cur%NOTFOUND;

--Insert records in Experience

	INSERT INTO table_Experience(
		Name,
		Description,
		ProjectCode,
		MainCode,
		YearFrom,
		YearTo,
		Reference,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_Experience_cur.Name,
		i_Experience_cur.Description,
		i_Experience_cur.ProjectCode,
		i_Experience_cur.MainCode,
		i_Experience_cur.YearFrom,
		i_Experience_cur.YearTo,
		i_Experience_cur.Reference,
		id,
		pnObjid,
		i_Experience_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Experience_cur;
 End;
--opening cursor m_Subscription_cur
	id:=0;
Begin
	OPEN m_Subscription_cur;
	LOOP
	id := id-1;
	FETCH m_Subscription_cur INTO i_Subscription_cur;
	EXIT WHEN m_Subscription_cur%NOTFOUND;

--Insert records in Subscription

	INSERT INTO table_Subscription(
		Name,
		SubscribeCode,
		StartDate,
		EndDate,
		FullName,
		CardNo,
		MonthCode,
		YearCode,
		CardId,
		CardTypeCode,
		Status,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_Subscription_cur.Name,
		i_Subscription_cur.SubscribeCode,
		i_Subscription_cur.StartDate,
		i_Subscription_cur.EndDate,
		i_Subscription_cur.FullName,
		i_Subscription_cur.CardNo,
		i_Subscription_cur.MonthCode,
		i_Subscription_cur.YearCode,
		i_Subscription_cur.CardId,
		i_Subscription_cur.CardTypeCode,
		i_Subscription_cur.Status,
		id,
		pnObjid,
		i_Subscription_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Subscription_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

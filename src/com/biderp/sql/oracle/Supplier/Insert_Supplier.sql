CREATE OR REPLACE PROCEDURE Insert_Supplier(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_BizProfile_cur IS Select
		Name,
		Rating,
		Years,
		moduser
		from table_BizProfile
	 where objid=pnObjid 
	 and not exists (select *from table_BizProfile where BizProfile2Supplier=pnObjid);



--Please Modify the cursor as you need

CURSOR m_Contact_cur IS Select
		Name,
		LastName,
		Attention,
		Street,
		City,
		State,
		ZipCode,
		CountryCode,
		Phone,
		Phone2,
		Fax,
		Email,
		ContactCode,
		moduser
		from table_Contact
	 where objid=pnObjid 
	 and not exists (select *from table_Contact where Contact2Supplier=pnObjid);



--Please Modify the cursor as you need

CURSOR m_JobHistory_cur IS Select
		Name,
		Description,
		StartDate,
		Duration,
		Remark,
		Rating,
		moduser
		from table_JobHistory
	 where objid=pnObjid 
	 and not exists (select *from table_JobHistory where JobHistory2Supplier=pnObjid);



--Please Modify the cursor as you need

CURSOR m_User_cur IS Select
		Name,
		LastName,
		LoginName,
		Password,
		VerifyPassword,
		Status,
		UserType,
		Email,
		moduser
		from table_User
	 where objid=pnObjid 
	 and not exists (select *from table_User where User2Supplier=pnObjid);


--variables
id	NUMBER:=0;
i_BizProfile_cur	m_BizProfile_cur%rowtype;
i_Contact_cur	m_Contact_cur%rowtype;
i_JobHistory_cur	m_JobHistory_cur%rowtype;
i_User_cur	m_User_cur%rowtype;

BEGIN
--opening cursor m_BizProfile_cur
	id:=0;
Begin
	OPEN m_BizProfile_cur;
	LOOP
	id := id-1;
	FETCH m_BizProfile_cur INTO i_BizProfile_cur;
	EXIT WHEN m_BizProfile_cur%NOTFOUND;

--Insert records in BizProfile

	INSERT INTO table_BizProfile(
		Name,
		Rating,
		Years,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_BizProfile_cur.Name,
		i_BizProfile_cur.Rating,
		i_BizProfile_cur.Years,
		id,
		pnObjid,
		i_BizProfile_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_BizProfile_cur;
 End;
--opening cursor m_Contact_cur
	id:=0;
Begin
	OPEN m_Contact_cur;
	LOOP
	id := id-1;
	FETCH m_Contact_cur INTO i_Contact_cur;
	EXIT WHEN m_Contact_cur%NOTFOUND;

--Insert records in Contact

	INSERT INTO table_Contact(
		Name,
		LastName,
		Attention,
		Street,
		City,
		State,
		ZipCode,
		CountryCode,
		Phone,
		Phone2,
		Fax,
		Email,
		ContactCode,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_Contact_cur.Name,
		i_Contact_cur.LastName,
		i_Contact_cur.Attention,
		i_Contact_cur.Street,
		i_Contact_cur.City,
		i_Contact_cur.State,
		i_Contact_cur.ZipCode,
		i_Contact_cur.CountryCode,
		i_Contact_cur.Phone,
		i_Contact_cur.Phone2,
		i_Contact_cur.Fax,
		i_Contact_cur.Email,
		i_Contact_cur.ContactCode,
		id,
		pnObjid,
		i_Contact_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Contact_cur;
 End;
--opening cursor m_JobHistory_cur
	id:=0;
Begin
	OPEN m_JobHistory_cur;
	LOOP
	id := id-1;
	FETCH m_JobHistory_cur INTO i_JobHistory_cur;
	EXIT WHEN m_JobHistory_cur%NOTFOUND;

--Insert records in JobHistory

	INSERT INTO table_JobHistory(
		Name,
		Description,
		StartDate,
		Duration,
		Remark,
		Rating,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_JobHistory_cur.Name,
		i_JobHistory_cur.Description,
		i_JobHistory_cur.StartDate,
		i_JobHistory_cur.Duration,
		i_JobHistory_cur.Remark,
		i_JobHistory_cur.Rating,
		id,
		pnObjid,
		i_JobHistory_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_JobHistory_cur;
 End;
--opening cursor m_User_cur
	id:=0;
Begin
	OPEN m_User_cur;
	LOOP
	id := id-1;
	FETCH m_User_cur INTO i_User_cur;
	EXIT WHEN m_User_cur%NOTFOUND;

--Insert records in User

	INSERT INTO table_User(
		Name,
		LastName,
		LoginName,
		Password,
		VerifyPassword,
		Status,
		UserType,
		Email,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_User_cur.Name,
		i_User_cur.LastName,
		i_User_cur.LoginName,
		i_User_cur.Password,
		i_User_cur.VerifyPassword,
		i_User_cur.Status,
		i_User_cur.UserType,
		i_User_cur.Email,
		id,
		pnObjid,
		i_User_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_User_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

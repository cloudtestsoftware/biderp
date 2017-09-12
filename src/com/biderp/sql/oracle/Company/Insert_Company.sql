CREATE OR REPLACE PROCEDURE Insert_Company(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_Currency_cur IS Select
		Name,
		Country,
		Symbol,
		Conversion,
		Type,
		moduser
		from table_Currency
	 where objid=pnObjid 
	 and not exists (select *from table_Currency where Currency2Company=pnObjid);



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
	 and not exists (select *from table_User where User2Company=pnObjid);


--variables
id	NUMBER:=0;
i_Currency_cur	m_Currency_cur%rowtype;
i_User_cur	m_User_cur%rowtype;

BEGIN
--opening cursor m_Currency_cur
	id:=0;
Begin
	OPEN m_Currency_cur;
	LOOP
	id := id-1;
	FETCH m_Currency_cur INTO i_Currency_cur;
	EXIT WHEN m_Currency_cur%NOTFOUND;

--Insert records in Currency

	INSERT INTO table_Currency(
		Name,
		Country,
		Symbol,
		Conversion,
		Type,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_Currency_cur.Name,
		i_Currency_cur.Country,
		i_Currency_cur.Symbol,
		i_Currency_cur.Conversion,
		i_Currency_cur.Type,
		id,
		pnObjid,
		i_Currency_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Currency_cur;
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

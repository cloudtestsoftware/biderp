CREATE OR REPLACE PROCEDURE Insert_Lead(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_LeadNote_cur IS Select
		Name,
		Discussion,
		CreateDate,
		moduser
		from table_LeadNote
	 where objid=pnObjid 
	 and not exists (select *from table_LeadNote where LeadNote2Lead=pnObjid);



--Please Modify the cursor as you need

CURSOR m_LeadEmail_cur IS Select
		Name,
		MessageTitle,
		Header,
		Footer,
		CreateDate,
		Status,
		moduser
		from table_LeadEmail
	 where objid=pnObjid 
	 and not exists (select *from table_LeadEmail where LeadEmail2Lead=pnObjid);



--Please Modify the cursor as you need

CURSOR m_LeadAccess_cur IS Select
		Name,
		AccessAllowed,
		AccessTaken,
		Reason,
		moduser
		from table_LeadAccess
	 where objid=pnObjid 
	 and not exists (select *from table_LeadAccess where LeadAccess2Lead=pnObjid);


--variables
id	NUMBER:=0;
i_LeadNote_cur	m_LeadNote_cur%rowtype;
i_LeadEmail_cur	m_LeadEmail_cur%rowtype;
i_LeadAccess_cur	m_LeadAccess_cur%rowtype;

BEGIN
--opening cursor m_LeadNote_cur
	id:=0;
Begin
	OPEN m_LeadNote_cur;
	LOOP
	id := id-1;
	FETCH m_LeadNote_cur INTO i_LeadNote_cur;
	EXIT WHEN m_LeadNote_cur%NOTFOUND;

--Insert records in LeadNote

	INSERT INTO table_LeadNote(
		Name,
		Discussion,
		CreateDate,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_LeadNote_cur.Name,
		i_LeadNote_cur.Discussion,
		i_LeadNote_cur.CreateDate,
		id,
		pnObjid,
		i_LeadNote_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_LeadNote_cur;
 End;
--opening cursor m_LeadEmail_cur
	id:=0;
Begin
	OPEN m_LeadEmail_cur;
	LOOP
	id := id-1;
	FETCH m_LeadEmail_cur INTO i_LeadEmail_cur;
	EXIT WHEN m_LeadEmail_cur%NOTFOUND;

--Insert records in LeadEmail

	INSERT INTO table_LeadEmail(
		Name,
		MessageTitle,
		Header,
		Footer,
		CreateDate,
		Status,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_LeadEmail_cur.Name,
		i_LeadEmail_cur.MessageTitle,
		i_LeadEmail_cur.Header,
		i_LeadEmail_cur.Footer,
		i_LeadEmail_cur.CreateDate,
		i_LeadEmail_cur.Status,
		id,
		pnObjid,
		i_LeadEmail_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_LeadEmail_cur;
 End;
--opening cursor m_LeadAccess_cur
	id:=0;
Begin
	OPEN m_LeadAccess_cur;
	LOOP
	id := id-1;
	FETCH m_LeadAccess_cur INTO i_LeadAccess_cur;
	EXIT WHEN m_LeadAccess_cur%NOTFOUND;

--Insert records in LeadAccess

	INSERT INTO table_LeadAccess(
		Name,
		AccessAllowed,
		AccessTaken,
		Reason,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_LeadAccess_cur.Name,
		i_LeadAccess_cur.AccessAllowed,
		i_LeadAccess_cur.AccessTaken,
		i_LeadAccess_cur.Reason,
		id,
		pnObjid,
		i_LeadAccess_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_LeadAccess_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

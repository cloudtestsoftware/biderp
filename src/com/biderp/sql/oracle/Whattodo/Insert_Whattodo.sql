CREATE OR REPLACE PROCEDURE Insert_Whattodo(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_LogNote_cur IS Select
		Name,
		Note,
		CreateDate,
		LogIndex,
		moduser
		from table_LogNote
	 where objid=pnObjid 
	 and not exists (select *from table_LogNote where LogNote2Whattodo=pnObjid);


--variables
id	NUMBER:=0;
i_LogNote_cur	m_LogNote_cur%rowtype;

BEGIN
--opening cursor m_LogNote_cur
	id:=0;
Begin
	OPEN m_LogNote_cur;
	LOOP
	id := id-1;
	FETCH m_LogNote_cur INTO i_LogNote_cur;
	EXIT WHEN m_LogNote_cur%NOTFOUND;

--Insert records in LogNote

	INSERT INTO table_LogNote(
		Name,
		Note,
		CreateDate,
		LogIndex,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_LogNote_cur.Name,
		i_LogNote_cur.Note,
		i_LogNote_cur.CreateDate,
		i_LogNote_cur.LogIndex,
		id,
		pnObjid,
		i_LogNote_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_LogNote_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

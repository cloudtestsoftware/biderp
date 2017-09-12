CREATE OR REPLACE PROCEDURE Insert_Bidproject(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_BidNote_cur IS Select
		Name,
		Discussion,
		CreateDate,
		moduser
		from table_BidNote
	 where objid=pnObjid 
	 and not exists (select *from table_BidNote where BidNote2Bidproject=pnObjid);


--variables
id	NUMBER:=0;
i_BidNote_cur	m_BidNote_cur%rowtype;

BEGIN
--opening cursor m_BidNote_cur
	id:=0;
Begin
	OPEN m_BidNote_cur;
	LOOP
	id := id-1;
	FETCH m_BidNote_cur INTO i_BidNote_cur;
	EXIT WHEN m_BidNote_cur%NOTFOUND;

--Insert records in BidNote

	INSERT INTO table_BidNote(
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
		i_BidNote_cur.Name,
		i_BidNote_cur.Discussion,
		i_BidNote_cur.CreateDate,
		id,
		pnObjid,
		i_BidNote_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_BidNote_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

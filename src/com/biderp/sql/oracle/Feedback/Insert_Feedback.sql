CREATE OR REPLACE PROCEDURE Insert_Feedback(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_FeedNote_cur IS Select
		Name,
		Discussion,
		CreateDate,
		moduser
		from table_FeedNote
	 where objid=pnObjid 
	 and not exists (select *from table_FeedNote where FeedNote2Feedback=pnObjid);


--variables
id	NUMBER:=0;
i_FeedNote_cur	m_FeedNote_cur%rowtype;

BEGIN
--opening cursor m_FeedNote_cur
	id:=0;
Begin
	OPEN m_FeedNote_cur;
	LOOP
	id := id-1;
	FETCH m_FeedNote_cur INTO i_FeedNote_cur;
	EXIT WHEN m_FeedNote_cur%NOTFOUND;

--Insert records in FeedNote

	INSERT INTO table_FeedNote(
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
		i_FeedNote_cur.Name,
		i_FeedNote_cur.Discussion,
		i_FeedNote_cur.CreateDate,
		id,
		pnObjid,
		i_FeedNote_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_FeedNote_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

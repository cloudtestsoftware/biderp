CREATE OR REPLACE PROCEDURE Insert_Quote(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_QuoteMaster_cur IS Select
		Name,
		ProjectCode,
		MainCode,
		Description,
		IsBid,
		moduser
		from table_QuoteMaster
	 where objid=pnObjid 
	 and not exists (select *from table_QuoteMaster where QuoteMaster2Quote=pnObjid);



--Please Modify the cursor as you need

CURSOR m_QuoteLog_cur IS Select
		Name,
		Note,
		CreateDate,
		LogIndex,
		moduser
		from table_QuoteLog
	 where objid=pnObjid 
	 and not exists (select *from table_QuoteLog where QuoteLog2Quote=pnObjid);


--variables
id	NUMBER:=0;
i_QuoteMaster_cur	m_QuoteMaster_cur%rowtype;
i_QuoteLog_cur	m_QuoteLog_cur%rowtype;

BEGIN
--opening cursor m_QuoteMaster_cur
	id:=0;
Begin
	OPEN m_QuoteMaster_cur;
	LOOP
	id := id-1;
	FETCH m_QuoteMaster_cur INTO i_QuoteMaster_cur;
	EXIT WHEN m_QuoteMaster_cur%NOTFOUND;

--Insert records in QuoteMaster

	INSERT INTO table_QuoteMaster(
		Name,
		ProjectCode,
		MainCode,
		Description,
		IsBid,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_QuoteMaster_cur.Name,
		i_QuoteMaster_cur.ProjectCode,
		i_QuoteMaster_cur.MainCode,
		i_QuoteMaster_cur.Description,
		i_QuoteMaster_cur.IsBid,
		id,
		pnObjid,
		i_QuoteMaster_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_QuoteMaster_cur;
 End;
--opening cursor m_QuoteLog_cur
	id:=0;
Begin
	OPEN m_QuoteLog_cur;
	LOOP
	id := id-1;
	FETCH m_QuoteLog_cur INTO i_QuoteLog_cur;
	EXIT WHEN m_QuoteLog_cur%NOTFOUND;

--Insert records in QuoteLog

	INSERT INTO table_QuoteLog(
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
		i_QuoteLog_cur.Name,
		i_QuoteLog_cur.Note,
		i_QuoteLog_cur.CreateDate,
		i_QuoteLog_cur.LogIndex,
		id,
		pnObjid,
		i_QuoteLog_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_QuoteLog_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

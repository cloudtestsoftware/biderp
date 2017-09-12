CREATE OR REPLACE PROCEDURE Insert_Quotemaster(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_QuoteJobs_cur IS Select
		Name,
		ProjectCode,
		MainCode,
		SubCode,
		Description,
		UnitCount,
		moduser
		from table_QuoteJobs
	 where objid=pnObjid 
	 and not exists (select *from table_QuoteJobs where QuoteJobs2Quotemaster=pnObjid);


--variables
id	NUMBER:=0;
i_QuoteJobs_cur	m_QuoteJobs_cur%rowtype;

BEGIN
--opening cursor m_QuoteJobs_cur
	id:=0;
Begin
	OPEN m_QuoteJobs_cur;
	LOOP
	id := id-1;
	FETCH m_QuoteJobs_cur INTO i_QuoteJobs_cur;
	EXIT WHEN m_QuoteJobs_cur%NOTFOUND;

--Insert records in QuoteJobs

	INSERT INTO table_QuoteJobs(
		Name,
		ProjectCode,
		MainCode,
		SubCode,
		Description,
		UnitCount,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_QuoteJobs_cur.Name,
		i_QuoteJobs_cur.ProjectCode,
		i_QuoteJobs_cur.MainCode,
		i_QuoteJobs_cur.SubCode,
		i_QuoteJobs_cur.Description,
		i_QuoteJobs_cur.UnitCount,
		id,
		pnObjid,
		i_QuoteJobs_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_QuoteJobs_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

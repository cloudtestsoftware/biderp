CREATE OR REPLACE PROCEDURE Insert_Jobmaster(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_JobList_cur IS Select
		Name,
		ProjectCode,
		MainCode,
		SubCode,
		MainJobCode,
		SubJobCode,
		MainOffSetTime,
		SubOffSetTime,
		UnitCount,
		UnitPrice,
		QntFinished,
		StartDate,
		ExecuteDate,
		Note,
		moduser
		from table_JobList
	 where objid=pnObjid 
	 and not exists (select *from table_JobList where JobList2Jobmaster=pnObjid);


--variables
id	NUMBER:=0;
i_JobList_cur	m_JobList_cur%rowtype;

BEGIN
--opening cursor m_JobList_cur
	id:=0;
Begin
	OPEN m_JobList_cur;
	LOOP
	id := id-1;
	FETCH m_JobList_cur INTO i_JobList_cur;
	EXIT WHEN m_JobList_cur%NOTFOUND;

--Insert records in JobList

	INSERT INTO table_JobList(
		Name,
		ProjectCode,
		MainCode,
		SubCode,
		MainJobCode,
		SubJobCode,
		MainOffSetTime,
		SubOffSetTime,
		UnitCount,
		UnitPrice,
		QntFinished,
		StartDate,
		ExecuteDate,
		Note,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_JobList_cur.Name,
		i_JobList_cur.ProjectCode,
		i_JobList_cur.MainCode,
		i_JobList_cur.SubCode,
		i_JobList_cur.MainJobCode,
		i_JobList_cur.SubJobCode,
		i_JobList_cur.MainOffSetTime,
		i_JobList_cur.SubOffSetTime,
		i_JobList_cur.UnitCount,
		i_JobList_cur.UnitPrice,
		i_JobList_cur.QntFinished,
		i_JobList_cur.StartDate,
		i_JobList_cur.ExecuteDate,
		i_JobList_cur.Note,
		id,
		pnObjid,
		i_JobList_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_JobList_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

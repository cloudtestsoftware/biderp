CREATE OR REPLACE PROCEDURE Insert_Phase(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_Project_cur IS Select
		Name,
		Title,
		ProjectCode,
		Scope,
		LeadSourceCode,
		StartDate,
		EndDate,
		Status,
		CurrencyCode,
		ExtraCost,
		moduser
		from table_Project
	 where objid=pnObjid 
	 and not exists (select *from table_Project where Project2Phase=pnObjid);


--variables
id	NUMBER:=0;
i_Project_cur	m_Project_cur%rowtype;

BEGIN
--opening cursor m_Project_cur
	id:=0;
Begin
	OPEN m_Project_cur;
	LOOP
	id := id-1;
	FETCH m_Project_cur INTO i_Project_cur;
	EXIT WHEN m_Project_cur%NOTFOUND;

--Insert records in Project

	INSERT INTO table_Project(
		Name,
		Title,
		ProjectCode,
		Scope,
		LeadSourceCode,
		StartDate,
		EndDate,
		Status,
		CurrencyCode,
		ExtraCost,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_Project_cur.Name,
		i_Project_cur.Title,
		i_Project_cur.ProjectCode,
		i_Project_cur.Scope,
		i_Project_cur.LeadSourceCode,
		i_Project_cur.StartDate,
		i_Project_cur.EndDate,
		i_Project_cur.Status,
		i_Project_cur.CurrencyCode,
		i_Project_cur.ExtraCost,
		id,
		pnObjid,
		i_Project_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Project_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

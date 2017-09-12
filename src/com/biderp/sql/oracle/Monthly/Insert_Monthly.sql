CREATE OR REPLACE PROCEDURE Insert_Monthly(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_Approval_cur IS Select
		ObjId,
		Name,
		Title,
		ProjectCode,
		StartDate,
		MonthCode,
		YearCode,
		Quantity,
		EstAmount,
		Status,
		moduser
		from table_Approval
	 where objid=pnObjid 
	 and not exists (select *from table_Approval where Approval2Monthly=pnObjid);


--variables
id	NUMBER:=0;
i_Approval_cur	m_Approval_cur%rowtype;

BEGIN
--opening cursor m_Approval_cur
	id:=0;
Begin
	OPEN m_Approval_cur;
	LOOP
	id := id-1;
	FETCH m_Approval_cur INTO i_Approval_cur;
	EXIT WHEN m_Approval_cur%NOTFOUND;

--Insert records in Approval

	INSERT INTO table_Approval(
		ObjId,
		Name,
		Title,
		ProjectCode,
		StartDate,
		MonthCode,
		YearCode,
		Quantity,
		EstAmount,
		Status,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_Approval_cur.ObjId,
		i_Approval_cur.Name,
		i_Approval_cur.Title,
		i_Approval_cur.ProjectCode,
		i_Approval_cur.StartDate,
		i_Approval_cur.MonthCode,
		i_Approval_cur.YearCode,
		i_Approval_cur.Quantity,
		i_Approval_cur.EstAmount,
		i_Approval_cur.Status,
		id,
		pnObjid,
		i_Approval_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Approval_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

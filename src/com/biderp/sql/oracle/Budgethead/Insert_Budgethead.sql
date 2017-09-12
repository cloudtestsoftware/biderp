CREATE OR REPLACE PROCEDURE Insert_Budgethead(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_Allocation_cur IS Select
		Name,
		Note,
		Amount,
		ApproveDate,
		ApprovedBy,
		moduser
		from table_Allocation
	 where objid=pnObjid 
	 and not exists (select *from table_Allocation where Allocation2Budgethead=pnObjid);


--variables
id	NUMBER:=0;
i_Allocation_cur	m_Allocation_cur%rowtype;

BEGIN
--opening cursor m_Allocation_cur
	id:=0;
Begin
	OPEN m_Allocation_cur;
	LOOP
	id := id-1;
	FETCH m_Allocation_cur INTO i_Allocation_cur;
	EXIT WHEN m_Allocation_cur%NOTFOUND;

--Insert records in Allocation

	INSERT INTO table_Allocation(
		Name,
		Note,
		Amount,
		ApproveDate,
		ApprovedBy,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_Allocation_cur.Name,
		i_Allocation_cur.Note,
		i_Allocation_cur.Amount,
		i_Allocation_cur.ApproveDate,
		i_Allocation_cur.ApprovedBy,
		id,
		pnObjid,
		i_Allocation_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Allocation_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

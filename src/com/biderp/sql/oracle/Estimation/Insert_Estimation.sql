CREATE OR REPLACE PROCEDURE Insert_Estimation(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_Boq_cur IS Select
		Name,
		Title,
		ProjectCode,
		MainCode,
		SubCode,
		Description,
		UmCode,
		QntContract,
		Rate,
		DeptCode,
		Status,
		moduser
		from table_Boq
	 where objid=pnObjid 
	 and not exists (select *from table_Boq where Boq2Estimation=pnObjid);


--variables
id	NUMBER:=0;
i_Boq_cur	m_Boq_cur%rowtype;

BEGIN
--opening cursor m_Boq_cur
	id:=0;
Begin
	OPEN m_Boq_cur;
	LOOP
	id := id-1;
	FETCH m_Boq_cur INTO i_Boq_cur;
	EXIT WHEN m_Boq_cur%NOTFOUND;

--Insert records in Boq

	INSERT INTO table_Boq(
		Name,
		Title,
		ProjectCode,
		MainCode,
		SubCode,
		Description,
		UmCode,
		QntContract,
		Rate,
		DeptCode,
		Status,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_Boq_cur.Name,
		i_Boq_cur.Title,
		i_Boq_cur.ProjectCode,
		i_Boq_cur.MainCode,
		i_Boq_cur.SubCode,
		i_Boq_cur.Description,
		i_Boq_cur.UmCode,
		i_Boq_cur.QntContract,
		i_Boq_cur.Rate,
		i_Boq_cur.DeptCode,
		i_Boq_cur.Status,
		id,
		pnObjid,
		i_Boq_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Boq_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

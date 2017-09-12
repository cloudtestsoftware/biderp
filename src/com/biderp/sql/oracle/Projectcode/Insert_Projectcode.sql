CREATE OR REPLACE PROCEDURE Insert_Projectcode(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_MainCode_cur IS Select
		Name,
		ProjectCode,
		MainJobCode,
		DeptCode,
		UmCode,
		Description,
		Status,
		ApprovedBy,
		moduser
		from table_MainCode
	 where objid=pnObjid 
	 and not exists (select *from table_MainCode where MainCode2Projectcode=pnObjid);


--variables
id	NUMBER:=0;
i_MainCode_cur	m_MainCode_cur%rowtype;

BEGIN
--opening cursor m_MainCode_cur
	id:=0;
Begin
	OPEN m_MainCode_cur;
	LOOP
	id := id-1;
	FETCH m_MainCode_cur INTO i_MainCode_cur;
	EXIT WHEN m_MainCode_cur%NOTFOUND;

--Insert records in MainCode

	INSERT INTO table_MainCode(
		Name,
		ProjectCode,
		MainJobCode,
		DeptCode,
		UmCode,
		Description,
		Status,
		ApprovedBy,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_MainCode_cur.Name,
		i_MainCode_cur.ProjectCode,
		i_MainCode_cur.MainJobCode,
		i_MainCode_cur.DeptCode,
		i_MainCode_cur.UmCode,
		i_MainCode_cur.Description,
		i_MainCode_cur.Status,
		i_MainCode_cur.ApprovedBy,
		id,
		pnObjid,
		i_MainCode_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_MainCode_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

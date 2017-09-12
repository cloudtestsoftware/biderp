CREATE OR REPLACE PROCEDURE Insert_Maincode(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_SubCode_cur IS Select
		Name,
		ProjectCode,
		MainJobCode,
		SubJobCode,
		UmCode,
		UnitRate,
		Description,
		Status,
		ApprovedBy,
		moduser
		from table_SubCode
	 where objid=pnObjid 
	 and not exists (select *from table_SubCode where SubCode2Maincode=pnObjid);


--variables
id	NUMBER:=0;
i_SubCode_cur	m_SubCode_cur%rowtype;

BEGIN
--opening cursor m_SubCode_cur
	id:=0;
Begin
	OPEN m_SubCode_cur;
	LOOP
	id := id-1;
	FETCH m_SubCode_cur INTO i_SubCode_cur;
	EXIT WHEN m_SubCode_cur%NOTFOUND;

--Insert records in SubCode

	INSERT INTO table_SubCode(
		Name,
		ProjectCode,
		MainJobCode,
		SubJobCode,
		UmCode,
		UnitRate,
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
		i_SubCode_cur.Name,
		i_SubCode_cur.ProjectCode,
		i_SubCode_cur.MainJobCode,
		i_SubCode_cur.SubJobCode,
		i_SubCode_cur.UmCode,
		i_SubCode_cur.UnitRate,
		i_SubCode_cur.Description,
		i_SubCode_cur.Status,
		i_SubCode_cur.ApprovedBy,
		id,
		pnObjid,
		i_SubCode_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_SubCode_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

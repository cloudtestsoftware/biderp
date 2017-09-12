CREATE OR REPLACE PROCEDURE Insert_Boq(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_Parts_cur IS Select
		Name,
		ProjectCode,
		MainCode,
		SubCode,
		UnitCount,
		CoveredPrice,
		OriginCode,
		DeptCode,
		Status,
		Note,
		moduser
		from table_Parts
	 where objid=pnObjid 
	 and not exists (select *from table_Parts where Parts2Boq=pnObjid);



--Please Modify the cursor as you need

CURSOR m_TaskResource_cur IS Select
		Name,
		Title,
		ProjectCode,
		MainCode,
		SubCode,
		TaskCode,
		StartDate,
		EndDate,
		ResourceCode,
		EstUnit,
		QntRequest,
		ActualRate,
		OriginCode,
		PoCode,
		DeptCode,
		ProgressCode,
		moduser
		from table_TaskResource
	 where objid=pnObjid 
	 and not exists (select *from table_TaskResource where TaskResource2Boq=pnObjid);



--Please Modify the cursor as you need

CURSOR m_Change_cur IS Select
		Name,
		Title,
		ProjectCode,
		MainCode,
		SubCode,
		SeverityCode,
		Status,
		QntChange,
		ActualCost,
		ChangeDetail,
		Note,
		IssuedBy,
		moduser
		from table_Change
	 where objid=pnObjid 
	 and not exists (select *from table_Change where Change2Boq=pnObjid);



--Please Modify the cursor as you need

CURSOR m_Requirement_cur IS Select
		Name,
		Title,
		ProjectCode,
		MainCode,
		SubCode,
		Detail,
		Status,
		Note,
		moduser
		from table_Requirement
	 where objid=pnObjid 
	 and not exists (select *from table_Requirement where Requirement2Boq=pnObjid);


--variables
id	NUMBER:=0;
i_Parts_cur	m_Parts_cur%rowtype;
i_TaskResource_cur	m_TaskResource_cur%rowtype;
i_Change_cur	m_Change_cur%rowtype;
i_Requirement_cur	m_Requirement_cur%rowtype;

BEGIN
--opening cursor m_Parts_cur
	id:=0;
Begin
	OPEN m_Parts_cur;
	LOOP
	id := id-1;
	FETCH m_Parts_cur INTO i_Parts_cur;
	EXIT WHEN m_Parts_cur%NOTFOUND;

--Insert records in Parts

	INSERT INTO table_Parts(
		Name,
		ProjectCode,
		MainCode,
		SubCode,
		UnitCount,
		CoveredPrice,
		OriginCode,
		DeptCode,
		Status,
		Note,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_Parts_cur.Name,
		i_Parts_cur.ProjectCode,
		i_Parts_cur.MainCode,
		i_Parts_cur.SubCode,
		i_Parts_cur.UnitCount,
		i_Parts_cur.CoveredPrice,
		i_Parts_cur.OriginCode,
		i_Parts_cur.DeptCode,
		i_Parts_cur.Status,
		i_Parts_cur.Note,
		id,
		pnObjid,
		i_Parts_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Parts_cur;
 End;
--opening cursor m_TaskResource_cur
	id:=0;
Begin
	OPEN m_TaskResource_cur;
	LOOP
	id := id-1;
	FETCH m_TaskResource_cur INTO i_TaskResource_cur;
	EXIT WHEN m_TaskResource_cur%NOTFOUND;

--Insert records in TaskResource

	INSERT INTO table_TaskResource(
		Name,
		Title,
		ProjectCode,
		MainCode,
		SubCode,
		TaskCode,
		StartDate,
		EndDate,
		ResourceCode,
		EstUnit,
		QntRequest,
		ActualRate,
		OriginCode,
		PoCode,
		DeptCode,
		ProgressCode,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_TaskResource_cur.Name,
		i_TaskResource_cur.Title,
		i_TaskResource_cur.ProjectCode,
		i_TaskResource_cur.MainCode,
		i_TaskResource_cur.SubCode,
		i_TaskResource_cur.TaskCode,
		i_TaskResource_cur.StartDate,
		i_TaskResource_cur.EndDate,
		i_TaskResource_cur.ResourceCode,
		i_TaskResource_cur.EstUnit,
		i_TaskResource_cur.QntRequest,
		i_TaskResource_cur.ActualRate,
		i_TaskResource_cur.OriginCode,
		i_TaskResource_cur.PoCode,
		i_TaskResource_cur.DeptCode,
		i_TaskResource_cur.ProgressCode,
		id,
		pnObjid,
		i_TaskResource_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_TaskResource_cur;
 End;
--opening cursor m_Change_cur
	id:=0;
Begin
	OPEN m_Change_cur;
	LOOP
	id := id-1;
	FETCH m_Change_cur INTO i_Change_cur;
	EXIT WHEN m_Change_cur%NOTFOUND;

--Insert records in Change

	INSERT INTO table_Change(
		Name,
		Title,
		ProjectCode,
		MainCode,
		SubCode,
		SeverityCode,
		Status,
		QntChange,
		ActualCost,
		ChangeDetail,
		Note,
		IssuedBy,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_Change_cur.Name,
		i_Change_cur.Title,
		i_Change_cur.ProjectCode,
		i_Change_cur.MainCode,
		i_Change_cur.SubCode,
		i_Change_cur.SeverityCode,
		i_Change_cur.Status,
		i_Change_cur.QntChange,
		i_Change_cur.ActualCost,
		i_Change_cur.ChangeDetail,
		i_Change_cur.Note,
		i_Change_cur.IssuedBy,
		id,
		pnObjid,
		i_Change_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Change_cur;
 End;
--opening cursor m_Requirement_cur
	id:=0;
Begin
	OPEN m_Requirement_cur;
	LOOP
	id := id-1;
	FETCH m_Requirement_cur INTO i_Requirement_cur;
	EXIT WHEN m_Requirement_cur%NOTFOUND;

--Insert records in Requirement

	INSERT INTO table_Requirement(
		Name,
		Title,
		ProjectCode,
		MainCode,
		SubCode,
		Detail,
		Status,
		Note,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_Requirement_cur.Name,
		i_Requirement_cur.Title,
		i_Requirement_cur.ProjectCode,
		i_Requirement_cur.MainCode,
		i_Requirement_cur.SubCode,
		i_Requirement_cur.Detail,
		i_Requirement_cur.Status,
		i_Requirement_cur.Note,
		id,
		pnObjid,
		i_Requirement_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Requirement_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

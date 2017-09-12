CREATE OR REPLACE PROCEDURE Insert_Change(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_LogNote_cur IS Select
		Name,
		Note,
		CreateDate,
		LogIndex,
		moduser
		from table_LogNote
	 where objid=pnObjid 
	 and not exists (select *from table_LogNote where LogNote2Change=pnObjid);



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
	 and not exists (select *from table_Parts where Parts2Change=pnObjid);



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
	 and not exists (select *from table_TaskResource where TaskResource2Change=pnObjid);



--Please Modify the cursor as you need

CURSOR m_WhatToDo_cur IS Select
		Name,
		YouShould,
		Status,
		RelatedName,
		WhatYouDid,
		ExpiryDate,
		moduser
		from table_WhatToDo
	 where objid=pnObjid 
	 and not exists (select *from table_WhatToDo where WhatToDo2Change=pnObjid);


--variables
id	NUMBER:=0;
i_LogNote_cur	m_LogNote_cur%rowtype;
i_Parts_cur	m_Parts_cur%rowtype;
i_TaskResource_cur	m_TaskResource_cur%rowtype;
i_WhatToDo_cur	m_WhatToDo_cur%rowtype;

BEGIN
--opening cursor m_LogNote_cur
	id:=0;
Begin
	OPEN m_LogNote_cur;
	LOOP
	id := id-1;
	FETCH m_LogNote_cur INTO i_LogNote_cur;
	EXIT WHEN m_LogNote_cur%NOTFOUND;

--Insert records in LogNote

	INSERT INTO table_LogNote(
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
		i_LogNote_cur.Name,
		i_LogNote_cur.Note,
		i_LogNote_cur.CreateDate,
		i_LogNote_cur.LogIndex,
		id,
		pnObjid,
		i_LogNote_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_LogNote_cur;
 End;
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
--opening cursor m_WhatToDo_cur
	id:=0;
Begin
	OPEN m_WhatToDo_cur;
	LOOP
	id := id-1;
	FETCH m_WhatToDo_cur INTO i_WhatToDo_cur;
	EXIT WHEN m_WhatToDo_cur%NOTFOUND;

--Insert records in WhatToDo

	INSERT INTO table_WhatToDo(
		Name,
		YouShould,
		Status,
		RelatedName,
		WhatYouDid,
		ExpiryDate,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_WhatToDo_cur.Name,
		i_WhatToDo_cur.YouShould,
		i_WhatToDo_cur.Status,
		i_WhatToDo_cur.RelatedName,
		i_WhatToDo_cur.WhatYouDid,
		i_WhatToDo_cur.ExpiryDate,
		id,
		pnObjid,
		i_WhatToDo_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_WhatToDo_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

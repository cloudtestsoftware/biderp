CREATE OR REPLACE PROCEDURE Insert_Milestone(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



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
	 and not exists (select *from table_TaskResource where TaskResource2Milestone=pnObjid);



--Please Modify the cursor as you need

CURSOR m_BoqPlan_cur IS Select
		Name,
		Title,
		ProjectCode,
		MonthCode,
		YearCode,
		Quantity,
		QntFinished,
		Status,
		Note,
		moduser
		from table_BoqPlan
	 where objid=pnObjid 
	 and not exists (select *from table_BoqPlan where BoqPlan2Milestone=pnObjid);



--Please Modify the cursor as you need

CURSOR m_MSLink_cur IS Select
		Objid,
		Name,
		Title,
		ProjectCode,
		MainCode,
		SubCode,
		StartBefore,
		StartActual,
		FloatCount,
		StartDate,
		EndDate,
		moduser
		from table_MSLink
	 where objid=pnObjid 
	 and not exists (select *from table_MSLink where MSLink2Milestone=pnObjid);



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
	 and not exists (select *from table_WhatToDo where WhatToDo2Milestone=pnObjid);


--variables
id	NUMBER:=0;
i_TaskResource_cur	m_TaskResource_cur%rowtype;
i_BoqPlan_cur	m_BoqPlan_cur%rowtype;
i_MSLink_cur	m_MSLink_cur%rowtype;
i_WhatToDo_cur	m_WhatToDo_cur%rowtype;

BEGIN
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
--opening cursor m_BoqPlan_cur
	id:=0;
Begin
	OPEN m_BoqPlan_cur;
	LOOP
	id := id-1;
	FETCH m_BoqPlan_cur INTO i_BoqPlan_cur;
	EXIT WHEN m_BoqPlan_cur%NOTFOUND;

--Insert records in BoqPlan

	INSERT INTO table_BoqPlan(
		Name,
		Title,
		ProjectCode,
		MonthCode,
		YearCode,
		Quantity,
		QntFinished,
		Status,
		Note,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_BoqPlan_cur.Name,
		i_BoqPlan_cur.Title,
		i_BoqPlan_cur.ProjectCode,
		i_BoqPlan_cur.MonthCode,
		i_BoqPlan_cur.YearCode,
		i_BoqPlan_cur.Quantity,
		i_BoqPlan_cur.QntFinished,
		i_BoqPlan_cur.Status,
		i_BoqPlan_cur.Note,
		id,
		pnObjid,
		i_BoqPlan_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_BoqPlan_cur;
 End;
--opening cursor m_MSLink_cur
	id:=0;
Begin
	OPEN m_MSLink_cur;
	LOOP
	id := id-1;
	FETCH m_MSLink_cur INTO i_MSLink_cur;
	EXIT WHEN m_MSLink_cur%NOTFOUND;

--Insert records in MSLink

	INSERT INTO table_MSLink(
		Objid,
		Name,
		Title,
		ProjectCode,
		MainCode,
		SubCode,
		StartBefore,
		StartActual,
		FloatCount,
		StartDate,
		EndDate,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_MSLink_cur.Objid,
		i_MSLink_cur.Name,
		i_MSLink_cur.Title,
		i_MSLink_cur.ProjectCode,
		i_MSLink_cur.MainCode,
		i_MSLink_cur.SubCode,
		i_MSLink_cur.StartBefore,
		i_MSLink_cur.StartActual,
		i_MSLink_cur.FloatCount,
		i_MSLink_cur.StartDate,
		i_MSLink_cur.EndDate,
		id,
		pnObjid,
		i_MSLink_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_MSLink_cur;
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

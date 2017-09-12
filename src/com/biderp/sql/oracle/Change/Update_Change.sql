CREATE OR REPLACE PROCEDURE Update_Change(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Change set Name=Name,
		Title=Title,
		ProjectCode=ProjectCode,
		MainCode=MainCode,
		SubCode=SubCode,
		SeverityCode=SeverityCode,
		Status=Status,
		QntChange=QntChange,
		EstCost=EstCost,
		ActualCost=ActualCost,
		ChangeDetail=ChangeDetail,
		UmCode=UmCode,
		Note=Note,
		IssuedBy=IssuedBy,
		moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for LogNote
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_LogNote set LogNote2Change=pnObjid where LogNote2Change=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for Parts
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Parts set Parts2Change=pnObjid where Parts2Change=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for TaskResource
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_TaskResource set TaskResource2Change=pnObjid where TaskResource2Change=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for WhatToDo
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_WhatToDo set WhatToDo2Change=pnObjid where WhatToDo2Change=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

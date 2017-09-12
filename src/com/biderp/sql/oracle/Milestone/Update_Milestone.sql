CREATE OR REPLACE PROCEDURE Update_Milestone(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Milestone set moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for TaskResource
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_TaskResource set TaskResource2Milestone=pnObjid where TaskResource2Milestone=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for BoqPlan
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_BoqPlan set BoqPlan2Milestone=pnObjid where BoqPlan2Milestone=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for MSLink
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_MSLink set MSLink2Milestone=pnObjid where MSLink2Milestone=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for WhatToDo
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_WhatToDo set WhatToDo2Milestone=pnObjid where WhatToDo2Milestone=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

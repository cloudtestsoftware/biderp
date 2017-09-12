CREATE OR REPLACE PROCEDURE Update_Joblist(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Joblist set moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for Parts
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Parts set Parts2Joblist=pnObjid where Parts2Joblist=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for TaskResource
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_TaskResource set TaskResource2Joblist=pnObjid where TaskResource2Joblist=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

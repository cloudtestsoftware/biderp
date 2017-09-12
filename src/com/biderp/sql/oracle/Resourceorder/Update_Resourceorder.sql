CREATE OR REPLACE PROCEDURE Update_Resourceorder(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Resourceorder set moduser=moduser 
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
		update table_TaskResource set TaskResource2Resourceorder=pnObjid where TaskResource2Resourceorder=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for PoInvoice
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_PoInvoice set PoInvoice2Resourceorder=pnObjid where PoInvoice2Resourceorder=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

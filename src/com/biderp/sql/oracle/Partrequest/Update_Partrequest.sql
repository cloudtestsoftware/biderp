CREATE OR REPLACE PROCEDURE Update_Partrequest(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Partrequest set moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for ItemReceived
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_ItemReceived set ItemReceived2Partrequest=pnObjid where ItemReceived2Partrequest=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for ItemDispatch
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_ItemDispatch set ItemDispatch2Partrequest=pnObjid where ItemDispatch2Partrequest=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

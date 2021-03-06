CREATE OR REPLACE PROCEDURE Update_Inventory(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Inventory set Name=Name,
		Description=Description,
		UmCode=UmCode,
		PartCode=PartCode,
		QntRequest=QntRequest,
		QntUsed=QntUsed,
		QntBalance=QntBalance,
		QntOrdered=QntOrdered,
		QntToOrdered=QntToOrdered,
		moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for Transaction
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Transaction set Transaction2Inventory=pnObjid where Transaction2Inventory=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

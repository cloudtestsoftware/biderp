CREATE OR REPLACE PROCEDURE Update_Substruction(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Substruction set Name=Name,
		AdjustCode=AdjustCode,
		TaxTypeCode=TaxTypeCode,
		Amount=Amount,
		ApplyDate=ApplyDate,
		Note=Note,
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
		update table_Transaction set Transaction2Substruction=pnObjid where Transaction2Substruction=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

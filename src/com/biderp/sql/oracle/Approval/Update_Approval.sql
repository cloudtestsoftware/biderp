CREATE OR REPLACE PROCEDURE Update_Approval(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Approval set ObjId=ObjId,
		Name=Name,
		Title=Title,
		ProjectCode=ProjectCode,
		StartDate=StartDate,
		MonthCode=MonthCode,
		YearCode=YearCode,
		Quantity=Quantity,
		EstAmount=EstAmount,
		Status=Status,
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
		update table_Transaction set Transaction2Approval=pnObjid where Transaction2Approval=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

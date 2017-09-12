CREATE OR REPLACE PROCEDURE Update_Yearly(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Yearly set Name=Name,
		Title=Title,
		ProjectCode=ProjectCode,
		YearCode=YearCode,
		FundRequested=FundRequested,
		FundApproved=FundApproved,
		PctProjectEst=PctProjectEst,
		AlotedPercent=AlotedPercent,
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
		update table_Transaction set Transaction2Yearly=pnObjid where Transaction2Yearly=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

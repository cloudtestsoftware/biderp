CREATE OR REPLACE PROCEDURE Update_Monthly(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Monthly set Name=Name,
		Title=Title,
		ProjectCode=ProjectCode,
		StartDate=StartDate,
		MonthCode=MonthCode,
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

--Modifying Child record for Approval
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Approval set Approval2Monthly=pnObjid where Approval2Monthly=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

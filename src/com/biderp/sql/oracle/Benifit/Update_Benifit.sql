CREATE OR REPLACE PROCEDURE Update_Benifit(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Benifit set Name=Name,
		BenifitCode=BenifitCode,
		BenifitType=BenifitType,
		MonthlyCost=MonthlyCost,
		AnnualCost=AnnualCost,
		StartDate=StartDate,
		Note=Note,
		moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for BenifitChange
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_BenifitChange set BenifitChange2Benifit=pnObjid where BenifitChange2Benifit=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

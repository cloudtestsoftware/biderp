CREATE OR REPLACE PROCEDURE Update_Performance(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Performance set Name=Name,
		YearCode=YearCode,
		DeptCode=DeptCode,
		CountryCode=CountryCode,
		EvalCode=EvalCode,
		EvalGroupCode=EvalGroupCode,
		JobCode=JobCode,
		FromDate=FromDate,
		TillDate=TillDate,
		Incharge=Incharge,
		Objective=Objective,
		moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for Participants
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Participants set Participants2Performance=pnObjid where Participants2Performance=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

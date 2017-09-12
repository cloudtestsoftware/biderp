CREATE OR REPLACE PROCEDURE Update_Participants(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Participants set Name=Name,
		LastName=LastName,
		EmpNo=EmpNo,
		ReportTo=ReportTo,
		JoinDate=JoinDate,
		EvalCode=EvalCode,
		JobCode=JobCode,
		Instruction=Instruction,
		Summary=Summary,
		Total=Total,
		moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for Evaluation
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Evaluation set Evaluation2Participants=pnObjid where Evaluation2Participants=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

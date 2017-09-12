CREATE OR REPLACE PROCEDURE Update_Estimation(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Estimation set Name=Name,
		Title=Title,
		ProjectCode=ProjectCode,
		MainCode=MainCode,
		StartDate=StartDate,
		EndDate=EndDate,
		Status=Status,
		ContractAmount=ContractAmount,
		EstChangeCost=EstChangeCost,
		ActChangeCost=ActChangeCost,
		EstJobCost=EstJobCost,
		ActJobCost=ActJobCost,
		NetAmount=NetAmount,
		Note=Note,
		moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for Boq
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Boq set Boq2Estimation=pnObjid where Boq2Estimation=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

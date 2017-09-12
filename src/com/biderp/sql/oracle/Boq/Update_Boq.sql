CREATE OR REPLACE PROCEDURE Update_Boq(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Boq set Name=Name,
		Title=Title,
		ProjectCode=ProjectCode,
		MainCode=MainCode,
		SubCode=SubCode,
		Description=Description,
		UmCode=UmCode,
		QntContract=QntContract,
		QntChange=QntChange,
		QntTotal=QntTotal,
		Rate=Rate,
		ActualRate=ActualRate,
		ContractToActual=ContractToActual,
		ContractToEst=ContractToEst,
		DeptCode=DeptCode,
		Status=Status,
		moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for Parts
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Parts set Parts2Boq=pnObjid where Parts2Boq=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for TaskResource
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_TaskResource set TaskResource2Boq=pnObjid where TaskResource2Boq=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for Change
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Change set Change2Boq=pnObjid where Change2Boq=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for Requirement
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Requirement set Requirement2Boq=pnObjid where Requirement2Boq=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

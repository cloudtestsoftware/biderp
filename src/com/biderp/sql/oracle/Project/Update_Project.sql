CREATE OR REPLACE PROCEDURE Update_Project(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Project set Name=Name,
		Title=Title,
		ProjectNo=ProjectNo,
		ProjectCode=ProjectCode,
		Scope=Scope,
		LeadSourceCode=LeadSourceCode,
		StartDate=StartDate,
		EndDate=EndDate,
		Status=Status,
		CurrencyCode=CurrencyCode,
		ExtraCost=ExtraCost,
		TotalTax=TotalTax,
		ContractCost=ContractCost,
		TotalCost=TotalCost,
		ActualCost=ActualCost,
		AchievedTarget=AchievedTarget,
		BudgetUsed=BudgetUsed,
		moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for JobMaster
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_JobMaster set JobMaster2Project=pnObjid where JobMaster2Project=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for Contact
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Contact set Contact2Project=pnObjid where Contact2Project=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for Estimation
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Estimation set Estimation2Project=pnObjid where Estimation2Project=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for ProjectPlan
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_ProjectPlan set ProjectPlan2Project=pnObjid where ProjectPlan2Project=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for Budget
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Budget set Budget2Project=pnObjid where Budget2Project=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for Tax
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Tax set Tax2Project=pnObjid where Tax2Project=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for PoMaster
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_PoMaster set PoMaster2Project=pnObjid where PoMaster2Project=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

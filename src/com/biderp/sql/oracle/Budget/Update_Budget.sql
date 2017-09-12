CREATE OR REPLACE PROCEDURE Update_Budget(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Budget set Name=Name,
		Title=Title,
		ProjectCode=ProjectCode,
		TotalContract=TotalContract,
		TotalWithTax=TotalWithTax,
		InitialEstimate=InitialEstimate,
		CumulativeCost=CumulativeCost,
		FundReleased=FundReleased,
		FundBalance=FundBalance,
		FundUsed=FundUsed,
		FundUnUsed=FundUnUsed,
		ContractToCost=ContractToCost,
		AchievedTarget=AchievedTarget,
		BudgetUsed=BudgetUsed,
		Status=Status,
		moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for BudgetHead
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_BudgetHead set BudgetHead2Budget=pnObjid where BudgetHead2Budget=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for BudgetPlan
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_BudgetPlan set BudgetPlan2Budget=pnObjid where BudgetPlan2Budget=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for Invoice
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Invoice set Invoice2Budget=pnObjid where Invoice2Budget=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for Payment
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Payment set Payment2Budget=pnObjid where Payment2Budget=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for Monthly
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Monthly set Monthly2Budget=pnObjid where Monthly2Budget=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for Yearly
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Yearly set Yearly2Budget=pnObjid where Yearly2Budget=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

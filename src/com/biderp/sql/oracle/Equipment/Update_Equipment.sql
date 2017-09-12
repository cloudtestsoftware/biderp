CREATE OR REPLACE PROCEDURE Update_Equipment(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Equipment set Name=Name,
		Description=Description,
		PartNo=PartNo,
		TagNo=TagNo,
		UmCode=UmCode,
		ExpectedLife=ExpectedLife,
		UnitUses=UnitUses,
		PendingUses=PendingUses,
		TotalUses=TotalUses,
		PurchaseCost=PurchaseCost,
		CurrentValue=CurrentValue,
		RevenueEarned=RevenueEarned,
		RevenueExpected=RevenueExpected,
		TotalRevenue=TotalRevenue,
		Status=Status,
		Note=Note,
		AccountGroup=AccountGroup,
		OwnerCode=OwnerCode,
		moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for FindAsset
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_FindAsset set FindAsset2Equipment=pnObjid where FindAsset2Equipment=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for Maintenance
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Maintenance set Maintenance2Equipment=pnObjid where Maintenance2Equipment=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for YearlyCost
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_YearlyCost set YearlyCost2Equipment=pnObjid where YearlyCost2Equipment=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for Depricated
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Depricated set Depricated2Equipment=pnObjid where Depricated2Equipment=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for AccountDebit
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_AccountDebit set AccountDebit2Equipment=pnObjid where AccountDebit2Equipment=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

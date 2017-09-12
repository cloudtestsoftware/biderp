CREATE OR REPLACE PROCEDURE Update_Asset(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Asset set Name=Name,
		Location=Location,
		Description=Description,
		Type=Type,
		PurchaseCost=PurchaseCost,
		AssetValue=AssetValue,
		PurchaseDate=PurchaseDate,
		AssetTag=AssetTag,
		Status=Status,
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

--Modifying Child record for Maintenance
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Maintenance set Maintenance2Asset=pnObjid where Maintenance2Asset=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for ServiceCost
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_ServiceCost set ServiceCost2Asset=pnObjid where ServiceCost2Asset=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for Depricated
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Depricated set Depricated2Asset=pnObjid where Depricated2Asset=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for Address
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Address set Address2Asset=pnObjid where Address2Asset=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for AccountDebit
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_AccountDebit set AccountDebit2Asset=pnObjid where AccountDebit2Asset=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

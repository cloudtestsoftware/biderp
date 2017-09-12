CREATE OR REPLACE PROCEDURE Update_Site(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Site set Name=Name,
		Description=Description,
		StartDate=StartDate,
		CompleteDate=CompleteDate,
		Address=Address,
		City=City,
		ZipCode=ZipCode,
		State=State,
		SiteIncharge=SiteIncharge,
		Phone=Phone,
		Phone2=Phone2,
		Fax=Fax,
		Email=Email,
		OtherContact=OtherContact,
		OtherInfo=OtherInfo,
		Status=Status,
		Total=Total,
		moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for Phase
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Phase set Phase2Site=pnObjid where Phase2Site=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for Warehouse
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Warehouse set Warehouse2Site=pnObjid where Warehouse2Site=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for Machinary
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Machinary set Machinary2Site=pnObjid where Machinary2Site=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for AssetPlan
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_AssetPlan set AssetPlan2Site=pnObjid where AssetPlan2Site=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for Asset
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Asset set Asset2Site=pnObjid where Asset2Site=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

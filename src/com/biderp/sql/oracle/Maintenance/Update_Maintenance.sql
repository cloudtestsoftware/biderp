CREATE OR REPLACE PROCEDURE Update_Maintenance(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Maintenance set Name=Name,
		Description=Description,
		ServiceType=ServiceType,
		Category=Category,
		DeptCode=DeptCode,
		ServiceDate=ServiceDate,
		Status=Status,
		TotalCost=TotalCost,
		moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for ServiceParts
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_ServiceParts set ServiceParts2Maintenance=pnObjid where ServiceParts2Maintenance=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for ServiceResource
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_ServiceResource set ServiceResource2Maintenance=pnObjid where ServiceResource2Maintenance=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for MaintenanceLog
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_MaintenanceLog set MaintenanceLog2Maintenance=pnObjid where MaintenanceLog2Maintenance=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

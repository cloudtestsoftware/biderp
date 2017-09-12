CREATE OR REPLACE PROCEDURE Update_Warehouse(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Warehouse set Name=Name,
		Location=Location,
		moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for Address
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Address set Address2Warehouse=pnObjid where Address2Warehouse=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for WarehouseLine
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_WarehouseLine set WarehouseLine2Warehouse=pnObjid where WarehouseLine2Warehouse=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

CREATE OR REPLACE PROCEDURE Update_Boqplan(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Boqplan set moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for Material
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Material set Material2Boqplan=pnObjid where Material2Boqplan=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for ResourceAudit
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_ResourceAudit set ResourceAudit2Boqplan=pnObjid where ResourceAudit2Boqplan=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for Inspection
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Inspection set Inspection2Boqplan=pnObjid where Inspection2Boqplan=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

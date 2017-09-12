CREATE OR REPLACE PROCEDURE Update_Projectcontrol(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Projectcontrol set moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for CurrentPlan
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_CurrentPlan set CurrentPlan2Projectcontrol=pnObjid where CurrentPlan2Projectcontrol=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for PartPlan
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_PartPlan set PartPlan2Projectcontrol=pnObjid where PartPlan2Projectcontrol=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for ResourcePlan
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_ResourcePlan set ResourcePlan2Projectcontrol=pnObjid where ResourcePlan2Projectcontrol=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

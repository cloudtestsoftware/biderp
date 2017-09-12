CREATE OR REPLACE PROCEDURE Update_Weeklylead(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Weeklylead set moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for WeekLeadContact
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_WeekLeadContact set WeekLeadContact2Weeklylead=pnObjid where WeekLeadContact2Weeklylead=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for WeekLeadJobs
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_WeekLeadJobs set WeekLeadJobs2Weeklylead=pnObjid where WeekLeadJobs2Weeklylead=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

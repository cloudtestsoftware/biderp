CREATE OR REPLACE PROCEDURE Update_Projectlead(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Projectlead set moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for LeadContact
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_LeadContact set LeadContact2Projectlead=pnObjid where LeadContact2Projectlead=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for LeadJobs
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_LeadJobs set LeadJobs2Projectlead=pnObjid where LeadJobs2Projectlead=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

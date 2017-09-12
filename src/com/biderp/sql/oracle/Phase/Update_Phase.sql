CREATE OR REPLACE PROCEDURE Update_Phase(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Phase set Name=Name,
		Description=Description,
		StartDate=StartDate,
		CompleteDate=CompleteDate,
		PhaseIncharge=PhaseIncharge,
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

--Modifying Child record for Project
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Project set Project2Phase=pnObjid where Project2Phase=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

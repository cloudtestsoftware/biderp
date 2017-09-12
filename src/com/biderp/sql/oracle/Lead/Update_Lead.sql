CREATE OR REPLACE PROCEDURE Update_Lead(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Lead set Name=Name,
		FirstName=FirstName,
		LastName=LastName,
		Company=Company,
		Url=Url,
		Street=Street,
		City=City,
		State=State,
		ZipCode=ZipCode,
		CountryCode=CountryCode,
		Phone=Phone,
		Phone2=Phone2,
		Fax=Fax,
		ReasonCode=ReasonCode,
		MediaCode=MediaCode,
		CreateDate=CreateDate,
		Status=Status,
		AssignTo=AssignTo,
		moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for LeadNote
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_LeadNote set LeadNote2Lead=pnObjid where LeadNote2Lead=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for LeadEmail
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_LeadEmail set LeadEmail2Lead=pnObjid where LeadEmail2Lead=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for LeadAccess
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_LeadAccess set LeadAccess2Lead=pnObjid where LeadAccess2Lead=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

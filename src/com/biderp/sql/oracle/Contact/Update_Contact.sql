CREATE OR REPLACE PROCEDURE Update_Contact(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Contact set Name=Name,
		LastName=LastName,
		Attention=Attention,
		Street=Street,
		City=City,
		State=State,
		ZipCode=ZipCode,
		CountryCode=CountryCode,
		Phone=Phone,
		Phone2=Phone2,
		Fax=Fax,
		Email=Email,
		ContactCode=ContactCode,
		moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for Transaction
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Transaction set Transaction2Contact=pnObjid where Transaction2Contact=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

CREATE OR REPLACE PROCEDURE Update_Account(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Account set Name=Name,
		LastName=LastName,
		Address=Address,
		Phone1=Phone1,
		Phone2=Phone2,
		Fax=Fax,
		Email=Email,
		Amount=Amount,
		StartDate=StartDate,
		AccountGroup=AccountGroup,
		AccountCode=AccountCode,
		Note=Note,
		Status=Status,
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
		update table_Transaction set Transaction2Account=pnObjid where Transaction2Account=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

CREATE OR REPLACE PROCEDURE Update_Transaction(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Transaction set Name=Name,
		Amount=Amount,
		Debited=Debited,
		Credited=Credited,
		TransactionType=TransactionType,
		TransactionCode=TransactionCode,
		Voucher=Voucher,
		PostDate=PostDate,
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
		update table_Transaction set Transaction2Transaction=pnObjid where Transaction2Transaction=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

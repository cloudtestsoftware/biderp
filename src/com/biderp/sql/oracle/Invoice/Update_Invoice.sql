CREATE OR REPLACE PROCEDURE Update_Invoice(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Invoice set Name=Name,
		FromDate=FromDate,
		UptoDate=UptoDate,
		Amount=Amount,
		Tax=Tax,
		Total=Total,
		moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for InvoiceItem
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_InvoiceItem set InvoiceItem2Invoice=pnObjid where InvoiceItem2Invoice=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

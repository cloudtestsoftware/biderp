CREATE OR REPLACE PROCEDURE Update_Poinvoice(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Poinvoice set moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for InvoicedItems
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_InvoicedItems set InvoicedItems2Poinvoice=pnObjid where InvoicedItems2Poinvoice=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for InvoicedResource
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_InvoicedResource set InvoicedResource2Poinvoice=pnObjid where InvoicedResource2Poinvoice=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

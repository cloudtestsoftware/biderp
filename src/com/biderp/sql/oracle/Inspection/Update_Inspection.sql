CREATE OR REPLACE PROCEDURE Update_Inspection(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Inspection set Name=Name,
		Note=Note,
		InspectDate=InspectDate,
		QntInspected=QntInspected,
		Status=Status,
		moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for InvoiceItemList
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_InvoiceItemList set InvoiceItemList2Inspection=pnObjid where InvoiceItemList2Inspection=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

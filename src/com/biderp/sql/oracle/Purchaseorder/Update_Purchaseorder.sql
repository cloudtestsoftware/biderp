CREATE OR REPLACE PROCEDURE Update_Purchaseorder(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Purchaseorder set moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for PartRequest
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_PartRequest set PartRequest2Purchaseorder=pnObjid where PartRequest2Purchaseorder=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for PurchaseIndent
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_PurchaseIndent set PurchaseIndent2Purchaseorder=pnObjid where PurchaseIndent2Purchaseorder=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for PoInvoice
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_PoInvoice set PoInvoice2Purchaseorder=pnObjid where PoInvoice2Purchaseorder=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

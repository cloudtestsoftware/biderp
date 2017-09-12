CREATE OR REPLACE PROCEDURE Update_Pomaster(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Pomaster set moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for PurchaseOrder
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_PurchaseOrder set PurchaseOrder2Pomaster=pnObjid where PurchaseOrder2Pomaster=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for PoSupplier
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_PoSupplier set PoSupplier2Pomaster=pnObjid where PoSupplier2Pomaster=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for ResourceOrder
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_ResourceOrder set ResourceOrder2Pomaster=pnObjid where ResourceOrder2Pomaster=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for ResourceSupplier
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_ResourceSupplier set ResourceSupplier2Pomaster=pnObjid where ResourceSupplier2Pomaster=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for BomPurchase
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_BomPurchase set BomPurchase2Pomaster=pnObjid where BomPurchase2Pomaster=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for DeptPoBudget
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_DeptPoBudget set DeptPoBudget2Pomaster=pnObjid where DeptPoBudget2Pomaster=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

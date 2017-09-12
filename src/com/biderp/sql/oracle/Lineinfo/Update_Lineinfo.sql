CREATE OR REPLACE PROCEDURE Update_Lineinfo(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Lineinfo set Name=Name,
		Warehouse=Warehouse,
		WarehouseLine=WarehouseLine,
		ProjectNo=ProjectNo,
		ProjectName=ProjectName,
		QntRequest=QntRequest,
		QntUsed=QntUsed,
		QntPurchased=QntPurchased,
		QntBalance=QntBalance,
		qntaccepted=qntaccepted,
		Status=Status,
		StartDate=StartDate,
		PurchaseDate=PurchaseDate,
		PartrequestId=PartrequestId,
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
		update table_Transaction set Transaction2Lineinfo=pnObjid where Transaction2Lineinfo=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

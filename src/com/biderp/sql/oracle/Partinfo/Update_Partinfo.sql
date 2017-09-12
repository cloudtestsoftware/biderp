CREATE OR REPLACE PROCEDURE Update_Partinfo(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Partinfo set Name=Name,
		ProjectNo=ProjectNo,
		ProjectName=ProjectName,
		ProjectCode=ProjectCode,
		QntRequest=QntRequest,
		QntUsed=QntUsed,
		QntPurchased=QntPurchased,
		QntBalance=QntBalance,
		totalqntaccepted=totalqntaccepted,
		totalqntdispatched=totalqntdispatched,
		Status=Status,
		StartDate=StartDate,
		PurchaseDate=PurchaseDate,
		Note=Note,
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
		update table_Transaction set Transaction2Partinfo=pnObjid where Transaction2Partinfo=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

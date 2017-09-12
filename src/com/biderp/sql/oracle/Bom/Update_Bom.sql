CREATE OR REPLACE PROCEDURE Update_Bom(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Bom set Name=Name,
		MrNo=MrNo,
		Title=Title,
		PartNo=PartNo,
		UmCode=UmCode,
		DeptCode=DeptCode,
		PoCode=PoCode,
		QntRequest=QntRequest,
		QntUsed=QntUsed,
		QntBalance=QntBalance,
		QntToPurchase=QntToPurchase,
		LineCount=LineCount,
		StartDate=StartDate,
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
		update table_Transaction set Transaction2Bom=pnObjid where Transaction2Bom=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

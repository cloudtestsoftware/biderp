CREATE OR REPLACE PROCEDURE Update_Material(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Material set Name=Name,
		ProjectCode=ProjectCode,
		MainCode=MainCode,
		SubCode=SubCode,
		DomainCode=DomainCode,
		PartCode=PartCode,
		PartNo=PartNo,
		Description=Description,
		UnitPrice=UnitPrice,
		UnitCount=UnitCount,
		QntRequest=QntRequest,
		QntUsed=QntUsed,
		QntPurchased=QntPurchased,
		UmCode=UmCode,
		OriginCode=OriginCode,
		Note=Note,
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
		update table_Transaction set Transaction2Material=pnObjid where Transaction2Material=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

CREATE OR REPLACE PROCEDURE Update_Indent(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Indent set Name=Name,
		IndentNo=IndentNo,
		MrNo=MrNo,
		PartNo=PartNo,
		Description=Description,
		UmCode=UmCode,
		DeptCode=DeptCode,
		PoCode=PoCode,
		QntRequest=QntRequest,
		Total=Total,
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
		update table_Transaction set Transaction2Indent=pnObjid where Transaction2Indent=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

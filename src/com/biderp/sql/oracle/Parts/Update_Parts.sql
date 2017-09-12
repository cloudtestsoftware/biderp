CREATE OR REPLACE PROCEDURE Update_Parts(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Parts set Name=Name,
		Title=Title,
		ProjectCode=ProjectCode,
		MainCode=MainCode,
		SubCode=SubCode,
		DomainCode=DomainCode,
		PartCode=PartCode,
		PartNo=PartNo,
		Description=Description,
		StartDate=StartDate,
		EndDate=EndDate,
		UnitPrice=UnitPrice,
		UnitCount=UnitCount,
		UmCode=UmCode,
		CoveredPrice=CoveredPrice,
		OriginCode=OriginCode,
		DeptCode=DeptCode,
		Status=Status,
		Note=Note,
		moduser=moduser 
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
		update table_PartRequest set PartRequest2Parts=pnObjid where PartRequest2Parts=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for Indent
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Indent set Indent2Parts=pnObjid where Indent2Parts=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

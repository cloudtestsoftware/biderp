CREATE OR REPLACE PROCEDURE Update_Machinary(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Machinary set Name=Name,
		SiteName=SiteName,
		UmCode=UmCode,
		EstUnit=EstUnit,
		QntRequest=QntRequest,
		StartDate=StartDate,
		EndDate=EndDate,
		UseFactor=UseFactor,
		moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for AssetPlan
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_AssetPlan set AssetPlan2Machinary=pnObjid where AssetPlan2Machinary=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

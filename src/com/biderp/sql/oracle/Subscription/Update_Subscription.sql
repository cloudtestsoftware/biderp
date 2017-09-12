CREATE OR REPLACE PROCEDURE Update_Subscription(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Subscription set Name=Name,
		SubscribeCode=SubscribeCode,
		StartDate=StartDate,
		EndDate=EndDate,
		FullName=FullName,
		CardNo=CardNo,
		MonthCode=MonthCode,
		YearCode=YearCode,
		CardId=CardId,
		CardTypeCode=CardTypeCode,
		Status=Status,
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
		update table_Transaction set Transaction2Subscription=pnObjid where Transaction2Subscription=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

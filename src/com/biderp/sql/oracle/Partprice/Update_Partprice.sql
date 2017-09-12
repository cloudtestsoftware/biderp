CREATE OR REPLACE PROCEDURE Update_Partprice(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Partprice set moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for OrderPending
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_OrderPending set OrderPending2Partprice=pnObjid where OrderPending2Partprice=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for PartCount
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_PartCount set PartCount2Partprice=pnObjid where PartCount2Partprice=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for QRInfo
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_QRInfo set QRInfo2Partprice=pnObjid where QRInfo2Partprice=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

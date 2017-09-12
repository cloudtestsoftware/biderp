CREATE OR REPLACE PROCEDURE Update_Quotejobs(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Quotejobs set moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for BidQuiz
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_BidQuiz set BidQuiz2Quotejobs=pnObjid where BidQuiz2Quotejobs=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for QuoteParts
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_QuoteParts set QuoteParts2Quotejobs=pnObjid where QuoteParts2Quotejobs=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for QuoteResource
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_QuoteResource set QuoteResource2Quotejobs=pnObjid where QuoteResource2Quotejobs=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

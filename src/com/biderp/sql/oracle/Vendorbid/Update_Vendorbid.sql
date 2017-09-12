CREATE OR REPLACE PROCEDURE Update_Vendorbid(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Vendorbid set moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for ItemPrice
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_ItemPrice set ItemPrice2Vendorbid=pnObjid where ItemPrice2Vendorbid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for BidArtifacts
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_BidArtifacts set BidArtifacts2Vendorbid=pnObjid where BidArtifacts2Vendorbid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for RequestInfo
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_RequestInfo set RequestInfo2Vendorbid=pnObjid where RequestInfo2Vendorbid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for QuizReply
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_QuizReply set QuizReply2Vendorbid=pnObjid where QuizReply2Vendorbid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for Attachment
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Attachment set Attachment2Vendorbid=pnObjid where Attachment2Vendorbid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

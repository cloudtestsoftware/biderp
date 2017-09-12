CREATE OR REPLACE PROCEDURE Update_Bids(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Bids set FirstName=FirstName,
		LastName=LastName,
		Email=Email,
		Phone=Phone,
		Url=Url,
		BidAmount=BidAmount,
		TotalPoint=TotalPoint,
		TotalEarned=TotalEarned,
		TotalPct=TotalPct,
		TotalComplete=TotalComplete,
		moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for ItemCost
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_ItemCost set ItemCost2Bids=pnObjid where ItemCost2Bids=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for QuizReply
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_QuizReply set QuizReply2Bids=pnObjid where QuizReply2Bids=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for Attachment
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Attachment set Attachment2Bids=pnObjid where Attachment2Bids=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

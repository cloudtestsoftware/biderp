CREATE OR REPLACE PROCEDURE Update_Bidrequest(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Bidrequest set moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for General
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_General set General2Bidrequest=pnObjid where General2Bidrequest=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for Invitation
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Invitation set Invitation2Bidrequest=pnObjid where Invitation2Bidrequest=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for Artifacts
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Artifacts set Artifacts2Bidrequest=pnObjid where Artifacts2Bidrequest=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for BidQuiz
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_BidQuiz set BidQuiz2Bidrequest=pnObjid where BidQuiz2Bidrequest=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for PartBid
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_PartBid set PartBid2Bidrequest=pnObjid where PartBid2Bidrequest=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for RequestInfo
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_RequestInfo set RequestInfo2Bidrequest=pnObjid where RequestInfo2Bidrequest=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for Bids
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Bids set Bids2Bidrequest=pnObjid where Bids2Bidrequest=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for LowBid
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_LowBid set LowBid2Bidrequest=pnObjid where LowBid2Bidrequest=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for PurchaseOrder
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_PurchaseOrder set PurchaseOrder2Bidrequest=pnObjid where PurchaseOrder2Bidrequest=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for ResourceOrder
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_ResourceOrder set ResourceOrder2Bidrequest=pnObjid where ResourceOrder2Bidrequest=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

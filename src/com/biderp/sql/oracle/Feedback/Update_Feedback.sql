CREATE OR REPLACE PROCEDURE Update_Feedback(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Feedback set Name=Name,
		Email=Email,
		Contact=Contact,
		WhatYouUse=WhatYouUse,
		LookingFor=LookingFor,
		NoOfUser=NoOfUser,
		YourComment=YourComment,
		YourRating=YourRating,
		Improvement=Improvement,
		CreateDate=CreateDate,
		Status=Status,
		AssignTo=AssignTo,
		moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for FeedNote
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_FeedNote set FeedNote2Feedback=pnObjid where FeedNote2Feedback=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

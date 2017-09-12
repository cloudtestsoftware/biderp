CREATE OR REPLACE PROCEDURE Update_Projectplan(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Projectplan set moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for MileStone
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_MileStone set MileStone2Projectplan=pnObjid where MileStone2Projectplan=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for PPLink
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_PPLink set PPLink2Projectplan=pnObjid where PPLink2Projectplan=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for WhatToDo
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_WhatToDo set WhatToDo2Projectplan=pnObjid where WhatToDo2Projectplan=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

CREATE OR REPLACE PROCEDURE Insert_Projectplan(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_MileStone_cur IS Select
		Name,
		Title,
		ProjectCode,
		MainCode,
		SubCode,
		StartDate,
		EndDate,
		Status,
		Note,
		moduser
		from table_MileStone
	 where objid=pnObjid 
	 and not exists (select *from table_MileStone where MileStone2Projectplan=pnObjid);



--Please Modify the cursor as you need

CURSOR m_PPLink_cur IS Select
		Objid,
		Name,
		Title,
		ProjectCode,
		MainCode,
		StartBefore,
		StartActual,
		FloatCount,
		StartDate,
		EndDate,
		moduser
		from table_PPLink
	 where objid=pnObjid 
	 and not exists (select *from table_PPLink where PPLink2Projectplan=pnObjid);



--Please Modify the cursor as you need

CURSOR m_WhatToDo_cur IS Select
		Name,
		YouShould,
		Status,
		RelatedName,
		WhatYouDid,
		ExpiryDate,
		moduser
		from table_WhatToDo
	 where objid=pnObjid 
	 and not exists (select *from table_WhatToDo where WhatToDo2Projectplan=pnObjid);


--variables
id	NUMBER:=0;
i_MileStone_cur	m_MileStone_cur%rowtype;
i_PPLink_cur	m_PPLink_cur%rowtype;
i_WhatToDo_cur	m_WhatToDo_cur%rowtype;

BEGIN
--opening cursor m_MileStone_cur
	id:=0;
Begin
	OPEN m_MileStone_cur;
	LOOP
	id := id-1;
	FETCH m_MileStone_cur INTO i_MileStone_cur;
	EXIT WHEN m_MileStone_cur%NOTFOUND;

--Insert records in MileStone

	INSERT INTO table_MileStone(
		Name,
		Title,
		ProjectCode,
		MainCode,
		SubCode,
		StartDate,
		EndDate,
		Status,
		Note,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_MileStone_cur.Name,
		i_MileStone_cur.Title,
		i_MileStone_cur.ProjectCode,
		i_MileStone_cur.MainCode,
		i_MileStone_cur.SubCode,
		i_MileStone_cur.StartDate,
		i_MileStone_cur.EndDate,
		i_MileStone_cur.Status,
		i_MileStone_cur.Note,
		id,
		pnObjid,
		i_MileStone_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_MileStone_cur;
 End;
--opening cursor m_PPLink_cur
	id:=0;
Begin
	OPEN m_PPLink_cur;
	LOOP
	id := id-1;
	FETCH m_PPLink_cur INTO i_PPLink_cur;
	EXIT WHEN m_PPLink_cur%NOTFOUND;

--Insert records in PPLink

	INSERT INTO table_PPLink(
		Objid,
		Name,
		Title,
		ProjectCode,
		MainCode,
		StartBefore,
		StartActual,
		FloatCount,
		StartDate,
		EndDate,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_PPLink_cur.Objid,
		i_PPLink_cur.Name,
		i_PPLink_cur.Title,
		i_PPLink_cur.ProjectCode,
		i_PPLink_cur.MainCode,
		i_PPLink_cur.StartBefore,
		i_PPLink_cur.StartActual,
		i_PPLink_cur.FloatCount,
		i_PPLink_cur.StartDate,
		i_PPLink_cur.EndDate,
		id,
		pnObjid,
		i_PPLink_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_PPLink_cur;
 End;
--opening cursor m_WhatToDo_cur
	id:=0;
Begin
	OPEN m_WhatToDo_cur;
	LOOP
	id := id-1;
	FETCH m_WhatToDo_cur INTO i_WhatToDo_cur;
	EXIT WHEN m_WhatToDo_cur%NOTFOUND;

--Insert records in WhatToDo

	INSERT INTO table_WhatToDo(
		Name,
		YouShould,
		Status,
		RelatedName,
		WhatYouDid,
		ExpiryDate,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_WhatToDo_cur.Name,
		i_WhatToDo_cur.YouShould,
		i_WhatToDo_cur.Status,
		i_WhatToDo_cur.RelatedName,
		i_WhatToDo_cur.WhatYouDid,
		i_WhatToDo_cur.ExpiryDate,
		id,
		pnObjid,
		i_WhatToDo_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_WhatToDo_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

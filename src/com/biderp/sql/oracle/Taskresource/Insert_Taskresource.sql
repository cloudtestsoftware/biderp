CREATE OR REPLACE PROCEDURE Insert_Taskresource(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_TimeSheet_cur IS Select
		Name,
		Title,
		ServiceDate,
		UmCode,
		RegularTime,
		OverTime,
		Status,
		ApprovedBy,
		ProgressCode,
		moduser
		from table_TimeSheet
	 where objid=pnObjid 
	 and not exists (select *from table_TimeSheet where TimeSheet2Taskresource=pnObjid);


--variables
id	NUMBER:=0;
i_TimeSheet_cur	m_TimeSheet_cur%rowtype;

BEGIN
--opening cursor m_TimeSheet_cur
	id:=0;
Begin
	OPEN m_TimeSheet_cur;
	LOOP
	id := id-1;
	FETCH m_TimeSheet_cur INTO i_TimeSheet_cur;
	EXIT WHEN m_TimeSheet_cur%NOTFOUND;

--Insert records in TimeSheet

	INSERT INTO table_TimeSheet(
		Name,
		Title,
		ServiceDate,
		UmCode,
		RegularTime,
		OverTime,
		Status,
		ApprovedBy,
		ProgressCode,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_TimeSheet_cur.Name,
		i_TimeSheet_cur.Title,
		i_TimeSheet_cur.ServiceDate,
		i_TimeSheet_cur.UmCode,
		i_TimeSheet_cur.RegularTime,
		i_TimeSheet_cur.OverTime,
		i_TimeSheet_cur.Status,
		i_TimeSheet_cur.ApprovedBy,
		i_TimeSheet_cur.ProgressCode,
		id,
		pnObjid,
		i_TimeSheet_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_TimeSheet_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

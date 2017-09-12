CREATE OR REPLACE PROCEDURE Insert_Performance(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_Participants_cur IS Select
		Name,
		LastName,
		EmpNo,
		ReportTo,
		JoinDate,
		Instruction,
		Summary,
		moduser
		from table_Participants
	 where objid=pnObjid 
	 and not exists (select *from table_Participants where Participants2Performance=pnObjid);


--variables
id	NUMBER:=0;
i_Participants_cur	m_Participants_cur%rowtype;

BEGIN
--opening cursor m_Participants_cur
	id:=0;
Begin
	OPEN m_Participants_cur;
	LOOP
	id := id-1;
	FETCH m_Participants_cur INTO i_Participants_cur;
	EXIT WHEN m_Participants_cur%NOTFOUND;

--Insert records in Participants

	INSERT INTO table_Participants(
		Name,
		LastName,
		EmpNo,
		ReportTo,
		JoinDate,
		Instruction,
		Summary,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_Participants_cur.Name,
		i_Participants_cur.LastName,
		i_Participants_cur.EmpNo,
		i_Participants_cur.ReportTo,
		i_Participants_cur.JoinDate,
		i_Participants_cur.Instruction,
		i_Participants_cur.Summary,
		id,
		pnObjid,
		i_Participants_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Participants_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

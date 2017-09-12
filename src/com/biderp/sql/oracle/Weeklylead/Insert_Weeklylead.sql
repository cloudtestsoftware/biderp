CREATE OR REPLACE PROCEDURE Insert_Weeklylead(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_WeekLeadContact_cur IS Select
		Name,
		Street,
		Phone,
		Fax,
		Email,
		Type,
		moduser
		from table_WeekLeadContact
	 where objid=pnObjid 
	 and not exists (select *from table_WeekLeadContact where WeekLeadContact2Weeklylead=pnObjid);



--Please Modify the cursor as you need

CURSOR m_WeekLeadJobs_cur IS Select
		Name,
		Description,
		UnitPrice,
		UnitCount,
		UmCode,
		Total,
		moduser
		from table_WeekLeadJobs
	 where objid=pnObjid 
	 and not exists (select *from table_WeekLeadJobs where WeekLeadJobs2Weeklylead=pnObjid);


--variables
id	NUMBER:=0;
i_WeekLeadContact_cur	m_WeekLeadContact_cur%rowtype;
i_WeekLeadJobs_cur	m_WeekLeadJobs_cur%rowtype;

BEGIN
--opening cursor m_WeekLeadContact_cur
	id:=0;
Begin
	OPEN m_WeekLeadContact_cur;
	LOOP
	id := id-1;
	FETCH m_WeekLeadContact_cur INTO i_WeekLeadContact_cur;
	EXIT WHEN m_WeekLeadContact_cur%NOTFOUND;

--Insert records in WeekLeadContact

	INSERT INTO table_WeekLeadContact(
		Name,
		Street,
		Phone,
		Fax,
		Email,
		Type,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_WeekLeadContact_cur.Name,
		i_WeekLeadContact_cur.Street,
		i_WeekLeadContact_cur.Phone,
		i_WeekLeadContact_cur.Fax,
		i_WeekLeadContact_cur.Email,
		i_WeekLeadContact_cur.Type,
		id,
		pnObjid,
		i_WeekLeadContact_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_WeekLeadContact_cur;
 End;
--opening cursor m_WeekLeadJobs_cur
	id:=0;
Begin
	OPEN m_WeekLeadJobs_cur;
	LOOP
	id := id-1;
	FETCH m_WeekLeadJobs_cur INTO i_WeekLeadJobs_cur;
	EXIT WHEN m_WeekLeadJobs_cur%NOTFOUND;

--Insert records in WeekLeadJobs

	INSERT INTO table_WeekLeadJobs(
		Name,
		Description,
		UnitPrice,
		UnitCount,
		UmCode,
		Total,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_WeekLeadJobs_cur.Name,
		i_WeekLeadJobs_cur.Description,
		i_WeekLeadJobs_cur.UnitPrice,
		i_WeekLeadJobs_cur.UnitCount,
		i_WeekLeadJobs_cur.UmCode,
		i_WeekLeadJobs_cur.Total,
		id,
		pnObjid,
		i_WeekLeadJobs_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_WeekLeadJobs_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

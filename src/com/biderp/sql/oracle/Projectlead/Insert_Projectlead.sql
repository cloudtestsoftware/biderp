CREATE OR REPLACE PROCEDURE Insert_Projectlead(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_LeadContact_cur IS Select
		Name,
		Street,
		Phone,
		State,
		ZipCode,
		moduser
		from table_LeadContact
	 where objid=pnObjid 
	 and not exists (select *from table_LeadContact where LeadContact2Projectlead=pnObjid);



--Please Modify the cursor as you need

CURSOR m_LeadJobs_cur IS Select
		Name,
		Description,
		UnitPrice,
		UnitCount,
		UmCode,
		Total,
		moduser
		from table_LeadJobs
	 where objid=pnObjid 
	 and not exists (select *from table_LeadJobs where LeadJobs2Projectlead=pnObjid);


--variables
id	NUMBER:=0;
i_LeadContact_cur	m_LeadContact_cur%rowtype;
i_LeadJobs_cur	m_LeadJobs_cur%rowtype;

BEGIN
--opening cursor m_LeadContact_cur
	id:=0;
Begin
	OPEN m_LeadContact_cur;
	LOOP
	id := id-1;
	FETCH m_LeadContact_cur INTO i_LeadContact_cur;
	EXIT WHEN m_LeadContact_cur%NOTFOUND;

--Insert records in LeadContact

	INSERT INTO table_LeadContact(
		Name,
		Street,
		Phone,
		State,
		ZipCode,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_LeadContact_cur.Name,
		i_LeadContact_cur.Street,
		i_LeadContact_cur.Phone,
		i_LeadContact_cur.State,
		i_LeadContact_cur.ZipCode,
		id,
		pnObjid,
		i_LeadContact_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_LeadContact_cur;
 End;
--opening cursor m_LeadJobs_cur
	id:=0;
Begin
	OPEN m_LeadJobs_cur;
	LOOP
	id := id-1;
	FETCH m_LeadJobs_cur INTO i_LeadJobs_cur;
	EXIT WHEN m_LeadJobs_cur%NOTFOUND;

--Insert records in LeadJobs

	INSERT INTO table_LeadJobs(
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
		i_LeadJobs_cur.Name,
		i_LeadJobs_cur.Description,
		i_LeadJobs_cur.UnitPrice,
		i_LeadJobs_cur.UnitCount,
		i_LeadJobs_cur.UmCode,
		i_LeadJobs_cur.Total,
		id,
		pnObjid,
		i_LeadJobs_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_LeadJobs_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

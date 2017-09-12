CREATE OR REPLACE PROCEDURE Insert_Project(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_JobMaster_cur IS Select
		Name,
		ProjectCode,
		MainCode,
		Description,
		StartDate,
		EndDate,
		moduser
		from table_JobMaster
	 where objid=pnObjid 
	 and not exists (select *from table_JobMaster where JobMaster2Project=pnObjid);



--Please Modify the cursor as you need

CURSOR m_Contact_cur IS Select
		Name,
		LastName,
		Attention,
		Street,
		City,
		State,
		ZipCode,
		CountryCode,
		Phone,
		Phone2,
		Fax,
		Email,
		ContactCode,
		moduser
		from table_Contact
	 where objid=pnObjid 
	 and not exists (select *from table_Contact where Contact2Project=pnObjid);



--Please Modify the cursor as you need

CURSOR m_Estimation_cur IS Select
		Name,
		Title,
		ProjectCode,
		MainCode,
		Status,
		Note,
		moduser
		from table_Estimation
	 where objid=pnObjid 
	 and not exists (select *from table_Estimation where Estimation2Project=pnObjid);



--Please Modify the cursor as you need

CURSOR m_ProjectPlan_cur IS Select
		Name,
		Title,
		ProjectCode,
		MainCode,
		StartDate,
		EndDate,
		Status,
		moduser
		from table_ProjectPlan
	 where objid=pnObjid 
	 and not exists (select *from table_ProjectPlan where ProjectPlan2Project=pnObjid);



--Please Modify the cursor as you need

CURSOR m_Budget_cur IS Select
		Name,
		Title,
		ProjectCode,
		Status,
		moduser
		from table_Budget
	 where objid=pnObjid 
	 and not exists (select *from table_Budget where Budget2Project=pnObjid);



--Please Modify the cursor as you need

CURSOR m_Tax_cur IS Select
		Name,
		TaxCode,
		Pct,
		moduser
		from table_Tax
	 where objid=pnObjid 
	 and not exists (select *from table_Tax where Tax2Project=pnObjid);



--Please Modify the cursor as you need

CURSOR m_PoMaster_cur IS Select
		Name,
		PoCode,
		ItemCode,
		Status,
		Note,
		moduser
		from table_PoMaster
	 where objid=pnObjid 
	 and not exists (select *from table_PoMaster where PoMaster2Project=pnObjid);


--variables
id	NUMBER:=0;
i_JobMaster_cur	m_JobMaster_cur%rowtype;
i_Contact_cur	m_Contact_cur%rowtype;
i_Estimation_cur	m_Estimation_cur%rowtype;
i_ProjectPlan_cur	m_ProjectPlan_cur%rowtype;
i_Budget_cur	m_Budget_cur%rowtype;
i_Tax_cur	m_Tax_cur%rowtype;
i_PoMaster_cur	m_PoMaster_cur%rowtype;

BEGIN
--opening cursor m_JobMaster_cur
	id:=0;
Begin
	OPEN m_JobMaster_cur;
	LOOP
	id := id-1;
	FETCH m_JobMaster_cur INTO i_JobMaster_cur;
	EXIT WHEN m_JobMaster_cur%NOTFOUND;

--Insert records in JobMaster

	INSERT INTO table_JobMaster(
		Name,
		ProjectCode,
		MainCode,
		Description,
		StartDate,
		EndDate,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_JobMaster_cur.Name,
		i_JobMaster_cur.ProjectCode,
		i_JobMaster_cur.MainCode,
		i_JobMaster_cur.Description,
		i_JobMaster_cur.StartDate,
		i_JobMaster_cur.EndDate,
		id,
		pnObjid,
		i_JobMaster_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_JobMaster_cur;
 End;
--opening cursor m_Contact_cur
	id:=0;
Begin
	OPEN m_Contact_cur;
	LOOP
	id := id-1;
	FETCH m_Contact_cur INTO i_Contact_cur;
	EXIT WHEN m_Contact_cur%NOTFOUND;

--Insert records in Contact

	INSERT INTO table_Contact(
		Name,
		LastName,
		Attention,
		Street,
		City,
		State,
		ZipCode,
		CountryCode,
		Phone,
		Phone2,
		Fax,
		Email,
		ContactCode,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_Contact_cur.Name,
		i_Contact_cur.LastName,
		i_Contact_cur.Attention,
		i_Contact_cur.Street,
		i_Contact_cur.City,
		i_Contact_cur.State,
		i_Contact_cur.ZipCode,
		i_Contact_cur.CountryCode,
		i_Contact_cur.Phone,
		i_Contact_cur.Phone2,
		i_Contact_cur.Fax,
		i_Contact_cur.Email,
		i_Contact_cur.ContactCode,
		id,
		pnObjid,
		i_Contact_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Contact_cur;
 End;
--opening cursor m_Estimation_cur
	id:=0;
Begin
	OPEN m_Estimation_cur;
	LOOP
	id := id-1;
	FETCH m_Estimation_cur INTO i_Estimation_cur;
	EXIT WHEN m_Estimation_cur%NOTFOUND;

--Insert records in Estimation

	INSERT INTO table_Estimation(
		Name,
		Title,
		ProjectCode,
		MainCode,
		Status,
		Note,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_Estimation_cur.Name,
		i_Estimation_cur.Title,
		i_Estimation_cur.ProjectCode,
		i_Estimation_cur.MainCode,
		i_Estimation_cur.Status,
		i_Estimation_cur.Note,
		id,
		pnObjid,
		i_Estimation_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Estimation_cur;
 End;
--opening cursor m_ProjectPlan_cur
	id:=0;
Begin
	OPEN m_ProjectPlan_cur;
	LOOP
	id := id-1;
	FETCH m_ProjectPlan_cur INTO i_ProjectPlan_cur;
	EXIT WHEN m_ProjectPlan_cur%NOTFOUND;

--Insert records in ProjectPlan

	INSERT INTO table_ProjectPlan(
		Name,
		Title,
		ProjectCode,
		MainCode,
		StartDate,
		EndDate,
		Status,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_ProjectPlan_cur.Name,
		i_ProjectPlan_cur.Title,
		i_ProjectPlan_cur.ProjectCode,
		i_ProjectPlan_cur.MainCode,
		i_ProjectPlan_cur.StartDate,
		i_ProjectPlan_cur.EndDate,
		i_ProjectPlan_cur.Status,
		id,
		pnObjid,
		i_ProjectPlan_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_ProjectPlan_cur;
 End;
--opening cursor m_Budget_cur
	id:=0;
Begin
	OPEN m_Budget_cur;
	LOOP
	id := id-1;
	FETCH m_Budget_cur INTO i_Budget_cur;
	EXIT WHEN m_Budget_cur%NOTFOUND;

--Insert records in Budget

	INSERT INTO table_Budget(
		Name,
		Title,
		ProjectCode,
		Status,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_Budget_cur.Name,
		i_Budget_cur.Title,
		i_Budget_cur.ProjectCode,
		i_Budget_cur.Status,
		id,
		pnObjid,
		i_Budget_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Budget_cur;
 End;
--opening cursor m_Tax_cur
	id:=0;
Begin
	OPEN m_Tax_cur;
	LOOP
	id := id-1;
	FETCH m_Tax_cur INTO i_Tax_cur;
	EXIT WHEN m_Tax_cur%NOTFOUND;

--Insert records in Tax

	INSERT INTO table_Tax(
		Name,
		TaxCode,
		Pct,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_Tax_cur.Name,
		i_Tax_cur.TaxCode,
		i_Tax_cur.Pct,
		id,
		pnObjid,
		i_Tax_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Tax_cur;
 End;
--opening cursor m_PoMaster_cur
	id:=0;
Begin
	OPEN m_PoMaster_cur;
	LOOP
	id := id-1;
	FETCH m_PoMaster_cur INTO i_PoMaster_cur;
	EXIT WHEN m_PoMaster_cur%NOTFOUND;

--Insert records in PoMaster

	INSERT INTO table_PoMaster(
		Name,
		PoCode,
		ItemCode,
		Status,
		Note,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_PoMaster_cur.Name,
		i_PoMaster_cur.PoCode,
		i_PoMaster_cur.ItemCode,
		i_PoMaster_cur.Status,
		i_PoMaster_cur.Note,
		id,
		pnObjid,
		i_PoMaster_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_PoMaster_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

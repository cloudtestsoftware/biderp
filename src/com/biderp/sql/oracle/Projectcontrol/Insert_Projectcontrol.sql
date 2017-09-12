CREATE OR REPLACE PROCEDURE Insert_Projectcontrol(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_CurrentPlan_cur IS Select
		Name,
		ProjectName,
		Title,
		ProjectCode,
		MonthCode,
		YearCode,
		Quantity,
		QntFinished,
		Status,
		moduser
		from table_CurrentPlan
	 where objid=pnObjid 
	 and not exists (select *from table_CurrentPlan where CurrentPlan2Projectcontrol=pnObjid);



--Please Modify the cursor as you need

CURSOR m_PartPlan_cur IS Select
		Name,
		ProjectName,
		ProjectCode,
		QntRequest,
		QntUsed,
		QntPurchased,
		QntBalance,
		Status,
		StartDate,
		PurchaseDate,
		Note,
		moduser
		from table_PartPlan
	 where objid=pnObjid 
	 and not exists (select *from table_PartPlan where PartPlan2Projectcontrol=pnObjid);



--Please Modify the cursor as you need

CURSOR m_ResourcePlan_cur IS Select
		Name,
		ProjectName,
		ProjectCode,
		QntRequest,
		QntUsed,
		QntBalance,
		Status,
		StartDate,
		EndDate,
		Note,
		moduser
		from table_ResourcePlan
	 where objid=pnObjid 
	 and not exists (select *from table_ResourcePlan where ResourcePlan2Projectcontrol=pnObjid);


--variables
id	NUMBER:=0;
i_CurrentPlan_cur	m_CurrentPlan_cur%rowtype;
i_PartPlan_cur	m_PartPlan_cur%rowtype;
i_ResourcePlan_cur	m_ResourcePlan_cur%rowtype;

BEGIN
--opening cursor m_CurrentPlan_cur
	id:=0;
Begin
	OPEN m_CurrentPlan_cur;
	LOOP
	id := id-1;
	FETCH m_CurrentPlan_cur INTO i_CurrentPlan_cur;
	EXIT WHEN m_CurrentPlan_cur%NOTFOUND;

--Insert records in CurrentPlan

	INSERT INTO table_CurrentPlan(
		Name,
		ProjectName,
		Title,
		ProjectCode,
		MonthCode,
		YearCode,
		Quantity,
		QntFinished,
		Status,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_CurrentPlan_cur.Name,
		i_CurrentPlan_cur.ProjectName,
		i_CurrentPlan_cur.Title,
		i_CurrentPlan_cur.ProjectCode,
		i_CurrentPlan_cur.MonthCode,
		i_CurrentPlan_cur.YearCode,
		i_CurrentPlan_cur.Quantity,
		i_CurrentPlan_cur.QntFinished,
		i_CurrentPlan_cur.Status,
		id,
		pnObjid,
		i_CurrentPlan_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_CurrentPlan_cur;
 End;
--opening cursor m_PartPlan_cur
	id:=0;
Begin
	OPEN m_PartPlan_cur;
	LOOP
	id := id-1;
	FETCH m_PartPlan_cur INTO i_PartPlan_cur;
	EXIT WHEN m_PartPlan_cur%NOTFOUND;

--Insert records in PartPlan

	INSERT INTO table_PartPlan(
		Name,
		ProjectName,
		ProjectCode,
		QntRequest,
		QntUsed,
		QntPurchased,
		QntBalance,
		Status,
		StartDate,
		PurchaseDate,
		Note,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_PartPlan_cur.Name,
		i_PartPlan_cur.ProjectName,
		i_PartPlan_cur.ProjectCode,
		i_PartPlan_cur.QntRequest,
		i_PartPlan_cur.QntUsed,
		i_PartPlan_cur.QntPurchased,
		i_PartPlan_cur.QntBalance,
		i_PartPlan_cur.Status,
		i_PartPlan_cur.StartDate,
		i_PartPlan_cur.PurchaseDate,
		i_PartPlan_cur.Note,
		id,
		pnObjid,
		i_PartPlan_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_PartPlan_cur;
 End;
--opening cursor m_ResourcePlan_cur
	id:=0;
Begin
	OPEN m_ResourcePlan_cur;
	LOOP
	id := id-1;
	FETCH m_ResourcePlan_cur INTO i_ResourcePlan_cur;
	EXIT WHEN m_ResourcePlan_cur%NOTFOUND;

--Insert records in ResourcePlan

	INSERT INTO table_ResourcePlan(
		Name,
		ProjectName,
		ProjectCode,
		QntRequest,
		QntUsed,
		QntBalance,
		Status,
		StartDate,
		EndDate,
		Note,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_ResourcePlan_cur.Name,
		i_ResourcePlan_cur.ProjectName,
		i_ResourcePlan_cur.ProjectCode,
		i_ResourcePlan_cur.QntRequest,
		i_ResourcePlan_cur.QntUsed,
		i_ResourcePlan_cur.QntBalance,
		i_ResourcePlan_cur.Status,
		i_ResourcePlan_cur.StartDate,
		i_ResourcePlan_cur.EndDate,
		i_ResourcePlan_cur.Note,
		id,
		pnObjid,
		i_ResourcePlan_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_ResourcePlan_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

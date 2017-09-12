CREATE OR REPLACE PROCEDURE Insert_Site(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_Phase_cur IS Select
		Name,
		Description,
		StartDate,
		CompleteDate,
		PhaseIncharge,
		Phone,
		Phone2,
		Fax,
		Email,
		OtherContact,
		OtherInfo,
		Status,
		moduser
		from table_Phase
	 where objid=pnObjid 
	 and not exists (select *from table_Phase where Phase2Site=pnObjid);



--Please Modify the cursor as you need

CURSOR m_Warehouse_cur IS Select
		Name,
		Location,
		moduser
		from table_Warehouse
	 where objid=pnObjid 
	 and not exists (select *from table_Warehouse where Warehouse2Site=pnObjid);



--Please Modify the cursor as you need

CURSOR m_Machinary_cur IS Select
		Name,
		SiteName,
		UmCode,
		EstUnit,
		QntRequest,
		StartDate,
		EndDate,
		UseFactor,
		moduser
		from table_Machinary
	 where objid=pnObjid 
	 and not exists (select *from table_Machinary where Machinary2Site=pnObjid);



--Please Modify the cursor as you need

CURSOR m_AssetPlan_cur IS Select
		Name,
		TagNo,
		PrivLocation,
		CurLocation,
		Note,
		Status,
		AvailDate,
		StartDate,
		EndDate,
		moduser
		from table_AssetPlan
	 where objid=pnObjid 
	 and not exists (select *from table_AssetPlan where AssetPlan2Site=pnObjid);



--Please Modify the cursor as you need

CURSOR m_Asset_cur IS Select
		Name,
		Location,
		Description,
		Type,
		PurchaseCost,
		AssetValue,
		PurchaseDate,
		AssetTag,
		Status,
		AccountGroup,
		OwnerCode,
		moduser
		from table_Asset
	 where objid=pnObjid 
	 and not exists (select *from table_Asset where Asset2Site=pnObjid);


--variables
id	NUMBER:=0;
i_Phase_cur	m_Phase_cur%rowtype;
i_Warehouse_cur	m_Warehouse_cur%rowtype;
i_Machinary_cur	m_Machinary_cur%rowtype;
i_AssetPlan_cur	m_AssetPlan_cur%rowtype;
i_Asset_cur	m_Asset_cur%rowtype;

BEGIN
--opening cursor m_Phase_cur
	id:=0;
Begin
	OPEN m_Phase_cur;
	LOOP
	id := id-1;
	FETCH m_Phase_cur INTO i_Phase_cur;
	EXIT WHEN m_Phase_cur%NOTFOUND;

--Insert records in Phase

	INSERT INTO table_Phase(
		Name,
		Description,
		StartDate,
		CompleteDate,
		PhaseIncharge,
		Phone,
		Phone2,
		Fax,
		Email,
		OtherContact,
		OtherInfo,
		Status,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_Phase_cur.Name,
		i_Phase_cur.Description,
		i_Phase_cur.StartDate,
		i_Phase_cur.CompleteDate,
		i_Phase_cur.PhaseIncharge,
		i_Phase_cur.Phone,
		i_Phase_cur.Phone2,
		i_Phase_cur.Fax,
		i_Phase_cur.Email,
		i_Phase_cur.OtherContact,
		i_Phase_cur.OtherInfo,
		i_Phase_cur.Status,
		id,
		pnObjid,
		i_Phase_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Phase_cur;
 End;
--opening cursor m_Warehouse_cur
	id:=0;
Begin
	OPEN m_Warehouse_cur;
	LOOP
	id := id-1;
	FETCH m_Warehouse_cur INTO i_Warehouse_cur;
	EXIT WHEN m_Warehouse_cur%NOTFOUND;

--Insert records in Warehouse

	INSERT INTO table_Warehouse(
		Name,
		Location,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_Warehouse_cur.Name,
		i_Warehouse_cur.Location,
		id,
		pnObjid,
		i_Warehouse_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Warehouse_cur;
 End;
--opening cursor m_Machinary_cur
	id:=0;
Begin
	OPEN m_Machinary_cur;
	LOOP
	id := id-1;
	FETCH m_Machinary_cur INTO i_Machinary_cur;
	EXIT WHEN m_Machinary_cur%NOTFOUND;

--Insert records in Machinary

	INSERT INTO table_Machinary(
		Name,
		SiteName,
		UmCode,
		EstUnit,
		QntRequest,
		StartDate,
		EndDate,
		UseFactor,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_Machinary_cur.Name,
		i_Machinary_cur.SiteName,
		i_Machinary_cur.UmCode,
		i_Machinary_cur.EstUnit,
		i_Machinary_cur.QntRequest,
		i_Machinary_cur.StartDate,
		i_Machinary_cur.EndDate,
		i_Machinary_cur.UseFactor,
		id,
		pnObjid,
		i_Machinary_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Machinary_cur;
 End;
--opening cursor m_AssetPlan_cur
	id:=0;
Begin
	OPEN m_AssetPlan_cur;
	LOOP
	id := id-1;
	FETCH m_AssetPlan_cur INTO i_AssetPlan_cur;
	EXIT WHEN m_AssetPlan_cur%NOTFOUND;

--Insert records in AssetPlan

	INSERT INTO table_AssetPlan(
		Name,
		TagNo,
		PrivLocation,
		CurLocation,
		Note,
		Status,
		AvailDate,
		StartDate,
		EndDate,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_AssetPlan_cur.Name,
		i_AssetPlan_cur.TagNo,
		i_AssetPlan_cur.PrivLocation,
		i_AssetPlan_cur.CurLocation,
		i_AssetPlan_cur.Note,
		i_AssetPlan_cur.Status,
		i_AssetPlan_cur.AvailDate,
		i_AssetPlan_cur.StartDate,
		i_AssetPlan_cur.EndDate,
		id,
		pnObjid,
		i_AssetPlan_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_AssetPlan_cur;
 End;
--opening cursor m_Asset_cur
	id:=0;
Begin
	OPEN m_Asset_cur;
	LOOP
	id := id-1;
	FETCH m_Asset_cur INTO i_Asset_cur;
	EXIT WHEN m_Asset_cur%NOTFOUND;

--Insert records in Asset

	INSERT INTO table_Asset(
		Name,
		Location,
		Description,
		Type,
		PurchaseCost,
		AssetValue,
		PurchaseDate,
		AssetTag,
		Status,
		AccountGroup,
		OwnerCode,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_Asset_cur.Name,
		i_Asset_cur.Location,
		i_Asset_cur.Description,
		i_Asset_cur.Type,
		i_Asset_cur.PurchaseCost,
		i_Asset_cur.AssetValue,
		i_Asset_cur.PurchaseDate,
		i_Asset_cur.AssetTag,
		i_Asset_cur.Status,
		i_Asset_cur.AccountGroup,
		i_Asset_cur.OwnerCode,
		id,
		pnObjid,
		i_Asset_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Asset_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

CREATE OR REPLACE PROCEDURE Insert_Equipment(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_FindAsset_cur IS Select
		Name,
		AvailDate,
		TagNo,
		PrivLocation,
		moduser
		from table_FindAsset
	 where objid=pnObjid 
	 and not exists (select *from table_FindAsset where FindAsset2Equipment=pnObjid);



--Please Modify the cursor as you need

CURSOR m_Maintenance_cur IS Select
		Name,
		Description,
		ServiceType,
		Category,
		DeptCode,
		ServiceDate,
		Status,
		moduser
		from table_Maintenance
	 where objid=pnObjid 
	 and not exists (select *from table_Maintenance where Maintenance2Equipment=pnObjid);



--Please Modify the cursor as you need

CURSOR m_YearlyCost_cur IS Select
		Name,
		Description,
		LaborCost,
		PartCost,
		TotoalCost,
		Year,
		moduser
		from table_YearlyCost
	 where objid=pnObjid 
	 and not exists (select *from table_YearlyCost where YearlyCost2Equipment=pnObjid);



--Please Modify the cursor as you need

CURSOR m_Depricated_cur IS Select
		Name,
		YearCode,
		Amount,
		Note,
		moduser
		from table_Depricated
	 where objid=pnObjid 
	 and not exists (select *from table_Depricated where Depricated2Equipment=pnObjid);



--Please Modify the cursor as you need

CURSOR m_AccountDebit_cur IS Select
		Name,
		Amount,
		AccountGroup,
		Voucher,
		PostDate,
		Note,
		moduser
		from table_AccountDebit
	 where objid=pnObjid 
	 and not exists (select *from table_AccountDebit where AccountDebit2Equipment=pnObjid);


--variables
id	NUMBER:=0;
i_FindAsset_cur	m_FindAsset_cur%rowtype;
i_Maintenance_cur	m_Maintenance_cur%rowtype;
i_YearlyCost_cur	m_YearlyCost_cur%rowtype;
i_Depricated_cur	m_Depricated_cur%rowtype;
i_AccountDebit_cur	m_AccountDebit_cur%rowtype;

BEGIN
--opening cursor m_FindAsset_cur
	id:=0;
Begin
	OPEN m_FindAsset_cur;
	LOOP
	id := id-1;
	FETCH m_FindAsset_cur INTO i_FindAsset_cur;
	EXIT WHEN m_FindAsset_cur%NOTFOUND;

--Insert records in FindAsset

	INSERT INTO table_FindAsset(
		Name,
		AvailDate,
		TagNo,
		PrivLocation,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_FindAsset_cur.Name,
		i_FindAsset_cur.AvailDate,
		i_FindAsset_cur.TagNo,
		i_FindAsset_cur.PrivLocation,
		id,
		pnObjid,
		i_FindAsset_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_FindAsset_cur;
 End;
--opening cursor m_Maintenance_cur
	id:=0;
Begin
	OPEN m_Maintenance_cur;
	LOOP
	id := id-1;
	FETCH m_Maintenance_cur INTO i_Maintenance_cur;
	EXIT WHEN m_Maintenance_cur%NOTFOUND;

--Insert records in Maintenance

	INSERT INTO table_Maintenance(
		Name,
		Description,
		ServiceType,
		Category,
		DeptCode,
		ServiceDate,
		Status,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_Maintenance_cur.Name,
		i_Maintenance_cur.Description,
		i_Maintenance_cur.ServiceType,
		i_Maintenance_cur.Category,
		i_Maintenance_cur.DeptCode,
		i_Maintenance_cur.ServiceDate,
		i_Maintenance_cur.Status,
		id,
		pnObjid,
		i_Maintenance_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Maintenance_cur;
 End;
--opening cursor m_YearlyCost_cur
	id:=0;
Begin
	OPEN m_YearlyCost_cur;
	LOOP
	id := id-1;
	FETCH m_YearlyCost_cur INTO i_YearlyCost_cur;
	EXIT WHEN m_YearlyCost_cur%NOTFOUND;

--Insert records in YearlyCost

	INSERT INTO table_YearlyCost(
		Name,
		Description,
		LaborCost,
		PartCost,
		TotoalCost,
		Year,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_YearlyCost_cur.Name,
		i_YearlyCost_cur.Description,
		i_YearlyCost_cur.LaborCost,
		i_YearlyCost_cur.PartCost,
		i_YearlyCost_cur.TotoalCost,
		i_YearlyCost_cur.Year,
		id,
		pnObjid,
		i_YearlyCost_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_YearlyCost_cur;
 End;
--opening cursor m_Depricated_cur
	id:=0;
Begin
	OPEN m_Depricated_cur;
	LOOP
	id := id-1;
	FETCH m_Depricated_cur INTO i_Depricated_cur;
	EXIT WHEN m_Depricated_cur%NOTFOUND;

--Insert records in Depricated

	INSERT INTO table_Depricated(
		Name,
		YearCode,
		Amount,
		Note,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_Depricated_cur.Name,
		i_Depricated_cur.YearCode,
		i_Depricated_cur.Amount,
		i_Depricated_cur.Note,
		id,
		pnObjid,
		i_Depricated_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Depricated_cur;
 End;
--opening cursor m_AccountDebit_cur
	id:=0;
Begin
	OPEN m_AccountDebit_cur;
	LOOP
	id := id-1;
	FETCH m_AccountDebit_cur INTO i_AccountDebit_cur;
	EXIT WHEN m_AccountDebit_cur%NOTFOUND;

--Insert records in AccountDebit

	INSERT INTO table_AccountDebit(
		Name,
		Amount,
		AccountGroup,
		Voucher,
		PostDate,
		Note,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_AccountDebit_cur.Name,
		i_AccountDebit_cur.Amount,
		i_AccountDebit_cur.AccountGroup,
		i_AccountDebit_cur.Voucher,
		i_AccountDebit_cur.PostDate,
		i_AccountDebit_cur.Note,
		id,
		pnObjid,
		i_AccountDebit_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_AccountDebit_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

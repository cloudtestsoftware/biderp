CREATE OR REPLACE PROCEDURE Insert_Asset(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



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
	 and not exists (select *from table_Maintenance where Maintenance2Asset=pnObjid);



--Please Modify the cursor as you need

CURSOR m_ServiceCost_cur IS Select
		Name,
		Location,
		Description,
		LaborCost,
		PartCost,
		TotoalCost,
		Year,
		moduser
		from table_ServiceCost
	 where objid=pnObjid 
	 and not exists (select *from table_ServiceCost where ServiceCost2Asset=pnObjid);



--Please Modify the cursor as you need

CURSOR m_Depricated_cur IS Select
		Name,
		YearCode,
		Amount,
		Note,
		moduser
		from table_Depricated
	 where objid=pnObjid 
	 and not exists (select *from table_Depricated where Depricated2Asset=pnObjid);



--Please Modify the cursor as you need

CURSOR m_Address_cur IS Select
		Name,
		Street,
		City,
		State,
		ZipCode,
		CountryCode,
		Phone,
		Phone2,
		Fax,
		Email,
		moduser
		from table_Address
	 where objid=pnObjid 
	 and not exists (select *from table_Address where Address2Asset=pnObjid);



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
	 and not exists (select *from table_AccountDebit where AccountDebit2Asset=pnObjid);


--variables
id	NUMBER:=0;
i_Maintenance_cur	m_Maintenance_cur%rowtype;
i_ServiceCost_cur	m_ServiceCost_cur%rowtype;
i_Depricated_cur	m_Depricated_cur%rowtype;
i_Address_cur	m_Address_cur%rowtype;
i_AccountDebit_cur	m_AccountDebit_cur%rowtype;

BEGIN
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
--opening cursor m_ServiceCost_cur
	id:=0;
Begin
	OPEN m_ServiceCost_cur;
	LOOP
	id := id-1;
	FETCH m_ServiceCost_cur INTO i_ServiceCost_cur;
	EXIT WHEN m_ServiceCost_cur%NOTFOUND;

--Insert records in ServiceCost

	INSERT INTO table_ServiceCost(
		Name,
		Location,
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
		i_ServiceCost_cur.Name,
		i_ServiceCost_cur.Location,
		i_ServiceCost_cur.Description,
		i_ServiceCost_cur.LaborCost,
		i_ServiceCost_cur.PartCost,
		i_ServiceCost_cur.TotoalCost,
		i_ServiceCost_cur.Year,
		id,
		pnObjid,
		i_ServiceCost_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_ServiceCost_cur;
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
--opening cursor m_Address_cur
	id:=0;
Begin
	OPEN m_Address_cur;
	LOOP
	id := id-1;
	FETCH m_Address_cur INTO i_Address_cur;
	EXIT WHEN m_Address_cur%NOTFOUND;

--Insert records in Address

	INSERT INTO table_Address(
		Name,
		Street,
		City,
		State,
		ZipCode,
		CountryCode,
		Phone,
		Phone2,
		Fax,
		Email,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_Address_cur.Name,
		i_Address_cur.Street,
		i_Address_cur.City,
		i_Address_cur.State,
		i_Address_cur.ZipCode,
		i_Address_cur.CountryCode,
		i_Address_cur.Phone,
		i_Address_cur.Phone2,
		i_Address_cur.Fax,
		i_Address_cur.Email,
		id,
		pnObjid,
		i_Address_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Address_cur;
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

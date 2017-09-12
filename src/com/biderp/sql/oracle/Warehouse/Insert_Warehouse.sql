CREATE OR REPLACE PROCEDURE Insert_Warehouse(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



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
	 and not exists (select *from table_Address where Address2Warehouse=pnObjid);



--Please Modify the cursor as you need

CURSOR m_WarehouseLine_cur IS Select
		Name,
		Location,
		RowNumber,
		TrayNumber,
		moduser
		from table_WarehouseLine
	 where objid=pnObjid 
	 and not exists (select *from table_WarehouseLine where WarehouseLine2Warehouse=pnObjid);


--variables
id	NUMBER:=0;
i_Address_cur	m_Address_cur%rowtype;
i_WarehouseLine_cur	m_WarehouseLine_cur%rowtype;

BEGIN
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
--opening cursor m_WarehouseLine_cur
	id:=0;
Begin
	OPEN m_WarehouseLine_cur;
	LOOP
	id := id-1;
	FETCH m_WarehouseLine_cur INTO i_WarehouseLine_cur;
	EXIT WHEN m_WarehouseLine_cur%NOTFOUND;

--Insert records in WarehouseLine

	INSERT INTO table_WarehouseLine(
		Name,
		Location,
		RowNumber,
		TrayNumber,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_WarehouseLine_cur.Name,
		i_WarehouseLine_cur.Location,
		i_WarehouseLine_cur.RowNumber,
		i_WarehouseLine_cur.TrayNumber,
		id,
		pnObjid,
		i_WarehouseLine_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_WarehouseLine_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

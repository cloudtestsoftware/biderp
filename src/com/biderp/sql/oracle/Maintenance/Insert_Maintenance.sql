CREATE OR REPLACE PROCEDURE Insert_Maintenance(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_ServiceParts_cur IS Select
		Name,
		UnitCount,
		Note,
		moduser
		from table_ServiceParts
	 where objid=pnObjid 
	 and not exists (select *from table_ServiceParts where ServiceParts2Maintenance=pnObjid);



--Please Modify the cursor as you need

CURSOR m_ServiceResource_cur IS Select
		Name,
		StartDate,
		EndDate,
		ResourceCode,
		Quantity,
		moduser
		from table_ServiceResource
	 where objid=pnObjid 
	 and not exists (select *from table_ServiceResource where ServiceResource2Maintenance=pnObjid);



--Please Modify the cursor as you need

CURSOR m_MaintenanceLog_cur IS Select
		Name,
		Note,
		moduser
		from table_MaintenanceLog
	 where objid=pnObjid 
	 and not exists (select *from table_MaintenanceLog where MaintenanceLog2Maintenance=pnObjid);


--variables
id	NUMBER:=0;
i_ServiceParts_cur	m_ServiceParts_cur%rowtype;
i_ServiceResource_cur	m_ServiceResource_cur%rowtype;
i_MaintenanceLog_cur	m_MaintenanceLog_cur%rowtype;

BEGIN
--opening cursor m_ServiceParts_cur
	id:=0;
Begin
	OPEN m_ServiceParts_cur;
	LOOP
	id := id-1;
	FETCH m_ServiceParts_cur INTO i_ServiceParts_cur;
	EXIT WHEN m_ServiceParts_cur%NOTFOUND;

--Insert records in ServiceParts

	INSERT INTO table_ServiceParts(
		Name,
		UnitCount,
		Note,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_ServiceParts_cur.Name,
		i_ServiceParts_cur.UnitCount,
		i_ServiceParts_cur.Note,
		id,
		pnObjid,
		i_ServiceParts_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_ServiceParts_cur;
 End;
--opening cursor m_ServiceResource_cur
	id:=0;
Begin
	OPEN m_ServiceResource_cur;
	LOOP
	id := id-1;
	FETCH m_ServiceResource_cur INTO i_ServiceResource_cur;
	EXIT WHEN m_ServiceResource_cur%NOTFOUND;

--Insert records in ServiceResource

	INSERT INTO table_ServiceResource(
		Name,
		StartDate,
		EndDate,
		ResourceCode,
		Quantity,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_ServiceResource_cur.Name,
		i_ServiceResource_cur.StartDate,
		i_ServiceResource_cur.EndDate,
		i_ServiceResource_cur.ResourceCode,
		i_ServiceResource_cur.Quantity,
		id,
		pnObjid,
		i_ServiceResource_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_ServiceResource_cur;
 End;
--opening cursor m_MaintenanceLog_cur
	id:=0;
Begin
	OPEN m_MaintenanceLog_cur;
	LOOP
	id := id-1;
	FETCH m_MaintenanceLog_cur INTO i_MaintenanceLog_cur;
	EXIT WHEN m_MaintenanceLog_cur%NOTFOUND;

--Insert records in MaintenanceLog

	INSERT INTO table_MaintenanceLog(
		Name,
		Note,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_MaintenanceLog_cur.Name,
		i_MaintenanceLog_cur.Note,
		id,
		pnObjid,
		i_MaintenanceLog_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_MaintenanceLog_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

CREATE OR REPLACE PROCEDURE Insert_Boqplan(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_Material_cur IS Select
		Name,
		ProjectCode,
		MainCode,
		SubCode,
		DomainCode,
		PartCode,
		PartNo,
		Description,
		UnitPrice,
		UnitCount,
		QntRequest,
		QntUsed,
		QntPurchased,
		UmCode,
		OriginCode,
		Note,
		moduser
		from table_Material
	 where objid=pnObjid 
	 and not exists (select *from table_Material where Material2Boqplan=pnObjid);



--Please Modify the cursor as you need

CURSOR m_ResourceAudit_cur IS Select
		Name,
		Title,
		ProjectCode,
		MainCode,
		SubCode,
		TaskCode,
		ResourceCode,
		EstUnit,
		ActualUnit,
		UmCode,
		Rate,
		OriginCode,
		moduser
		from table_ResourceAudit
	 where objid=pnObjid 
	 and not exists (select *from table_ResourceAudit where ResourceAudit2Boqplan=pnObjid);



--Please Modify the cursor as you need

CURSOR m_Inspection_cur IS Select
		Name,
		Note,
		InspectDate,
		QntInspected,
		Status,
		moduser
		from table_Inspection
	 where objid=pnObjid 
	 and not exists (select *from table_Inspection where Inspection2Boqplan=pnObjid);


--variables
id	NUMBER:=0;
i_Material_cur	m_Material_cur%rowtype;
i_ResourceAudit_cur	m_ResourceAudit_cur%rowtype;
i_Inspection_cur	m_Inspection_cur%rowtype;

BEGIN
--opening cursor m_Material_cur
	id:=0;
Begin
	OPEN m_Material_cur;
	LOOP
	id := id-1;
	FETCH m_Material_cur INTO i_Material_cur;
	EXIT WHEN m_Material_cur%NOTFOUND;

--Insert records in Material

	INSERT INTO table_Material(
		Name,
		ProjectCode,
		MainCode,
		SubCode,
		DomainCode,
		PartCode,
		PartNo,
		Description,
		UnitPrice,
		UnitCount,
		QntRequest,
		QntUsed,
		QntPurchased,
		UmCode,
		OriginCode,
		Note,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_Material_cur.Name,
		i_Material_cur.ProjectCode,
		i_Material_cur.MainCode,
		i_Material_cur.SubCode,
		i_Material_cur.DomainCode,
		i_Material_cur.PartCode,
		i_Material_cur.PartNo,
		i_Material_cur.Description,
		i_Material_cur.UnitPrice,
		i_Material_cur.UnitCount,
		i_Material_cur.QntRequest,
		i_Material_cur.QntUsed,
		i_Material_cur.QntPurchased,
		i_Material_cur.UmCode,
		i_Material_cur.OriginCode,
		i_Material_cur.Note,
		id,
		pnObjid,
		i_Material_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Material_cur;
 End;
--opening cursor m_ResourceAudit_cur
	id:=0;
Begin
	OPEN m_ResourceAudit_cur;
	LOOP
	id := id-1;
	FETCH m_ResourceAudit_cur INTO i_ResourceAudit_cur;
	EXIT WHEN m_ResourceAudit_cur%NOTFOUND;

--Insert records in ResourceAudit

	INSERT INTO table_ResourceAudit(
		Name,
		Title,
		ProjectCode,
		MainCode,
		SubCode,
		TaskCode,
		ResourceCode,
		EstUnit,
		ActualUnit,
		UmCode,
		Rate,
		OriginCode,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_ResourceAudit_cur.Name,
		i_ResourceAudit_cur.Title,
		i_ResourceAudit_cur.ProjectCode,
		i_ResourceAudit_cur.MainCode,
		i_ResourceAudit_cur.SubCode,
		i_ResourceAudit_cur.TaskCode,
		i_ResourceAudit_cur.ResourceCode,
		i_ResourceAudit_cur.EstUnit,
		i_ResourceAudit_cur.ActualUnit,
		i_ResourceAudit_cur.UmCode,
		i_ResourceAudit_cur.Rate,
		i_ResourceAudit_cur.OriginCode,
		id,
		pnObjid,
		i_ResourceAudit_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_ResourceAudit_cur;
 End;
--opening cursor m_Inspection_cur
	id:=0;
Begin
	OPEN m_Inspection_cur;
	LOOP
	id := id-1;
	FETCH m_Inspection_cur INTO i_Inspection_cur;
	EXIT WHEN m_Inspection_cur%NOTFOUND;

--Insert records in Inspection

	INSERT INTO table_Inspection(
		Name,
		Note,
		InspectDate,
		QntInspected,
		Status,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_Inspection_cur.Name,
		i_Inspection_cur.Note,
		i_Inspection_cur.InspectDate,
		i_Inspection_cur.QntInspected,
		i_Inspection_cur.Status,
		id,
		pnObjid,
		i_Inspection_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Inspection_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

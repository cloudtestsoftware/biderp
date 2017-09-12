CREATE OR REPLACE PROCEDURE Insert_Resourceorder(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_TaskResource_cur IS Select
		Name,
		Title,
		ProjectCode,
		MainCode,
		SubCode,
		TaskCode,
		StartDate,
		EndDate,
		ResourceCode,
		EstUnit,
		QntRequest,
		ActualRate,
		OriginCode,
		PoCode,
		DeptCode,
		ProgressCode,
		moduser
		from table_TaskResource
	 where objid=pnObjid 
	 and not exists (select *from table_TaskResource where TaskResource2Resourceorder=pnObjid);



--Please Modify the cursor as you need

CURSOR m_PoInvoice_cur IS Select
		Name,
		PoName,
		SupplierName,
		PaidTo,
		Cutoffdate,
		Invoicedate,
		Paydate,
		ItemCode,
		DeptCode,
		Status,
		Amount,
		Tax,
		Total,
		Note,
		ProgressCode,
		moduser
		from table_PoInvoice
	 where objid=pnObjid 
	 and not exists (select *from table_PoInvoice where PoInvoice2Resourceorder=pnObjid);


--variables
id	NUMBER:=0;
i_TaskResource_cur	m_TaskResource_cur%rowtype;
i_PoInvoice_cur	m_PoInvoice_cur%rowtype;

BEGIN
--opening cursor m_TaskResource_cur
	id:=0;
Begin
	OPEN m_TaskResource_cur;
	LOOP
	id := id-1;
	FETCH m_TaskResource_cur INTO i_TaskResource_cur;
	EXIT WHEN m_TaskResource_cur%NOTFOUND;

--Insert records in TaskResource

	INSERT INTO table_TaskResource(
		Name,
		Title,
		ProjectCode,
		MainCode,
		SubCode,
		TaskCode,
		StartDate,
		EndDate,
		ResourceCode,
		EstUnit,
		QntRequest,
		ActualRate,
		OriginCode,
		PoCode,
		DeptCode,
		ProgressCode,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_TaskResource_cur.Name,
		i_TaskResource_cur.Title,
		i_TaskResource_cur.ProjectCode,
		i_TaskResource_cur.MainCode,
		i_TaskResource_cur.SubCode,
		i_TaskResource_cur.TaskCode,
		i_TaskResource_cur.StartDate,
		i_TaskResource_cur.EndDate,
		i_TaskResource_cur.ResourceCode,
		i_TaskResource_cur.EstUnit,
		i_TaskResource_cur.QntRequest,
		i_TaskResource_cur.ActualRate,
		i_TaskResource_cur.OriginCode,
		i_TaskResource_cur.PoCode,
		i_TaskResource_cur.DeptCode,
		i_TaskResource_cur.ProgressCode,
		id,
		pnObjid,
		i_TaskResource_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_TaskResource_cur;
 End;
--opening cursor m_PoInvoice_cur
	id:=0;
Begin
	OPEN m_PoInvoice_cur;
	LOOP
	id := id-1;
	FETCH m_PoInvoice_cur INTO i_PoInvoice_cur;
	EXIT WHEN m_PoInvoice_cur%NOTFOUND;

--Insert records in PoInvoice

	INSERT INTO table_PoInvoice(
		Name,
		PoName,
		SupplierName,
		PaidTo,
		Cutoffdate,
		Invoicedate,
		Paydate,
		ItemCode,
		DeptCode,
		Status,
		Amount,
		Tax,
		Total,
		Note,
		ProgressCode,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_PoInvoice_cur.Name,
		i_PoInvoice_cur.PoName,
		i_PoInvoice_cur.SupplierName,
		i_PoInvoice_cur.PaidTo,
		i_PoInvoice_cur.Cutoffdate,
		i_PoInvoice_cur.Invoicedate,
		i_PoInvoice_cur.Paydate,
		i_PoInvoice_cur.ItemCode,
		i_PoInvoice_cur.DeptCode,
		i_PoInvoice_cur.Status,
		i_PoInvoice_cur.Amount,
		i_PoInvoice_cur.Tax,
		i_PoInvoice_cur.Total,
		i_PoInvoice_cur.Note,
		i_PoInvoice_cur.ProgressCode,
		id,
		pnObjid,
		i_PoInvoice_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_PoInvoice_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

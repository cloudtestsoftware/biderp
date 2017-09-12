CREATE OR REPLACE PROCEDURE Insert_Purchaseorder(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_PartRequest_cur IS Select
		Name,
		Title,
		ProjectCode,
		Description,
		ActualRate,
		PctTax,
		QntRequest,
		QntPurchased,
		QntUsed,
		Status,
		PoCode,
		ApproveDate,
		StartDate,
		PurchaseDate,
		Note,
		ProgressCode,
		moduser
		from table_PartRequest
	 where objid=pnObjid 
	 and not exists (select *from table_PartRequest where PartRequest2Purchaseorder=pnObjid);



--Please Modify the cursor as you need

CURSOR m_PurchaseIndent_cur IS Select
		Name,
		IndentNo,
		MrNo,
		PartNo,
		Description,
		UmCode,
		DeptCode,
		PoCode,
		QntRequest,
		Total,
		moduser
		from table_PurchaseIndent
	 where objid=pnObjid 
	 and not exists (select *from table_PurchaseIndent where PurchaseIndent2Purchaseorder=pnObjid);



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
	 and not exists (select *from table_PoInvoice where PoInvoice2Purchaseorder=pnObjid);


--variables
id	NUMBER:=0;
i_PartRequest_cur	m_PartRequest_cur%rowtype;
i_PurchaseIndent_cur	m_PurchaseIndent_cur%rowtype;
i_PoInvoice_cur	m_PoInvoice_cur%rowtype;

BEGIN
--opening cursor m_PartRequest_cur
	id:=0;
Begin
	OPEN m_PartRequest_cur;
	LOOP
	id := id-1;
	FETCH m_PartRequest_cur INTO i_PartRequest_cur;
	EXIT WHEN m_PartRequest_cur%NOTFOUND;

--Insert records in PartRequest

	INSERT INTO table_PartRequest(
		Name,
		Title,
		ProjectCode,
		Description,
		ActualRate,
		PctTax,
		QntRequest,
		QntPurchased,
		QntUsed,
		Status,
		PoCode,
		ApproveDate,
		StartDate,
		PurchaseDate,
		Note,
		ProgressCode,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_PartRequest_cur.Name,
		i_PartRequest_cur.Title,
		i_PartRequest_cur.ProjectCode,
		i_PartRequest_cur.Description,
		i_PartRequest_cur.ActualRate,
		i_PartRequest_cur.PctTax,
		i_PartRequest_cur.QntRequest,
		i_PartRequest_cur.QntPurchased,
		i_PartRequest_cur.QntUsed,
		i_PartRequest_cur.Status,
		i_PartRequest_cur.PoCode,
		i_PartRequest_cur.ApproveDate,
		i_PartRequest_cur.StartDate,
		i_PartRequest_cur.PurchaseDate,
		i_PartRequest_cur.Note,
		i_PartRequest_cur.ProgressCode,
		id,
		pnObjid,
		i_PartRequest_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_PartRequest_cur;
 End;
--opening cursor m_PurchaseIndent_cur
	id:=0;
Begin
	OPEN m_PurchaseIndent_cur;
	LOOP
	id := id-1;
	FETCH m_PurchaseIndent_cur INTO i_PurchaseIndent_cur;
	EXIT WHEN m_PurchaseIndent_cur%NOTFOUND;

--Insert records in PurchaseIndent

	INSERT INTO table_PurchaseIndent(
		Name,
		IndentNo,
		MrNo,
		PartNo,
		Description,
		UmCode,
		DeptCode,
		PoCode,
		QntRequest,
		Total,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_PurchaseIndent_cur.Name,
		i_PurchaseIndent_cur.IndentNo,
		i_PurchaseIndent_cur.MrNo,
		i_PurchaseIndent_cur.PartNo,
		i_PurchaseIndent_cur.Description,
		i_PurchaseIndent_cur.UmCode,
		i_PurchaseIndent_cur.DeptCode,
		i_PurchaseIndent_cur.PoCode,
		i_PurchaseIndent_cur.QntRequest,
		i_PurchaseIndent_cur.Total,
		id,
		pnObjid,
		i_PurchaseIndent_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_PurchaseIndent_cur;
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

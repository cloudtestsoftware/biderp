CREATE OR REPLACE PROCEDURE Insert_Pomaster(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_PurchaseOrder_cur IS Select
		Name,
		SupplierName,
		SuppAddress,
		ContactName,
		ContactNo,
		BillTo,
		ShipTo,
		PoDate,
		CompleteDate,
		MrNo,
		PoType,
		Status,
		MaxLineNo,
		DeptCode,
		Note,
		ProgressCode,
		moduser
		from table_PurchaseOrder
	 where objid=pnObjid 
	 and not exists (select *from table_PurchaseOrder where PurchaseOrder2Pomaster=pnObjid);



--Please Modify the cursor as you need

CURSOR m_PoSupplier_cur IS Select
		Name,
		Address,
		ContactCode,
		LineCount,
		moduser
		from table_PoSupplier
	 where objid=pnObjid 
	 and not exists (select *from table_PoSupplier where PoSupplier2Pomaster=pnObjid);



--Please Modify the cursor as you need

CURSOR m_ResourceOrder_cur IS Select
		Name,
		SupplierName,
		SuppAddress,
		ContactName,
		ContactNo,
		BillTo,
		ShipTo,
		PoDate,
		CompleteDate,
		Tax,
		Status,
		ResourceCode,
		MaxLineNo,
		DeptCode,
		Note,
		ProgressCode,
		moduser
		from table_ResourceOrder
	 where objid=pnObjid 
	 and not exists (select *from table_ResourceOrder where ResourceOrder2Pomaster=pnObjid);



--Please Modify the cursor as you need

CURSOR m_ResourceSupplier_cur IS Select
		Name,
		Address,
		ContactCode,
		TaskCount,
		moduser
		from table_ResourceSupplier
	 where objid=pnObjid 
	 and not exists (select *from table_ResourceSupplier where ResourceSupplier2Pomaster=pnObjid);



--Please Modify the cursor as you need

CURSOR m_BomPurchase_cur IS Select
		Name,
		MrNo,
		Title,
		PartNo,
		UmCode,
		DeptCode,
		PoCode,
		QntRequest,
		QntUsed,
		QntBalance,
		QntToPurchase,
		LineCount,
		StartDate,
		moduser
		from table_BomPurchase
	 where objid=pnObjid 
	 and not exists (select *from table_BomPurchase where BomPurchase2Pomaster=pnObjid);



--Please Modify the cursor as you need

CURSOR m_DeptPoBudget_cur IS Select
		Name,
		DeptCode,
		Total,
		AmountSpent,
		Balance,
		PctSpent,
		PctBalance,
		moduser
		from table_DeptPoBudget
	 where objid=pnObjid 
	 and not exists (select *from table_DeptPoBudget where DeptPoBudget2Pomaster=pnObjid);


--variables
id	NUMBER:=0;
i_PurchaseOrder_cur	m_PurchaseOrder_cur%rowtype;
i_PoSupplier_cur	m_PoSupplier_cur%rowtype;
i_ResourceOrder_cur	m_ResourceOrder_cur%rowtype;
i_ResourceSupplier_cur	m_ResourceSupplier_cur%rowtype;
i_BomPurchase_cur	m_BomPurchase_cur%rowtype;
i_DeptPoBudget_cur	m_DeptPoBudget_cur%rowtype;

BEGIN
--opening cursor m_PurchaseOrder_cur
	id:=0;
Begin
	OPEN m_PurchaseOrder_cur;
	LOOP
	id := id-1;
	FETCH m_PurchaseOrder_cur INTO i_PurchaseOrder_cur;
	EXIT WHEN m_PurchaseOrder_cur%NOTFOUND;

--Insert records in PurchaseOrder

	INSERT INTO table_PurchaseOrder(
		Name,
		SupplierName,
		SuppAddress,
		ContactName,
		ContactNo,
		BillTo,
		ShipTo,
		PoDate,
		CompleteDate,
		MrNo,
		PoType,
		Status,
		MaxLineNo,
		DeptCode,
		Note,
		ProgressCode,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_PurchaseOrder_cur.Name,
		i_PurchaseOrder_cur.SupplierName,
		i_PurchaseOrder_cur.SuppAddress,
		i_PurchaseOrder_cur.ContactName,
		i_PurchaseOrder_cur.ContactNo,
		i_PurchaseOrder_cur.BillTo,
		i_PurchaseOrder_cur.ShipTo,
		i_PurchaseOrder_cur.PoDate,
		i_PurchaseOrder_cur.CompleteDate,
		i_PurchaseOrder_cur.MrNo,
		i_PurchaseOrder_cur.PoType,
		i_PurchaseOrder_cur.Status,
		i_PurchaseOrder_cur.MaxLineNo,
		i_PurchaseOrder_cur.DeptCode,
		i_PurchaseOrder_cur.Note,
		i_PurchaseOrder_cur.ProgressCode,
		id,
		pnObjid,
		i_PurchaseOrder_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_PurchaseOrder_cur;
 End;
--opening cursor m_PoSupplier_cur
	id:=0;
Begin
	OPEN m_PoSupplier_cur;
	LOOP
	id := id-1;
	FETCH m_PoSupplier_cur INTO i_PoSupplier_cur;
	EXIT WHEN m_PoSupplier_cur%NOTFOUND;

--Insert records in PoSupplier

	INSERT INTO table_PoSupplier(
		Name,
		Address,
		ContactCode,
		LineCount,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_PoSupplier_cur.Name,
		i_PoSupplier_cur.Address,
		i_PoSupplier_cur.ContactCode,
		i_PoSupplier_cur.LineCount,
		id,
		pnObjid,
		i_PoSupplier_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_PoSupplier_cur;
 End;
--opening cursor m_ResourceOrder_cur
	id:=0;
Begin
	OPEN m_ResourceOrder_cur;
	LOOP
	id := id-1;
	FETCH m_ResourceOrder_cur INTO i_ResourceOrder_cur;
	EXIT WHEN m_ResourceOrder_cur%NOTFOUND;

--Insert records in ResourceOrder

	INSERT INTO table_ResourceOrder(
		Name,
		SupplierName,
		SuppAddress,
		ContactName,
		ContactNo,
		BillTo,
		ShipTo,
		PoDate,
		CompleteDate,
		Tax,
		Status,
		ResourceCode,
		MaxLineNo,
		DeptCode,
		Note,
		ProgressCode,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_ResourceOrder_cur.Name,
		i_ResourceOrder_cur.SupplierName,
		i_ResourceOrder_cur.SuppAddress,
		i_ResourceOrder_cur.ContactName,
		i_ResourceOrder_cur.ContactNo,
		i_ResourceOrder_cur.BillTo,
		i_ResourceOrder_cur.ShipTo,
		i_ResourceOrder_cur.PoDate,
		i_ResourceOrder_cur.CompleteDate,
		i_ResourceOrder_cur.Tax,
		i_ResourceOrder_cur.Status,
		i_ResourceOrder_cur.ResourceCode,
		i_ResourceOrder_cur.MaxLineNo,
		i_ResourceOrder_cur.DeptCode,
		i_ResourceOrder_cur.Note,
		i_ResourceOrder_cur.ProgressCode,
		id,
		pnObjid,
		i_ResourceOrder_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_ResourceOrder_cur;
 End;
--opening cursor m_ResourceSupplier_cur
	id:=0;
Begin
	OPEN m_ResourceSupplier_cur;
	LOOP
	id := id-1;
	FETCH m_ResourceSupplier_cur INTO i_ResourceSupplier_cur;
	EXIT WHEN m_ResourceSupplier_cur%NOTFOUND;

--Insert records in ResourceSupplier

	INSERT INTO table_ResourceSupplier(
		Name,
		Address,
		ContactCode,
		TaskCount,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_ResourceSupplier_cur.Name,
		i_ResourceSupplier_cur.Address,
		i_ResourceSupplier_cur.ContactCode,
		i_ResourceSupplier_cur.TaskCount,
		id,
		pnObjid,
		i_ResourceSupplier_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_ResourceSupplier_cur;
 End;
--opening cursor m_BomPurchase_cur
	id:=0;
Begin
	OPEN m_BomPurchase_cur;
	LOOP
	id := id-1;
	FETCH m_BomPurchase_cur INTO i_BomPurchase_cur;
	EXIT WHEN m_BomPurchase_cur%NOTFOUND;

--Insert records in BomPurchase

	INSERT INTO table_BomPurchase(
		Name,
		MrNo,
		Title,
		PartNo,
		UmCode,
		DeptCode,
		PoCode,
		QntRequest,
		QntUsed,
		QntBalance,
		QntToPurchase,
		LineCount,
		StartDate,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_BomPurchase_cur.Name,
		i_BomPurchase_cur.MrNo,
		i_BomPurchase_cur.Title,
		i_BomPurchase_cur.PartNo,
		i_BomPurchase_cur.UmCode,
		i_BomPurchase_cur.DeptCode,
		i_BomPurchase_cur.PoCode,
		i_BomPurchase_cur.QntRequest,
		i_BomPurchase_cur.QntUsed,
		i_BomPurchase_cur.QntBalance,
		i_BomPurchase_cur.QntToPurchase,
		i_BomPurchase_cur.LineCount,
		i_BomPurchase_cur.StartDate,
		id,
		pnObjid,
		i_BomPurchase_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_BomPurchase_cur;
 End;
--opening cursor m_DeptPoBudget_cur
	id:=0;
Begin
	OPEN m_DeptPoBudget_cur;
	LOOP
	id := id-1;
	FETCH m_DeptPoBudget_cur INTO i_DeptPoBudget_cur;
	EXIT WHEN m_DeptPoBudget_cur%NOTFOUND;

--Insert records in DeptPoBudget

	INSERT INTO table_DeptPoBudget(
		Name,
		DeptCode,
		Total,
		AmountSpent,
		Balance,
		PctSpent,
		PctBalance,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_DeptPoBudget_cur.Name,
		i_DeptPoBudget_cur.DeptCode,
		i_DeptPoBudget_cur.Total,
		i_DeptPoBudget_cur.AmountSpent,
		i_DeptPoBudget_cur.Balance,
		i_DeptPoBudget_cur.PctSpent,
		i_DeptPoBudget_cur.PctBalance,
		id,
		pnObjid,
		i_DeptPoBudget_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_DeptPoBudget_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

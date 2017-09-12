CREATE OR REPLACE PROCEDURE Insert_Bidrequest(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_General_cur IS Select
		Name,
		StartDate,
		EndDate,
		BidDueDate,
		BidDueTime,
		Address,
		ZipCode,
		State,
		ContactName,
		Phone,
		moduser
		from table_General
	 where objid=pnObjid 
	 and not exists (select *from table_General where General2Bidrequest=pnObjid);



--Please Modify the cursor as you need

CURSOR m_Invitation_cur IS Select
		Name,
		email,
		Phone,
		Rating,
		Status,
		moduser
		from table_Invitation
	 where objid=pnObjid 
	 and not exists (select *from table_Invitation where Invitation2Bidrequest=pnObjid);



--Please Modify the cursor as you need

CURSOR m_Artifacts_cur IS Select
		Name,
		PostingDate,
		Description,
		Priority,
		FormsCode,
		Url,
		moduser
		from table_Artifacts
	 where objid=pnObjid 
	 and not exists (select *from table_Artifacts where Artifacts2Bidrequest=pnObjid);



--Please Modify the cursor as you need

CURSOR m_BidQuiz_cur IS Select
		Description,
		RequireCode,
		UmCode,
		UnitCount,
		Criteria,
		Point,
		QuizIndex,
		Name,
		moduser
		from table_BidQuiz
	 where objid=pnObjid 
	 and not exists (select *from table_BidQuiz where BidQuiz2Bidrequest=pnObjid);



--Please Modify the cursor as you need

CURSOR m_PartBid_cur IS Select
		Name,
		Description,
		ProjectName,
		ProjectCode,
		PartCode,
		QntRequest,
		UnitPrice,
		UmCode,
		Total,
		Note,
		moduser
		from table_PartBid
	 where objid=pnObjid 
	 and not exists (select *from table_PartBid where PartBid2Bidrequest=pnObjid);



--Please Modify the cursor as you need

CURSOR m_RequestInfo_cur IS Select
		Name,
		AskedDoubt,
		Replied,
		Status,
		AskedBy,
		RepliedBy,
		moduser
		from table_RequestInfo
	 where objid=pnObjid 
	 and not exists (select *from table_RequestInfo where RequestInfo2Bidrequest=pnObjid);



--Please Modify the cursor as you need

CURSOR m_Bids_cur IS Select
		FirstName,
		LastName,
		Email,
		Phone,
		Url,
		BidAmount,
		moduser
		from table_Bids
	 where objid=pnObjid 
	 and not exists (select *from table_Bids where Bids2Bidrequest=pnObjid);



--Please Modify the cursor as you need

CURSOR m_LowBid_cur IS Select
		Name,
		Description,
		ProjectName,
		PartCode,
		QntRequest,
		UnitPrice,
		LowestBid,
		HighestBid,
		UmCode,
		Total,
		Supplier,
		moduser
		from table_LowBid
	 where objid=pnObjid 
	 and not exists (select *from table_LowBid where LowBid2Bidrequest=pnObjid);



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
	 and not exists (select *from table_PurchaseOrder where PurchaseOrder2Bidrequest=pnObjid);



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
	 and not exists (select *from table_ResourceOrder where ResourceOrder2Bidrequest=pnObjid);


--variables
id	NUMBER:=0;
i_General_cur	m_General_cur%rowtype;
i_Invitation_cur	m_Invitation_cur%rowtype;
i_Artifacts_cur	m_Artifacts_cur%rowtype;
i_BidQuiz_cur	m_BidQuiz_cur%rowtype;
i_PartBid_cur	m_PartBid_cur%rowtype;
i_RequestInfo_cur	m_RequestInfo_cur%rowtype;
i_Bids_cur	m_Bids_cur%rowtype;
i_LowBid_cur	m_LowBid_cur%rowtype;
i_PurchaseOrder_cur	m_PurchaseOrder_cur%rowtype;
i_ResourceOrder_cur	m_ResourceOrder_cur%rowtype;

BEGIN
--opening cursor m_General_cur
	id:=0;
Begin
	OPEN m_General_cur;
	LOOP
	id := id-1;
	FETCH m_General_cur INTO i_General_cur;
	EXIT WHEN m_General_cur%NOTFOUND;

--Insert records in General

	INSERT INTO table_General(
		Name,
		StartDate,
		EndDate,
		BidDueDate,
		BidDueTime,
		Address,
		ZipCode,
		State,
		ContactName,
		Phone,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_General_cur.Name,
		i_General_cur.StartDate,
		i_General_cur.EndDate,
		i_General_cur.BidDueDate,
		i_General_cur.BidDueTime,
		i_General_cur.Address,
		i_General_cur.ZipCode,
		i_General_cur.State,
		i_General_cur.ContactName,
		i_General_cur.Phone,
		id,
		pnObjid,
		i_General_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_General_cur;
 End;
--opening cursor m_Invitation_cur
	id:=0;
Begin
	OPEN m_Invitation_cur;
	LOOP
	id := id-1;
	FETCH m_Invitation_cur INTO i_Invitation_cur;
	EXIT WHEN m_Invitation_cur%NOTFOUND;

--Insert records in Invitation

	INSERT INTO table_Invitation(
		Name,
		email,
		Phone,
		Rating,
		Status,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_Invitation_cur.Name,
		i_Invitation_cur.email,
		i_Invitation_cur.Phone,
		i_Invitation_cur.Rating,
		i_Invitation_cur.Status,
		id,
		pnObjid,
		i_Invitation_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Invitation_cur;
 End;
--opening cursor m_Artifacts_cur
	id:=0;
Begin
	OPEN m_Artifacts_cur;
	LOOP
	id := id-1;
	FETCH m_Artifacts_cur INTO i_Artifacts_cur;
	EXIT WHEN m_Artifacts_cur%NOTFOUND;

--Insert records in Artifacts

	INSERT INTO table_Artifacts(
		Name,
		PostingDate,
		Description,
		Priority,
		FormsCode,
		Url,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_Artifacts_cur.Name,
		i_Artifacts_cur.PostingDate,
		i_Artifacts_cur.Description,
		i_Artifacts_cur.Priority,
		i_Artifacts_cur.FormsCode,
		i_Artifacts_cur.Url,
		id,
		pnObjid,
		i_Artifacts_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Artifacts_cur;
 End;
--opening cursor m_BidQuiz_cur
	id:=0;
Begin
	OPEN m_BidQuiz_cur;
	LOOP
	id := id-1;
	FETCH m_BidQuiz_cur INTO i_BidQuiz_cur;
	EXIT WHEN m_BidQuiz_cur%NOTFOUND;

--Insert records in BidQuiz

	INSERT INTO table_BidQuiz(
		Description,
		RequireCode,
		UmCode,
		UnitCount,
		Criteria,
		Point,
		QuizIndex,
		Name,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_BidQuiz_cur.Description,
		i_BidQuiz_cur.RequireCode,
		i_BidQuiz_cur.UmCode,
		i_BidQuiz_cur.UnitCount,
		i_BidQuiz_cur.Criteria,
		i_BidQuiz_cur.Point,
		i_BidQuiz_cur.QuizIndex,
		i_BidQuiz_cur.Name,
		id,
		pnObjid,
		i_BidQuiz_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_BidQuiz_cur;
 End;
--opening cursor m_PartBid_cur
	id:=0;
Begin
	OPEN m_PartBid_cur;
	LOOP
	id := id-1;
	FETCH m_PartBid_cur INTO i_PartBid_cur;
	EXIT WHEN m_PartBid_cur%NOTFOUND;

--Insert records in PartBid

	INSERT INTO table_PartBid(
		Name,
		Description,
		ProjectName,
		ProjectCode,
		PartCode,
		QntRequest,
		UnitPrice,
		UmCode,
		Total,
		Note,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_PartBid_cur.Name,
		i_PartBid_cur.Description,
		i_PartBid_cur.ProjectName,
		i_PartBid_cur.ProjectCode,
		i_PartBid_cur.PartCode,
		i_PartBid_cur.QntRequest,
		i_PartBid_cur.UnitPrice,
		i_PartBid_cur.UmCode,
		i_PartBid_cur.Total,
		i_PartBid_cur.Note,
		id,
		pnObjid,
		i_PartBid_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_PartBid_cur;
 End;
--opening cursor m_RequestInfo_cur
	id:=0;
Begin
	OPEN m_RequestInfo_cur;
	LOOP
	id := id-1;
	FETCH m_RequestInfo_cur INTO i_RequestInfo_cur;
	EXIT WHEN m_RequestInfo_cur%NOTFOUND;

--Insert records in RequestInfo

	INSERT INTO table_RequestInfo(
		Name,
		AskedDoubt,
		Replied,
		Status,
		AskedBy,
		RepliedBy,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_RequestInfo_cur.Name,
		i_RequestInfo_cur.AskedDoubt,
		i_RequestInfo_cur.Replied,
		i_RequestInfo_cur.Status,
		i_RequestInfo_cur.AskedBy,
		i_RequestInfo_cur.RepliedBy,
		id,
		pnObjid,
		i_RequestInfo_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_RequestInfo_cur;
 End;
--opening cursor m_Bids_cur
	id:=0;
Begin
	OPEN m_Bids_cur;
	LOOP
	id := id-1;
	FETCH m_Bids_cur INTO i_Bids_cur;
	EXIT WHEN m_Bids_cur%NOTFOUND;

--Insert records in Bids

	INSERT INTO table_Bids(
		FirstName,
		LastName,
		Email,
		Phone,
		Url,
		BidAmount,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_Bids_cur.FirstName,
		i_Bids_cur.LastName,
		i_Bids_cur.Email,
		i_Bids_cur.Phone,
		i_Bids_cur.Url,
		i_Bids_cur.BidAmount,
		id,
		pnObjid,
		i_Bids_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Bids_cur;
 End;
--opening cursor m_LowBid_cur
	id:=0;
Begin
	OPEN m_LowBid_cur;
	LOOP
	id := id-1;
	FETCH m_LowBid_cur INTO i_LowBid_cur;
	EXIT WHEN m_LowBid_cur%NOTFOUND;

--Insert records in LowBid

	INSERT INTO table_LowBid(
		Name,
		Description,
		ProjectName,
		PartCode,
		QntRequest,
		UnitPrice,
		LowestBid,
		HighestBid,
		UmCode,
		Total,
		Supplier,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_LowBid_cur.Name,
		i_LowBid_cur.Description,
		i_LowBid_cur.ProjectName,
		i_LowBid_cur.PartCode,
		i_LowBid_cur.QntRequest,
		i_LowBid_cur.UnitPrice,
		i_LowBid_cur.LowestBid,
		i_LowBid_cur.HighestBid,
		i_LowBid_cur.UmCode,
		i_LowBid_cur.Total,
		i_LowBid_cur.Supplier,
		id,
		pnObjid,
		i_LowBid_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_LowBid_cur;
 End;
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
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

CREATE OR REPLACE PROCEDURE Insert_Parts(pnObjid NUMBER) IS 

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
	 and not exists (select *from table_PartRequest where PartRequest2Parts=pnObjid);



--Please Modify the cursor as you need

CURSOR m_Indent_cur IS Select
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
		from table_Indent
	 where objid=pnObjid 
	 and not exists (select *from table_Indent where Indent2Parts=pnObjid);


--variables
id	NUMBER:=0;
i_PartRequest_cur	m_PartRequest_cur%rowtype;
i_Indent_cur	m_Indent_cur%rowtype;

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
--opening cursor m_Indent_cur
	id:=0;
Begin
	OPEN m_Indent_cur;
	LOOP
	id := id-1;
	FETCH m_Indent_cur INTO i_Indent_cur;
	EXIT WHEN m_Indent_cur%NOTFOUND;

--Insert records in Indent

	INSERT INTO table_Indent(
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
		i_Indent_cur.Name,
		i_Indent_cur.IndentNo,
		i_Indent_cur.MrNo,
		i_Indent_cur.PartNo,
		i_Indent_cur.Description,
		i_Indent_cur.UmCode,
		i_Indent_cur.DeptCode,
		i_Indent_cur.PoCode,
		i_Indent_cur.QntRequest,
		i_Indent_cur.Total,
		id,
		pnObjid,
		i_Indent_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Indent_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

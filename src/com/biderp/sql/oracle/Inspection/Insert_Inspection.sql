CREATE OR REPLACE PROCEDURE Insert_Inspection(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_InvoiceItemList_cur IS Select
		Name,
		Note,
		InspectDate,
		QntInspected,
		Status,
		Rate,
		Total,
		moduser
		from table_InvoiceItemList
	 where objid=pnObjid 
	 and not exists (select *from table_InvoiceItemList where InvoiceItemList2Inspection=pnObjid);


--variables
id	NUMBER:=0;
i_InvoiceItemList_cur	m_InvoiceItemList_cur%rowtype;

BEGIN
--opening cursor m_InvoiceItemList_cur
	id:=0;
Begin
	OPEN m_InvoiceItemList_cur;
	LOOP
	id := id-1;
	FETCH m_InvoiceItemList_cur INTO i_InvoiceItemList_cur;
	EXIT WHEN m_InvoiceItemList_cur%NOTFOUND;

--Insert records in InvoiceItemList

	INSERT INTO table_InvoiceItemList(
		Name,
		Note,
		InspectDate,
		QntInspected,
		Status,
		Rate,
		Total,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_InvoiceItemList_cur.Name,
		i_InvoiceItemList_cur.Note,
		i_InvoiceItemList_cur.InspectDate,
		i_InvoiceItemList_cur.QntInspected,
		i_InvoiceItemList_cur.Status,
		i_InvoiceItemList_cur.Rate,
		i_InvoiceItemList_cur.Total,
		id,
		pnObjid,
		i_InvoiceItemList_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_InvoiceItemList_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

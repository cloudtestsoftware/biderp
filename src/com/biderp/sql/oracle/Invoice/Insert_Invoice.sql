CREATE OR REPLACE PROCEDURE Insert_Invoice(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_InvoiceItem_cur IS Select
		Name,
		Note,
		InspectDate,
		QntInspected,
		Status,
		Rate,
		Total,
		moduser
		from table_InvoiceItem
	 where objid=pnObjid 
	 and not exists (select *from table_InvoiceItem where InvoiceItem2Invoice=pnObjid);


--variables
id	NUMBER:=0;
i_InvoiceItem_cur	m_InvoiceItem_cur%rowtype;

BEGIN
--opening cursor m_InvoiceItem_cur
	id:=0;
Begin
	OPEN m_InvoiceItem_cur;
	LOOP
	id := id-1;
	FETCH m_InvoiceItem_cur INTO i_InvoiceItem_cur;
	EXIT WHEN m_InvoiceItem_cur%NOTFOUND;

--Insert records in InvoiceItem

	INSERT INTO table_InvoiceItem(
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
		i_InvoiceItem_cur.Name,
		i_InvoiceItem_cur.Note,
		i_InvoiceItem_cur.InspectDate,
		i_InvoiceItem_cur.QntInspected,
		i_InvoiceItem_cur.Status,
		i_InvoiceItem_cur.Rate,
		i_InvoiceItem_cur.Total,
		id,
		pnObjid,
		i_InvoiceItem_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_InvoiceItem_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

CREATE OR REPLACE PROCEDURE Insert_Poinvoice(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_InvoicedItems_cur IS Select
		Name,
		Note,
		ReceiveDate,
		ActualRate,
		Amount,
		WithTax,
		moduser
		from table_InvoicedItems
	 where objid=pnObjid 
	 and not exists (select *from table_InvoicedItems where InvoicedItems2Poinvoice=pnObjid);



--Please Modify the cursor as you need

CURSOR m_InvoicedResource_cur IS Select
		Name,
		Title,
		TaskCode,
		ServiceDate,
		ActualRate,
		EstUnit,
		RegularTime,
		OverTime,
		TotalTime,
		Amount,
		WithTax,
		moduser
		from table_InvoicedResource
	 where objid=pnObjid 
	 and not exists (select *from table_InvoicedResource where InvoicedResource2Poinvoice=pnObjid);


--variables
id	NUMBER:=0;
i_InvoicedItems_cur	m_InvoicedItems_cur%rowtype;
i_InvoicedResource_cur	m_InvoicedResource_cur%rowtype;

BEGIN
--opening cursor m_InvoicedItems_cur
	id:=0;
Begin
	OPEN m_InvoicedItems_cur;
	LOOP
	id := id-1;
	FETCH m_InvoicedItems_cur INTO i_InvoicedItems_cur;
	EXIT WHEN m_InvoicedItems_cur%NOTFOUND;

--Insert records in InvoicedItems

	INSERT INTO table_InvoicedItems(
		Name,
		Note,
		ReceiveDate,
		ActualRate,
		Amount,
		WithTax,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_InvoicedItems_cur.Name,
		i_InvoicedItems_cur.Note,
		i_InvoicedItems_cur.ReceiveDate,
		i_InvoicedItems_cur.ActualRate,
		i_InvoicedItems_cur.Amount,
		i_InvoicedItems_cur.WithTax,
		id,
		pnObjid,
		i_InvoicedItems_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_InvoicedItems_cur;
 End;
--opening cursor m_InvoicedResource_cur
	id:=0;
Begin
	OPEN m_InvoicedResource_cur;
	LOOP
	id := id-1;
	FETCH m_InvoicedResource_cur INTO i_InvoicedResource_cur;
	EXIT WHEN m_InvoicedResource_cur%NOTFOUND;

--Insert records in InvoicedResource

	INSERT INTO table_InvoicedResource(
		Name,
		Title,
		TaskCode,
		ServiceDate,
		ActualRate,
		EstUnit,
		RegularTime,
		OverTime,
		TotalTime,
		Amount,
		WithTax,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_InvoicedResource_cur.Name,
		i_InvoicedResource_cur.Title,
		i_InvoicedResource_cur.TaskCode,
		i_InvoicedResource_cur.ServiceDate,
		i_InvoicedResource_cur.ActualRate,
		i_InvoicedResource_cur.EstUnit,
		i_InvoicedResource_cur.RegularTime,
		i_InvoicedResource_cur.OverTime,
		i_InvoicedResource_cur.TotalTime,
		i_InvoicedResource_cur.Amount,
		i_InvoicedResource_cur.WithTax,
		id,
		pnObjid,
		i_InvoicedResource_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_InvoicedResource_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

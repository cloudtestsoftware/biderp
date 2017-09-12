CREATE OR REPLACE PROCEDURE Insert_Budget(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_BudgetHead_cur IS Select
		Name,
		DeptCode,
		Description,
		moduser
		from table_BudgetHead
	 where objid=pnObjid 
	 and not exists (select *from table_BudgetHead where BudgetHead2Budget=pnObjid);



--Please Modify the cursor as you need

CURSOR m_BudgetPlan_cur IS Select
		Name,
		YearCode,
		MonthCode,
		PlanPct,
		Condition,
		FundDueDate,
		moduser
		from table_BudgetPlan
	 where objid=pnObjid 
	 and not exists (select *from table_BudgetPlan where BudgetPlan2Budget=pnObjid);



--Please Modify the cursor as you need

CURSOR m_Invoice_cur IS Select
		Name,
		FromDate,
		UptoDate,
		moduser
		from table_Invoice
	 where objid=pnObjid 
	 and not exists (select *from table_Invoice where Invoice2Budget=pnObjid);



--Please Modify the cursor as you need

CURSOR m_Payment_cur IS Select
		Name,
		Title,
		ProjectCode,
		InvoicedAmount,
		Description,
		PaymentMethod,
		FinanceCode,
		Note,
		FundDueDate,
		FundPayDate,
		Status,
		ProgressCode,
		moduser
		from table_Payment
	 where objid=pnObjid 
	 and not exists (select *from table_Payment where Payment2Budget=pnObjid);



--Please Modify the cursor as you need

CURSOR m_Monthly_cur IS Select
		Name,
		Title,
		ProjectCode,
		StartDate,
		MonthCode,
		YearCode,
		moduser
		from table_Monthly
	 where objid=pnObjid 
	 and not exists (select *from table_Monthly where Monthly2Budget=pnObjid);



--Please Modify the cursor as you need

CURSOR m_Yearly_cur IS Select
		Name,
		Title,
		ProjectCode,
		YearCode,
		moduser
		from table_Yearly
	 where objid=pnObjid 
	 and not exists (select *from table_Yearly where Yearly2Budget=pnObjid);


--variables
id	NUMBER:=0;
i_BudgetHead_cur	m_BudgetHead_cur%rowtype;
i_BudgetPlan_cur	m_BudgetPlan_cur%rowtype;
i_Invoice_cur	m_Invoice_cur%rowtype;
i_Payment_cur	m_Payment_cur%rowtype;
i_Monthly_cur	m_Monthly_cur%rowtype;
i_Yearly_cur	m_Yearly_cur%rowtype;

BEGIN
--opening cursor m_BudgetHead_cur
	id:=0;
Begin
	OPEN m_BudgetHead_cur;
	LOOP
	id := id-1;
	FETCH m_BudgetHead_cur INTO i_BudgetHead_cur;
	EXIT WHEN m_BudgetHead_cur%NOTFOUND;

--Insert records in BudgetHead

	INSERT INTO table_BudgetHead(
		Name,
		DeptCode,
		Description,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_BudgetHead_cur.Name,
		i_BudgetHead_cur.DeptCode,
		i_BudgetHead_cur.Description,
		id,
		pnObjid,
		i_BudgetHead_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_BudgetHead_cur;
 End;
--opening cursor m_BudgetPlan_cur
	id:=0;
Begin
	OPEN m_BudgetPlan_cur;
	LOOP
	id := id-1;
	FETCH m_BudgetPlan_cur INTO i_BudgetPlan_cur;
	EXIT WHEN m_BudgetPlan_cur%NOTFOUND;

--Insert records in BudgetPlan

	INSERT INTO table_BudgetPlan(
		Name,
		YearCode,
		MonthCode,
		PlanPct,
		Condition,
		FundDueDate,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_BudgetPlan_cur.Name,
		i_BudgetPlan_cur.YearCode,
		i_BudgetPlan_cur.MonthCode,
		i_BudgetPlan_cur.PlanPct,
		i_BudgetPlan_cur.Condition,
		i_BudgetPlan_cur.FundDueDate,
		id,
		pnObjid,
		i_BudgetPlan_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_BudgetPlan_cur;
 End;
--opening cursor m_Invoice_cur
	id:=0;
Begin
	OPEN m_Invoice_cur;
	LOOP
	id := id-1;
	FETCH m_Invoice_cur INTO i_Invoice_cur;
	EXIT WHEN m_Invoice_cur%NOTFOUND;

--Insert records in Invoice

	INSERT INTO table_Invoice(
		Name,
		FromDate,
		UptoDate,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_Invoice_cur.Name,
		i_Invoice_cur.FromDate,
		i_Invoice_cur.UptoDate,
		id,
		pnObjid,
		i_Invoice_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Invoice_cur;
 End;
--opening cursor m_Payment_cur
	id:=0;
Begin
	OPEN m_Payment_cur;
	LOOP
	id := id-1;
	FETCH m_Payment_cur INTO i_Payment_cur;
	EXIT WHEN m_Payment_cur%NOTFOUND;

--Insert records in Payment

	INSERT INTO table_Payment(
		Name,
		Title,
		ProjectCode,
		InvoicedAmount,
		Description,
		PaymentMethod,
		FinanceCode,
		Note,
		FundDueDate,
		FundPayDate,
		Status,
		ProgressCode,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_Payment_cur.Name,
		i_Payment_cur.Title,
		i_Payment_cur.ProjectCode,
		i_Payment_cur.InvoicedAmount,
		i_Payment_cur.Description,
		i_Payment_cur.PaymentMethod,
		i_Payment_cur.FinanceCode,
		i_Payment_cur.Note,
		i_Payment_cur.FundDueDate,
		i_Payment_cur.FundPayDate,
		i_Payment_cur.Status,
		i_Payment_cur.ProgressCode,
		id,
		pnObjid,
		i_Payment_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Payment_cur;
 End;
--opening cursor m_Monthly_cur
	id:=0;
Begin
	OPEN m_Monthly_cur;
	LOOP
	id := id-1;
	FETCH m_Monthly_cur INTO i_Monthly_cur;
	EXIT WHEN m_Monthly_cur%NOTFOUND;

--Insert records in Monthly

	INSERT INTO table_Monthly(
		Name,
		Title,
		ProjectCode,
		StartDate,
		MonthCode,
		YearCode,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_Monthly_cur.Name,
		i_Monthly_cur.Title,
		i_Monthly_cur.ProjectCode,
		i_Monthly_cur.StartDate,
		i_Monthly_cur.MonthCode,
		i_Monthly_cur.YearCode,
		id,
		pnObjid,
		i_Monthly_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Monthly_cur;
 End;
--opening cursor m_Yearly_cur
	id:=0;
Begin
	OPEN m_Yearly_cur;
	LOOP
	id := id-1;
	FETCH m_Yearly_cur INTO i_Yearly_cur;
	EXIT WHEN m_Yearly_cur%NOTFOUND;

--Insert records in Yearly

	INSERT INTO table_Yearly(
		Name,
		Title,
		ProjectCode,
		YearCode,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_Yearly_cur.Name,
		i_Yearly_cur.Title,
		i_Yearly_cur.ProjectCode,
		i_Yearly_cur.YearCode,
		id,
		pnObjid,
		i_Yearly_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Yearly_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

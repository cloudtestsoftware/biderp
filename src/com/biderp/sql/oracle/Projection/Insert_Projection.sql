CREATE OR REPLACE PROCEDURE Insert_Projection(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_MonthlyRevenue_cur IS Select
		MonthCode,
		ForcastAmount,
		EarnedRevenue,
		NotInvoiced,
		moduser
		from table_MonthlyRevenue
	 where objid=pnObjid 
	 and not exists (select *from table_MonthlyRevenue where MonthlyRevenue2Projection=pnObjid);


--variables
id	NUMBER:=0;
i_MonthlyRevenue_cur	m_MonthlyRevenue_cur%rowtype;

BEGIN
--opening cursor m_MonthlyRevenue_cur
	id:=0;
Begin
	OPEN m_MonthlyRevenue_cur;
	LOOP
	id := id-1;
	FETCH m_MonthlyRevenue_cur INTO i_MonthlyRevenue_cur;
	EXIT WHEN m_MonthlyRevenue_cur%NOTFOUND;

--Insert records in MonthlyRevenue

	INSERT INTO table_MonthlyRevenue(
		MonthCode,
		ForcastAmount,
		EarnedRevenue,
		NotInvoiced,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_MonthlyRevenue_cur.MonthCode,
		i_MonthlyRevenue_cur.ForcastAmount,
		i_MonthlyRevenue_cur.EarnedRevenue,
		i_MonthlyRevenue_cur.NotInvoiced,
		id,
		pnObjid,
		i_MonthlyRevenue_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_MonthlyRevenue_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

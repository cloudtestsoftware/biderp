CREATE OR REPLACE PROCEDURE Insert_Bidtotal(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_Transaction_cur IS Select
		Name,
		Amount,
		Debited,
		Credited,
		TransactionType,
		TransactionCode,
		Voucher,
		PostDate,
		Note,
		Status,
		moduser
		from table_Transaction
	 where objid=pnObjid 
	 and not exists (select *from table_Transaction where Transaction2Bidtotal=pnObjid);


--variables
id	NUMBER:=0;
i_Transaction_cur	m_Transaction_cur%rowtype;

BEGIN
--opening cursor m_Transaction_cur
	id:=0;
Begin
	OPEN m_Transaction_cur;
	LOOP
	id := id-1;
	FETCH m_Transaction_cur INTO i_Transaction_cur;
	EXIT WHEN m_Transaction_cur%NOTFOUND;

--Insert records in Transaction

	INSERT INTO table_Transaction(
		Name,
		Amount,
		Debited,
		Credited,
		TransactionType,
		TransactionCode,
		Voucher,
		PostDate,
		Note,
		Status,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_Transaction_cur.Name,
		i_Transaction_cur.Amount,
		i_Transaction_cur.Debited,
		i_Transaction_cur.Credited,
		i_Transaction_cur.TransactionType,
		i_Transaction_cur.TransactionCode,
		i_Transaction_cur.Voucher,
		i_Transaction_cur.PostDate,
		i_Transaction_cur.Note,
		i_Transaction_cur.Status,
		id,
		pnObjid,
		i_Transaction_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Transaction_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

CREATE OR REPLACE PROCEDURE Insert_Balancesheet(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_BalanceDetails_cur IS Select
		Name,
		AccountGroup,
		Amount,
		moduser
		from table_BalanceDetails
	 where objid=pnObjid 
	 and not exists (select *from table_BalanceDetails where BalanceDetails2Balancesheet=pnObjid);


--variables
id	NUMBER:=0;
i_BalanceDetails_cur	m_BalanceDetails_cur%rowtype;

BEGIN
--opening cursor m_BalanceDetails_cur
	id:=0;
Begin
	OPEN m_BalanceDetails_cur;
	LOOP
	id := id-1;
	FETCH m_BalanceDetails_cur INTO i_BalanceDetails_cur;
	EXIT WHEN m_BalanceDetails_cur%NOTFOUND;

--Insert records in BalanceDetails

	INSERT INTO table_BalanceDetails(
		Name,
		AccountGroup,
		Amount,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_BalanceDetails_cur.Name,
		i_BalanceDetails_cur.AccountGroup,
		i_BalanceDetails_cur.Amount,
		id,
		pnObjid,
		i_BalanceDetails_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_BalanceDetails_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

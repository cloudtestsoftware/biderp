CREATE OR REPLACE PROCEDURE Insert_Partrequest(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_ItemReceived_cur IS Select
		Name,
		Note,
		QntReceived,
		QntAccepted,
		ReceiveDate,
		ProgressCode,
		moduser
		from table_ItemReceived
	 where objid=pnObjid 
	 and not exists (select *from table_ItemReceived where ItemReceived2Partrequest=pnObjid);



--Please Modify the cursor as you need

CURSOR m_ItemDispatch_cur IS Select
		Note,
		QntDispatched,
		DisptachDate,
		moduser
		from table_ItemDispatch
	 where objid=pnObjid 
	 and not exists (select *from table_ItemDispatch where ItemDispatch2Partrequest=pnObjid);


--variables
id	NUMBER:=0;
i_ItemReceived_cur	m_ItemReceived_cur%rowtype;
i_ItemDispatch_cur	m_ItemDispatch_cur%rowtype;

BEGIN
--opening cursor m_ItemReceived_cur
	id:=0;
Begin
	OPEN m_ItemReceived_cur;
	LOOP
	id := id-1;
	FETCH m_ItemReceived_cur INTO i_ItemReceived_cur;
	EXIT WHEN m_ItemReceived_cur%NOTFOUND;

--Insert records in ItemReceived

	INSERT INTO table_ItemReceived(
		Name,
		Note,
		QntReceived,
		QntAccepted,
		ReceiveDate,
		ProgressCode,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_ItemReceived_cur.Name,
		i_ItemReceived_cur.Note,
		i_ItemReceived_cur.QntReceived,
		i_ItemReceived_cur.QntAccepted,
		i_ItemReceived_cur.ReceiveDate,
		i_ItemReceived_cur.ProgressCode,
		id,
		pnObjid,
		i_ItemReceived_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_ItemReceived_cur;
 End;
--opening cursor m_ItemDispatch_cur
	id:=0;
Begin
	OPEN m_ItemDispatch_cur;
	LOOP
	id := id-1;
	FETCH m_ItemDispatch_cur INTO i_ItemDispatch_cur;
	EXIT WHEN m_ItemDispatch_cur%NOTFOUND;

--Insert records in ItemDispatch

	INSERT INTO table_ItemDispatch(
		Note,
		QntDispatched,
		DisptachDate,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_ItemDispatch_cur.Note,
		i_ItemDispatch_cur.QntDispatched,
		i_ItemDispatch_cur.DisptachDate,
		id,
		pnObjid,
		i_ItemDispatch_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_ItemDispatch_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

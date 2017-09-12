CREATE OR REPLACE PROCEDURE Insert_Partprice(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_OrderPending_cur IS Select
		Name,
		Description,
		PartNo,
		PartCode,
		UmCode,
		QntPending,
		OrderAmount,
		moduser
		from table_OrderPending
	 where objid=pnObjid 
	 and not exists (select *from table_OrderPending where OrderPending2Partprice=pnObjid);



--Please Modify the cursor as you need

CURSOR m_PartCount_cur IS Select
		Name,
		UmCode,
		QntUsed,
		QntRequest,
		QntBalance,
		moduser
		from table_PartCount
	 where objid=pnObjid 
	 and not exists (select *from table_PartCount where PartCount2Partprice=pnObjid);



--Please Modify the cursor as you need

CURSOR m_QRInfo_cur IS Select
		Name,
		InfoCode,
		Description,
		URL,
		moduser
		from table_QRInfo
	 where objid=pnObjid 
	 and not exists (select *from table_QRInfo where QRInfo2Partprice=pnObjid);


--variables
id	NUMBER:=0;
i_OrderPending_cur	m_OrderPending_cur%rowtype;
i_PartCount_cur	m_PartCount_cur%rowtype;
i_QRInfo_cur	m_QRInfo_cur%rowtype;

BEGIN
--opening cursor m_OrderPending_cur
	id:=0;
Begin
	OPEN m_OrderPending_cur;
	LOOP
	id := id-1;
	FETCH m_OrderPending_cur INTO i_OrderPending_cur;
	EXIT WHEN m_OrderPending_cur%NOTFOUND;

--Insert records in OrderPending

	INSERT INTO table_OrderPending(
		Name,
		Description,
		PartNo,
		PartCode,
		UmCode,
		QntPending,
		OrderAmount,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_OrderPending_cur.Name,
		i_OrderPending_cur.Description,
		i_OrderPending_cur.PartNo,
		i_OrderPending_cur.PartCode,
		i_OrderPending_cur.UmCode,
		i_OrderPending_cur.QntPending,
		i_OrderPending_cur.OrderAmount,
		id,
		pnObjid,
		i_OrderPending_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_OrderPending_cur;
 End;
--opening cursor m_PartCount_cur
	id:=0;
Begin
	OPEN m_PartCount_cur;
	LOOP
	id := id-1;
	FETCH m_PartCount_cur INTO i_PartCount_cur;
	EXIT WHEN m_PartCount_cur%NOTFOUND;

--Insert records in PartCount

	INSERT INTO table_PartCount(
		Name,
		UmCode,
		QntUsed,
		QntRequest,
		QntBalance,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_PartCount_cur.Name,
		i_PartCount_cur.UmCode,
		i_PartCount_cur.QntUsed,
		i_PartCount_cur.QntRequest,
		i_PartCount_cur.QntBalance,
		id,
		pnObjid,
		i_PartCount_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_PartCount_cur;
 End;
--opening cursor m_QRInfo_cur
	id:=0;
Begin
	OPEN m_QRInfo_cur;
	LOOP
	id := id-1;
	FETCH m_QRInfo_cur INTO i_QRInfo_cur;
	EXIT WHEN m_QRInfo_cur%NOTFOUND;

--Insert records in QRInfo

	INSERT INTO table_QRInfo(
		Name,
		InfoCode,
		Description,
		URL,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_QRInfo_cur.Name,
		i_QRInfo_cur.InfoCode,
		i_QRInfo_cur.Description,
		i_QRInfo_cur.URL,
		id,
		pnObjid,
		i_QRInfo_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_QRInfo_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

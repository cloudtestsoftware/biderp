CREATE OR REPLACE PROCEDURE Insert_Vendorbid(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_ItemPrice_cur IS Select
		Name,
		UnitPrice,
		moduser
		from table_ItemPrice
	 where objid=pnObjid 
	 and not exists (select *from table_ItemPrice where ItemPrice2Vendorbid=pnObjid);



--Please Modify the cursor as you need

CURSOR m_BidArtifacts_cur IS Select
		Name,
		PostingDate,
		Description,
		Priority,
		FormsCode,
		Url,
		moduser
		from table_BidArtifacts
	 where objid=pnObjid 
	 and not exists (select *from table_BidArtifacts where BidArtifacts2Vendorbid=pnObjid);



--Please Modify the cursor as you need

CURSOR m_RequestInfo_cur IS Select
		Name,
		AskedDoubt,
		Replied,
		Status,
		AskedBy,
		RepliedBy,
		moduser
		from table_RequestInfo
	 where objid=pnObjid 
	 and not exists (select *from table_RequestInfo where RequestInfo2Vendorbid=pnObjid);



--Please Modify the cursor as you need

CURSOR m_QuizReply_cur IS Select
		Name,
		Reply,
		IsComply,
		PointEarned,
		moduser
		from table_QuizReply
	 where objid=pnObjid 
	 and not exists (select *from table_QuizReply where QuizReply2Vendorbid=pnObjid);



--Please Modify the cursor as you need

CURSOR m_Attachment_cur IS Select
		Name,
		Description,
		Url,
		moduser
		from table_Attachment
	 where objid=pnObjid 
	 and not exists (select *from table_Attachment where Attachment2Vendorbid=pnObjid);


--variables
id	NUMBER:=0;
i_ItemPrice_cur	m_ItemPrice_cur%rowtype;
i_BidArtifacts_cur	m_BidArtifacts_cur%rowtype;
i_RequestInfo_cur	m_RequestInfo_cur%rowtype;
i_QuizReply_cur	m_QuizReply_cur%rowtype;
i_Attachment_cur	m_Attachment_cur%rowtype;

BEGIN
--opening cursor m_ItemPrice_cur
	id:=0;
Begin
	OPEN m_ItemPrice_cur;
	LOOP
	id := id-1;
	FETCH m_ItemPrice_cur INTO i_ItemPrice_cur;
	EXIT WHEN m_ItemPrice_cur%NOTFOUND;

--Insert records in ItemPrice

	INSERT INTO table_ItemPrice(
		Name,
		UnitPrice,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_ItemPrice_cur.Name,
		i_ItemPrice_cur.UnitPrice,
		id,
		pnObjid,
		i_ItemPrice_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_ItemPrice_cur;
 End;
--opening cursor m_BidArtifacts_cur
	id:=0;
Begin
	OPEN m_BidArtifacts_cur;
	LOOP
	id := id-1;
	FETCH m_BidArtifacts_cur INTO i_BidArtifacts_cur;
	EXIT WHEN m_BidArtifacts_cur%NOTFOUND;

--Insert records in BidArtifacts

	INSERT INTO table_BidArtifacts(
		Name,
		PostingDate,
		Description,
		Priority,
		FormsCode,
		Url,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_BidArtifacts_cur.Name,
		i_BidArtifacts_cur.PostingDate,
		i_BidArtifacts_cur.Description,
		i_BidArtifacts_cur.Priority,
		i_BidArtifacts_cur.FormsCode,
		i_BidArtifacts_cur.Url,
		id,
		pnObjid,
		i_BidArtifacts_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_BidArtifacts_cur;
 End;
--opening cursor m_RequestInfo_cur
	id:=0;
Begin
	OPEN m_RequestInfo_cur;
	LOOP
	id := id-1;
	FETCH m_RequestInfo_cur INTO i_RequestInfo_cur;
	EXIT WHEN m_RequestInfo_cur%NOTFOUND;

--Insert records in RequestInfo

	INSERT INTO table_RequestInfo(
		Name,
		AskedDoubt,
		Replied,
		Status,
		AskedBy,
		RepliedBy,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_RequestInfo_cur.Name,
		i_RequestInfo_cur.AskedDoubt,
		i_RequestInfo_cur.Replied,
		i_RequestInfo_cur.Status,
		i_RequestInfo_cur.AskedBy,
		i_RequestInfo_cur.RepliedBy,
		id,
		pnObjid,
		i_RequestInfo_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_RequestInfo_cur;
 End;
--opening cursor m_QuizReply_cur
	id:=0;
Begin
	OPEN m_QuizReply_cur;
	LOOP
	id := id-1;
	FETCH m_QuizReply_cur INTO i_QuizReply_cur;
	EXIT WHEN m_QuizReply_cur%NOTFOUND;

--Insert records in QuizReply

	INSERT INTO table_QuizReply(
		Name,
		Reply,
		IsComply,
		PointEarned,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_QuizReply_cur.Name,
		i_QuizReply_cur.Reply,
		i_QuizReply_cur.IsComply,
		i_QuizReply_cur.PointEarned,
		id,
		pnObjid,
		i_QuizReply_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_QuizReply_cur;
 End;
--opening cursor m_Attachment_cur
	id:=0;
Begin
	OPEN m_Attachment_cur;
	LOOP
	id := id-1;
	FETCH m_Attachment_cur INTO i_Attachment_cur;
	EXIT WHEN m_Attachment_cur%NOTFOUND;

--Insert records in Attachment

	INSERT INTO table_Attachment(
		Name,
		Description,
		Url,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_Attachment_cur.Name,
		i_Attachment_cur.Description,
		i_Attachment_cur.Url,
		id,
		pnObjid,
		i_Attachment_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Attachment_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

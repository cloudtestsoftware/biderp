CREATE OR REPLACE PROCEDURE Insert_Bids(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_ItemCost_cur IS Select
		Name,
		Description,
		UmCode,
		UnitCount,
		UnitPrice,
		Total,
		Bidder,
		moduser
		from table_ItemCost
	 where objid=pnObjid 
	 and not exists (select *from table_ItemCost where ItemCost2Bids=pnObjid);



--Please Modify the cursor as you need

CURSOR m_QuizReply_cur IS Select
		Name,
		Reply,
		IsComply,
		PointEarned,
		moduser
		from table_QuizReply
	 where objid=pnObjid 
	 and not exists (select *from table_QuizReply where QuizReply2Bids=pnObjid);



--Please Modify the cursor as you need

CURSOR m_Attachment_cur IS Select
		Name,
		Description,
		Url,
		moduser
		from table_Attachment
	 where objid=pnObjid 
	 and not exists (select *from table_Attachment where Attachment2Bids=pnObjid);


--variables
id	NUMBER:=0;
i_ItemCost_cur	m_ItemCost_cur%rowtype;
i_QuizReply_cur	m_QuizReply_cur%rowtype;
i_Attachment_cur	m_Attachment_cur%rowtype;

BEGIN
--opening cursor m_ItemCost_cur
	id:=0;
Begin
	OPEN m_ItemCost_cur;
	LOOP
	id := id-1;
	FETCH m_ItemCost_cur INTO i_ItemCost_cur;
	EXIT WHEN m_ItemCost_cur%NOTFOUND;

--Insert records in ItemCost

	INSERT INTO table_ItemCost(
		Name,
		Description,
		UmCode,
		UnitCount,
		UnitPrice,
		Total,
		Bidder,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_ItemCost_cur.Name,
		i_ItemCost_cur.Description,
		i_ItemCost_cur.UmCode,
		i_ItemCost_cur.UnitCount,
		i_ItemCost_cur.UnitPrice,
		i_ItemCost_cur.Total,
		i_ItemCost_cur.Bidder,
		id,
		pnObjid,
		i_ItemCost_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_ItemCost_cur;
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

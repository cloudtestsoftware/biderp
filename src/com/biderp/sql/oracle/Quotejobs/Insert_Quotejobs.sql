CREATE OR REPLACE PROCEDURE Insert_Quotejobs(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_BidQuiz_cur IS Select
		Description,
		RequireCode,
		UmCode,
		UnitCount,
		Criteria,
		Point,
		QuizIndex,
		Name,
		moduser
		from table_BidQuiz
	 where objid=pnObjid 
	 and not exists (select *from table_BidQuiz where BidQuiz2Quotejobs=pnObjid);



--Please Modify the cursor as you need

CURSOR m_QuoteParts_cur IS Select
		Name,
		CoveredPrice,
		UnitCount,
		Note,
		moduser
		from table_QuoteParts
	 where objid=pnObjid 
	 and not exists (select *from table_QuoteParts where QuoteParts2Quotejobs=pnObjid);



--Please Modify the cursor as you need

CURSOR m_QuoteResource_cur IS Select
		Name,
		TaskCode,
		ResourceCode,
		EstUnit,
		moduser
		from table_QuoteResource
	 where objid=pnObjid 
	 and not exists (select *from table_QuoteResource where QuoteResource2Quotejobs=pnObjid);


--variables
id	NUMBER:=0;
i_BidQuiz_cur	m_BidQuiz_cur%rowtype;
i_QuoteParts_cur	m_QuoteParts_cur%rowtype;
i_QuoteResource_cur	m_QuoteResource_cur%rowtype;

BEGIN
--opening cursor m_BidQuiz_cur
	id:=0;
Begin
	OPEN m_BidQuiz_cur;
	LOOP
	id := id-1;
	FETCH m_BidQuiz_cur INTO i_BidQuiz_cur;
	EXIT WHEN m_BidQuiz_cur%NOTFOUND;

--Insert records in BidQuiz

	INSERT INTO table_BidQuiz(
		Description,
		RequireCode,
		UmCode,
		UnitCount,
		Criteria,
		Point,
		QuizIndex,
		Name,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_BidQuiz_cur.Description,
		i_BidQuiz_cur.RequireCode,
		i_BidQuiz_cur.UmCode,
		i_BidQuiz_cur.UnitCount,
		i_BidQuiz_cur.Criteria,
		i_BidQuiz_cur.Point,
		i_BidQuiz_cur.QuizIndex,
		i_BidQuiz_cur.Name,
		id,
		pnObjid,
		i_BidQuiz_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_BidQuiz_cur;
 End;
--opening cursor m_QuoteParts_cur
	id:=0;
Begin
	OPEN m_QuoteParts_cur;
	LOOP
	id := id-1;
	FETCH m_QuoteParts_cur INTO i_QuoteParts_cur;
	EXIT WHEN m_QuoteParts_cur%NOTFOUND;

--Insert records in QuoteParts

	INSERT INTO table_QuoteParts(
		Name,
		CoveredPrice,
		UnitCount,
		Note,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_QuoteParts_cur.Name,
		i_QuoteParts_cur.CoveredPrice,
		i_QuoteParts_cur.UnitCount,
		i_QuoteParts_cur.Note,
		id,
		pnObjid,
		i_QuoteParts_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_QuoteParts_cur;
 End;
--opening cursor m_QuoteResource_cur
	id:=0;
Begin
	OPEN m_QuoteResource_cur;
	LOOP
	id := id-1;
	FETCH m_QuoteResource_cur INTO i_QuoteResource_cur;
	EXIT WHEN m_QuoteResource_cur%NOTFOUND;

--Insert records in QuoteResource

	INSERT INTO table_QuoteResource(
		Name,
		TaskCode,
		ResourceCode,
		EstUnit,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_QuoteResource_cur.Name,
		i_QuoteResource_cur.TaskCode,
		i_QuoteResource_cur.ResourceCode,
		i_QuoteResource_cur.EstUnit,
		id,
		pnObjid,
		i_QuoteResource_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_QuoteResource_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

CREATE OR REPLACE PROCEDURE Insert_Participants(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_Evaluation_cur IS Select
		Name,
		Keyword,
		KeyObjective,
		Description,
		Achievement,
		Weightage,
		Remarks,
		PerformCode,
		moduser
		from table_Evaluation
	 where objid=pnObjid 
	 and not exists (select *from table_Evaluation where Evaluation2Participants=pnObjid);


--variables
id	NUMBER:=0;
i_Evaluation_cur	m_Evaluation_cur%rowtype;

BEGIN
--opening cursor m_Evaluation_cur
	id:=0;
Begin
	OPEN m_Evaluation_cur;
	LOOP
	id := id-1;
	FETCH m_Evaluation_cur INTO i_Evaluation_cur;
	EXIT WHEN m_Evaluation_cur%NOTFOUND;

--Insert records in Evaluation

	INSERT INTO table_Evaluation(
		Name,
		Keyword,
		KeyObjective,
		Description,
		Achievement,
		Weightage,
		Remarks,
		PerformCode,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_Evaluation_cur.Name,
		i_Evaluation_cur.Keyword,
		i_Evaluation_cur.KeyObjective,
		i_Evaluation_cur.Description,
		i_Evaluation_cur.Achievement,
		i_Evaluation_cur.Weightage,
		i_Evaluation_cur.Remarks,
		i_Evaluation_cur.PerformCode,
		id,
		pnObjid,
		i_Evaluation_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Evaluation_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

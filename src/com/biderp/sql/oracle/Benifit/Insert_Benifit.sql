CREATE OR REPLACE PROCEDURE Insert_Benifit(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_BenifitChange_cur IS Select
		Name,
		BenifitCode,
		OldMonthlyCost,
		MonthlyCost,
		OldAnnualCost,
		AnnualCost,
		StartDate,
		Note,
		moduser
		from table_BenifitChange
	 where objid=pnObjid 
	 and not exists (select *from table_BenifitChange where BenifitChange2Benifit=pnObjid);


--variables
id	NUMBER:=0;
i_BenifitChange_cur	m_BenifitChange_cur%rowtype;

BEGIN
--opening cursor m_BenifitChange_cur
	id:=0;
Begin
	OPEN m_BenifitChange_cur;
	LOOP
	id := id-1;
	FETCH m_BenifitChange_cur INTO i_BenifitChange_cur;
	EXIT WHEN m_BenifitChange_cur%NOTFOUND;

--Insert records in BenifitChange

	INSERT INTO table_BenifitChange(
		Name,
		BenifitCode,
		OldMonthlyCost,
		MonthlyCost,
		OldAnnualCost,
		AnnualCost,
		StartDate,
		Note,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_BenifitChange_cur.Name,
		i_BenifitChange_cur.BenifitCode,
		i_BenifitChange_cur.OldMonthlyCost,
		i_BenifitChange_cur.MonthlyCost,
		i_BenifitChange_cur.OldAnnualCost,
		i_BenifitChange_cur.AnnualCost,
		i_BenifitChange_cur.StartDate,
		i_BenifitChange_cur.Note,
		id,
		pnObjid,
		i_BenifitChange_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_BenifitChange_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

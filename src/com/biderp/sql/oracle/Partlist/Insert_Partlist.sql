CREATE OR REPLACE PROCEDURE Insert_Partlist(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_PartPrice_cur IS Select
		Name,
		DomainCode,
		ServiceLife,
		UmCode,
		UnitPrice,
		PctTax,
		PartSpec,
		Note,
		PartNo,
		moduser
		from table_PartPrice
	 where objid=pnObjid 
	 and not exists (select *from table_PartPrice where PartPrice2Partlist=pnObjid);


--variables
id	NUMBER:=0;
i_PartPrice_cur	m_PartPrice_cur%rowtype;

BEGIN
--opening cursor m_PartPrice_cur
	id:=0;
Begin
	OPEN m_PartPrice_cur;
	LOOP
	id := id-1;
	FETCH m_PartPrice_cur INTO i_PartPrice_cur;
	EXIT WHEN m_PartPrice_cur%NOTFOUND;

--Insert records in PartPrice

	INSERT INTO table_PartPrice(
		Name,
		DomainCode,
		ServiceLife,
		UmCode,
		UnitPrice,
		PctTax,
		PartSpec,
		Note,
		PartNo,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_PartPrice_cur.Name,
		i_PartPrice_cur.DomainCode,
		i_PartPrice_cur.ServiceLife,
		i_PartPrice_cur.UmCode,
		i_PartPrice_cur.UnitPrice,
		i_PartPrice_cur.PctTax,
		i_PartPrice_cur.PartSpec,
		i_PartPrice_cur.Note,
		i_PartPrice_cur.PartNo,
		id,
		pnObjid,
		i_PartPrice_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_PartPrice_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

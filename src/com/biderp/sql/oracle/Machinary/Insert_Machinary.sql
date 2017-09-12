CREATE OR REPLACE PROCEDURE Insert_Machinary(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_AssetPlan_cur IS Select
		Name,
		TagNo,
		PrivLocation,
		CurLocation,
		Note,
		Status,
		AvailDate,
		StartDate,
		EndDate,
		moduser
		from table_AssetPlan
	 where objid=pnObjid 
	 and not exists (select *from table_AssetPlan where AssetPlan2Machinary=pnObjid);


--variables
id	NUMBER:=0;
i_AssetPlan_cur	m_AssetPlan_cur%rowtype;

BEGIN
--opening cursor m_AssetPlan_cur
	id:=0;
Begin
	OPEN m_AssetPlan_cur;
	LOOP
	id := id-1;
	FETCH m_AssetPlan_cur INTO i_AssetPlan_cur;
	EXIT WHEN m_AssetPlan_cur%NOTFOUND;

--Insert records in AssetPlan

	INSERT INTO table_AssetPlan(
		Name,
		TagNo,
		PrivLocation,
		CurLocation,
		Note,
		Status,
		AvailDate,
		StartDate,
		EndDate,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_AssetPlan_cur.Name,
		i_AssetPlan_cur.TagNo,
		i_AssetPlan_cur.PrivLocation,
		i_AssetPlan_cur.CurLocation,
		i_AssetPlan_cur.Note,
		i_AssetPlan_cur.Status,
		i_AssetPlan_cur.AvailDate,
		i_AssetPlan_cur.StartDate,
		i_AssetPlan_cur.EndDate,
		id,
		pnObjid,
		i_AssetPlan_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_AssetPlan_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

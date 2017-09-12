CREATE OR REPLACE PROCEDURE Insert_Employee(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_Contact_cur IS Select
		Name,
		LastName,
		Attention,
		Street,
		City,
		State,
		ZipCode,
		CountryCode,
		Phone,
		Phone2,
		Fax,
		Email,
		ContactCode,
		moduser
		from table_Contact
	 where objid=pnObjid 
	 and not exists (select *from table_Contact where Contact2Employee=pnObjid);



--Please Modify the cursor as you need

CURSOR m_Payrole_cur IS Select
		Name,
		StartDate,
		EndDate,
		PayDate,
		CheckNo,
		Status,
		moduser
		from table_Payrole
	 where objid=pnObjid 
	 and not exists (select *from table_Payrole where Payrole2Employee=pnObjid);



--Please Modify the cursor as you need

CURSOR m_Benifit_cur IS Select
		Name,
		BenifitCode,
		BenifitType,
		MonthlyCost,
		AnnualCost,
		StartDate,
		Note,
		moduser
		from table_Benifit
	 where objid=pnObjid 
	 and not exists (select *from table_Benifit where Benifit2Employee=pnObjid);



--Please Modify the cursor as you need

CURSOR m_Dependent_cur IS Select
		Name,
		LastName,
		DependentCode,
		Phone,
		SSN,
		BirthDate,
		Email,
		moduser
		from table_Dependent
	 where objid=pnObjid 
	 and not exists (select *from table_Dependent where Dependent2Employee=pnObjid);



--Please Modify the cursor as you need

CURSOR m_Deduction_cur IS Select
		Name,
		DeductionCode,
		TaxTypeCode,
		Amount,
		StartDate,
		EndDate,
		Note,
		moduser
		from table_Deduction
	 where objid=pnObjid 
	 and not exists (select *from table_Deduction where Deduction2Employee=pnObjid);



--Please Modify the cursor as you need

CURSOR m_Addition_cur IS Select
		Name,
		AdjustCode,
		TaxTypeCode,
		Amount,
		ApplyDate,
		Note,
		moduser
		from table_Addition
	 where objid=pnObjid 
	 and not exists (select *from table_Addition where Addition2Employee=pnObjid);



--Please Modify the cursor as you need

CURSOR m_Substruction_cur IS Select
		Name,
		AdjustCode,
		TaxTypeCode,
		Amount,
		ApplyDate,
		Note,
		moduser
		from table_Substruction
	 where objid=pnObjid 
	 and not exists (select *from table_Substruction where Substruction2Employee=pnObjid);



--Please Modify the cursor as you need

CURSOR m_Vacation_cur IS Select
		Name,
		YearCode,
		VacationCode,
		DayTaken,
		StartDate,
		EndDate,
		Note,
		moduser
		from table_Vacation
	 where objid=pnObjid 
	 and not exists (select *from table_Vacation where Vacation2Employee=pnObjid);



--Please Modify the cursor as you need

CURSOR m_EarnedVacation_cur IS Select
		Name,
		YearCode,
		VacationCode,
		IsCumulative,
		TotalMonth,
		DaysPerMonth,
		LastBalance,
		Note,
		moduser
		from table_EarnedVacation
	 where objid=pnObjid 
	 and not exists (select *from table_EarnedVacation where EarnedVacation2Employee=pnObjid);



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
	 and not exists (select *from table_BenifitChange where BenifitChange2Employee=pnObjid);



--Please Modify the cursor as you need

CURSOR m_Tax_cur IS Select
		Name,
		TaxCode,
		Pct,
		moduser
		from table_Tax
	 where objid=pnObjid 
	 and not exists (select *from table_Tax where Tax2Employee=pnObjid);


--variables
id	NUMBER:=0;
i_Contact_cur	m_Contact_cur%rowtype;
i_Payrole_cur	m_Payrole_cur%rowtype;
i_Benifit_cur	m_Benifit_cur%rowtype;
i_Dependent_cur	m_Dependent_cur%rowtype;
i_Deduction_cur	m_Deduction_cur%rowtype;
i_Addition_cur	m_Addition_cur%rowtype;
i_Substruction_cur	m_Substruction_cur%rowtype;
i_Vacation_cur	m_Vacation_cur%rowtype;
i_EarnedVacation_cur	m_EarnedVacation_cur%rowtype;
i_BenifitChange_cur	m_BenifitChange_cur%rowtype;
i_Tax_cur	m_Tax_cur%rowtype;

BEGIN
--opening cursor m_Contact_cur
	id:=0;
Begin
	OPEN m_Contact_cur;
	LOOP
	id := id-1;
	FETCH m_Contact_cur INTO i_Contact_cur;
	EXIT WHEN m_Contact_cur%NOTFOUND;

--Insert records in Contact

	INSERT INTO table_Contact(
		Name,
		LastName,
		Attention,
		Street,
		City,
		State,
		ZipCode,
		CountryCode,
		Phone,
		Phone2,
		Fax,
		Email,
		ContactCode,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_Contact_cur.Name,
		i_Contact_cur.LastName,
		i_Contact_cur.Attention,
		i_Contact_cur.Street,
		i_Contact_cur.City,
		i_Contact_cur.State,
		i_Contact_cur.ZipCode,
		i_Contact_cur.CountryCode,
		i_Contact_cur.Phone,
		i_Contact_cur.Phone2,
		i_Contact_cur.Fax,
		i_Contact_cur.Email,
		i_Contact_cur.ContactCode,
		id,
		pnObjid,
		i_Contact_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Contact_cur;
 End;
--opening cursor m_Payrole_cur
	id:=0;
Begin
	OPEN m_Payrole_cur;
	LOOP
	id := id-1;
	FETCH m_Payrole_cur INTO i_Payrole_cur;
	EXIT WHEN m_Payrole_cur%NOTFOUND;

--Insert records in Payrole

	INSERT INTO table_Payrole(
		Name,
		StartDate,
		EndDate,
		PayDate,
		CheckNo,
		Status,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_Payrole_cur.Name,
		i_Payrole_cur.StartDate,
		i_Payrole_cur.EndDate,
		i_Payrole_cur.PayDate,
		i_Payrole_cur.CheckNo,
		i_Payrole_cur.Status,
		id,
		pnObjid,
		i_Payrole_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Payrole_cur;
 End;
--opening cursor m_Benifit_cur
	id:=0;
Begin
	OPEN m_Benifit_cur;
	LOOP
	id := id-1;
	FETCH m_Benifit_cur INTO i_Benifit_cur;
	EXIT WHEN m_Benifit_cur%NOTFOUND;

--Insert records in Benifit

	INSERT INTO table_Benifit(
		Name,
		BenifitCode,
		BenifitType,
		MonthlyCost,
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
		i_Benifit_cur.Name,
		i_Benifit_cur.BenifitCode,
		i_Benifit_cur.BenifitType,
		i_Benifit_cur.MonthlyCost,
		i_Benifit_cur.AnnualCost,
		i_Benifit_cur.StartDate,
		i_Benifit_cur.Note,
		id,
		pnObjid,
		i_Benifit_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Benifit_cur;
 End;
--opening cursor m_Dependent_cur
	id:=0;
Begin
	OPEN m_Dependent_cur;
	LOOP
	id := id-1;
	FETCH m_Dependent_cur INTO i_Dependent_cur;
	EXIT WHEN m_Dependent_cur%NOTFOUND;

--Insert records in Dependent

	INSERT INTO table_Dependent(
		Name,
		LastName,
		DependentCode,
		Phone,
		SSN,
		BirthDate,
		Email,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_Dependent_cur.Name,
		i_Dependent_cur.LastName,
		i_Dependent_cur.DependentCode,
		i_Dependent_cur.Phone,
		i_Dependent_cur.SSN,
		i_Dependent_cur.BirthDate,
		i_Dependent_cur.Email,
		id,
		pnObjid,
		i_Dependent_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Dependent_cur;
 End;
--opening cursor m_Deduction_cur
	id:=0;
Begin
	OPEN m_Deduction_cur;
	LOOP
	id := id-1;
	FETCH m_Deduction_cur INTO i_Deduction_cur;
	EXIT WHEN m_Deduction_cur%NOTFOUND;

--Insert records in Deduction

	INSERT INTO table_Deduction(
		Name,
		DeductionCode,
		TaxTypeCode,
		Amount,
		StartDate,
		EndDate,
		Note,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_Deduction_cur.Name,
		i_Deduction_cur.DeductionCode,
		i_Deduction_cur.TaxTypeCode,
		i_Deduction_cur.Amount,
		i_Deduction_cur.StartDate,
		i_Deduction_cur.EndDate,
		i_Deduction_cur.Note,
		id,
		pnObjid,
		i_Deduction_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Deduction_cur;
 End;
--opening cursor m_Addition_cur
	id:=0;
Begin
	OPEN m_Addition_cur;
	LOOP
	id := id-1;
	FETCH m_Addition_cur INTO i_Addition_cur;
	EXIT WHEN m_Addition_cur%NOTFOUND;

--Insert records in Addition

	INSERT INTO table_Addition(
		Name,
		AdjustCode,
		TaxTypeCode,
		Amount,
		ApplyDate,
		Note,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_Addition_cur.Name,
		i_Addition_cur.AdjustCode,
		i_Addition_cur.TaxTypeCode,
		i_Addition_cur.Amount,
		i_Addition_cur.ApplyDate,
		i_Addition_cur.Note,
		id,
		pnObjid,
		i_Addition_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Addition_cur;
 End;
--opening cursor m_Substruction_cur
	id:=0;
Begin
	OPEN m_Substruction_cur;
	LOOP
	id := id-1;
	FETCH m_Substruction_cur INTO i_Substruction_cur;
	EXIT WHEN m_Substruction_cur%NOTFOUND;

--Insert records in Substruction

	INSERT INTO table_Substruction(
		Name,
		AdjustCode,
		TaxTypeCode,
		Amount,
		ApplyDate,
		Note,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_Substruction_cur.Name,
		i_Substruction_cur.AdjustCode,
		i_Substruction_cur.TaxTypeCode,
		i_Substruction_cur.Amount,
		i_Substruction_cur.ApplyDate,
		i_Substruction_cur.Note,
		id,
		pnObjid,
		i_Substruction_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Substruction_cur;
 End;
--opening cursor m_Vacation_cur
	id:=0;
Begin
	OPEN m_Vacation_cur;
	LOOP
	id := id-1;
	FETCH m_Vacation_cur INTO i_Vacation_cur;
	EXIT WHEN m_Vacation_cur%NOTFOUND;

--Insert records in Vacation

	INSERT INTO table_Vacation(
		Name,
		YearCode,
		VacationCode,
		DayTaken,
		StartDate,
		EndDate,
		Note,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_Vacation_cur.Name,
		i_Vacation_cur.YearCode,
		i_Vacation_cur.VacationCode,
		i_Vacation_cur.DayTaken,
		i_Vacation_cur.StartDate,
		i_Vacation_cur.EndDate,
		i_Vacation_cur.Note,
		id,
		pnObjid,
		i_Vacation_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Vacation_cur;
 End;
--opening cursor m_EarnedVacation_cur
	id:=0;
Begin
	OPEN m_EarnedVacation_cur;
	LOOP
	id := id-1;
	FETCH m_EarnedVacation_cur INTO i_EarnedVacation_cur;
	EXIT WHEN m_EarnedVacation_cur%NOTFOUND;

--Insert records in EarnedVacation

	INSERT INTO table_EarnedVacation(
		Name,
		YearCode,
		VacationCode,
		IsCumulative,
		TotalMonth,
		DaysPerMonth,
		LastBalance,
		Note,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_EarnedVacation_cur.Name,
		i_EarnedVacation_cur.YearCode,
		i_EarnedVacation_cur.VacationCode,
		i_EarnedVacation_cur.IsCumulative,
		i_EarnedVacation_cur.TotalMonth,
		i_EarnedVacation_cur.DaysPerMonth,
		i_EarnedVacation_cur.LastBalance,
		i_EarnedVacation_cur.Note,
		id,
		pnObjid,
		i_EarnedVacation_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_EarnedVacation_cur;
 End;
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
--opening cursor m_Tax_cur
	id:=0;
Begin
	OPEN m_Tax_cur;
	LOOP
	id := id-1;
	FETCH m_Tax_cur INTO i_Tax_cur;
	EXIT WHEN m_Tax_cur%NOTFOUND;

--Insert records in Tax

	INSERT INTO table_Tax(
		Name,
		TaxCode,
		Pct,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		i_Tax_cur.Name,
		i_Tax_cur.TaxCode,
		i_Tax_cur.Pct,
		id,
		pnObjid,
		i_Tax_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Tax_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

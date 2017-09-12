CREATE OR REPLACE PROCEDURE Update_Employee(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Employee set Name=Name,
		LastName=LastName,
		Street=Street,
		City=City,
		State=State,
		ZipCode=ZipCode,
		CountryCode=CountryCode,
		DeptCode=DeptCode,
		JobCode=JobCode,
		EmpCode=EmpCode,
		EvalCode=EvalCode,
		ReportTo=ReportTo,
		Phone=Phone,
		Phone2=Phone2,
		SSN=SSN,
		Email=Email,
		BirthDate=BirthDate,
		JoinDate=JoinDate,
		LastDate=LastDate,
		Status=Status,
		PayroleHeader=PayroleHeader,
		moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for Contact
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Contact set Contact2Employee=pnObjid where Contact2Employee=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for Payrole
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Payrole set Payrole2Employee=pnObjid where Payrole2Employee=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for Benifit
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Benifit set Benifit2Employee=pnObjid where Benifit2Employee=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for Dependent
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Dependent set Dependent2Employee=pnObjid where Dependent2Employee=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for Deduction
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Deduction set Deduction2Employee=pnObjid where Deduction2Employee=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for Addition
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Addition set Addition2Employee=pnObjid where Addition2Employee=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for Substruction
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Substruction set Substruction2Employee=pnObjid where Substruction2Employee=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for Vacation
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Vacation set Vacation2Employee=pnObjid where Vacation2Employee=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for EarnedVacation
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_EarnedVacation set EarnedVacation2Employee=pnObjid where EarnedVacation2Employee=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for BenifitChange
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_BenifitChange set BenifitChange2Employee=pnObjid where BenifitChange2Employee=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for Tax
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Tax set Tax2Employee=pnObjid where Tax2Employee=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

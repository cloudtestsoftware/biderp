CREATE OR REPLACE PROCEDURE Update_Projection(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Projection set Name=Name,
		YearCode=YearCode,
		ProjectedAmount=ProjectedAmount,
		InvoicedAmount=InvoicedAmount,
		BalanceAmount=BalanceAmount,
		moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for MonthlyRevenue
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_MonthlyRevenue set MonthlyRevenue2Projection=pnObjid where MonthlyRevenue2Projection=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

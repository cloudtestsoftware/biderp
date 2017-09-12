CREATE OR REPLACE PROCEDURE Update_Quote(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Quote set Name=Name,
		ProjectCode=ProjectCode,
		Description=Description,
		EstAmount=EstAmount,
		BgtRangeCode=BgtRangeCode,
		QuoteType=QuoteType,
		StartDate=StartDate,
		Address=Address,
		City=City,
		ZipCode=ZipCode,
		State=State,
		FirstName=FirstName,
		LastName=LastName,
		Phone=Phone,
		Phone2=Phone2,
		Fax=Fax,
		Email=Email,
		OtherContact=OtherContact,
		OtherInfo=OtherInfo,
		LeadSourceCode=LeadSourceCode,
		CurrencyCode=CurrencyCode,
		PctWaste=PctWaste,
		PctProfit=PctProfit,
		Status=Status,
		BidQuoteNo=BidQuoteNo,
		moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for QuoteMaster
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_QuoteMaster set QuoteMaster2Quote=pnObjid where QuoteMaster2Quote=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for QuoteLog
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_QuoteLog set QuoteLog2Quote=pnObjid where QuoteLog2Quote=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

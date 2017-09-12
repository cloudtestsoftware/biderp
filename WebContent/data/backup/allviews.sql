--------------------------------------------------------
--  File created - Tuesday-July-04-2017   
--------------------------------------------------------
-- Unable to render VIEW DDL for object ERP.TABLE_ACCOUNTTOTAL with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_ACCOUNTTOTAL
AS select distinct a.name Name,a.objid AccountNo,sum(nvl(t.credited,0))-sum(nvl(t.debited,0)) AccountTotal,a.accountgroup,b.objid AccountTotal2BalanceSheet from table_account a,table_transaction t,table_balancesheet b where a.objid=t.transaction2account(+) and t.postdate between b.periodstart and b.periodend group by a.name,a.objid,a.accountgroup,b.objid
-- Unable to render VIEW DDL for object ERP.TABLE_ADDEQUIPMENT with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_ADDEQUIPMENT
AS select distinct p.name Name,p.umcode UmCode,p.partno PartNo,p.unitprice UnitPrice,pl.DomainCode DomainCode,p.objid objid,p.groupuser,p.objid AddEquipment2PartPrice from table_partprice p,table_partlist pl where p.partprice2partlist=pl.objid and pl.domaincode=3
-- Unable to render VIEW DDL for object ERP.TABLE_ADDITIONNONTAXED with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_ADDITIONNONTAXED
AS select distinct sum(nvl(a.amount,0)) NonTaxedAmount,p.objid  AdditionNonTaxed2Payrole from table_Payrole p,table_addition a where p.payrole2employee=a.addition2employee(+) and a.taxtypecode(+)=1 and nvl(a.applydate,p.startdate+1) between p.startdate and p.enddate  group by p.objid
-- Unable to render VIEW DDL for object ERP.TABLE_ADDITIONTAXED with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_ADDITIONTAXED
AS select distinct sum(nvl(a.amount,0)) TaxedAmount,p.objid  AdditionTaxed2Payrole from table_Payrole p,table_addition a where p.payrole2employee=a.addition2employee(+) and a.taxtypecode(+)=2 and nvl(a.applydate,p.startdate+1) between p.startdate and p.enddate  group by p.objid
-- Unable to render VIEW DDL for object ERP.TABLE_ALLBIDS with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_ALLBIDS
AS select distinct p.name Name,p.Description Description,p.Criteria Criteria,p.ListDate ListDate,p.closedate CloseDate,nvl(p.BgtRangeCode,0) BgtRangeCode,p.StartCode StartCode,l.ResponseCount ResponseCount,l.BidCount BidCount,p.ZipCode ZipCode,p.address Address,p.state State,p.Instruction Instruction,p.objid ObjId,p.objid AllBids2BidRequest, p.gendate,p.genuser,p.groupuser from table_ProjectLead p,table_leadresult l where  p.objid=l.LeadResult2bidrequest(+) order by p.gendate desc
-- Unable to render VIEW DDL for object ERP.TABLE_APPROVAL with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_APPROVAL
AS select distinct bt.objid ObjId,b.name Name,b.title Title,b.projectcode ProjectCode,m.startdate StartDate,b.monthcode MonthCode,b.yearcode YearCode,b.quantity Quantity,b.quantity * bq.rate EstAmount,b.status Status,m.objid Approval2Monthly,b.groupuser from table_monthly m, table_milestone ms, table_Boq bq, table_BoqPlan b,table_budget bt, table_projectplan p where  b.monthcode=m.monthcode and b.yearcode=m.yearcode  and b.projectcode=m.projectcode and b.status=2 and bq.objid=ms.milestone2Boq and ms.milestone2projectplan=p.objid  and p.projectplan2project=bt.budget2project and bt.objid=m.monthly2budget and ms.objid=b.Boqplan2milestone order by bt.objid
-- Unable to render VIEW DDL for object ERP.TABLE_ASSETOWN with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_ASSETOWN
AS select distinct a.Name Name,a.Description Description,nvl(a.PurchaseCost,0) PurchaseCost,nvl(a.AssetValue,0) CurrentValue,a.groupuser,a.objid, a.objid AssetOwn2Asset from table_Asset a where a.ownercode='1' order by a.name
-- Unable to render VIEW DDL for object ERP.TABLE_ASSETSERVICE with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_ASSETSERVICE
AS select distinct m.name Name,m.description Description,sum(nvl(p.partcost,0)) PartCost,sum(nvl(l.partcost,0)) LaborCost,sum(nvl(p.partcost,0))+sum(nvl(l.partcost,0)) TotalCost,to_char(m.servicedate,'YYYY') Year,m.maintenance2Asset Assetid,m.objid,m.objid AssetService2Maintenance, m.groupuser,m.genuser from table_ServiceLabor l,table_ServicePartCost p,table_maintenance m where m.objid=l.ServiceLabor2Maintenance and l.ServiceLabor2Maintenance=p.ServicePartCost2Maintenance(+) and m.category=1 group by m.objid,m.name,m.servicedate,m.maintenance2Asset,m.description,m.groupuser,m.genuser
-- Unable to render VIEW DDL for object ERP.TABLE_ASSETTOTAL with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_ASSETTOTAL
AS select distinct g.name ||'- Asset' Name,nvl(sum(a.assetvalue),0) TotalValue,b.groupuser,a.accountgroup,b.objid AssetTotal2BalanceSheet from table_BalanceSheet b,table_asset a,table_genericcode g where a.groupuser=b.groupuser and a.ownercode=1 and a.status=1 and g.attributename='AccountGroup' and g.codevalue=a.accountgroup group by g.name||'- Asset', b.objid,b.groupuser,a.accountgroup
-- Unable to render VIEW DDL for object ERP.TABLE_BALANCE with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_BALANCE
AS select distinct a.name Name,a.objid AccountNo,sum(nvl(t.credited,0))-sum(nvl(t.debited,0)) AccountBalance,a.objid objid,a.objid Balance2Account from table_account a,table_transaction t where a.objid=t.transaction2account(+) group by a.name,a.objid
-- Unable to render VIEW DDL for object ERP.TABLE_BALANCEDETAILS with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_BALANCEDETAILS
AS select distinct a.name Name,a.accountgroup AccountGroup,decode(to_number(a.accountgroup),1, a.accounttotal,2,a.accounttotal,5,a.accounttotal,0-a.accounttotal) Amount,a.accountno objid,a.accounttotal2balancesheet BalanceDetails2BalanceSheet from table_accounttotal a where length(a.accounttotal2balancesheet)>0 union select e.name,e.accountgroup,e.totalvalue amount,e.equipmenttotal2balancesheet objid,e.equipmenttotal2balancesheet BalanceDetails2BalanceSheet from table_equipmenttotal e union select s.name,s.accountgroup,s.totalvalue amount,s.assettotal2balancesheet objid,s.assettotal2balancesheet BalanceDetails2BalanceSheet from table_assettotal s union select d.name,d.accountgroup,d.depricatedcost amount,d.objid objid,d.objid BalanceDetails2BalanceSheet from table_depricatedcost d order by 2
-- Unable to render VIEW DDL for object ERP.TABLE_BIDARTIFACTS with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_BIDARTIFACTS
AS select distinct m.name Name,m.PostingDate PostingDate,m.Description Description,m.Priority Priority,m.FormsCode FormsCode,m.Url Url,m.objid objid,v.objid BidArtifacts2VendorBid from table_artifacts m,table_vendorbid v where v.destinitionid=m.artifacts2bidrequest
-- Unable to render VIEW DDL for object ERP.TABLE_BIDCONTACT with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_BIDCONTACT
AS select distinct p.firstname FirstName,p.lastname LastName,p.company Company,p.url Url,p.street Street,p.City City,p.state State,p.CountryCode CountryCode,p.phone Phone,p.phone2 Phone2,p.fax Fax,p.name Email,br.objid BidRequest2BidContact from table_vendorbid v,table_profile p,table_bidrequest br where p.name=v.genuser and v.destinitionid=br.objid
-- Unable to render VIEW DDL for object ERP.TABLE_BIDCOUNT with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_BIDCOUNT
AS select distinct br.name Name,count(v.objid) ResponseCount,count(bd.objid) BidCount,br.objid BidCount2BidRequest from table_bids bd,table_vendorbid v,table_bidrequest br where br.objid=v.destinitionid and v.objid=bd.objid(+) group by br.name,br.objid
-- Unable to render VIEW DDL for object ERP.TABLE_BIDHIGHLOW with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_BIDHIGHLOW
AS select distinct br.name Name,max(b.TotalBid) HighBidTotal,min(b.TotalBid) LowBidTotal,nvl(w.amount,0) WinBid,br.objid BidHighLow2BidRequest from table_bidtotal b,table_winner w,table_bidrequest br where br.objid=b.bidrequestid and br.objid=w.winner2bidrequest(+) group by br.name,br.objid,w.amount
-- Unable to render VIEW DDL for object ERP.TABLE_BIDLOST with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_BIDLOST
AS select distinct v.name Name,b.name LeadName,b.objid LeadNo,b.Description Description,t.TotalBid MinBidTotal,w.amount WinningAmount,b.objid bidrequestid,v.objid,v.genuser from table_bidtotal t,table_bidrequest b,table_winner w, table_vendorbid v where v.genuser<>w.name and v.destinitionid=w.winner2bidrequest and t.itemprice2vendorbid=v.objid and t.bidrequestid=b.objid and w.winner2bidrequest=b.objid and t.itemprice2vendorbid not in (select o.objid from table_bidwon o where o.bidrequestid=b.objid)
-- Unable to render VIEW DDL for object ERP.TABLE_BIDMAX with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_BIDMAX
AS select distinct br.name Name,max(b.TotalBid) MaxBidAmount,b.bidrequestid from table_bidtotal b,table_bidrequest br where br.objid=b.bidrequestid group by br.name,b.bidrequestid
-- Unable to render VIEW DDL for object ERP.TABLE_BIDMIN with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_BIDMIN
AS select distinct br.name Name,min(b.TotalBid) MinBidTotal,b.bidrequestid from table_bidtotal b,table_bidrequest br where br.objid=b.bidrequestid group by br.name,b.bidrequestid
-- Unable to render VIEW DDL for object ERP.TABLE_BIDS with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_BIDS
AS select distinct u.name Name,u.lastname LastName,v.email Email,v.phone Phone,nvl(v.url,0) Url,t.totalbid BidAmount,q.totalpoint TotalPoint,q.totalearned TotalEarned,b.objid Bids2BidRequest,u.objid profileid,v.objid,v.genuser,b.groupuser from table_bidtotal t,table_user u,table_bidrequest b, table_vendorbid v,table_quiztotal q where v.genuser=u.loginname and v.destinitionid=b.objid and t.itemprice2vendorbid=v.objid and v.objid=q.quiztotal2bids(+)
-- Unable to render VIEW DDL for object ERP.TABLE_BIDTOTAL with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_BIDTOTAL
AS select distinct br.name Name,u.loginname BidderLogin,sum(nvl(i.unitprice,0)*nvl(j.qntrequest,0)) TotalBid,br.objid bidrequestid,u.objid profileid,u.loginname,i.itemprice2vendorbid from table_BidRequest br,table_partbid j, table_itemprice i,table_user u where br.objid=j.partbid2bidrequest and j.objid=i.itemprice2partbid and i.genuser=u.loginname group by br.name,u.loginname,br.objid,u.objid,u.loginname,i.itemprice2vendorbid
-- Unable to render VIEW DDL for object ERP.TABLE_BIDWON with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_BIDWON
AS select distinct v.name Name,b.name LeadName,b.objid LeadNo,b.Description Description,w.winmessage Message,m.minbidtotal MinBidTotal,w.amount ContractAmount,w.OwnerEmail OwnerEmail,w.Phone Phone,b.objid bidrequestid,p.objid profileid,v.objid,v.objid BidWon2VendorBid,v.genuser from table_bidmin m,table_profile p,table_bidrequest b,table_winner w, table_vendorbid v where v.genuser=p.name and v.destinitionid=b.objid and m.bidrequestid=b.objid and w.winner2bidrequest=b.objid and ltrim(rtrim(w.name))=ltrim(rtrim(v.bidderlogin))
-- Unable to render VIEW DDL for object ERP.TABLE_BIWEEKLYBID with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_BIWEEKLYBID
AS select distinct p.name Name,p.Description Description,p.Criteria Criteria,p.ListDate ListDate,p.closedate CloseDate,nvl(p.BgtRangeCode,0) BgtRangeCode,p.StartCode StartCode,l.ResponseCount ResponseCount,l.BidCount BidCount,p.ZipCode ZipCode,p.address Address,p.state State,p.Instruction Instruction,p.objid ObjId,p.objid BiWeeklyBid2BidRequest, p.gendate,p.genuser,p.groupuser from table_ProjectLead p,table_leadresult l where p.gendate<sysdate and p.gendate>sysdate-16 and p.objid=l.LeadResult2bidrequest(+) order by p.gendate desc
-- Unable to render VIEW DDL for object ERP.TABLE_BOM with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_BOM
AS select distinct pr.name Name,pu.deptcode||pu.pocode||p.objid||pt.parts2partprice MrNo,p.title Title,pr.partno PartNo,pr.umcode UmCode,pu.deptcode DeptCode,decode(pu.pocode,1,'Owner','Sub Contractor') PoCode,sum(nvl(pu.QntRequest,0)) QntRequest,sum(nvl(pu.qntused,0)) QntUsed,sum(nvl(pu.qntbalance,0)) QntBalance,sum(nvl(pu.QntToPurchase,0)) QntToPurchase,sum(pu.linecount) LineCount,min(pu.startdate) StartDate,p.objid Bom2Project,pt.parts2partprice objid,pt.parts2partprice Bom2PartPrice,pt.groupuser from table_project p,table_Boqplan b,table_milestone m,table_projectplan pp,table_partusebom pu,table_parts pt,table_partprice pr where p.objid=pp.projectplan2project and pp.objid=m.milestone2projectplan and m.objid=b.Boqplan2milestone and b.Boqplan2Boq=pt.parts2Boq and pt.parts2partprice=pu.PartUseBom2PartPrice and pr.objid=pu.PartUseBom2PartPrice group by p.title,pr.name, pr.partno, pr.umcode, pt.parts2partprice,pu.deptcode,pu.pocode,decode(pu.pocode,1,'Owner','Sub Contractor'),p.objid, pt.groupuser
-- Unable to render VIEW DDL for object ERP.TABLE_BOMPURCHASE with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_BOMPURCHASE
AS select distinct b.name Name,b.MrNo MrNo,b.title Title,b.partno PartNo,b.umcode UmCode,b.deptcode DeptCode,b.pocode PoCode,sum(nvl(b.QntRequest,0)) QntRequest,sum(nvl(b.qntused,0)) QntUsed,sum(nvl(b.qntbalance,0)) QntBalance,sum(nvl(b.QntToPurchase,0)) QntToPurchase,sum(b.linecount) LineCount,min(to_char(b.startdate,'mm-dd-yyyy')) StartDate,b.Bom2Project BomPurchase2Project,po.objid BomPurchase2PoMaster,b.objid,b.objid BomPurchase2PartPrice,b.groupuser from table_pomaster po,table_bom b where b.bom2project=po.pomaster2project and po.pocode=decode(b.pocode,'Owner',1,2) and po.itemcode=1 group by b.name, b.MrNo, b.title, b.partno, b.umcode, b.deptcode, b.pocode, b.Bom2Project, po.objid, b.objid,b.Bom2Project,b.groupuser
-- Unable to render VIEW DDL for object ERP.TABLE_BOQCHANGECOST with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_BOQCHANGECOST
AS select distinct sum(tc.estchangecost) EstChangeCost,sum(tc.actchangecost) ActChangeCost,cg.change2Boq  BoqChangeCost2Boq from table_totalchangecost tc,table_Boq b,table_change cg where b.objid=cg.change2Boq and cg.objid=tc.totalchangecost2change(+) group by cg.change2Boq
-- Unable to render VIEW DDL for object ERP.TABLE_BOQPART with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_BOQPART
AS select distinct pp.name Name,pp.partgroup PartGroup,pp.ServiceLife ServiceLife,pl.domaincode DomainCode,pl.partcode PartCode,pp.umcode UmCode,pp.unitprice UnitPrice,pp.PartSpec PartSpec,pp.note Note,pp.partno PartNo,pp.objid,pp.objid BoqPart2PartList, pp.groupuser,pp.genuser from table_partprice pp,table_partlist pl where pp.partprice2partlist=pl.objid and to_number(pl.domaincode)<3
-- Unable to render VIEW DDL for object ERP.TABLE_BOQPARTCOST with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_BOQPARTCOST
AS select distinct sum(p.EstPartCost) EstPartCost,sum(p.ActPartCost) ActPartCost,b.objid BoqPartCost2Boq from table_partcost p,table_Boq b where b.objid=p.partcost2Boq(+) group by b.objid
-- Unable to render VIEW DDL for object ERP.TABLE_BOQQNT with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_BOQQNT
AS select distinct b.qntcontract qntcontract,nvl(sum(c.qntchange),0) TotQntChange,b.qntcontract+nvl(sum(c.qntchange),0) NetQnt,b.objid  BoqQnt2Boq from table_change c, table_Boq b where b.objid=c.change2Boq(+) group by b.qntcontract,b.objid
-- Unable to render VIEW DDL for object ERP.TABLE_BOQRATE with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_BOQRATE
AS select distinct round(nvl(tb.estBoqcost,0)/decode(bq.netqnt,0,1,bq.netqnt),2) EstBoqRate,round(nvl(tb.actBoqcost,0)/decode(bq.netqnt,0,1,bq.netqnt),2) ActBoqRate,nvl(b.rate,0)-round((nvl(tb.actBoqcost,0)/decode(bq.netqnt,0,1,bq.netqnt)),2) ContractToActRate,nvl(b.rate,0)-round((nvl(tb.estBoqcost,0)/decode(bq.netqnt,0,1,bq.netqnt)),2) ContractToEstRate,bq.qntcontract QntContract,bq.totqntchange QntChange,bq.netqnt netQnt,b.objid  BoqRate2Boq from table_totalBoqcost tb,table_Boqqnt bq,table_Boq b where b.objid=tb.totalBoqcost2Boq(+) and b.objid=bq.Boqqnt2Boq
-- Unable to render VIEW DDL for object ERP.TABLE_BOQRESOURCECOST with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_BOQRESOURCECOST
AS select distinct sum(r.EstResCost) EstResCost,sum(r.ActResCost) ActResCost,b.objid BoqResourceCost2Boq from table_resourcecost r, table_Boq b where b.objid=r.resourcecost2Boq(+) group by b.objid
-- Unable to render VIEW DDL for object ERP.TABLE_BUDGETESTTOTAL with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_BUDGETESTTOTAL
AS select distinct nvl(sum(j.contractamount),0) TotalContract,nvl(sum(j.estjobcost),0) TotalEstAmount,nvl(sum(j.actjobcost),0) CumulativeAmount,nvl(sum(j.contractamount),0)-nvl(sum(j.actjobcost),0) ContToCumulative,b.budget2project BudgetEstTotal2Project,b.objid  BudgetEstTotal2Budget from table_jobcost j,table_budget b,table_estimation e where b.budget2project=e.estimation2project and e.objid=j.jobcost2estimation group by b.objid,b.budget2project
-- Unable to render VIEW DDL for object ERP.TABLE_BUDGETFUNDRELEASED with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_BUDGETFUNDRELEASED
AS select distinct nvl(sum(f.fundreleased),0) FundReleased,nvl(sum(j.totalwithtax),0)-nvl(sum(f.fundreleased),0) FundBalance,nvl(sum(j.totalwithtax),0) TotalWithTax,j.jobcosttotal2budget BudgetFundReleased2Budget from table_funded f,table_jobcosttotal j where j.jobcosttotal2budget=f.funded2budget(+) group by j.jobcosttotal2budget
-- Unable to render VIEW DDL for object ERP.TABLE_BUDGETFUNDUSED with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_BUDGETFUNDUSED
AS select distinct nvl(sum(a.estamount),0) FundUsed,b.objid  BudgetFundUsed2Budget from table_budget b,table_approval a where a.status=2 and b.objid=a.objid group by b.objid
-- Unable to render VIEW DDL for object ERP.TABLE_BUDGETTARGETACHIEVED with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_BUDGETTARGETACHIEVED
AS select distinct round((nvl(sum(j.contractamount*pp.AchievedTarget/100),0)/decode(sum(j.contractamount),0,1,sum(j.contractamount)))*100,2) AchievedTarget,b.objid  BudgetTargetAchieved2Budget from table_ppprogress pp,table_projectplan p,table_jobcost j,table_budget b,table_estimation e where b.budget2project=p.projectplan2project and p.objid=pp.ppprogress2projectplan and b.budget2project=e.estimation2project and e.objid=j.jobcost2estimation group by b.objid
-- Unable to render VIEW DDL for object ERP.TABLE_BUDGETTOTAL with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_BUDGETTOTAL
AS select distinct round(nvl(sum(u.totalcontract),0)+nvl(sum(p.extracost),0),2) TotalContract,nvl(sum(u.TotalEstAmount),0) TotalEstAmount,nvl(sum(u.CumulativeAmount),0) CumulativeAmount,nvl(sum(r.fundreleased),0) FundReleased,round(nvl(sum(r.totalwithtax),0)+nvl(sum(p.extracost),0)-nvl(sum(r.fundreleased),0),2) BalanceAmount,nvl(sum(u.fundused),0) FundUsed,nvl(sum(r.fundreleased),0)-nvl(sum(u.fundused),0) UnUsedFund,round(nvl(sum(u.ContToCumulative),0)+nvl(sum(p.extracost),0)+(nvl(sum(r.totalwithtax),0)-nvl(sum(u.totalcontract),0)),2) ContToCumulative,sum(u.BudgetUsed) BudgetUsed,sum(u.achievedtarget) AchievedTarget,round((nvl(sum(r.totalwithtax),0)-nvl(sum(u.totalcontract),0)),2) TotalTax,round(nvl(sum(u.totalcontract),0),2) TotalJobCost,round(nvl(sum(p.extracost),0),2) TotalExtraCost,round(sum(r.totalwithtax)+nvl(sum(p.extracost),0),2) TotalWithTax,u.BudgetUsed2project BudgetTotal2Project,u.BudgetUsed2Budget  BudgetTotal2Budget from table_BudgetUsed u,table_BudgetFundReleased r,table_project p where p.objid=u.BudgetUsed2project and u.BudgetUsed2Budget=r.BudgetFundReleased2budget(+) group by u.BudgetUsed2project,u.BudgetUsed2Budget
-- Unable to render VIEW DDL for object ERP.TABLE_BUDGETUSED with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_BUDGETUSED
AS select distinct b.name Name,b.Title Title,b.projectcode ProjectCode,nvl(sum(e.totalcontract),0) TotalContract,nvl(sum(e.TotalEstAmount),0) TotalEstAmount,nvl(sum(e.CumulativeAmount),0) CumulativeAmount,nvl(sum(u.fundused),0) FundUsed,nvl(sum(e.ContToCumulative),0) ContToCumulative,round((nvl(sum(u.fundused),0)/decode(sum(e.totalcontract),0,1,sum(e.totalcontract)))*100,2) BudgetUsed,sum(bt.achievedtarget) AchievedTarget,b.budget2project ProjectNo,b.objid,b.budget2project budgetused2project,b.groupuser,e.BudgetEstTotal2Budget  BudgetUsed2Budget from table_budget b,table_BudgetEstTotal e,table_BudgetFundUsed u,table_budgettargetachieved bt where b.objid=e.BudgetEstTotal2budget and e.BudgetEstTotal2budget=bt.budgettargetachieved2budget and bt.budgettargetachieved2budget=u.budgetfundused2budget(+) group by e.BudgetEstTotal2project,e.BudgetEstTotal2Budget,b.objid,b.name,b.title,b.projectcode,b.groupuser,b.budget2project
-- Unable to render VIEW DDL for object ERP.TABLE_CHANGEPART with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_CHANGEPART
AS select distinct sum(p.unitcount*pp.unitprice) EstPartCost,sum(rq.qntrequest*pp.unitprice) ActPartCost,p.parts2change  ChangePart2change from table_parts p, table_partrequest rq, table_partprice pp where pp.objid=p.parts2partprice and p.objid=rq.partrequest2parts(+) group by p.parts2change
-- Unable to render VIEW DDL for object ERP.TABLE_CHANGEPARTCOST with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_CHANGEPARTCOST
AS select distinct sum(cp.EstPartCost) EstPartCost,sum(cp.ActPartCost) ActPartCost,c.objid  ChangePartCost2change from table_ChangePart cp, table_Change c where c.objid=cp.ChangePart2change(+) group by c.objid
-- Unable to render VIEW DDL for object ERP.TABLE_CHANGERESOURCE with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_CHANGERESOURCE
AS select distinct sum(r.estunit*pp.unitprice) EstResCost,sum(r.qntrequest*pp.unitprice) ActResCost,r.taskresource2change ChangeResource2change from table_taskresource r, table_partprice pp where pp.objid=r.taskresource2partprice group by r.taskresource2change
-- Unable to render VIEW DDL for object ERP.TABLE_CHANGERESOURCECOST with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_CHANGERESOURCECOST
AS select distinct sum(cr.EstResCost) EstResCost,sum(cr.ActResCost) ActResCost,c.objid ChangeResourceCost2change from table_Changeresource cr, table_Change c where c.objid=cr.changeresource2change(+) group by c.objid
-- Unable to render VIEW DDL for object ERP.TABLE_CURRENTASSET with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_CURRENTASSET
AS select distinct sum(nvl(bd.amount,0)) CurrentAsset,bd.accountgroup, b.objid CurrentAsset2BalanceSheet from table_balancedetails bd,table_balancesheet b where b.objid=bd.balancedetails2balancesheet(+) and bd.accountgroup='1' group by bd.accountgroup,b.objid
-- Unable to render VIEW DDL for object ERP.TABLE_CURRENTLIABILITY with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_CURRENTLIABILITY
AS select distinct sum(nvl(bd.amount,0)) CurrentLiability,bd.accountgroup, b.objid CurrentLiability2BalanceSheet from table_balancedetails bd,table_balancesheet b where b.objid=bd.balancedetails2balancesheet(+) and bd.accountgroup='3' group by bd.accountgroup,b.objid
-- Unable to render VIEW DDL for object ERP.TABLE_CURRENTPLAN with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_CURRENTPLAN
AS select distinct b.name Name,p.name ProjectName,p.title Title,p.projectcode ProjectCode,b.monthcode MonthCode,b.yearcode YearCode,b.quantity Quantity,b.qntfinished QntFinished,b.status Status,b.objid,p.objid CurrentPlan2ProjectControl,p.groupuser from table_project p,table_Boqplan b,table_milestone m,table_projectplan pp where p.objid=pp.projectplan2project and pp.objid=m.milestone2projectplan and m.objid=b.Boqplan2milestone and b.monthcode between to_number(to_char(sysdate,'mm')) and to_number(to_char(sysdate+30,'mm')) and b.yearcode=to_number(to_char(sysdate,'yyyy'))
-- Unable to render VIEW DDL for object ERP.TABLE_DEDUCTIONNONTAXED with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_DEDUCTIONNONTAXED
AS select distinct sum(nvl(d.amount,0)) NonTaxedAmount,p.objid  DeductionNonTaxed2Payrole from table_Payrole p,table_deduction d where p.payrole2employee=d.deduction2employee(+) and p.paydate between nvl(d.startdate,sysdate-5) and nvl(d.enddate,sysdate+10) and d.taxtypecode(+)=1 group by p.objid
-- Unable to render VIEW DDL for object ERP.TABLE_DEDUCTIONTAXED with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_DEDUCTIONTAXED
AS select distinct sum(nvl(d.amount,0)) TaxedAmount,p.objid  DeductionTaxed2Payrole from table_Payrole p,table_deduction d where p.payrole2employee=d.deduction2employee(+) and p.paydate between nvl(d.startdate,sysdate-5) and nvl(d.enddate,sysdate+10) and d.taxtypecode (+)=2 group by p.objid
-- Unable to render VIEW DDL for object ERP.TABLE_DEMOBID with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_DEMOBID
AS select distinct p.name Name,p.Description Description,p.Criteria Criteria,p.ListDate ListDate,p.closedate CloseDate,nvl(p.BgtRangeCode,0) BgtRangeCode,p.StartCode StartCode,l.ResponseCount ResponseCount,l.BidCount BidCount,p.ZipCode ZipCode,p.address Address,p.state State,p.Instruction Instruction,p.objid ObjId,p.objid DemoBid2BidRequest, p.gendate,p.genuser from table_ProjectLead p,table_leadresult l where p.objid=l.LeadResult2bidrequest(+) order by p.gendate asc
-- Unable to render VIEW DDL for object ERP.TABLE_DEMOLEAD with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_DEMOLEAD
AS select distinct p.name Name,p.Description Description,p.Criteria Criteria,p.ListDate ListDate,p.closedate CloseDate,nvl(p.BgtRangeCode,0) BgtRangeCode,p.StartCode StartCode,l.ResponseCount ResponseCount,l.BidCount BidCount,p.ZipCode ZipCode,p.address Address,p.state State,p.Instruction Instruction,p.objid ObjId, p.objid DemoLead2BidRequest,p.gendate,p.genuser from table_ProjectLead p,table_leadresult l where rownum<11 and p.objid=l.LeadResult2bidrequest(+) order by p.gendate asc
-- Unable to render VIEW DDL for object ERP.TABLE_DEPRICATEDCOST with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_DEPRICATEDCOST
AS select distinct g.name ||'- Depricated' Name,(0-nvl(sum(d.amount),0)) DepricatedCost,b.objid,b.groupuser,g.codevalue accountgroup,b.yearcode,b.objid  DepricatedCost2BalanceSheet from table_BalanceSheet b,table_Depricated d,table_genericcode g where d.yearcode=b.yearcode and g.attributename='AccountGroup' and g.codevalue='3' group by g.name||'- Depricated',b.objid,b.groupuser,b.yearcode,g.codevalue
-- Unable to render VIEW DDL for object ERP.TABLE_DEPTBUDGET with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_DEPTBUDGET
AS select distinct sum(nvl(h.total,0)) Total,sum(nvl(pb.total,0)) AmountSpent,sum(nvl(h.total,0))-sum(nvl(pb.total,0)) Balance,TRUNC((SUM(NVL(pb.total,0))/SUM(decode(nvl(h.total,0),0,1000000000,h.total))),2)*100 PctSpent,TRUNC(((SUM(NVL(h.total,0))-SUM(NVL(pb.total,0)))/SUM(decode(nvl(h.total,0),0,1000000000,h.total))),2)*100 PctBalance,b.objid,b.deptcode, b.objid DeptBudget2BudgetHead from table_BudgetHead b,table_headtotal h,table_PoBudgetHead pb  where pb.pobudgethead2budget=b.budgethead2budget and b.objid=h.headtotal2budgethead(+)  group by b.objid,b.deptcode
-- Unable to render VIEW DDL for object ERP.TABLE_DEPTPOBUDGET with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_DEPTPOBUDGET
AS select distinct b.name Name,b.deptcode DeptCode,sum(nvl(h.total,0)) Total,sum(nvl(pb.total,0)) AmountSpent,sum(nvl(h.total,0))-sum(nvl(pb.total,0)) Balance,TRUNC((SUM(NVL(pb.total,0))/SUM(decode(nvl(h.total,0),0,1000000000,h.total))),2)*100 PctSpent,TRUNC(((SUM(NVL(h.total,0))-SUM(NVL(pb.total,0)))/SUM(decode(nvl(h.total,0),0,1000000000,h.total))),2)*100 PctBalance,b.objid, pm.objid DeptPoBudget2PoMaster from table_BudgetHead b,table_headtotal h,table_PoBudgetHead pb,table_pomaster pm, table_budget bg  where pm.pomaster2project=bg.budget2project and bg.objid=b.budgethead2budget and pb.pobudgethead2budget=b.budgethead2budget and b.objid=h.headtotal2budgethead(+) group by b.name, b.deptcode, b.objid, pm.objid
-- Unable to render VIEW DDL for object ERP.TABLE_EQUIPMENTOWN with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_EQUIPMENTOWN
AS select distinct e.Name Name,e.Description Description,nvl(e.PurchaseCost,0) PurchaseCost,nvl(e.CurrentValue,0) CurrentValue,e.groupuser,e.objid, e.objid EquipmentOwn2Equipment from table_Equipment e where e.ownercode='1' order by e.name
-- Unable to render VIEW DDL for object ERP.TABLE_EQUIPMENTTOTAL with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_EQUIPMENTTOTAL
AS select distinct g.name ||'- Equipment' Name,nvl(sum(e.currentvalue),0) TotalValue,b.groupuser,e.accountgroup,b.objid EquipmentTotal2BalanceSheet from table_BalanceSheet b,table_equipment e,table_genericcode g where e.groupuser=b.groupuser and e.ownercode=1 and e.status=1 and g.attributename='AccountGroup' and g.codevalue=e.accountgroup group by g.name||'- Equipment', b.objid,b.groupuser,e.accountgroup
-- Unable to render VIEW DDL for object ERP.TABLE_EQUIPMENTUSE with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_EQUIPMENTUSE
AS select distinct e.name Name,e.Description Description,e.TagNo TagNo,e.PurchaseCost PurchaseCost,e.ExpectedLife ExpectedLife,sum(ru.qntused) QntUsed,sum(ru.qntrequest) QntRequest,sum(nvl(ru.qntrequest,0)-nvl(ru.qntused,0)) QntBalance,sum(nvl(ru.qntused,0)*p.unitprice) RevenueEarned,sum(nvl(ru.qntrequest,0)*p.unitprice) RevenueExpected,sum((nvl(ru.qntrequest,0)+nvl(ru.qntrequest,0))*p.unitprice) TotalRevenue,e.objid,e.groupuser,e.objid EquipmentUse2Equipment from table_Resourceuse ru,table_TaskResource tr,table_partprice p,table_equipment e where e.equipment2addequipment=p.objid and p.objid=tr.taskresource2partprice and tr.objid=ru.Resourceuse2taskResource group by e.objid,e.name,e.description,e.purchasecost,e.tagno,e.expectedlife,e.groupuser
-- Unable to render VIEW DDL for object ERP.TABLE_EQUIPSERVICE with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_EQUIPSERVICE
AS select distinct m.name Name,m.description Description,sum(nvl(p.partcost,0)) PartCost,sum(nvl(l.partcost,0)) LaborCost,sum(nvl(p.partcost,0))+sum(nvl(l.partcost,0)) TotalCost,to_char(m.servicedate,'YYYY') Year,m.maintenance2equipment Equipid,m.objid,m.objid EquipService2Maintenance, m.groupuser,m.genuser from table_ServiceLabor l,table_ServicePartCost p,table_maintenance m where m.objid=l.ServiceLabor2Maintenance and l.ServiceLabor2Maintenance=p.ServicePartCost2Maintenance(+) and m.category=2 group by m.objid,m.maintenance2equipment,m.name,m.servicedate,m.description,m.groupuser,m.genuser
-- Unable to render VIEW DDL for object ERP.TABLE_FINDASSET with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_FINDASSET
AS select distinct e.name Name,nvl(max(ap.enddate),sysdate) AvailDate,e.tagno TagNo,ap.CurLocation PrivLocation,e.groupuser,e.objid, e.objid FindAsset2Equipment from table_Equipment e,table_AssetPlan ap where e.objid=ap.AssetPlan2FindAsset(+) group by e.objid, e.name,ap.CurLocation,e.tagno,e.groupuser
-- Unable to render VIEW DDL for object ERP.TABLE_FROMACCOUNT with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_FROMACCOUNT
AS select distinct a.name Name,a.lastname LastName,a.objid AccountNo,b.accountbalance AccountBalance,a.note Note,a.objid objid,a.objid FromAccount2Account,a.groupuser from table_account a,table_balance b where a.objid=b.balance2account(+)
-- Unable to render VIEW DDL for object ERP.TABLE_FUNDAUDIT with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_FUNDAUDIT
AS select distinct b.name Name,b.Title Title,c.Name FirstName,c.LastName LastName,c.phone Phone,bt.TotalContract LimitAmount,bt.FundReleased TermPaid,bt.totalcontract-bt.fundreleased TermBalanced,b.objid objid,b.groupuser,b.objid  FundAudit2Budget from table_budget b,table_contact c,table_budgettotal bt where b.budget2project=c.contact2project and c.contactcode=1 and b.objid=bt.budgettotal2Budget(+)
-- Unable to render VIEW DDL for object ERP.TABLE_FUNDED with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_FUNDED
AS select distinct nvl(sum(p.invoicedamount),0) FundReleased,p.payment2budget Funded2Budget from table_payment p where nvl(p.status,2)=2 group by p.payment2budget
-- Unable to render VIEW DDL for object ERP.TABLE_GOALTOTAL with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_GOALTOTAL
AS select distinct sum(to_number(e.weightage)*to_number(e.PerformCode)) TotalGoal,p.objid  GoalTotal2Participants from table_Evaluation e,table_Participants p where p.objid=e.evaluation2participants(+) group by p.objid
-- Unable to render VIEW DDL for object ERP.TABLE_HEADTOTAL with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_HEADTOTAL
AS select distinct sum(nvl(a.amount,0)) Total,b.objid HeadTotal2BudgetHead from table_budgethead b,table_allocation a where b.objid=a.allocation2budgethead(+) group by b.objid,b.deptcode
-- Unable to render VIEW DDL for object ERP.TABLE_INDENT with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_INDENT
AS select distinct p.name Name,i.indentNo IndentNo,b.MrNo MrNo,b.partno PartNo,p.description Description,b.umcode UmCode,pt.deptcode DeptCode,b.pocode PoCode,nvl(p.QntRequest,0) QntRequest,nvl(p.QntRequest,0)*(1+ nvl(p.PctTax,0)/100)*nvl(p.actualrate,0) Total,p.PartRequest2Parts Indent2Parts,p.objid from table_ItemDispatchCount i,table_partrequest p,table_parts pt,table_bom b where p.objid=i.ItemDispatchCount2partrequest and p.partrequest2parts=pt.objid and b.objid=pt.parts2partprice
-- Unable to render VIEW DDL for object ERP.TABLE_INSPECTIONCOUNT with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_INSPECTIONCOUNT
AS select distinct bp.name Name,sum(nvl(i.QntInspected,0)) QntInspect,bp.objid InspectionCount2BoqPlan from table_BoqPlan bp,table_Inspection i where bp.objid=i.inspection2boqplan(+) group by bp.objid,bp.name
-- Unable to render VIEW DDL for object ERP.TABLE_INSTALLPENDING with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_INSTALLPENDING
AS select distinct pp.name Name,pj.title Title,pl.Description Description,pp.PartNo PartNo,pl.PartCode PartCode,pp.UmCode UmCode,sum(nvl(p.qntrequest,0)) QntPending,sum(nvl(p.qntrequest,0)*nvl(pp.unitprice,0)) OrderAmount,pp.objid InstallPending2PartPrice,pp.objid Objid,p.groupuser from table_project pj, table_estimation e,table_boq b,table_partrequest p,table_partprice pp,table_partlist pl,table_parts pr where pj.objid=e.estimation2project AND e.objid=b.boq2estimation AND b.objid=pr.parts2boq and pl.objid=pp.partprice2partlist and pp.objid=pr.parts2partprice and pr.objid=p.partrequest2parts and p.status=1 group by pp.name, pj.title,pl.description,pp.partNo,pl.partcode,pp.umcode,pp.objid,p.groupuser
-- Unable to render VIEW DDL for object ERP.TABLE_INVENTORY with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_INVENTORY
AS select distinct p.name Name,pl.description Description,pp.umcode UmCode,pl.partcode PartCode,sum(nvl(p.QntRequest,0)) QntRequest,sum(nvl(p.qntused,0)) QntUsed,sum(nvl(p.QntBalance,0)) QntBalance,sum(nvl(pi.QntPending,0)) QntOrdered,sum(nvl(p.qntbalance,0)-nvl(pi.QntPending,0)) QntToOrdered,pl.objid Inventory2PartList,pp.objid ObjId,pp.groupuser from table_PartCount p,table_InstallPending pi,table_partprice pp ,table_partlist pl where p.partcount2partprice=pi.objid(+) and p.partcount2partprice=pp.objid and pp.partprice2partlist=pl.objid group by pl.objid,pl.description,pp.umcode,p.name,pp.objid,pl.partcode,pp.groupuser
-- Unable to render VIEW DDL for object ERP.TABLE_INVOICEAMOUNT with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_INVOICEAMOUNT
AS select distinct it.total Amount,nvl(it.total,0)*nvl(t.totaltax/100,0) TaxAmount,nvl(it.total,0)*nvl(1+t.totaltax/100,0) Total,it.objid  InvoiceAmount2Invoice from table_totaltax t,table_budget b,table_invoice i,table_invoicetotal it where it.invoicetotal2invoice=i.objid and i.invoice2budget=b.objid and b.budget2project=t.totaltax2project
-- Unable to render VIEW DDL for object ERP.TABLE_INVOICEDITEMS with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_INVOICEDITEMS
AS select distinct ir.name Name,ir.note Note,ir.receivedate ReceiveDate,pr.actualrate ActualRate,nvl(ir.QntAccepted,0)*nvl(pr.actualrate,0) Amount,nvl(ir.QntAccepted,0)*nvl(pr.actualrate,0)*nvl(1+pi.tax/100,0) WithTax,ir.objid,pi.objid  InvoicedItems2PoInvoice from table_itemreceived ir ,table_partrequest pr, table_Poinvoice pi where pi.objid=ir.itemreceived2poinvoice and ir.itemreceived2partrequest=pr.objid and nvl(ir.progresscode,0)>0 order by ir.receivedate
-- Unable to render VIEW DDL for object ERP.TABLE_INVOICEDRESOURCE with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_INVOICEDRESOURCE
AS select distinct ts.name Name,ts.title Title,tr.taskcode TaskCode,ts.servicedate ServiceDate,tr.actualrate ActualRate,tr.estunit EstUnit,ts.regulartime RegularTime,ts.overtime OverTime,nvl(ts.overtime,0)+nvl(ts.regulartime,0) TotalTime,nvl(ts.overtime,0)+nvl(ts.regulartime,0)*nvl(tr.actualrate,0) Amount,(nvl(ts.overtime,0)+nvl(ts.regulartime,0)*nvl(tr.actualrate,0))*nvl(1+pi.tax/100,0) WithTax,ts.objid,pi.objid  InvoicedResource2PoInvoice from table_timesheet ts ,table_taskresource tr, table_Poinvoice pi where pi.objid=ts.timesheet2poinvoice and ts.timesheet2taskresource=tr.objid order by ts.servicedate
-- Unable to render VIEW DDL for object ERP.TABLE_INVOICEITEM with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_INVOICEITEM
AS select distinct i.name Name,i.note Note,i.InspectDate InspectDate,i.QntInspected QntInspected,i.Status Status,nvl(b.rate,0) Rate,nvl(b.rate,0)*nvl(i.QntInspected,0) Total,bp.objid  invoiceitem2boqplan,i.objid invoiceitem2inspection,i.objid,nvl(inv.objid,'0')InvoiceItem2Invoice from table_inspection i,table_boqplan bp,table_boq b,table_invoice inv where b.objid=bp.boqplan2boq and bp.objid=i.inspection2boqplan and i.inspectdate between nvl(inv.fromdate,i.inspectdate-1 ) and nvl(inv.uptodate,i.inspectdate+1)
-- Unable to render VIEW DDL for object ERP.TABLE_INVOICEITEMLIST with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_INVOICEITEMLIST
AS select distinct i.name Name,i.note Note,i.InspectDate InspectDate,i.QntInspected QntInspected,i.Status Status,nvl(b.rate,0) Rate,nvl(b.rate,0)*nvl(i.QntInspected,0) Total,bp.objid  InvoiceItemList2boqplan,i.objid InvoiceItemList2inspection,i.objid,i.groupuser from table_inspection i,table_boqplan bp,table_boq b where b.objid=bp.boqplan2boq and bp.objid=i.inspection2boqplan and i.inspectdate between sysdate-30 and sysdate+60 order by i.inspectdate
-- Unable to render VIEW DDL for object ERP.TABLE_INVOICETOTAL with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_INVOICETOTAL
AS select distinct sum(nvl(it.total,0)) Total,i.objid,i.objid  InvoiceTotal2Invoice from table_invoice i,table_invoiceitem it where it.InspectDate between i.fromdate and i.uptodate group by i.objid
-- Unable to render VIEW DDL for object ERP.TABLE_ITEMCOST with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_ITEMCOST
AS select distinct j.name Name,j.description Description,j.umcode UmCode,j.qntrequest UnitCount,i.unitprice UnitPrice,nvl(j.qntrequest,0)*nvl(i.unitprice,0) Total,i.genuser Bidder,v.destinitionid bidrequestid,u.objid profileid,v.objid ItemCost2Bids,i.objid from table_VendorBid v,table_partbid j, table_itemprice i,table_user u where v.destinitionid =j.partbid2bidrequest and j.objid=i.itemprice2partbid and v.objid=i.itemprice2vendorbid and i.genuser=u.loginname
-- Unable to render VIEW DDL for object ERP.TABLE_ITEMDISPATCHCOUNT with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_ITEMDISPATCHCOUNT
AS select distinct nvl(sum(i.qntdispatched),0) TotalQntDispatched,lpad(p.objid,12,'0') IndentNo,p.objid ItemDispatchCount2PartRequest,p.objid from table_Itemdispatch i,table_partrequest p where p.objid=i.ItemDispatch2partrequest(+) group by p.objid,lpad(p.objid,10,'0')
-- Unable to render VIEW DDL for object ERP.TABLE_ITEMMIN with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_ITEMMIN
AS select distinct i.name Name,max(i.UnitPrice) HighestBid,min(i.UnitPrice) LowestBid,i.itemPrice2PartBid ItemMin2PartRequest from table_ItemPrice i group by i.name,i.itemPrice2PartBid
-- Unable to render VIEW DDL for object ERP.TABLE_ITEMNOTRECEIVE with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_ITEMNOTRECEIVE
AS select distinct r.name Name,po.objid PoNo,p.objid ProjectNo,p.name ProjectName,r.QntRequest QntRequest,r.qntused QntUsed,r.qntpurchased QntPurchased,nvl(r.qntrequest,0)-nvl(r.qntused,0) QntBalance,nvl(t.qntreceived,0) qntreceived,nvl(t.qntaccepted,0) qntaccepted,r.startdate StartDate,r.purchasedate PurchaseDate,r.objid PartrequestId,r.objid,p.objid ItemNotReceive2Project,p.groupuser from table_project p,table_Boqplan b,table_milestone m,table_projectplan pp,table_parts pt,table_partrequest r,table_itemreceived t,table_purchaseorder po where p.objid=pp.projectplan2project and pp.objid=m.milestone2projectplan and m.objid=b.Boqplan2milestone and b.Boqplan2Boq=pt.parts2Boq and pt.objid= r.partrequest2parts and r.objid=t.itemreceived2partrequest(+) and po.objid=r.partrequest2purchaseorder and nvl(t.qntaccepted,0)=0
-- Unable to render VIEW DDL for object ERP.TABLE_JOBCOST with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_JOBCOST
AS select distinct e.name Name,e.Title Title,e.projectcode ProjectCode,nvl(sum(bq.netqnt* b.rate),0) ContractAmount,nvl(sum(estBoqchangecost),0) EstChangeCost,nvl(sum(actBoqchangecost),0) ActChangeCost,nvl(sum(estBoqcost),0) EstJobCost,nvl(sum(actBoqcost),0) ActJobCost,nvl(sum(bq.netqnt* b.rate),0)-nvl(sum(actBoqcost),0) Profit,e.objid,e.groupuser,b.Boq2estimation  JobCost2Estimation,bg.objid jobcost2budget from table_totalBoqcost tb,table_Boqqnt bq,table_Boq b,table_estimation e,table_budget bg where bg.budget2project=e.estimation2project and e.objid=b.Boq2estimation and b.objid=tb.totalBoqcost2Boq(+) and b.objid=bq.Boqqnt2Boq group by e.objid,e.name,e.title,e.projectcode,e.groupuser, b.Boq2estimation,bg.objid
-- Unable to render VIEW DDL for object ERP.TABLE_JOBCOSTTOTAL with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_JOBCOSTTOTAL
AS select distinct nvl(sum(j.contractamount* (1+nvl(t.TotalTax/100,0))),0) TotalWithTax,nvl(sum(j.contractamount),0) TotalContract,b.objid  JobCostTotal2Budget from table_totaltax t,table_budget b,table_jobcost j where t.totaltax2project=b.budget2project and b.objid=j.jobcost2budget group by b.objid
-- Unable to render VIEW DDL for object ERP.TABLE_JOBEST with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_JOBEST
AS select distinct br.name Name,nvl(j.unitcount,0)*nvl(j.unitprice,0) JobTotal,pb.total PartTotal,br.objid JobEst2BidRequest,j.objid from table_jobs j,table_partbid pb,table_bidrequest br where br.objid=pb.partbid2bidrequest and br.objid=j.jobs2bidrequest(+)
-- Unable to render VIEW DDL for object ERP.TABLE_JOBEXPERIENCE with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_JOBEXPERIENCE
AS select distinct p.company Name,p.name ProfileEmail,e.name Title,e.Description Description,e.YearFrom YearFrom,e.YearTo YearTo,e.Reference Reference,e.objid ObjId from table_Experience e,table_profile p where e.genuser=p.name order by p.name
-- Unable to render VIEW DDL for object ERP.TABLE_LATESTLEAD with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_LATESTLEAD
AS select distinct b.name Name,b.Description Description,b.gendate ListDate,sum(nvl(j.unitprice,0)*nvl(j.unitcount,0)) Amount,b.objid ObjId,b.objid LatestLead2BidRequest from table_BidRequest b,table_jobs j where b.objid=j.jobs2bidrequest and rownum<=23 group by b.objid,b.name,b.description,b.gendate order by b.gendate desc
-- Unable to render VIEW DDL for object ERP.TABLE_LEADCONTACT with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_LEADCONTACT
AS select distinct l.contactname Name,l.address Street,l.phone Phone,l.state State,l.zipcode ZipCode,l.objid,l.projectlead2bidrequest LeadContact2ProjectLead from table_projectlead l where length(l.objid)>0
-- Unable to render VIEW DDL for object ERP.TABLE_LEADJOBS with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_LEADJOBS
AS select distinct j.name Name,j.description Description,j.unitprice UnitPrice,j.unitcount UnitCount,j.umcode UmCode,e.JobTotal Total,j.objid,j.jobs2bidrequest LeadJobs2ProjectLead from table_jobs j,table_projectlead p,table_jobest e where p.objid=j.jobs2bidrequest and j.objid=e.objid
-- Unable to render VIEW DDL for object ERP.TABLE_LEADRESULT with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_LEADRESULT
AS select distinct br.name Name,nvl(r.responsecount,0) ResponseCount,nvl(r.bidcount,0) BidCount,nvl(r.TotRFICount,0) TotRFICount,nvl(l.HighBidTotal,0) HighBidTotal,nvl(l.LowBidTotal,0) LowBidTotal,nvl(l.WinBid,0) WinBid,sum(j.jobtotal) JobEst,sum(j.parttotal) PartEst,br.objid LeadResult2BidRequest from table_bidhighlow l,table_rficount r,table_jobest j,table_bidrequest br where br.objid=r.rficount2bidrequest and l.BidHighLow2BidRequest=br.objid and j.jobest2bidrequest=br.objid group by br.name,br.objid,l.HighBidTotal,l.LowBidTotal,l.WinBid,r.TotRFICount,r.bidcount,r.responsecount
-- Unable to render VIEW DDL for object ERP.TABLE_LEADTOTAL with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_LEADTOTAL
AS select distinct br.name Name,sum(j.jobtotal) TotalAmount,sum(j.parttotal) PartTotal,br.objid LeadTotal2BidRequest from table_jobest j,table_bidrequest br where br.objid=j.jobest2bidrequest(+) group by br.objid,br.name
-- Unable to render VIEW DDL for object ERP.TABLE_LINEINFO with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_LINEINFO
AS select distinct r.name Name,w.name Warehouse,l.name WarehouseLine,p.objid ProjectNo,p.name ProjectName,r.QntRequest QntRequest,r.qntused QntUsed,r.qntpurchased QntPurchased,nvl(r.qntrequest,0)-nvl(r.qntused,0) QntBalance,nvl(t.qntaccepted,0) qntaccepted,r.status Status,r.startdate StartDate,r.purchasedate PurchaseDate,r.objid PartrequestId,r.objid,p.objid Lineinfo2Project,p.groupuser from table_project p,table_Boqplan b,table_milestone m,table_projectplan pp,table_parts pt,table_partrequest r,table_itemdispatch i,table_itemreceived t,table_warehouse w,table_warehouseline l where p.objid=pp.projectplan2project and pp.objid=m.milestone2projectplan and m.objid=b.Boqplan2milestone and b.Boqplan2Boq=pt.parts2Boq and pt.objid= r.partrequest2parts and r.objid=t.itemreceived2partrequest and t.itemreceived2warehouseline=l.objid and l.warehouseline2warehouse=w.objid and t.itemreceived2warehouseline=i.itemdispatch2warehouseline(+) and (nvl(t.qntaccepted,0)-nvl(i.qntdispatched,0))>0
-- Unable to render VIEW DDL for object ERP.TABLE_LONGTERMASSET with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_LONGTERMASSET
AS select distinct sum(nvl(bd.amount,0)) LongTermAsset,bd.accountgroup, b.objid LongTermAsset2BalanceSheet from table_balancedetails bd,table_balancesheet b where b.objid=bd.balancedetails2balancesheet(+) and bd.accountgroup='2' group by bd.accountgroup,b.objid
-- Unable to render VIEW DDL for object ERP.TABLE_LONGTERMLIABILITY with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_LONGTERMLIABILITY
AS select distinct sum(nvl(bd.amount,0)) LongtermLiability,bd.accountgroup, b.objid LongtermLiability2BalanceSheet from table_balancedetails bd,table_balancesheet b where b.objid=bd.balancedetails2balancesheet(+) and bd.accountgroup='4' group by bd.accountgroup,b.objid
-- Unable to render VIEW DDL for object ERP.TABLE_LOWBID with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_LOWBID
AS select distinct p.name Name,p.description Description,p.Projectname ProjectName,p.partcode PartCode,p.QntRequest QntRequest,p.unitprice UnitPrice,i.LowestBid LowestBid,i.HighestBid HighestBid,p.umcode UmCode,nvl(i.LowestBid,0)*nvl(p.QntRequest,0) Total,s.name Supplier,p.objid, br.objid LowBid2BidRequest,br.groupuser,s.objid Lowbid2Supplier from table_user u, table_supplier s,table_partbid p, table_itemmin i,table_itemprice ip,table_bidrequest br where p.partbid2bidrequest=br.objid and i.itemmin2partrequest=p.objid and i.itemmin2partrequest=ip.itemprice2partbid and i.lowestbid=ip.unitprice and ip.genuser=u.loginname and u.user2supplier=s.objid
-- Unable to render VIEW DDL for object ERP.TABLE_MACHINARY with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_MACHINARY
AS select distinct t.name Name,s.name SiteName,ae.umcode UmCode,sum(nvl(t.estunit,0)) EstUnit,sum(nvl(t.qntrequest,0)) QntRequest,min(t.startdate) StartDate,max(t.enddate) EndDate,trunc((sum(nvl(t.estunit,0))/((max(t.enddate)-min(t.startdate)))) *5/7,2) UseFactor,s.groupuser,ae.objid, s.objid Machinary2Site from table_Site s, table_phase ph, table_project p,Table_taskresource t,table_addequipment ae where s.objid=ph.phase2site and ph.objid=p.project2phase and p.title=t.title and t.taskresource2partprice=ae.objid group by t.name, s.name, ae.umcode, ae.objid, s.objid, s.groupuser
-- Unable to render VIEW DDL for object ERP.TABLE_MATERIAL with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_MATERIAL
AS select distinct p.name Name,p.projectcode ProjectCode,p.maincode MainCode,p.subcode SubCode,pl.domaincode DomainCode,pl.partcode PartCode,pr.partno PartNo,pl.description Description,pr.unitprice UnitPrice,p.unitcount UnitCount,pt.qntrequest QntRequest,pt.qntused QntUsed,pt.qntpurchased QntPurchased,pr.UmCode UmCode,p.origincode OriginCode,p.note Note,p.objid, p.groupuser,bp.objid Material2BoqPlan from table_partlist pl,table_partprice pr,table_parts p,table_Boqplan bp,table_partrequestcount pt where bp.Boqplan2Boq=p.parts2Boq and p.parts2partprice=pr.objid and pr.partprice2partlist=pl.objid and pt.partrequestcount2parts=p.objid
-- Unable to render VIEW DDL for object ERP.TABLE_MONTHLYAPPROVED with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_MONTHLYAPPROVED
AS select distinct sum(a.estamount) MonthlyApproved,a.Approval2monthly MonthlyApproved2Monthly from table_approval a where a.status=2 group by a.Approval2monthly
-- Unable to render VIEW DDL for object ERP.TABLE_MONTHLYBID with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_MONTHLYBID
AS select distinct p.name Name,p.Description Description,p.Criteria Criteria,p.ListDate ListDate,p.closedate CloseDate,nvl(p.BgtRangeCode,0) BgtRangeCode,p.StartCode StartCode,l.ResponseCount ResponseCount,l.BidCount BidCount,p.ZipCode ZipCode,p.address Address,p.state State,p.Instruction Instruction,p.objid ObjId,p.objid MonthlyBid2BidRequest, p.gendate,p.genuser,p.groupuser from table_ProjectLead p,table_leadresult l where p.gendate<sysdate and p.gendate>sysdate-31 and p.objid=l.LeadResult2bidrequest(+) order by p.gendate desc
-- Unable to render VIEW DDL for object ERP.TABLE_MONTHLYBUDGET with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_MONTHLYBUDGET
AS select distinct m.YearCode YearCode,m.MonthCode MonthCode,sum(decode(to_number(to_char(nvl(p.fundduedate,sysdate), 'mm')),to_number(m.monthcode),nvl(p.planpct,0)*bt.totalwithtax/100,0)) Budget,b.objid MonthlyBudget2Budget, nvl(p.objid,m.monthcode) objid from table_month m, table_BudgetPlan p,table_budget b,table_budgettotal bt where b.objid=bt.budgettotal2budget and b.objid=p.budgetplan2budget(+) and to_number(to_char(nvl(p.fundduedate,sysdate), 'yyyy'))=to_number(m.yearcode) group by m.yearcode, m.monthcode, b.objid,p.objid
-- Unable to render VIEW DDL for object ERP.TABLE_MONTHLYESTIMATE with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_MONTHLYESTIMATE
AS select distinct sum(a.estamount) MonthlyEstimate,a.Approval2monthly MonthlyEstimate2Monthly from table_approval a where a.status>=0 group by a.Approval2monthly
-- Unable to render VIEW DDL for object ERP.TABLE_MONTHLYFORCAST with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_MONTHLYFORCAST
AS select distinct nvl(sum(ma.monthlyapproved),0) MonthlyApproved,nvl(sum(me.monthlyestimate),0) MonthlyEstimated,round((nvl(sum(ma.monthlyapproved),0)/decode(sum(bt.totalcontract),0,1,sum(bt.totalcontract)))*100,2) PctProjectApproved,round((nvl(sum(me.monthlyestimate),0)/decode(sum(bt.totalcontract),0,1,sum(bt.totalcontract)))*100,2) PctProjectEstimate,m.objid MonthlyForcast2Monthly from table_monthlyapproved ma,table_monthlyestimate me,table_monthly m, table_budgettotal bt where m.objid=ma.monthlyapproved2monthly and me.monthlyestimate2monthly=m.objid and m.monthly2budget=bt.budgettotal2budget(+) group by m.objid
-- Unable to render VIEW DDL for object ERP.TABLE_MONTHLYPAYMENT with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_MONTHLYPAYMENT
AS select distinct m.YearCode YearCode,m.MonthCode MonthCode,sum(decode(to_number(to_char(nvl(p.fundduedate,sysdate), 'mm')),to_number(m.monthcode),nvl(p.invoicedamount,0),0)) Payment,b.objid MonthlyPayment2Budget, nvl(p.objid,m.monthcode) objid from table_month m,table_payment p,table_budget b where b.objid=p.payment2budget(+) and to_number(to_char(nvl(p.fundduedate,sysdate), 'yyyy'))=to_number(m.yearcode) group by m.yearcode, m.monthcode, b.objid,p.objid
-- Unable to render VIEW DDL for object ERP.TABLE_MONTHLYREVENUE with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_MONTHLYREVENUE
AS select distinct b.monthcode MonthCode,nvl(sum(b.budget),0) ForcastAmount,nvl(sum(p.payment),0) EarnedRevenue,nvl(sum(b.budget),0)-nvl(sum(p.payment),0) NotInvoiced,b.monthcode objid,b.yearcode,pj.groupuser,pj.objid MonthlyRevenue2Projection from table_monthlypayment p, table_monthlybudget b,table_budgettotal bt,table_projection pj where bt.budgettotal2budget=b.monthlybudget2budget and b.monthlybudget2budget=p.monthlypayment2budget(+) and p.yearcode=b.yearcode and b.yearcode=pj.yearcode and p.monthcode=b.monthcode  group by b.monthcode,b.yearcode,pj.groupuser,pj.objid
-- Unable to render VIEW DDL for object ERP.TABLE_MSFLOAT with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_MSFLOAT
AS select distinct m1.objid Objid,nvl(min(m2.floatcount),0) FloatCount,ms.schedule Schedule,m1.objid MSFloat2MileStone from table_msschedule ms,table_milestone m1,table_Mslink m2 where  ms.objid=m1.objid and m1.objid=m2.MsLink2MileStone(+) group by m1.objid,ms.schedule
-- Unable to render VIEW DDL for object ERP.TABLE_MSLINK with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_MSLINK
AS select distinct m1.objid Objid,m1.name Name,m1.title Title,m1.projectcode ProjectCode,m1.maincode MainCode,m1.subcode SubCode,j1.SubOffSetTime StartBefore,round((m2.StartDate-m1.startdate)) StartActual,round(((m2.StartDate-m1.startdate)-j1.SubOffSetTime)) FloatCount,m1.startdate StartDate,m1.enddate EndDate,m2.objid MSLink2MileStone from table_milestone m1,table_milestone m2,table_joblist j1,table_projectplan p where j1.maincode=m2.maincode and j1.subcode=m2.subcode and m1.maincode=j1.mainjobcode and m1.subcode=j1.subjobcode  and j1.subjobcode<>-100 and m1.subcode<>m2.subcode and j1.joblist2project=p.projectplan2project and p.objid=m1.milestone2projectplan and m1.destinitionid=m2.destinitionid
-- Unable to render VIEW DDL for object ERP.TABLE_MSPROGRESS with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_MSPROGRESS
AS select distinct round((sum(nvl(bp.qntfinished,0))/decode(bq.netqnt,0,1,bq.netqnt))*100,2) AchievedTarget,sum(bp.quantity) QntPlanned,sum(bp.qntfinished) QntFinished,bq.netqnt NetBoqQnt,m.objid MSProgress2MileStone from table_Boqqnt bq,table_milestone m,table_Boqplan bp where m.objid=bp.Boqplan2milestone(+) and m.milestone2Boq=bq.Boqqnt2Boq group by m.objid,bq.netqnt
-- Unable to render VIEW DDL for object ERP.TABLE_MSSCHEDULE with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_MSSCHEDULE
AS select distinct m1.objid Objid,(round(m1.startdate-p.startdate)||'@'||round(m1.enddate-m1.startdate)||'@'||round(p.enddate-p.startdate)) Schedule,m1.objid MSSchedule2MileStone from table_ProjectPlan p,table_MileStone m1 where  p.objid=m1.MileStone2ProjectPlan
-- Unable to render VIEW DDL for object ERP.TABLE_MYBILLING with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_MYBILLING
AS select distinct b.name Name,b.Description Description,b.BillDate BillDate,b.Amount Amount,p.name genuser,b.objid ObjId from table_billing b,table_profile p where b.name=p.name and b.status=5
-- Unable to render VIEW DDL for object ERP.TABLE_MYEXPERIENCE with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_MYEXPERIENCE
AS select distinct e.name Name,e.Description Description,e.YearFrom YearFrom,e.YearTo YearTo,e.Reference Reference,e.genuser genuser,e.objid ObjId from table_Experience e,table_profile p where e.genuser=p.name
-- Unable to render VIEW DDL for object ERP.TABLE_MYLOOSE with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_MYLOOSE
AS select distinct v.name Name,b.name LeadName,b.objid LeadNo,b.Description Description,m.mybidamount MinBidTotal,t.totalbid MyBidAmount,b.objid bidrequestid,p.objid profileid,v.objid,v.genuser from table_mywin m,table_profile p,table_bidrequest b,table_bidtotal t, table_vendorbid v where v.genuser=p.name and v.destinitionid=b.objid and m.bidrequestid=b.objid and m.profileid<>p.objid and v.objid=t.itemprice2vendorbid and t.bidrequestid=b.objid and nvl(t.totalbid,0)>nvl(m.mybidamount,0) and b.status=1
-- Unable to render VIEW DDL for object ERP.TABLE_MYPROJECT with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_MYPROJECT
AS select distinct b.name Name,b.Description Description,b.Criteria Criteria,b.ListDate ListDate,g.BidDueDate CloseDate,nvl(t.TotalAmount,0) EstAmount,l.ResponseCount ResponseCount,l.BidCount BidCount,l.TotRFICount RFICount,nvl(l.lowbidtotal,0) MinBid,nvl(l.highbidtotal,0) MaxBid,b.objid ObjId,b.objid MyProject2BidRequest,b.genuser from table_BidRequest b,table_profile p,table_leadtotal t,table_general g,table_leadresult l where p.name=b.genuser and b.objid=l.leadresult2bidrequest(+) and b.objid=t.leadtotal2bidrequest and b.objid=g.general2bidrequest
-- Unable to render VIEW DDL for object ERP.TABLE_MYREQUEST with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_MYREQUEST
AS select distinct s.Name Name,s.Description Description,s.PostDate PostDate,s.DetailAction DetailAction,s.status Status,s.genuser genuser,s.objid ObjId from table_Assistance s,table_profile p where s.genuser=p.name
-- Unable to render VIEW DDL for object ERP.TABLE_MYSUBSCRIPTION with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_MYSUBSCRIPTION
AS select distinct s.Name Name,s.SubscribeCode SubscribeCode,s.StartDate StartDate,s.EndDate EndDate,s.name genuser,s.objid ObjId,p.objid mysubscription2profile from table_Subscription s,table_profile p where p.objid=s.subscription2profile
-- Unable to render VIEW DDL for object ERP.TABLE_MYWIN with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_MYWIN
AS select distinct v.name Name,b.name LeadName,b.objid LeadNo,b.Description Description,m.minbidtotal MyBidAmount,b.objid bidrequestid,p.objid profileid,v.objid,v.genuser from table_bidmin m,table_profile p,table_bidrequest b, table_vendorbid v,table_bidtotal t where v.genuser=p.name and v.destinitionid=b.objid and m.bidrequestid=b.objid and m.minbidtotal=t.totalbid and t.bidrequestid=b.objid and t.itemprice2vendorbid=v.objid and b.status=1
-- Unable to render VIEW DDL for object ERP.TABLE_NETASSET with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_NETASSET
AS select distinct nvl(ca.CurrentAsset,0) CurrentAsset,nvl(la.LongTermAsset,0) LongTermAsset,nvl(ta.TotalAsset,0) TotalAsset,nvl(ta.TotalAsset,0)+nvl(tl.TotalLiability,0)+nvl(te.TotalEquity,0) NetAsset,nvl(ll.LongTermLiability,0) LongTermLiability,nvl(tl.TotalLiability,0) TotalLiability,nvl(te.TotalEquity,0) TotalEquity,nvl(cl.CurrentLiability,0) CurrentLiability,b.objid NetAsset2BalanceSheet from table_CurrentAsset ca,table_LongTermAsset la,table_totalasset ta,table_CurrentLiability cl,table_LongTermLiability ll,table_TotalLiability tl,table_TotalEquity te, table_balancesheet b where b.objid=te.TotalEquity2balancesheet(+) and b.objid=tl.TotalLiability2balancesheet(+) and b.objid=ll.LongTermLiability2BalanceSheet(+) and b.objid=cl.CurrentLiability2BalanceSheet(+) and b.objid=ta.totalasset2BalanceSheet(+) and b.objid=la.LongTermAsset2BalanceSheet(+) and b.objid=ca.CurrentAsset2BalanceSheet(+)
-- Unable to render VIEW DDL for object ERP.TABLE_ORDERPENDING with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_ORDERPENDING
AS select distinct p.name Name,pl.Description Description,pp.PartNo PartNo,pl.PartCode PartCode,pp.UmCode UmCode,nvl(p.qntrequest,0) QntPending,nvl(p.qntrequest,0)*nvl(pp.unitprice,0) OrderAmount,pp.objid OrderPending2PartPrice,p.objid Objid,p.groupuser from table_partrequest p,table_partprice pp,table_partlist pl,table_parts pr where pl.objid=pp.partprice2partlist and pp.objid=pr.parts2partprice and pr.objid=p.partrequest2parts
-- Unable to render VIEW DDL for object ERP.TABLE_ORDERRECEIVEDCOUNT with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_ORDERRECEIVEDCOUNT
AS select distinct nvl(sum(r.qntreceived),0) TotalQntReceived,nvl(sum(r.qntaccepted),0) TotalQntAccepted,p.objid OrderReceivedCount2PartRequest,p.objid from table_ItemReceived r,table_partrequest p where p.objid=r.ItemReceived2partrequest(+) group by p.objid
-- Unable to render VIEW DDL for object ERP.TABLE_PARTBID with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_PARTBID
AS select distinct r.name Name,r.description Description,p.name ProjectName,p.projectcode ProjectCode,pl.partcode PartCode,r.QntRequest QntRequest,pr.unitprice UnitPrice,pr.umcode UmCode,nvl(pr.unitprice,0)*nvl(r.QntRequest,0) Total,r.note Note,r.objid, br.objid PartBid2BidRequest,r.objid PartBid2PartRequest, p.objid PartBid2Project,p.groupuser from table_project p,table_parts pt,table_partrequest r,table_partprice pr,table_partlist pl,table_bidrequest br where p.title=r.title and r.partrequest2parts=pt.objid and pt.parts2partprice=pr.objid and pr.partprice2partlist=pl.objid and (r.status=1 or length(r.originid) >0) and p.objid=decode(br.typecode,1,br.bidrequest2project,2,br.bidrequest2project,p.objid)  and pt.maincode=decode(br.typecode,1,br.maincode,br.typecode,3,br.maincode,pt.maincode)
-- Unable to render VIEW DDL for object ERP.TABLE_PARTCOST with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_PARTCOST
AS select distinct sum(p.unitcount*pp.unitprice) EstPartCost,sum(rq.qntrequest*pp.unitprice) ActPartCost,p.parts2Boq  PartCost2Boq from table_parts p, table_partrequest rq, table_partprice pp where pp.objid=p.parts2partprice and p.objid=rq.partrequest2parts(+) group by p.parts2Boq
-- Unable to render VIEW DDL for object ERP.TABLE_PARTCOUNT with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_PARTCOUNT
AS select distinct pr.name Name,pr.umcode UmCode,sum(nvl(b.QntRequest,0)) QntRequest,sum(nvl(b.qntused,0)) QntUsed,sum(nvl(b.QntBalance,0)) QntBalance,pr.objid,pr.objid PartCount2PartPrice from table_bom b,table_partprice pr where pr.objid=b.objid(+) group by pr.objid,pr.name,pr.umcode
-- Unable to render VIEW DDL for object ERP.TABLE_PARTINFO with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_PARTINFO
AS select distinct r.name Name,p.objid ProjectNo,p.name ProjectName,p.projectcode ProjectCode,r.QntRequest QntRequest,r.qntused QntUsed,r.qntpurchased QntPurchased,nvl(r.qntrequest,0)-nvl(r.qntused,0) QntBalance,o.totalqntaccepted totalqntaccepted,i.totalqntdispatched totalqntdispatched,r.status Status,r.startdate StartDate,r.purchasedate PurchaseDate,r.note Note,r.objid PartrequestId,r.objid,p.objid Partinfo2Project,p.groupuser from table_project p,table_Boqplan b,table_milestone m,table_projectplan pp,table_parts pt,table_partrequest r,table_orderreceivedcount o,table_itemdispatchcount i where p.objid=pp.projectplan2project and pp.objid=m.milestone2projectplan and m.objid=b.Boqplan2milestone and b.Boqplan2Boq=pt.parts2Boq and pt.objid= r.partrequest2parts and i.objid=r.objid and o.objid=r.objid and (r.qntrequest-i.totalqntdispatched)>0
-- Unable to render VIEW DDL for object ERP.TABLE_PARTORDER with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_PARTORDER
AS select distinct pp.name Name,pj.Title Title,s.name SupplierName,pl.description Description,pp.umcode UmCode,pl.partcode PartCode,sum(nvl(p.qntbalance,0)-nvl(pi.QntPending,0)) QntMinimum,sum(nvl(p.qntbalance,0)-nvl(pi.QntPending,0)) QntOrdered,sum(nvl(p.qntbalance,0)*pp.unitprice-nvl(pi.QntPending,0)*pp.unitprice) OrderAmount,pl.objid PartOrder2PartList, pp.objid ObjId,pp.groupuser,s.objid PartOrder2Supplier from table_project pj,table_estimation e, table_boq b,table_parts pt,table_partcount p,table_partprice pp,table_supplier s,table_partlist pl,table_InstallPending pi where pj.objid=e.estimation2project and e.objid=b.boq2estimation and b.objid=pt.parts2boq and pt.parts2partprice=pp.objid and p.partcount2partprice=pp.objid and pp.partprice2supplier=s.objid and pp.partprice2partlist=pl.objid and pl.domaincode<3 and pp.objid=pi.InstallPending2PartPrice(+)  group by pp.objid,pp.name,pj.title, pp.umcode,pl.objid,pl.description,pl.partcode,pp.groupuser,s.name,s.objid
-- Unable to render VIEW DDL for object ERP.TABLE_PARTPLAN with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_PARTPLAN
AS select distinct r.name Name,p.name ProjectName,p.projectcode ProjectCode,r.QntRequest QntRequest,r.qntused QntUsed,r.qntpurchased QntPurchased,nvl(r.qntrequest,0)-nvl(r.qntused,0) QntBalance,r.status Status,r.startdate StartDate,r.purchasedate PurchaseDate,r.note Note,r.objid,p.objid PartPlan2ProjectControl,p.groupuser from table_project p,table_Boqplan b,table_milestone m,table_projectplan pp,table_parts pt,table_partrequest r where p.objid=pp.projectplan2project and pp.objid=m.milestone2projectplan and m.objid=b.Boqplan2milestone and b.Boqplan2Boq=pt.parts2Boq and pt.objid= r.partrequest2parts and (r.startdate between sysdate-30 and pp.enddate) and r.status=1
-- Unable to render VIEW DDL for object ERP.TABLE_PARTREQUESTCOUNT with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_PARTREQUESTCOUNT
AS select distinct pt.name Name,sum(nvl(pr.QntRequest,0)) QntRequest,sum(nvl(pr.qntused,0)) QntUsed,sum(nvl(pr.Qntpurchased,0)) QntPurchased,pt.objid,pt.objid PartRequestCount2Parts from table_partrequest pr,table_parts pt where pt.objid=pr.partrequest2parts(+) group by pt.objid,pt.name
-- Unable to render VIEW DDL for object ERP.TABLE_PARTUSE with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_PARTUSE
AS select distinct sum(nvl(r.qntused,0)) QntUsed,sum(nvl(r.qntrequest,0)) QntRequest,sum(nvl(r.qntrequest,0)-nvl(r.qntused,0)) QntBalance,sum(nvl(r.qntrequest,0)-nvl(r.qntpurchased,0)) QntToPurchase,Count(r.objid) LineCount,min(r.startdate) StartDate,pt.deptcode,pt.Parts2PartPrice PartUse2PartPrice,r.pocode,r.objid PartUse2PartRequest from table_PartRequest r,table_parts pt where r.PartRequest2Parts=pt.objid group by pt.deptcode,pt.Parts2PartPrice,r.pocode,r.objid
-- Unable to render VIEW DDL for object ERP.TABLE_PARTUSEBOM with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_PARTUSEBOM
AS select distinct sum(nvl(pu.qntused,0)) QntUsed,sum(nvl(pu.qntrequest,0)) QntRequest,sum(nvl(pu.qntbalance,0)) QntBalance,sum(nvl(pu.QntToPurchase,0)) QntToPurchase,sum(pu.linecount) LineCount,min(pu.startdate) StartDate,pu.deptcode,pu.pocode,pu.PartUse2PartPrice PartUseBom2PartPrice from table_PartUse pu,table_partprice pr where pr.objid=pu.PartUse2PartPrice group by pu.deptcode,pu.pocode,pu.PartUse2PartPrice
-- Unable to render VIEW DDL for object ERP.TABLE_PAYROLEBENIFIT with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_PAYROLEBENIFIT
AS select distinct sum(nvl(b.annualcost,0)) YearlyTotal,sum(nvl(b.monthlycost,0)) MonthlyTotal,e.objid  PayroleBenifit2Employee,p.objid payrolebenifit2payrole from table_Employee e,table_benifit b,table_payrole p where e.objid=b.benifit2employee  and p.payrole2employee=e.objid(+) and b.benifittype =1 group by e.objid,p.objid
-- Unable to render VIEW DDL for object ERP.TABLE_PAYROLETOTAL with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_PAYROLETOTAL
AS select distinct nvl(s.TaxedAmount,0) s_TaxedAmount,nvl(s1.NonTaxedAmount,0) s_NonTaxedAmount,nvl(a.TaxedAmount,0) a_TaxedAmount,nvl(a1.NonTaxedAmount,0) a_NonTaxedAmount,nvl(d.TaxedAmount,0) d_TaxedAmount,nvl(d1.NonTaxedAmount,0) d_NonTaxedAmount,nvl(a.TaxedAmount,0) t_TaxedAmount,nvl(d1.NonTaxedAmount,0)+nvl(s1.NonTaxedAmount,0) t_NonTaxedAmount,nvl(pb.MonthlyTotal,0)+nvl(a.TaxedAmount,0)-nvl(d1.NonTaxedAmount,0)-nvl(s1.NonTaxedAmount,0) p_TaxedAmount,(nvl(pb.MonthlyTotal,0) +nvl(a.TaxedAmount,0)-nvl(d1.NonTaxedAmount,0)-nvl(s1.NonTaxedAmount,0))*nvl(t.salarytax,0)/100 p_TaxPaid,nvl(pb.MonthlyTotal,0) BenifitTotal,(nvl(pb.MonthlyTotal,0) +nvl(a.TaxedAmount,0)-nvl(d1.NonTaxedAmount,0)-nvl(s1.NonTaxedAmount,0)*(1-nvl(t.salarytax,0))/100) AfterTax,((nvl(pb.MonthlyTotal,0)+nvl(a.TaxedAmount,0)-nvl(d1.NonTaxedAmount,0)-nvl(s1.NonTaxedAmount,0))*(1-nvl(t.salarytax,0)/100)+nvl(a1.NonTaxedAmount,0)-nvl(d.TaxedAmount,0)-nvl(s.TaxedAmount,0)) NetAfterTax,p.objid  PayroleTotal2Payrole,p.objid, e.name,p.paydate from table_employee e,table_Payrole p,table_SubstructTaxed s,table_substructNonTaxed s1, table_deductionTaxed d,table_deductionNonTaxed d1,table_additionTaxed a, table_additionNonTaxed a1, table_salarytax t,table_payrolebenifit pb where e.objid=p.payrole2employee and p.objid=pb.PayroleBenifit2Payrole(+) and p.objid=a.additionTaxed2payrole(+) and p.objid=a1.additionNonTaxed2payrole(+) and p.objid=d.deductionTaxed2payrole(+) and p.objid=d1.deductionNonTaxed2payrole(+) and p.objid=s.substructTaxed2payrole(+) and p.objid=s1.substructNontaxed2payrole(+) and e.objid=t.salarytax2employee(+)
-- Unable to render VIEW DDL for object ERP.TABLE_PHASETOTAL with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_PHASETOTAL
AS select distinct p.name Name,sum(nvl(bt.totaljobcost,0)) Total,p.objid, p.objid PhaseTotal2Phase,p.phase2site phasetotal2site from table_Phase p,Table_budgettotal bt,table_project pj where p.objid=pj.project2phase(+) and pj.objid=bt.budgettotal2project (+) group by p.objid, p.name,p.phase2site
-- Unable to render VIEW DDL for object ERP.TABLE_PLANRECEIVED with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_PLANRECEIVED
AS select distinct nvl(sum((bt.totalwithtax * b.planpct/100)),0) PlannedAmount,nvl(sum(p.payment),0) Invoiced,nvl(sum(bt.totalwithtax * b.planpct/100),0)-nvl(sum(p.payment),0) NotInvoiced,b.yearcode,b.monthcode, b.objid PlanReceived2BudgetPlan from table_monthlypayment p, table_budgetplan b,table_budgettotal bt where bt.budgettotal2budget=b.budgetplan2budget and b.budgetplan2budget=p.monthlypayment2budget(+) and b.yearcode=p.yearcode and b.monthcode=p.monthcode group by b.objid,b.yearcode,b.monthcode
-- Unable to render VIEW DDL for object ERP.TABLE_POAMOUNT with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_POAMOUNT
AS select distinct sum(nvl(pi.total,0)) Total,po.objid  PoAmount2PurchaseOrder from table_purchaseindent pi ,table_purchaseorder po where po.objid=pi.purchaseindent2PurchaseOrder group by po.objid
-- Unable to render VIEW DDL for object ERP.TABLE_POBUDGET with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_POBUDGET
AS select distinct sum(nvl(pa.total,0)) PoTotal,p.deptcode,b.objid PoBudget2Budget from table_poamount pa,table_purchaseorder p,table_pomaster pm,table_budget b where p.objid=pa.poamount2purchaseorder and p.purchaseorder2pomaster=pm.objid and pm.pomaster2project=b.budget2project group by p.deptcode,b.objid
-- Unable to render VIEW DDL for object ERP.TABLE_POBUDGETHEAD with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_POBUDGETHEAD
AS select distinct decode(nvl(pb.deptcode,'CIVIL'),'CIVIL',rb.deptcode,pb.deptcode) DeptCode,sum(nvl(pb.pototal,0)+nvl(rb.restotal,0)) Total,b.objid PoBudgetHead2Budget from table_pobudget pb,table_resbudget rb,table_budget b where b.objid=pb.pobudget2budget(+) and b.objid=rb.resbudget2budget(+) group by b.objid,decode(nvl(pb.deptcode,'CIVIL'),'CIVIL',rb.deptcode,pb.deptcode)
-- Unable to render VIEW DDL for object ERP.TABLE_POORDERINFO with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_POORDERINFO
AS select distinct r.name Name,po.objid PoNo,p.objid ProjectNo,p.name ProjectName,r.QntRequest QntRequest,r.qntused QntUsed,r.qntpurchased QntPurchased,nvl(r.qntrequest,0)-nvl(r.qntused,0) QntBalance,nvl(t.qntaccepted,0) qntaccepted,r.status Status,r.startdate StartDate,r.purchasedate PurchaseDate,r.objid PartrequestId,r.objid,p.objid PoOrderInfo2Project,p.groupuser from table_project p,table_Boqplan b,table_milestone m,table_projectplan pp,table_parts pt,table_partrequest r,table_itemreceived t,table_purchaseorder po where p.objid=pp.projectplan2project and pp.objid=m.milestone2projectplan and m.objid=b.Boqplan2milestone and b.Boqplan2Boq=pt.parts2Boq and pt.objid= r.partrequest2parts and r.objid=t.itemreceived2partrequest(+) and po.objid=r.partrequest2purchaseorder
-- Unable to render VIEW DDL for object ERP.TABLE_POPARTINFO with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_POPARTINFO
AS select distinct r.name Name,po.objid PoNo,w.name Warehouse,l.name WarehouseLine,p.objid ProjectNo,p.name ProjectName,r.QntRequest QntRequest,r.qntused QntUsed,r.qntpurchased QntPurchased,nvl(r.qntrequest,0)-nvl(r.qntused,0) QntBalance,nvl(t.qntaccepted,0) qntaccepted,r.status Status,r.startdate StartDate,r.purchasedate PurchaseDate,r.objid PartrequestId,r.objid,p.objid PoPartInfo2Project,p.groupuser from table_project p,table_Boqplan b,table_milestone m,table_projectplan pp,table_parts pt,table_partrequest r,table_itemdispatch i,table_itemreceived t,table_warehouse w,table_warehouseline l,table_purchaseorder po where p.objid=pp.projectplan2project and pp.objid=m.milestone2projectplan and m.objid=b.Boqplan2milestone and b.Boqplan2Boq=pt.parts2Boq and pt.objid= r.partrequest2parts and r.objid=t.itemreceived2partrequest and t.itemreceived2warehouseline=l.objid and l.warehouseline2warehouse=w.objid and po.objid=r.partrequest2purchaseorder and t.itemreceived2warehouseline=i.itemdispatch2warehouseline(+)
-- Unable to render VIEW DDL for object ERP.TABLE_PORESAMOUNT with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_PORESAMOUNT
AS select distinct sum(nvl(tr.QntRequest,0)*nvl(tr.actualrate,0)) Amount,sum(nvl(tr.Qntrequest,0)*nvl(tr.actualrate,0)*nvl(ro.tax/100,0)) TaxAmount,sum(nvl(tr.Qntrequest,0)*nvl(tr.actualrate,0)*nvl(1+ro.tax/100,0)) Total,ro.objid  PoResAmount2ResourceOrder from table_taskresource tr ,table_resourceorder ro where ro.objid=tr.taskresource2resourceorder group by ro.objid
-- Unable to render VIEW DDL for object ERP.TABLE_POSUPPLIER with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_POSUPPLIER
AS select distinct s.name Name,c.name||' '||c.lastname||','||c.street||','||c.city||','||c.zipcode ||',Phone: '||c.phone||',Fax: '||c.fax Address,c.contactcode ContactCode,count(pr.objid) LineCount,pm.pocode,s.objid PoSupplier2Supplier,pm.objid PoSupplier2Pomaster, s.objid objid from table_Supplier s,table_Pomaster pm,table_jobmaster jm, table_joblist j,table_contact c,table_partrequest pr, table_parts pt, table_partprice pp where pm.pomaster2project=jm.jobmaster2project and pm.pocode=1 and jm.objid=j.joblist2jobmaster and j.objid=pt.parts2joblist and pt.objid=pr.partrequest2parts and pt.parts2partprice=pp.objid and pp.partprice2supplier=s.objid and s.objid= c.contact2supplier(+) group by s.objid,s.name,c.name,c.lastname,c.street,c.city,c.zipcode,c.phone,c.fax,c.contactcode,pm.objid,pm.pocode
-- Unable to render VIEW DDL for object ERP.TABLE_PPFLOAT with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_PPFLOAT
AS select distinct p1.objid Objid,nvl(min(p2.floatcount),0) FloatCount,ps.schedule Schedule,p1.objid PPFloat2ProjectPlan from table_ppschedule ps,table_Projectplan p1,table_PPlink p2 where  ps.objid=p1.objid and p1.objid=p2.PPLink2ProjectPlan(+) group by p1.objid,ps.schedule
-- Unable to render VIEW DDL for object ERP.TABLE_PPLINK with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_PPLINK
AS select distinct p1.objid Objid,p1.name Name,p1.title Title,p1.projectcode ProjectCode,p1.maincode MainCode,j1.MainOffSetTime StartBefore,round((p2.StartDate-p1.startdate)) StartActual,round(((p2.StartDate-p1.startdate)-j1.MainOffSetTime)) FloatCount,p1.startdate StartDate,p1.enddate EndDate,p2.objid PPLink2ProjectPlan from table_projectplan p1,table_projectplan p2,table_joblist j1 where j1.maincode=p2.maincode and p1.maincode=j1.mainjobcode and j1.mainjobcode<>-100 and p1.maincode<>p2.maincode and j1.joblist2project=p1.projectplan2project and p1.projectplan2project=p2.projectplan2project
-- Unable to render VIEW DDL for object ERP.TABLE_PPPROGRESS with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_PPPROGRESS
AS select distinct round((nvl(sum(ms.qntfinished*b.rate),0)/decode(nvl(sum(ms.netBoqqnt*b.rate),1),0,1,1,1,sum(ms.netBoqqnt*b.rate)))*100,2) AchievedTarget,p.objid PPProgress2ProjectPlan from table_projectplan p,table_msprogress ms,table_milestone m,table_Boq b where p.objid=m.milestone2projectplan and m.objid=ms.msprogress2milestone(+) and m.milestone2Boq=b.objid group by p.objid
-- Unable to render VIEW DDL for object ERP.TABLE_PPSCHEDULE with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_PPSCHEDULE
AS select distinct p1.objid Objid,(round(p1.startdate-p.startdate)||'@'||round(p1.enddate-p1.startdate)||'@'||round(p.enddate-p.startdate)) Schedule,p1.objid PPSchedule2ProjectPlan from table_project p,table_Projectplan p1 where  p.objid=p1.projectplan2project
-- Unable to render VIEW DDL for object ERP.TABLE_PROJECTCONTROL with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_PROJECTCONTROL
AS select distinct p.objid ObjId,p.name Name,p.title Title,p.projectcode ProjectCode,p.startdate StartDate,b.totalcontract ContractCost,b.cumulativeamount ActualCost,b.achievedtarget AchievedTarget,b.budgetused BudgetUsed,p.groupuser, p.objid ProjectControl2Project from table_project p, table_budgettotal b where p.objid=b.budgettotal2project(+)
-- Unable to render VIEW DDL for object ERP.TABLE_PROJECTLEAD with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_PROJECTLEAD
AS select distinct b.name Name,b.Description Description,b.Criteria Criteria,b.ListDate ListDate,g.BidDueDate CloseDate,nvl(b.BgtRangeCode,0) BgtRangeCode,b.StartCode StartCode,g.ZipCode ZipCode,g.address Address,g.state State,g.contactname ContactName,g.phone Phone,b.Instruction Instruction,b.objid ObjId,b.objid ProjectLead2BidRequest, b.gendate,b.typecode,b.genuser,b.groupuser from table_BidRequest b,table_general g where b.status=1 and g.general2bidrequest=b.objid order by b.gendate desc
-- Unable to render VIEW DDL for object ERP.TABLE_PURCHASEINDENT with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_PURCHASEINDENT
AS select distinct p.name Name,i.IndentNo IndentNo,b.MrNo MrNo,b.partno PartNo,p.description Description,b.umcode UmCode,pt.deptcode DeptCode,b.pocode PoCode,nvl(p.QntRequest,0) QntRequest,nvl(p.QntRequest,0)*(1+ nvl(p.PctTax,0)/100)*nvl(p.actualrate,0) Total,p.PartRequest2PurchaseOrder PurchaseIndent2PurchaseOrder,p.objid from table_ItemDispatchCount i,table_partrequest p,table_parts pt,table_bompurchase b where p.objid=i.ItemDispatchCount2partrequest and p.partrequest2parts=pt.objid and b.objid=pt.parts2partprice
-- Unable to render VIEW DDL for object ERP.TABLE_QUIZTOTAL with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_QUIZTOTAL
AS select distinct sum(to_number(bq.point)) TotalPoint,(sum(to_number(qr.pointearned))/sum(to_number(bq.point)))*100 TotalPct,sum(to_number(qr.pointearned)) TotalEarned,v.objid  QuizTotal2Bids from table_VendorBid v,table_quizreply qr,table_bidquiz bq where bq.objid=qr.destinitionid and v.objid=qr.quizreply2vendorbid and v.destinitionid=bq.bidquiz2bidrequest group by v.objid
-- Unable to render VIEW DDL for object ERP.TABLE_QUOTECALL with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_QUOTECALL
AS select distinct q.name Name,q.address Address,q.city City,q.zipcode ZipCode,q.state State,q.firstname FirstName,q.lastname LastName,q.phone Phone,q.phone2 Phone2,q.fax Fax,q.email Email,q.status Status,q.objid objid,q.groupuser,q.objid QuoteCall2Quote from table_Quote q where q.status=50
-- Unable to render VIEW DDL for object ERP.TABLE_QUOTEEST with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_QUOTEEST
AS select distinct q.name Name,q.unitcount UnitCount,s.umcode UmCode,decode(qt.quotetype,1, nvl(s.unitrate,0),0) UnitRate,decode(qt.quotetype,1,nvl(q.unitcount,0) * nvl(s.unitrate,0), (nvl(qr.totalcost,0)+ nvl(qp.totalcost,0)) *(1+(nvl(qt.pctwaste,10)+nvl(qt.pctprofit,50))/100)) Total,(nvl(qr.totalcost,0)+ nvl(qp.totalcost,0)) *(1+(nvl(qt.pctwaste,10)+nvl(qt.pctprofit,50))/100) TotalCostBasis,TRUNC((NVL(qr.totalcost,0)+ NVL(qp.totalcost,0)) /NVL(DECODE(q.unitcount,0,1,q.unitcount),1)*(1+(NVL(qt.pctwaste,10)+NVL(qt.pctprofit,50))/100),2) ProfitRate,trunc((nvl(qr.totalcost,0)+ nvl(qp.totalcost,0))/nvl(decode(q.unitcount,0,1,q.unitcount),1),2) EstRate,nvl(q.unitcount,0) * nvl(s.unitrate,0) TotalRateBasis,q.objid objid,q.quotejobs2quoteMaster QuoteEst2QuoteMaster,q.objid  QuoteEst2QuoteJobs from table_QuoteJobs q,table_quoterescost qr,table_QuotePartCost qp,table_quotemaster qm, table_quote qt, table_subcode s where q.objid=qr.quoterescost2quotejobs and q.objid=qp.QuotePartCost2quotejobs and qm.objid=q.quotejobs2quotemaster and qm.quotemaster2quote=qt.objid and q.projectcode=s.projectcode and q.maincode=s.mainjobcode and q.subcode=s.subjobcode
-- Unable to render VIEW DDL for object ERP.TABLE_QUOTEMASTEREST with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_QUOTEMASTEREST
AS select distinct m.name Name,sum(nvl(qe.total,0)) Total,m.objid objid,m.objid QuoteMasterEst2QuoteMaster,m.quotemaster2quote from table_QuoteMaster m,table_quoteest qe where m.objid=qe.quoteest2quotemaster group by m.objid,m.name,m.quotemaster2quote
-- Unable to render VIEW DDL for object ERP.TABLE_QUOTEPARTCOST with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_QUOTEPARTCOST
AS select distinct sum(nvl(qp.UnitCount,0)* nvl(p.unitprice,0)) TotalCost,q.objid, q.objid QuotePartCost2QuoteJobs from table_quotejobs q,table_QuoteParts qp,table_Partprice p where q.objid=qp.QuoteParts2QuoteJobs(+) and qp.QuoteParts2Partprice=p.objid(+) group by q.objid
-- Unable to render VIEW DDL for object ERP.TABLE_QUOTERESCOST with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_QUOTERESCOST
AS select distinct sum(nvl(qr.EstUnit,0)* nvl(p.unitprice,0)) TotalCost,q.objid, q.objid QuoteResCost2QuoteJobs from table_quotejobs q,table_QuoteResource qr,table_Partprice p where q.objid=qr.QuoteResource2QuoteJobs(+) and qr.QuoteResource2Partprice=p.objid(+) group by q.objid
-- Unable to render VIEW DDL for object ERP.TABLE_QUOTETOTAL with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_QUOTETOTAL
AS select distinct q.name Name,sum(nvl(qm.total,0)) Total,q.objid, q.objid QuoteTotal2Quote from table_Quote q,Table_quotemasterest qm where q.objid=qm.quotemaster2quote group by q.objid, q.name
-- Unable to render VIEW DDL for object ERP.TABLE_REQUISITIONTIME with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_REQUISITIONTIME
AS select distinct sum(rt.qntused) QntUsed,sum(r.estunit) QntRequest,r.objid requisition2taskresource from table_ReqUseTime rt,table_taskresource r where r.objid=rt.requsetime2requisition(+) group by r.objid
-- Unable to render VIEW DDL for object ERP.TABLE_REQUSETIME with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_REQUSETIME
AS select distinct sum(ts.regulartime)+sum(ts.overtime) QntUsed,sum(r.estunit)-sum(ts.regulartime)-sum(ts.overtime) qntbalance,r.objid ReqUseTime2Requisition from table_timesheet ts,table_taskresource r where r.objid=ts.timesheet2taskresource group by r.objid
-- Unable to render VIEW DDL for object ERP.TABLE_RESBUDGET with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_RESBUDGET
AS select distinct sum(nvl(pa.total,0)) ResTotal,r.deptcode,b.objid ResBudget2Budget from table_poresamount pa,table_resourceorder r,table_pomaster pm,table_budget b where r.objid=pa.poresamount2resourceorder and r.resourceorder2pomaster=pm.objid and pm.pomaster2project=b.budget2project group by r.deptcode,b.objid
-- Unable to render VIEW DDL for object ERP.TABLE_RESOURCEAUDIT with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_RESOURCEAUDIT
AS select distinct tr.name Name,tr.Title Title,tr.ProjectCode ProjectCode,tr.MainCode MainCode,tr.SubCode SubCode,tr.TaskCode TaskCode,tr.ResourceCode ResourceCode,tr.EstUnit EstUnit,ru.QntUsed ActualUnit,pp.UmCode UmCode,pp.unitprice Rate,tr.OriginCode OriginCode,tr.groupuser,tr.objid,bp.objid ResourceAudit2BoqPlan from table_ResourceUse ru,table_partprice pp,table_TaskResource tr,table_Boqplan bp where tr.objid=ru.resourceuse2taskResource(+) and tr.TaskResource2partprice=pp.objid and bp.Boqplan2Boq=tr.taskresource2Boq
-- Unable to render VIEW DDL for object ERP.TABLE_RESOURCECOST with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_RESOURCECOST
AS select distinct sum(r.estunit*pp.unitprice) EstResCost,sum(r.qntrequest*pp.unitprice) ActResCost,r.taskresource2Boq ResourceCost2Boq from table_taskresource r, table_partprice pp where pp.objid=r.taskresource2partprice group by r.taskresource2Boq
-- Unable to render VIEW DDL for object ERP.TABLE_RESOURCEPLAN with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_RESOURCEPLAN
AS select distinct tr.name Name,p.name ProjectName,p.projectcode ProjectCode,r.qntrequest QntRequest,r.qntused QntUsed,r.qntbalance QntBalance,m.status Status,tr.startdate StartDate,tr.enddate EndDate,m.note Note,tr.objid,p.objid ResourcePlan2ProjectControl,p.groupuser from table_project p,table_milestone m,table_projectplan pp,table_taskresource tr,table_resourceuse r where p.objid=pp.projectplan2project and pp.objid=m.milestone2projectplan and m.objid=tr.taskresource2milestone  and tr.objid=r.resourceuse2taskresource(+) and ((tr.startdate between sysdate-30 and sysdate+30) or (tr.enddate between sysdate-5 and sysdate+60)) and m.status=1
-- Unable to render VIEW DDL for object ERP.TABLE_RESOURCES with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_RESOURCES
AS select distinct pp.name Name,pp.partgroup PartGroup,pp.ServiceLife ServiceLife,pl.domaincode DomainCode,pl.partcode PartCode,pp.umcode UmCode,pp.unitprice UnitPrice,pp.PartSpec PartSpec,pp.note Note,pp.partno PartNo,pp.objid,pp.objid Resources2PartList,pp.groupuser,pp.genuser from table_partprice pp,table_partlist pl where pp.partprice2partlist=pl.objid and to_number(pl.domaincode) between 3 and 7
-- Unable to render VIEW DDL for object ERP.TABLE_RESOURCESUPPLIER with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_RESOURCESUPPLIER
AS select distinct s.name Name,c.name||' '||c.lastname||','||c.street||','||c.city||','||c.zipcode ||',Phone: '||c.phone||',Fax: '||c.fax Address,c.contactcode ContactCode,count(tr.objid) TaskCount,pm.pocode,tr.resourcecode,s.objid ResourceSupplier2Supplier,pm.objid ResourceSupplier2Pomaster, s.objid objid from table_Supplier s,table_Pomaster pm,table_jobmaster jm, table_joblist j,table_contact c,table_taskresource tr, table_partprice pp where pm.pomaster2project=jm.jobmaster2project and pm.pocode=1 and jm.objid=j.joblist2jobmaster and j.objid=tr.taskresource2joblist and tr.taskresource2partprice=pp.objid and pp.partprice2supplier=s.objid and s.objid= c.contact2supplier(+) group by s.objid,s.name,c.name,c.lastname,c.street,c.city,c.zipcode,c.phone,c.fax,c.contactcode,tr.resourcecode,pm.objid,pm.pocode
-- Unable to render VIEW DDL for object ERP.TABLE_RESOURCEUSE with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_RESOURCEUSE
AS select distinct sum(rt.qntused) QntUsed,sum(rt.qntrequest) QntRequest,sum(rt.qntrequest)-sum(rt.qntused) QntBalance,rs.objid ResourceUse2TaskResource from table_RequisitionTime rt,table_taskresource rs where rs.objid=rt.requisition2taskresource(+) group by rs.objid
-- Unable to render VIEW DDL for object ERP.TABLE_RFICOUNT with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_RFICOUNT
AS select distinct br.name Name,nvl(bd.responsecount,0) ResponseCount,nvl(bd.bidcount,0) BidCount,count(r.objid) TotRFICount,br.objid RFICount2BidRequest from table_bidcount bd,table_requestinfo r,table_bidrequest br where br.objid=r.requestinfo2bidrequest(+) and bd.bidcount2bidrequest=br.objid group by br.name,br.objid,bd.responsecount,bd.bidcount
-- Unable to render VIEW DDL for object ERP.TABLE_SALARYTAX with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_SALARYTAX
AS select distinct sum(nvl(t.pct,0)) SalaryTax,e.objid  SalaryTax2Employee from table_Employee e,table_tax t where e.objid=t.tax2employee(+) group by e.objid
-- Unable to render VIEW DDL for object ERP.TABLE_SERVICECOST with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_SERVICECOST
AS select distinct a.name Name,a.location Location,a.description Description,sum(nvl(s.LaborCost,0)) LaborCost,sum(nvl(s.partcost,0)) PartCost,sum(nvl(s.totalcost,0)) TotoalCost,s.year Year,a.objid,a.objid ServiceCost2Asset, a.groupuser,a.genuser from table_AssetService s,table_asset a where a.objid=s.assetid(+) group by a.objid,a.name,a.location,a.description,s.year,a.groupuser,a.genuser order by s.year
-- Unable to render VIEW DDL for object ERP.TABLE_SERVICELABOR with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_SERVICELABOR
AS select distinct s.name Name,pp.partno PartNo,pp.umcode UmCode,s.quantity UnitCount,pp.unitprice UnitPrice,s.quantity*pp.unitprice PartCost,s.ServiceResource2maintenance MaintenanceId,s.objid,s.objid ServiceLabor2ServiceResource,s.ServiceResource2Maintenance ServiceLabor2Maintenance, pp.objid ServiceLabor2PartPrice, s.groupuser,s.genuser from table_ServiceResource s,table_partprice pp where pp.objid=s.ServiceResource2Partprice
-- Unable to render VIEW DDL for object ERP.TABLE_SERVICEPART with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_SERVICEPART
AS select distinct pp.name Name,pp.partgroup PartGroup,pp.ServiceLife ServiceLife,pl.domaincode DomainCode,pl.partcode PartCode,pp.umcode UmCode,pp.unitprice UnitPrice,pp.PartSpec PartSpec,pp.note Note,pp.partno PartNo,pp.objid,pp.objid ServicePart2PartList, pp.groupuser,pp.genuser from table_partprice pp,table_partlist pl where pp.partprice2partlist=pl.objid and to_number(pl.domaincode)=8
-- Unable to render VIEW DDL for object ERP.TABLE_SERVICEPARTCOST with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_SERVICEPARTCOST
AS select distinct s.name Name,pp.partno PartNo,pp.umcode UmCode,s.unitcount UnitCount,pp.unitprice UnitPrice,s.unitcount*pp.unitprice PartCost,s.ServiceParts2maintenance MaintenanceId,s.objid,s.objid ServicePartCost2ServiceParts,s.ServiceParts2Maintenance ServicePartCost2Maintenance, pp.objid ServicePartCost2PartPrice, pp.groupuser,pp.genuser from table_ServiceParts s,table_partprice pp where pp.objid=s.ServiceParts2Partprice
-- Unable to render VIEW DDL for object ERP.TABLE_SERVICERESOURCES with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_SERVICERESOURCES
AS select distinct pp.name Name,pp.partgroup PartGroup,pp.ServiceLife ServiceLife,pl.domaincode DomainCode,pl.partcode PartCode,pp.umcode UmCode,pp.unitprice UnitPrice,pp.PartSpec PartSpec,pp.note Note,pp.partno PartNo,pp.objid,pp.objid ServiceResources2PartList,pp.groupuser,pp.genuser from table_partprice pp,table_partlist pl where pp.partprice2partlist=pl.objid and to_number(pl.domaincode)=9
-- Unable to render VIEW DDL for object ERP.TABLE_SERVICETOTAL with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_SERVICETOTAL
AS select distinct m.name Name,sum(nvl(p.partcost,0)) PartCost,sum(nvl(l.partcost,0)) LaborCost,sum(nvl(p.partcost,0))+sum(nvl(l.partcost,0)) TotalCost,m.objid,m.objid ServiceTotal2Maintenance, m.groupuser,m.genuser from table_ServiceLabor l,table_ServicePartCost p,table_maintenance m where m.objid=l.ServiceLabor2Maintenance and l.ServiceLabor2Maintenance=p.ServicePartCost2Maintenance(+) group by m.objid,m.name,m.groupuser,m.genuser
-- Unable to render VIEW DDL for object ERP.TABLE_SITETOTAL with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_SITETOTAL
AS select distinct s.name Name,sum(nvl(pt.total,0)) Total,s.objid, s.objid SiteTotal2Site from table_Site s,Table_Phasetotal pt where s.objid=pt.phasetotal2site (+) group by s.objid, s.name
-- Unable to render VIEW DDL for object ERP.TABLE_SUBSTRUCTNONTAXED with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_SUBSTRUCTNONTAXED
AS select distinct sum(nvl(s.amount,0)) NonTaxedAmount,p.objid  SubstructNonTaxed2Payrole from table_Payrole p,table_Substruction s where p.payrole2employee=s.Substruction2employee(+) and nvl(s.applydate,p.startdate+1) between p.startdate and p.enddate and s.taxtypecode(+)=1 group by p.objid
-- Unable to render VIEW DDL for object ERP.TABLE_SUBSTRUCTTAXED with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_SUBSTRUCTTAXED
AS select distinct sum(nvl(s.amount,0)) TaxedAmount,p.objid  SubstructTaxed2Payrole from table_Payrole p,table_Substruction s where p.payrole2employee=s.Substruction2employee(+) and nvl(s.applydate,p.startdate+1) between p.startdate and p.enddate and s.taxtypecode(+) =2 group by p.objid
-- Unable to render VIEW DDL for object ERP.TABLE_TASKPROGRESS with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_TASKPROGRESS
AS select distinct (sum(ru.qntused*pp.unitprice)/decode(sum(rs.estunit*pp.unitprice),0,1))*(sum(ru.qntused)/decode(sum(ru.qntrequest),0,1))*100 AchievedTarget,sum(rs.estunit*pp.unitprice) EstTaskCost,sum(rs.estunit*pp.unitprice)*(sum(ru.qntused)/decode(sum(ru.qntrequest),0,1,sum(ru.qntrequest))) ActTaskCost,m.objid TaskProgress2MileStone from table_ResourceUse ru,table_MileStone m,table_taskresource rs,table_partprice pp where m.objid=rs.taskresource2milestone and rs.objid=ru.resourceuse2taskresource and pp.objid=rs.taskresource2partprice group by m.objid
-- Unable to render VIEW DDL for object ERP.TABLE_TOTALASSET with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_TOTALASSET
AS select distinct sum(nvl(c.CurrentAsset,0)+nvl(l.LongTermAsset,0)) TotalAsset,c.CurrentAsset2balancesheet TotalAsset2BalanceSheet from table_CurrentAsset c,table_LongTermAsset l where c.CurrentAsset2balancesheet=l.LongTermAsset2balancesheet(+)  group by c.CurrentAsset2balancesheet
-- Unable to render VIEW DDL for object ERP.TABLE_TOTALBOQCOST with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_TOTALBOQCOST
AS select distinct nvl(sum(bp.estpartcost),0)+nvl(sum(bc.estchangecost),0)+nvl(sum(br.estrescost),0) EstBoqCost,nvl(sum(bp.actpartcost),0)+nvl(sum(bc.actchangecost),0)+nvl(sum(br.actrescost),0) ActBoqCost,nvl(sum(bc.estchangecost),0) EstBoqChangeCost,nvl(sum(bc.actchangecost),0) ActBoqChangeCost,b.objid  TotalBoqCost2Boq from table_Boqchangecost bc,table_Boqpartcost bp, table_Boqresourcecost br, table_Boq b where b.objid=br.Boqresourcecost2Boq and b.objid=bp.Boqpartcost2Boq and b.objid=bc.Boqchangecost2Boq(+)  group by b.objid
-- Unable to render VIEW DDL for object ERP.TABLE_TOTALCHANGECOST with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_TOTALCHANGECOST
AS select distinct sum(nvl(cp.estpartcost,0))+sum(nvl(cr.estrescost,0)) EstChangeCost,sum(nvl(cp.actpartcost,0))+sum(nvl(cr.actrescost,0)) ActChangeCost,cg.objid  TotalChangeCost2Change from table_changeresourcecost cr,table_changepartcost cp,table_change cg where cp.changepartcost2change=cg.objid and cr.changeresourcecost2change=cg.objid and cp.changepartcost2change=cr.changeresourcecost2change(+) group by cg.objid
-- Unable to render VIEW DDL for object ERP.TABLE_TOTALEQUITY with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_TOTALEQUITY
AS select distinct sum(nvl(bd.amount,0)) TotalEquity,bd.accountgroup, b.objid TotalEquity2BalanceSheet from table_balancedetails bd,table_balancesheet b where b.objid=bd.balancedetails2balancesheet(+) and bd.accountgroup='5' group by bd.accountgroup,b.objid
-- Unable to render VIEW DDL for object ERP.TABLE_TOTALLIABILITY with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_TOTALLIABILITY
AS select distinct sum(nvl(bd.amount,0)) TotalLiability,b.objid TotalLiability2BalanceSheet from table_balancedetails bd,table_balancesheet b where b.objid=bd.balancedetails2balancesheet(+) and to_number(bd.accountgroup) between 3 and 4 group by bd.accountgroup,b.objid
-- Unable to render VIEW DDL for object ERP.TABLE_TOTALPROJECTION with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_TOTALPROJECTION
AS select distinct sum(nvl(mr.ForcastAmount,0)) ForcastRevenue,sum(nvl(mr.EarnedRevenue,0)) EarnedRevenue,sum(nvl(mr.NotInvoiced,0)) Balance,mr.MonthlyRevenue2Projection objid, mr.MonthlyRevenue2Projection TotalProjection2Projection from table_MonthlyRevenue mr where nvl(mr.yearcode,0)>0 group by mr.MonthlyRevenue2Projection
-- Unable to render VIEW DDL for object ERP.TABLE_TOTALTAX with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_TOTALTAX
AS select distinct nvl(sum(t.pct),0) TotalTax,p.objid  TotalTax2Project from table_project p,table_tax t where p.objid=t.tax2project(+) group by p.objid
-- Unable to render VIEW DDL for object ERP.TABLE_VACATIONTAKEN with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_VACATIONTAKEN
AS select distinct nvl(sum(v.daytaken),0) Daytaken,v.vacationcode,v.yearcode,e.objid  VacationTaken2Employee from table_Employee e,table_Vacation v where e.objid=v.vacation2employee(+)  group by v.vacationcode,v.yearcode,e.objid
-- Unable to render VIEW DDL for object ERP.TABLE_VACATIONTOTAL with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_VACATIONTOTAL
AS select distinct (nvl(v.totalmonth,0)*nvl(v.dayspermonth,0)) TotalDays,(nvl(v.totalmonth,0)*nvl(v.dayspermonth,0))+nvl(v.lastbalance,0)-nvl(t.Daytaken,0) BalanceDays,v.vacationcode,v.yearcode,v.objid VacationTotal2EarnedVacation, e.objid  VacationTotal2Employee from table_Employee e,table_EarnedVacation v,table_VacationTaken t where e.objid=v.EarnedVacation2employee(+) and v.EarnedVacation2employee=t.VacationTaken2Employee(+)
-- Unable to render VIEW DDL for object ERP.TABLE_WEEKLEADCONTACT with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_WEEKLEADCONTACT
AS select distinct c.name||' '||c.lastname Name,c.street||','||c.city||','||c.state Street,c.phone Phone,c.fax Fax,c.email Email,c.contactcode Type,c.objid,c.contact2bidrequest WeekLeadContact2WeeklyLead from table_contact c,table_weeklylead w where w.objid=c.contact2bidrequest
-- Unable to render VIEW DDL for object ERP.TABLE_WEEKLEADJOBS with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_WEEKLEADJOBS
AS select distinct j.name Name,j.description Description,j.unitprice UnitPrice,j.unitcount UnitCount,j.umcode UmCode,e.JobTotal Total,j.objid,j.jobs2bidrequest WeekLeadJobs2WeeklyLead from table_jobs j,table_weeklylead w,table_jobest e where w.objid=j.jobs2bidrequest and j.objid=e.objid
-- Unable to render VIEW DDL for object ERP.TABLE_WEEKLYBID with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_WEEKLYBID
AS select distinct p.name Name,p.Description Description,p.Criteria Criteria,p.ListDate ListDate,p.closedate CloseDate,nvl(p.BgtRangeCode,0) BgtRangeCode,p.StartCode StartCode,l.ResponseCount ResponseCount,l.BidCount BidCount,p.ZipCode ZipCode,p.address Address,p.state State,p.Instruction Instruction,p.objid ObjId,p.objid WeeklyBid2BidRequest, p.gendate,p.genuser,p.groupuser from table_ProjectLead p,table_leadresult l where p.gendate<sysdate and p.gendate>sysdate-8 and p.objid=l.LeadResult2bidrequest(+) order by p.gendate desc
-- Unable to render VIEW DDL for object ERP.TABLE_WEEKLYLEAD with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_WEEKLYLEAD
AS select distinct p.name Name,p.Description Description,p.Criteria Criteria,p.ListDate ListDate,p.closedate CloseDate,nvl(p.BgtRangeCode,0) BgtRangeCode,p.StartCode StartCode,p.ZipCode ZipCode,p.address Address,p.state State,p.contactname ContactName,p.phone Phone,p.Instruction Instruction,p.objid ObjId,p.objid WeeklyLead2BidRequest, p.gendate,p.genuser from table_ProjectLead p where  p.gendate<sysdate and p.gendate>sysdate-8 order by p.gendate desc
-- Unable to render VIEW DDL for object ERP.TABLE_YEARLYCOST with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_YEARLYCOST
AS select distinct e.name Name,e.description Description,sum(nvl(s.LaborCost,0)) LaborCost,sum(nvl(s.partcost,0)) PartCost,sum(nvl(s.totalcost,0)) TotoalCost,s.year Year,e.objid,e.objid YearlyCost2Equipment, e.groupuser,e.genuser from table_EquipService s,table_equipment e where e.objid=s.equipid(+) group by e.objid,e.name,e.description,s.year,e.groupuser,e.genuser order by s.year
-- Unable to render VIEW DDL for object ERP.TABLE_YEARLYFORCAST with DBMS_METADATA attempting internal generator.
CREATE VIEW TABLE_YEARLYFORCAST
AS select distinct nvl(sum(ma.monthlyapproved),0) YearlyApproved,nvl(sum(me.monthlyestimate),0) YearlyEstimated,round((sum(ma.monthlyapproved)/decode(sum(bt.totalcontract),0,1,sum(bt.totalcontract)))*100,2) PctProjectApproved,round((sum(me.monthlyestimate)/decode(sum(bt.totalcontract),0,1,sum(bt.totalcontract)))*100,2) PctProjectEstimate,y.objid YearlyForcast2Yearly from table_monthlyapproved ma,table_monthlyestimate me,table_monthly m,table_yearly y, table_budgettotal bt where m.objid=ma.monthlyapproved2monthly and me.monthlyestimate2monthly=m.objid and m.monthly2budget=bt.budgettotal2budget(+) and m.yearcode=y.yearcode and y.yearly2budget=m.monthly2budget group by y.objid

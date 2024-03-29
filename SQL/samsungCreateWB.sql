USE [edi]
GO
/****** Object:  StoredProcedure [dbo].[samsungCreateWb]    Script Date: 02/22/2022 13:15:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		vd.zinovev
-- Create date: 09.02.2022
-- Description:	Создание веб накладных Самсунг
-- Example:		EXEC samsungCreateWb @OrederNO = 'PKGINF_7072831440'
-- =============================================
ALTER PROCEDURE [dbo].[samsungCreateWb]
AS
BEGIN
	-- Переменные для вставкм
	-- ============================================
	declare @deliveryID int
	declare @wb_no		varchar(50)
	declare @order_no	varchar(50)
	
	declare @org		varchar(5)		= 'MOW'
	declare @s_city_id	int				= 37659
	declare @s_city		varchar(200)	= 'Москва'
	declare @s_name		varchar(20)		= 'ООО "САМСУНГ СДС РУС"'
	declare @s_tel		varchar(50)		= ''
	declare @s_co		varchar(40)		= 'ООО "САМСУНГ СДС РУС"'
	declare @s_adr		varchar(200)	= 'Новинский бульвар, 31, офис 401-405'
	declare @s_mail		varchar(200)	= ''
	declare @s_ref		varchar(300)	= ''
	
	declare @dest		varchar(5)		
	declare @r_city_id	int
	declare @r_city		varchar(200)	
	declare @r_name		varchar(20)
	declare @r_tel		varchar(50)
	declare @r_co		varchar(40)
	declare @r_adr		varchar(200)
	declare @r_mail		varchar(200)
	declare @r_ref		varchar(300)
	
	declare @user_in	varchar(20)		= 'SUMSUNG API'
	declare @d_in		datetime
	
	declare @wt			float
	declare @vol_wt		float
	declare @pcs		int
	declare	@t_pack		int				= 1
	
	declare @payr		int				= 2
	declare @met_pay	varchar(3)		= 'INV'
	declare @in_sum		money			= 0.00
	declare @descr		varchar(500)	= ''
	declare @wb_source	varchar(50)		= 'api'
	declare @agent_id	int				= null				
	declare @a_status   smallint		= null
	
	declare @creationDate date			= null
	
	-- ============================================
	
	-- Выборка даты последний накладной из wwwClientWb и запуск цикла
	-- ==============================================================
	set @creationDate = (select MAX(c.Date_IN) from ALERT_F.dbo.wwwClientWB c where c.S_Co = @s_co)
	declare @id int
	declare CURS cursor local static READ_ONLY FORWARD_ONLY
	for
	select inf.id from edi.dbo.DeliveryPKGInfo inf where inf.creationDate > @creationDate
	
	open CURS
	fetch next from CURS into @id
	while @@FETCH_STATUS = 0
	begin
	
	-- Выборка из PKG_INF и присвоение переменных
	-- ============================================
	select
		@deliveryID	= d.ID,
		@d_in		= d.creationDate,
		@s_name		= d.senderName,
		@wb_no		= d.documentNumber	
	from 
		DeliveryPKGInfo d 			
	where d.id = @id
	
	-- Выборка информации об организации получателя
	-- =============================================	
		select
		@r_tel	= org.telephoneNumber,
		@r_co	= org.name,
		@r_adr	= org.address2 + ', ' + org.address1,	
		@r_city = org.address2,
		@r_name	= 
			case when org.contactPerson is null then ''
			else org.contactPerson
			end,
		@r_tel	= org.telephoneNumber,
		@r_mail = '',
		@r_ref	= ''
	from
			DeliveryOrgItem item
		join
			DeliveryRelatedOrganization org
		on item.ID = org.OrgItem
		where item.info = @deliveryID and org.[type] = 18
	-- ==================================================
	
		-- Информация о грузе
	-- ===================================================
	
	select
		@vol_wt = ti.volume,
		@wt = ti.grossWeight,
		@pcs = ti.quantity
	from
		DeliveryTotalCargoInformation ti
	where 
		ti.infoID = @deliveryID 
		
	-- ===================================================

	insert into ALERT_F.dbo.wwwClientWB
	(
		Wb_No,
		Ord_No,
		ORG,
		S_City_ID,
		S_City,
		S_Name,
		S_Tel,
		S_Co,
		S_Adr,
		S_Mail,
		S_Ref,
		DEST,
		R_City_ID,
		R_City,
		R_Name,
		R_Tel,
		R_Co,
		R_Adr,
		R_Mail,
		R_Ref,
		User_IN,
		Date_IN,
		WT,
		VOL_WT,
		PCS,
		T_PAC,
		Payr,
		MetPaym,
		INSSUM,
		Descr,
		wbsource,
		agentID,
		aStatus
	)
	 values(
		@wb_no,
		'',
		@org,
		@s_city_id,
		@s_city,
		@s_name,
		@s_tel,
		@s_co,
		@s_adr,
		@s_mail,
		@s_ref,
		'',	
		@r_city_id,
		@r_city,
		@r_name,
		@r_tel,
		@r_co,
		@r_adr,
		@r_mail,
		@r_ref,
		@user_in,
		@d_in,
		@wt,
		@vol_wt,
		@pcs,
		@t_pack,
		@payr,
		@met_pay,
		@in_sum,
		@descr,
		@wb_source,
		@agent_id,
		@a_status)
	end
	close CURS
	deallocate CURS
END	

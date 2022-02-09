-- ================================================
-- Template generated from Template Explorer using:
-- Create Procedure (New Menu).SQL
--
-- Use the Specify Values for Template Parameters 
-- command (Ctrl-Shift-M) to fill in the parameter 
-- values below.
--
-- This block of comments will not be included in
-- the definition of the procedure.
-- ================================================
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
CREATE PROCEDURE samsungCreateWb
	@OrederNO varchar(50)
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
	declare @wb_source	varchar(50)		= 'SAMSUNG API'
	declare @agent_id	int				= null				
	declare @a_status   smallint		= null
	
	-- ============================================
	
	-- Выборка из PKG_INF и присвоение переменных
	-- ============================================
	select
		@deliveryID	= d.ID,
		@d_in		= d.documentDate,
		@s_name		= d.senderName,
		@order_no	= d.documentNumber,
		@wb_no		= d.documentNumber	
	from 
		DeliveryPKGInfo d 			
	where d.documentNumber = @OrederNO
	
	-- Выборка информации об организации получателя
	-- =============================================	
		select
		@r_tel	= org.telephoneNumber,
		@r_co	= org.name,
		@r_adr	= org.address2 + ', ' + org.address1,	
		@r_city = org.address2,
		@r_name	= org.contactPerson,
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

	insert into WBSamsung values(
		@wb_no,
		@order_no,
		@org,
		@s_city_id,
		@s_city, @s_name,
		@s_tel,
		@s_co,
		@s_adr,
		@s_mail,
		@s_ref,
		@dest,	
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
		
	select @wb_no as wb_no
END	
GO

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
-- Description:	Процедура получения накладных самсунг
-- =============================================
CREATE PROCEDURE samsungGetWB
	@wb_no varchar(50)
AS
BEGIN
	select * from WBSamsung wb where wb.wb_no = @wb_no
END
GO

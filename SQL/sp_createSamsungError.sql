USE [edi]
GO

/****** Object:  StoredProcedure [dbo].[sp_createLGError]    Script Date: 09/01/2022 23:36:13 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		vd.zinovev
-- Create date: 14/08/22
-- Description:	procedure will create error message in ALERT_F.dbo.tErrorSamsung
-- =============================================
CREATE PROCEDURE [dbo].[sp_createSamsungError]
	@message varchar(500)
AS
BEGIN
	declare @date date = GETDATE()
	insert into ALERT_F.dbo.tErrorSamsung(aDate, Descr) values (@date, @message)
END

GO

GRANT EXECUTE ON sp_createSamsungError to edi



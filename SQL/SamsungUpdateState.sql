USE [edi]
GO

/****** Object:  StoredProcedure [dbo].[LGUpdateState]    Script Date: 09/01/2022 23:30:57 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		<VD.ZINOVEV>
-- Create date: <01/09/2022>
-- Description:	<Обновляет статус сервиса самсунг>
-- =============================================
CREATE PROCEDURE [dbo].[sp_samsungUpdateState]
AS
BEGIN 
	
	IF((SELECT COUNT(1) FROM ALERT_F.dbo.tPrintLGServiceState WHERE aName = 'SamsungApi') > 0)
		BEGIN
			UPDATE ALERT_F.dbo.tPrintLGServiceState
			SET lastSeen = GETDATE()
			WHERE aName = 'SamsungApi'
		END
	ELSE
		BEGIN
			INSERT INTO ALERT_F.dbo.tPrintLGServiceState
			(aName, lastSeen)
			VALUES ('SamsungApi', GETDATE())
		END
END

GO

GRANT EXECUTE ON SamsungUpdateState TO edi



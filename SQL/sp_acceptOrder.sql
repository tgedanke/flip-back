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
-- Author:		<vd.zinovev>
-- Create date: <07/09/2022>
-- Description:	<Подтверждает отправку ASKANS для заказа>
-- =============================================
CREATE PROCEDURE sp_samsungAcceptOrder
	@ID bigint,
	@accepted bit
AS
BEGIN
	IF @accepted = 1 
	BEGIN
		UPDATE DeliveryPKGInfo
		SET 
			askansSend = 1,
			processedDate = GETDATE()
		WHERE ID = @ID
	END
	ELSE 
	BEGIN
		DECLARE @errorOrderTryCount bigint = (SELECT tryCount FROM DeliveryPKGInfo WHERE ID = @ID)
		UPDATE DeliveryPKGInfo
		SET 
			tryCount = @errorOrderTryCount - 1
		WHERE ID = @ID
	END
END
GO
GRANT EXECUTE ON sp_samsungAcceptOrder to edi
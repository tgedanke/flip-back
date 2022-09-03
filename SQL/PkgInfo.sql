USE [edi]
GO

/****** Object:  StoredProcedure [dbo].[LGUpdateState]    Script Date: 09/01/2022 23:30:57 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		<VD.ZINOVEV>
-- Create date: <04/09/2022>
-- Description:	<��������� ������ ������� �������>
-- =============================================
ALTER TABLE DeliveryPKGInfo
    ADD
    [processedDate] [date] NULL,
	[askansSend] [bit] NULL,
	[tryCount] [int] NULL,
	[fileName] [varchar](255) NULL,
GO


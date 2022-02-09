USE [edi]
GO

/****** Object:  Table [dbo].[WBSamsung]    Script Date: 02/09/2022 14:50:24 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[WBSamsung](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[wb_no] [varchar](50) NOT NULL,
	[order_no] [varchar](50) NOT NULL,
	[org] [varchar](5) NULL,
	[s_city_id] [int] NULL,
	[s_city] [varchar](200) NOT NULL,
	[s_name] [varchar](20) NULL,
	[s_tel] [varchar](50) NOT NULL,
	[s_co] [varchar](50) NOT NULL,
	[s_adr] [varchar](200) NOT NULL,
	[s_mail] [varchar](200) NULL,
	[s_ref] [varchar](300) NULL,
	[dest] [varchar](5) NULL,
	[r_city_id] [int] NULL,
	[r_city] [varchar](200) NOT NULL,
	[r_name] [varchar](20) NULL,
	[r_tel] [varchar](50) NOT NULL,
	[r_co] [varchar](50) NOT NULL,
	[r_adr] [varchar](200) NOT NULL,
	[r_mail] [varchar](200) NULL,
	[r_ref] [varchar](300) NULL,
	[user_in] [varchar](20) NOT NULL,
	[d_in] [datetime] NOT NULL,
	[wt] [float] NOT NULL,
	[vol_wt] [float] NOT NULL,
	[pcs] [int] NOT NULL,
	[t_pack] [int] NOT NULL,
	[payr] [int] NOT NULL,
	[met_pay] [varchar](3) NOT NULL,
	[in_sum] [money] NOT NULL,
	[descr] [varchar](500) NULL,
	[wb_source] [varchar](50) NOT NULL,
	[agent_id] [int] NULL,
	[a_status] [smallint] NULL
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO



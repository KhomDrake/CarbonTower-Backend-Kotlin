package com.carbontower.domain.entities.http

import java.math.BigDecimal

data class Slack(val urlWorkspace: String,
                 val maxUseCPU: BigDecimal,
                 val maxUseGPU: BigDecimal,
                 val maxUseRam: BigDecimal,
                 val maxUseDisc: BigDecimal,
                 val maxTempCPU: BigDecimal,
                 val maxTempGPU: BigDecimal)
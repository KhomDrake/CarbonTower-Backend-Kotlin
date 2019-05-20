package com.carbontower.domain.entities.http

import org.joda.time.DateTime
import java.math.BigDecimal

data class InsertMetricMachineData(val useRam: BigDecimal,
                                   val tempGPU: BigDecimal,
                                   val useGPU: BigDecimal,
                                   val useDisc: BigDecimal,
                                   val rpmCooler: Int,
                                   val tempCPU: BigDecimal,
                                   val usbDevice: String,
                                   val metricDate: DateTime,
                                   val metricTime: String)